package com.pervasivecode.utils.concurrent.testing;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * This is a simple AwaitableRunnable whose only purpose is to signal that it has finished running.
 */
public final class AwaitableNoOpRunnable implements AwaitableRunnable {
  private final CountDownLatch isFinished = new CountDownLatch(1);

  @Override
  public void run() {
    if (isFinished.getCount() < 1) {
      throw new IllegalStateException("This instance can only be run once.");
    }
    isFinished.countDown();
  }

  @Override
  public boolean hasTaskFinished() {
    return isFinished.getCount() == 0;
  }

  @Override
  public void awaitTaskCompletion() throws InterruptedException {
    isFinished.await();
  }

  @Override
  public boolean awaitTaskCompletion(long amount, TimeUnit unit) throws InterruptedException {
    return isFinished.await(amount, unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isFinished);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof AwaitableNoOpRunnable)) {
      return false;
    }
    AwaitableNoOpRunnable otherRunnable = (AwaitableNoOpRunnable) other;
    return Objects.equals(otherRunnable.isFinished, isFinished);
  }
}
