package com.pervasivecode.utils.concurrent.testing;

import java.util.concurrent.Callable;
import org.junit.Test;
import com.google.common.truth.Truth;

public class FailingCallableTest {
  @Test
  public void call_shouldThrow() {
    Callable<Integer> failer = new FailingCallable<>();
    try {
      failer.call();
    } catch (Exception e) {
      Truth.assertThat(e).hasMessageThat().contains("always fails");
    }
  }
}
