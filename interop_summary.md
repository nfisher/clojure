# Java interop distribution summary

Summarized distribution of direct Java interop occurrences found under `src/clj/clojure`, grouped by package and by the Java/static/constructor type when available. Instance dot-calls whose receiver type is not explicit at the call site are grouped as `Unknown/dynamic instance receiver`.

## Package totals

| Package | Report | Total occurrences | Top operating types |
| --- | --- | ---: | --- |
| `clojure` | `clojure_interop.md` | 1888 | `Unknown/dynamic instance receiver` (1400), `Math` (91), `clojure.lang.RT` (49) |
| `clojure.core` | `clojure.core_interop.md` | 44 | `Unknown/dynamic instance receiver` (38), `System` (2), `java.util.concurrent.ForkJoinTask` (2) |
| `clojure.java` | `clojure.java_interop.md` | 124 | `Unknown/dynamic instance receiver` (90), `ProcessBuilder$Redirect` (6), `System` (6) |
| `clojure.pprint` | `clojure.pprint_interop.md` | 123 | `Unknown/dynamic instance receiver` (100), `Character` (13), `Math` (3) |
| `clojure.reflect` | `clojure.reflect_interop.md` | 30 | `Unknown/dynamic instance receiver` (22), `Type` (5), `Opcodes` (1) |
| `clojure.repl` | `clojure.repl_interop.md` | 6 | `Unknown/dynamic instance receiver` (3), `RT` (2), `Thread` (1) |
| `clojure.test` | `clojure.test_interop.md` | 4 | `Unknown/dynamic instance receiver` (4) |
| `clojure.tools` | `clojure.tools_interop.md` | 2 | `Unknown/dynamic instance receiver` (2) |

## Overall distribution by operating type

| Operating type | Count |
| --- | ---: |
| `Unknown/dynamic instance receiver` | 1659 |
| `Math` | 94 |
| `clojure.lang.RT` | 50 |
| `clojure.lang.Util` | 27 |
| `Character` | 26 |
| `System` | 23 |
| `clojure.lang.Compiler` | 20 |
| `Method` | 17 |
| `GeneratorAdapter` | 16 |
| `Exception` | 14 |
| `Vec` | 12 |
| `Double` | 11 |
| `Integer` | 11 |
| `clojure.lang.Var` | 11 |
| `Class` | 10 |
| `RT` | 10 |
| `Modifier` | 9 |
| `Opcodes` | 9 |
| `Type` | 9 |
| `Thread` | 7 |
| `clojure.lang.Agent` | 7 |
| `clojure.lang.Keyword` | 7 |
| `ProcessBuilder$Redirect` | 6 |
| `clojure.lang.MapEntry` | 6 |
| `Byte` | 5 |
| `clojure.lang.Compiler$HostExpr` | 5 |
| `clojure.lang.Namespace` | 5 |
| `clojure.lang.Numbers` | 5 |
| `clojure.lang.PersistentArrayMap` | 5 |
| `Float` | 4 |
| `Long` | 4 |
| `clojure.lang.Reflector` | 4 |
| `clojure.lang.Symbol` | 4 |
| `Classname` | 3 |
| `Matcher` | 3 |
| `VecSeq` | 3 |
| `clojure.lang.APersistentMap` | 3 |
| `clojure.lang.LongRange` | 3 |
| `clojure.lang.Murmur3` | 3 |
| `clojure.lang.PersistentList` | 3 |
| `clojure.lang.Range` | 3 |
| `clojure.lang.TransformerIterator` | 3 |
| `java.util.UUID` | 3 |
| `java.util.concurrent.TimeUnit` | 3 |
| `AssertionError` | 2 |
| `Boolean` | 2 |
| `BorderLayout` | 2 |
| `File` | 2 |
| `LazilyPersistentVector` | 2 |
| `Murmur3` | 2 |
| `Short` | 2 |
| `String` | 2 |
| `StringBuilder` | 2 |
| `clojure.asm.Type` | 2 |
| `clojure.lang.BigInt` | 2 |
| `clojure.lang.EdnReader` | 2 |
| `clojure.lang.LazilyPersistentVector` | 2 |
| `clojure.lang.PersistentTreeMap` | 2 |
| `clojure.lang.PersistentTreeSet` | 2 |
| `clojure.lang.Repeat` | 2 |
| `java.util.TimeZone` | 2 |
| `java.util.concurrent.CountDownLatch` | 2 |
| `java.util.concurrent.ForkJoinTask` | 2 |
| `ArrayChunk` | 1 |
| `BigDecimal` | 1 |
| `BigInteger` | 1 |
| `Calendar` | 1 |
| `Charset` | 1 |
| `Compiler` | 1 |
| `Executors` | 1 |
| `Files` | 1 |
| `IllegalArgumentException` | 1 |
| `IllegalStateException` | 1 |
| `InetAddress` | 1 |
| `JTable` | 1 |
| `Runtime` | 1 |
| `SAXParserFactory` | 1 |
| `TimeZone` | 1 |
| `URLDecoder` | 1 |
| `URLEncoder` | 1 |
| `Util` | 1 |
| `Void` | 1 |
| `clojure.lang.Atom` | 1 |
| `clojure.lang.Cycle` | 1 |
| `clojure.lang.EnumerationSeq` | 1 |
| `clojure.lang.Iterate` | 1 |
| `clojure.lang.LockingTransaction` | 1 |
| `clojure.lang.MultiFn` | 1 |
| `clojure.lang.PersistentHashSet` | 1 |
| `clojure.lang.ReaderConditional` | 1 |
| `clojure.lang.Ref` | 1 |
| `clojure.lang.TaggedLiteral` | 1 |
| `clojure.lang.XMLHandler` | 1 |
| `java.io.StringWriter` | 1 |
| `java.lang.Class` | 1 |
| `java.lang.IllegalAccessError` | 1 |
| `java.lang.Throwable` | 1 |
| `java.lang.annotation.RetentionPolicy` | 1 |
| `java.util.Collections` | 1 |
| `java.util.Properties` | 1 |
| `sun.misc.Signal` | 1 |
