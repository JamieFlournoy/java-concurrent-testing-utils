package com.pervasivecode.utils.concurrent.testing;

import java.util.concurrent.Callable;

/**
 * A Callable that always throws an exception when run, for testing error-handling code.
 *
 * @param <T> The type of value that would be returned if this class didn't always throw an
 *        exception.
 */
public final class FailingCallable<T> implements Callable<T> {
  public static final String FAILURE_MESSAGE = "This Callable always fails.";

  @Override
  public T call() throws Exception {
    throw new Exception(FAILURE_MESSAGE);
  }

  @Override
  public int hashCode() {
    return FailingCallable.class.hashCode();  // no fields to hash
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof FailingCallable;  // no fields to compare
  }
}
