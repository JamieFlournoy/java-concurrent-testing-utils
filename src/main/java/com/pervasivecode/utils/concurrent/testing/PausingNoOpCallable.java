package com.pervasivecode.utils.concurrent.testing;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * A simple Callable that will pause until released. When released, it finishes running and returns
 * the input value raised to the third power (cubed).
 */
public class PausingNoOpCallable implements Callable<Integer> {
  private PausingNoOpRunnable blocker = new PausingNoOpRunnable();
  private final int result;

  /**
   * Create a new PausingNoOpCallable that will cube and return the specified int value. Note that
   * since values larger than about 1290 will overflow an int when raised to the third power, this
   * method will throw an exception if the inputValue is larger than that.
   *
   * @param inputValue An int value that will be cubed and returned when the Callable finishes.
   * @throws IllegalArgumentException if inputValue is larger than 1290.
   */
  public PausingNoOpCallable(int inputValue) {
    if (Math.abs(inputValue) > 1290) {
      throw new IllegalArgumentException(
          String.format("Cubing the value %s will overflow an int.", inputValue));
    }
    result = inputValue * inputValue * inputValue;
  }

  /** Return the input value, raised to the third power. */
  @Override
  public Integer call() throws Exception {
    blocker.run();
    return result;
  }

  public boolean hasPaused() {
    return blocker.hasPaused();
  }

  public void waitUntilPaused() throws InterruptedException {
    blocker.waitUntilPaused();
  }

  public boolean waitUntilPaused(long amount, TimeUnit unit) throws InterruptedException {
    return blocker.waitUntilPaused(amount, unit);
  }

  public void unpause() {
    blocker.unpause();
  }

  public boolean hasUnpaused() {
    return blocker.hasUnpaused();
  }
}
