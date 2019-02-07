package com.pervasivecode.utils.concurrent.testing;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * A simple Runnable that pauses until released, and which provides a means of waiting until it has
 * finished executing and determining if it has already finished.
 *
 * @see AwaitableNoOpRunnable
 * @see PausingNoOpRunnable
 */
public final class PausingAwaitableNoOpRunnable implements AwaitableRunnable, PausingRunnable {
  private final PausingNoOpRunnable pausingTask = new PausingNoOpRunnable();
  private final AwaitableNoOpRunnable awaitableTask = new AwaitableNoOpRunnable();

  @Override
  public void run() {
    pausingTask.run();
    awaitableTask.run();
  }

  @Override
  public boolean hasTaskFinished() {
    return awaitableTask.hasTaskFinished();
  }

  @Override
  public void awaitTaskCompletion() throws InterruptedException {
    awaitableTask.awaitTaskCompletion();
  }

  @Override
  public boolean awaitTaskCompletion(long amount, TimeUnit unit) throws InterruptedException {
    return awaitableTask.awaitTaskCompletion(amount, unit);
  }

  @Override
  public boolean hasPaused() {
    return pausingTask.hasPaused();
  }

  @Override
  public void waitUntilPaused() throws InterruptedException {
    pausingTask.waitUntilPaused();
  }

  @Override
  public boolean waitUntilPaused(long amount, TimeUnit unit) throws InterruptedException {
    return pausingTask.waitUntilPaused(amount, unit);
  }

  @Override
  public void unpause() {
    pausingTask.unpause();
  }

  @Override
  public boolean hasUnpaused() {
    return pausingTask.hasUnpaused();
  }

  @Override
  public int hashCode() {
    return Objects.hash(pausingTask, awaitableTask);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof PausingAwaitableNoOpRunnable)) {
      return false;
    }
    PausingAwaitableNoOpRunnable otherRunnable = (PausingAwaitableNoOpRunnable) other;
    return Objects.equals(otherRunnable.pausingTask, pausingTask)
        && Objects.equals(otherRunnable.awaitableTask, awaitableTask);
  }
}
