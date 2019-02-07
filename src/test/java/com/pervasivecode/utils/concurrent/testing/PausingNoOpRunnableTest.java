package com.pervasivecode.utils.concurrent.testing;

import static com.google.common.truth.Truth.assertThat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.truth.Truth;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class PausingNoOpRunnableTest {
  // TODO simplify via com.github.peterwippermann.junit4.parameterizedsuite.ParameterizedSuite;

  private ExecutorService executor;

  @Before
  public void setup() {
    executor = null;
  }

  @After
  public void teardown() throws Exception {
    if (executor != null && !executor.isShutdown()) {
      executor.shutdownNow();
      executor.awaitTermination(10, TimeUnit.SECONDS);
    }
  }

  @Test
  public void aNewTask_shouldNotBePaused() {
    PausingNoOpRunnable task = new PausingNoOpRunnable();
    assertThat(task.hasPaused()).isFalse();
  }

  @Test
  public void aStartedTask_shouldBecomePaused() throws Exception {
    PausingRunnableTests.aStartedTask_shouldBecomePaused(new PausingNoOpRunnable());
  }

  @Test
  public void aPausedTask_shouldBeUnpausable() throws Exception {
    PausingRunnableTests.aPausedTask_shouldBeUnpausable(new PausingNoOpRunnable());
  }

  @Test
  public void aTask_shouldBeUnpausableAtAnyTime() throws Exception {
    PausingRunnableTests.aTask_shouldBeUnpausableAtAnyTime(new PausingNoOpRunnable());
  }

  @Test(timeout = 1000)
  public void run_onATaskThatHasRunOnce_shouldThrow() throws Exception {
    PausingNoOpRunnable task = new PausingNoOpRunnable();
    task.unpause();
    task.run();

    try {
      task.run();
      Truth.assert_().fail("Expected run to throw an exception.");
    } catch (IllegalStateException ise) {
      Truth.assertThat(ise).hasMessageThat().contains("can only be run once");
    }
  }

  @Test(timeout = 1000)
  public void onPause_withPausedInstance_shouldRunHandler() throws Exception {
    executor = singleThreadExecutor();

    PausingNoOpRunnable task = new PausingNoOpRunnable();
    Future<?> futureResult = executor.submit(task);

    task.waitUntilPaused();

    AtomicBoolean ran = new AtomicBoolean(false);
    task.onPause(() -> ran.set(true));

    assertThat(ran.get()).isTrue();
    task.unpause();
    futureResult.get();
  }

  @Test(timeout = 1000)
  public void onPause_withUnpausedInstance_shouldRunHandler() throws Exception {
    executor = singleThreadExecutor();

    PausingNoOpRunnable task = new PausingNoOpRunnable();
    Future<?> futureResult = executor.submit(task);

    task.waitUntilPaused();
    task.unpause();

    AtomicBoolean ran = new AtomicBoolean(false);
    task.onPause(() -> ran.set(true));

    assertThat(ran.get()).isTrue();
    futureResult.get();
  }

  @Test(timeout = 1000)
  public void onPause_withNotYetPausedInstance_shouldRunHandlerWhenInstancePauses()
      throws Exception {
    executor = singleThreadExecutor();

    PausingNoOpRunnable task = new PausingNoOpRunnable();

    AtomicBoolean ran = new AtomicBoolean(false);
    task.onPause(() -> ran.set(true));
    assertThat(ran.get()).isFalse();

    Future<?> futureResult = executor.submit(task);
    task.waitUntilPaused();

    assertThat(ran.get()).isTrue();

    task.unpause();
    futureResult.get();
  }

  private static ThreadPoolExecutor singleThreadExecutor() {
    return new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
  }

  @Test
  public void equalsAndHashCode_shouldWork() {
    EqualsVerifier.forClass(PausingNoOpRunnable.class).suppress(Warning.NONFINAL_FIELDS)
        .withPrefabValues(CountDownLatch.class, new CountDownLatch(2), new CountDownLatch(3))
        .verify();
  }
}
