# Pervasive Code's Java Concurrent Testing Utilities

This library includes classes to help with testing concurrent code that uses [Runnable][], [Callable][], and [Executor][].




## Overview of included classes

Javadocs are available on `javadoc.io`:

[![Javadocs](https://www.javadoc.io/badge/com.pervasivecode/concurrent-testing-utils.svg)](https://www.javadoc.io/doc/com.pervasivecode/concurrent-testing-utils)

See the separate [OVERVIEW.md](OVERVIEW.md) file for a description of what interfaces and classes are included.
(Overview content is taken from class Javadoc comments, so there's no need to read both.)

## Including it in your project

Use groupId `com.pervasivecode`, name `concurrent-testing-utils`, version `1.0` in your build tool of choice.

### Gradle Example

If you are using Gradle 4.x, put this in your build.properties file:

```
// in your build.gradle's repositories {} block:
    mavenCentral();

// in your build.gradle's dependencies {} block:
    implementation 'com.pervasivecode:concurrent-testing-utils:1.0'

    // or, if you prefer the separated group/name/version syntax:
    implementation group: 'com.pervasivecode', name: 'concurrent-testing-utils', version: '1.0'
```

## Contributing

See [DEVELOPERS.md](DEVELOPERS.md) and [GRADLE_INTRO.md](GRADLE_INTRO.md) if you want to build and hack on the code yourself.


## Copyright and License

Copyright © 2018 Jamie Flournoy.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.



[Callable]: https://docs.oracle.com/javase/10/docs/api/java/util/concurrent/Callable.html?is-external=true

[Runnable]: https://docs.oracle.com/javase/10/docs/api/java/lang/Runnable.html?is-external=true

[Executor]: https://docs.oracle.com/javase/10/docs/api/java/util/concurrent/Executor.html?is-external=true