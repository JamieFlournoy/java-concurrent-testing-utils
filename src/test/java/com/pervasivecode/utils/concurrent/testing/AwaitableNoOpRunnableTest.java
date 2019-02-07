package com.pervasivecode.utils.concurrent.testing;

import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import nl.jqno.equalsverifier.EqualsVerifier;

public class AwaitableNoOpRunnableTest {
  // TODO simplify via com.github.peterwippermann.junit4.parameterizedsuite.ParameterizedSuite;

  @Test
  public void aNewTask_shouldNotBeFinished() {
    AwaitableRunnableTests.aNewTask_shouldNotBeFinished(new AwaitableNoOpRunnable());
  }

  @Test
  public void aTaskThatHasRun_shouldBeFinished() {
    AwaitableRunnableTests.aTaskThatHasRun_shouldBeFinished(new AwaitableNoOpRunnable());
  }

  @Test
  public void aTaskThatHasRun_shouldNotBeReRunnable() {
    AwaitableRunnableTests.aTaskThatHasRun_shouldNotBeReRunnable(new AwaitableNoOpRunnable());
  }

  @Test
  public void awaitTaskCompletion_withAFinishedTask_shouldReturnImmediately() throws Exception {
    AwaitableRunnableTests
        .awaitTaskCompletion_withAFinishedTask_shouldReturnImmediately(new AwaitableNoOpRunnable());
  }

  @Test
  public void awaitTaskCompletion_shouldWaitUntilTaskCompletes() throws Exception {
    AwaitableRunnableTests
        .awaitTaskCompletion_shouldWaitUntilTaskCompletes(() -> new AwaitableNoOpRunnable());
  }

  @Test
  public void equalsAndHashCode_shouldWork() {
    EqualsVerifier.forClass(AwaitableNoOpRunnable.class)
        .withPrefabValues(CountDownLatch.class, new CountDownLatch(2), new CountDownLatch(3))
        .verify();
  }
}
