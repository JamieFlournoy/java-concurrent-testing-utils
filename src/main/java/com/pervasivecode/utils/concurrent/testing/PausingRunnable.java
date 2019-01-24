package com.pervasivecode.utils.concurrent.testing;

import java.util.concurrent.TimeUnit;

/**
 * A Runnable that pauses (waiting for a call to {@link PausingRunnable#unpause()}) at the start of
 * its {@link Runnable#run()} method.
 */
public interface PausingRunnable extends Runnable {
  /**
   * Has the PausingRunnable paused yet? If it has been unpaused this will also return true.
   *
   * @return true if it is paused, or was previously paused and has been unpaused.
   */
  boolean hasPaused();

  /**
   * Block the current thread indefinitely, until the PausingRunnable is paused.
   *
   * @throws InterruptedException if the thread that is waiting is interrupted.
   */
  void waitUntilPaused() throws InterruptedException;

  /**
   * Block the current thread until the PausingRunnable is paused, unless a timeout has expired
   * while waiting. If the timeout expires, this method will return false.
   *
   * @param amount The magnitude of the timeout value.
   * @param unit The units of the timeout value.
   * @return true if the PausingRunnable paused before the timeout expired, or false if the timeout
   *         expired before the PausingRunnable paused.
   * @throws InterruptedException if the thread that is waiting is interrupted.
   */
  boolean waitUntilPaused(long amount, TimeUnit unit) throws InterruptedException;

  /** Release a paused PausingRunnable so that it can continue running. */
  void unpause();

  /**
   * Has the PausingRunnable been paused and then unpaused?
   *
   * @return true if it has been unpaused.
   */
  boolean hasUnpaused();
}
