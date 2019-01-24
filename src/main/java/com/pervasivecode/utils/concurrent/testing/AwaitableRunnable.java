package com.pervasivecode.utils.concurrent.testing;

import java.util.concurrent.TimeUnit;

/**
 * A Runnable that signals that it has finished running. Task completion can be awaited via the
 * {@link #awaitTaskCompletion()} method. Task status (finished or not) can be observed via the
 * {@link #hasTaskFinished()} method.
 */
public interface AwaitableRunnable extends Runnable {

  /**
   * Has the task finished?
   *
   * @return True if the task has finished.
   */
  boolean hasTaskFinished();

  /**
   * Block until the task has finished running.
   *
   * @throws InterruptedException If the thread was interrupted while waiting.
   */
  void awaitTaskCompletion() throws InterruptedException;

  /**
   * Block until the task has finished running, or until a timeout has expired.
   *
   * @param amount The duration of the timeout.
   * @param unit The units of the amount parameter.
   * @return True if the task completed before the timeout expired; false if it did not.
   * @throws InterruptedException If the thread was interrupted while waiting.
   */
  boolean awaitTaskCompletion(long amount, TimeUnit unit) throws InterruptedException;
}
