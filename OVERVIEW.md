#  Code Overview

If you prefer Javadocs, they are available on `javadoc.io`:

[![Javadocs](https://www.javadoc.io/badge/com.pervasivecode/concurrent-testing-utils.svg)](https://www.javadoc.io/doc/com.pervasivecode/concurrent-testing-utils)

## Interfaces

### [AwaitableRunnable](src/main/java/com/pervasivecode/utils/concurrent/testing/AwaitableRunnable.java)

A Runnable that signals that it has finished running.

### [PausingRunnable](src/main/java/com/pervasivecode/utils/concurrent/testing/PausingRunnable.java)

A Runnable that pauses (waiting for a call to unpause()) at the start of its Runnable.run() method.


## Classes

### [AwaitableNoOpRunnable](src/main/java/com/pervasivecode/utils/concurrent/testing/AwaitableNoOpRunnable.java)

This is a simple AwaitableRunnable whose only purpose is to signal that it has finished running.

### [FailingCallable](src/main/java/com/pervasivecode/utils/concurrent/testing/FailingCallable.java)

A Callable that always throws an exception when run, for testing error-handling code.

### [PausingAwaitableNoOpRunnable](src/main/java/com/pervasivecode/utils/concurrent/testing/PausingAwaitableNoOpRunnable.java)

A simple Runnable that pauses until released, and which provides a means of waiting until it has finished executing and determining if it has already finished.

### [PausingNoOpCallable](src/main/java/com/pervasivecode/utils/concurrent/testing/PausingNoOpCallable.java)

A simple Callable that will pause until released.

### [PausingNoOpRunnable](src/main/java/com/pervasivecode/utils/concurrent/testing/PausingNoOpRunnable.java)

A simple Runnable that will pause until released.
