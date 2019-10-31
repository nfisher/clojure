Clojure
=======

Copyright (c) Rich Hickey. All rights reserved.

The use and distribution terms for this software are covered by the
[Eclipse Public License 1.0](http://opensource.org/licenses/eclipse-1.0.php)
which can be found in the file epl-v10.html at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by
the terms of this license.

You must not remove this notice, or any other, from this software.

### Useful Links

- [Clojure.org](https://clojure.org)
- [Google Group](http://groups.google.com/group/clojure)
- [Getting Started](https://clojure.org/guides/getting_started)

## Build

### Building With Ant

```
# One-time setup:
./antsetup.sh

# To build:
ant local

# To run:            
java -jar clojure.jar
```

### Building With Maven

```
# To build (output JARs in target/):
mvn package

# To build without testing:
mvn package -Dmaven.test.skip=true

# To build and install in local Maven repository:
mvn install

# To build a standalone jar with dependencies included:
mvn -Plocal -Dmaven.test.skip=true package

# To run with the standalone jar:
java -jar clojure.jar
```

### Third-party Licenses

See [third_party/LICENSES](third_party/LICENSES)