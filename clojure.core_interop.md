# Java interop occurrences for `clojure.core`

One line per direct Java interop occurrence in `src/clj/clojure`. Position is reported relative to the enclosing function/form.

## Distribution by operating type

Total interop occurrences: **44**.

| Operating type | Count |
| --- | ---: |
| `Unknown/dynamic instance receiver` | 38 |
| `System` | 2 |
| `java.util.concurrent.ForkJoinTask` | 2 |
| `InetAddress` | 1 |
| `Integer` | 1 |

Classification note: static calls and constructors are classified by their Java class; instance dot-calls without an explicit class receiver are grouped as `Unknown/dynamic instance receiver` because the receiver type is not reliably encoded at the call site.

## Occurrences

- `src/clj/clojure/core/protocols.clj` — `iterator-reduce!` — line 36, col 8 (function line +2) — `(.hasNext` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `iterator-reduce!` — line 37, col 31 (function line +3) — `(.next` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `iterator-reduce!` — line 41, col 10 (function line +7) — `(.hasNext` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `iterator-reduce!` — line 42, col 25 (function line +8) — `(.next` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `iter-reduce` — line 50, col 22 (function line +2) — `(.iterator` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `iter-reduce` — line 52, col 22 (function line +4) — `(.iterator` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `interface-or-naive-reduce` — line 71, col 5 (function line +4) — `(.reduce` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `interface-or-naive-reduce` — line 87, col 15 (function line +20) — `(.reduce` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `interface-or-naive-reduce` — line 88, col 19 (function line +21) — `(.reduce` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `interface-or-naive-reduce` — line 135, col 18 (function line +68) — `(.reduce` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `interface-or-naive-reduce` — line 147, col 12 (function line +80) — `(.s` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `interface-or-naive-reduce` — line 148, col 14 (function line +81) — `(.length` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `interface-or-naive-reduce` — line 149, col 15 (function line +82) — `(.i` (dot-call)
- `src/clj/clojure/core/protocols.clj` — `interface-or-naive-reduce` — line 152, col 27 (function line +85) — `(.charAt` (dot-call)
- `src/clj/clojure/core/reducers.clj` — `fjtask` — line 25, col 4 (function line +1) — `java.util.concurrent.ForkJoinTask/adapt` (static)
- `src/clj/clojure/core/reducers.clj` — `fjinvoke` — line 28, col 8 (function line +1) — `java.util.concurrent.ForkJoinTask/inForkJoinPool` (static)
- `src/clj/clojure/core/reducers.clj` — `fjinvoke` — line 30, col 5 (function line +3) — `(.invoke` (dot-call)
- `src/clj/clojure/core/reducers.clj` — `fjfork` — line 32, col 22 (function line +0) — `(.fork` (dot-call)
- `src/clj/clojure/core/reducers.clj` — `fjjoin` — line 34, col 22 (function line +0) — `(.join` (dot-call)
- `src/clj/clojure/core/reducers.clj` — `append!` — line 279, col 13 (function line +4) — `(.add` (dot-call)
- `src/clj/clojure/core/reducers.clj` — `foldvec` — line 334, col 3 (function line +36) — `(.fold` (dot-call)
- `src/clj/clojure/core/server.clj` — `with-lock` — line 33, col 6 (function line +3) — `(.lock` (dot-call)
- `src/clj/clojure/core/server.clj` — `with-lock` — line 37, col 10 (function line +7) — `(.unlock` (dot-call)
- `src/clj/clojure/core/server.clj` — `thread` — line 42, col 5 (function line +3) — `(.setDaemon` (dot-call)
- `src/clj/clojure/core/server.clj` — `thread` — line 43, col 5 (function line +4) — `(.start` (dot-call)
- `src/clj/clojure/core/server.clj` — `accept-connection` — line 83, col 7 (function line +25) — `(.close` (dot-call)
- `src/clj/clojure/core/server.clj` — `start-server` — line 102, col 19 (function line +17) — `InetAddress/getByName` (static)
- `src/clj/clojure/core/server.clj` — `start-server` — line 110, col 22 (function line +25) — `(.isClosed` (dot-call)
- `src/clj/clojure/core/server.clj` — `start-server` — line 112, col 26 (function line +27) — `(.accept` (dot-call)
- `src/clj/clojure/core/server.clj` — `start-server` — line 113, col 74 (function line +28) — `(.getInputStream` (dot-call)
- `src/clj/clojure/core/server.clj` — `start-server` — line 114, col 63 (function line +29) — `(.getOutputStream` (dot-call)
- `src/clj/clojure/core/server.clj` — `stop-server` — line 137, col 10 (function line +11) — `(.close` (dot-call)
- `src/clj/clojure/core/server.clj` — `parse-props` — line 158, col 4 (function line +11) — `(.stringPropertyNames` (dot-call)
- `src/clj/clojure/core/server.clj` — `prepl` — line 235, col 40 (function line +41) — `System/nanoTime` (static)
- `src/clj/clojure/core/server.clj` — `prepl` — line 237, col 46 (function line +43) — `System/nanoTime` (static)
- `src/clj/clojure/core/server.clj` — `prepl` — line 246, col 49 (function line +52) — `(.name` (dot-call)
- `src/clj/clojure/core/server.clj` — `prepl` — line 253, col 45 (function line +59) — `(.name` (dot-call)
- `src/clj/clojure/core/server.clj` — `prepl` — line 259, col 41 (function line +65) — `(.name` (dot-call)
- `src/clj/clojure/core/server.clj` — `remote-prepl` — line 313, col 40 (function line +15) — `Integer/valueOf` (static)
- `src/clj/clojure/core/server.clj` — `remote-prepl` — line 332, col 19 (function line +34) — `(.close` (dot-call)
- `src/clj/clojure/core/server.clj` — `remote-prepl` — line 335, col 22 (function line +37) — `(.read` (dot-call)
- `src/clj/clojure/core/server.clj` — `remote-prepl` — line 337, col 18 (function line +39) — `(.write` (dot-call)
- `src/clj/clojure/core/server.clj` — `remote-prepl` — line 338, col 18 (function line +40) — `(.flush` (dot-call)
- `src/clj/clojure/core/server.clj` — `remote-prepl` — line 341, col 13 (function line +43) — `(.close` (dot-call)
