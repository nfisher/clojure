# Java interop occurrences for `clojure.java`

One line per direct Java interop occurrence in `src/clj/clojure`. Position is reported relative to the enclosing function/form.

## Distribution by operating type

Total interop occurrences: **124**.

| Operating type | Count |
| --- | ---: |
| `Unknown/dynamic instance receiver` | 90 |
| `ProcessBuilder$Redirect` | 6 |
| `System` | 6 |
| `Character` | 4 |
| `RT` | 4 |
| `Byte` | 2 |
| `File` | 2 |
| `clojure.lang.Reflector` | 2 |
| `Charset` | 1 |
| `Class` | 1 |
| `Executors` | 1 |
| `Runtime` | 1 |
| `Thread` | 1 |
| `URLDecoder` | 1 |
| `URLEncoder` | 1 |
| `java.util.concurrent.TimeUnit` | 1 |

Classification note: static calls and constructors are classified by their Java class; instance dot-calls without an explicit class receiver are grouped as `Unknown/dynamic instance receiver` because the receiver type is not reliably encoded at the call site.

## Occurrences

- `src/clj/clojure/java/basis/impl.clj` — `read-basis` — line 37, col 11 (function line +4) — `(.exists` (dot-call)
- `src/clj/clojure/java/basis/impl.clj` — `init-basis` — line 42, col 23 (function line +1) — `System/getProperty` (static)
- `src/clj/clojure/java/browse.clj` — `macosx?` — line 20, col 17 (function line +1) — `System/getProperty` (static)
- `src/clj/clojure/java/browse.clj` — `macosx?` — line 21, col 5 (function line +2) — `(.startsWith` (dot-call)
- `src/clj/clojure/java/browse.clj` — `open-url-in-browser` — line 50, col 12 (function line +6) — `clojure.lang.Reflector/invokeStaticMethod` (static)
- `src/clj/clojure/java/browse.clj` — `open-url-in-browser` — line 52, col 12 (function line +8) — `clojure.lang.Reflector/invokeStaticMethod` (static)
- `src/clj/clojure/java/browse.clj` — `open-url-in-browser` — line 54, col 9 (function line +10) — `(.browse` (dot-call)
- `src/clj/clojure/java/browse.clj` — `browse-url` — line 79, col 40 (function line +11) — `(.startsWith` (dot-call)
- `src/clj/clojure/java/browse.clj` — `browse-url` — line 79, col 54 (function line +11) — `System/getProperty` (static)
- `src/clj/clojure/java/browse.clj` — `browse-url` — line 82, col 24 (function line +14) — `(.redirectOutput` (dot-call)
- `src/clj/clojure/java/browse.clj` — `browse-url` — line 83, col 24 (function line +15) — `(.redirectError` (dot-call)
- `src/clj/clojure/java/browse.clj` — `browse-url` — line 84, col 15 (function line +16) — `(.start` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 18, col 5 (function line +3) — `(.setEditable` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 19, col 5 (function line +4) — `(.addHyperlinkListener` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 22, col 20 (function line +7) — `(.getEventType` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 22, col 38 (function line +7) — `(.` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 24, col 41 (function line +9) — `(.processHTMLFrameHyperlinkEvent` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 25, col 15 (function line +10) — `(.setPage` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 25, col 34 (function line +10) — `(.getURL` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 27, col 7 (function line +12) — `(.setContentPane` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 28, col 7 (function line +13) — `(.setBounds` (dot-call)
- `src/clj/clojure/java/browse_ui.clj` — `open-url-in-swing` — line 29, col 7 (function line +14) — `(.setVisible` (dot-call)
- `src/clj/clojure/java/io.clj` — `<top-level>` — line 31, col 37 (function line +30) — `Byte/TYPE` (static)
- `src/clj/clojure/java/io.clj` — `<top-level>` — line 36, col 37 (function line +35) — `Character/TYPE` (static)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 44, col 38 (function line +1) — `URLEncoder/encode` (static)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 45, col 8 (function line +2) — `URLDecoder/decode` (static)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 54, col 16 (function line +11) — `RT/toUrl` (static)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 58, col 16 (function line +15) — `RT/toUrl` (static)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 63, col 19 (function line +20) — `(.getProtocol` (dot-call)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 65, col 17 (function line +22) — `(.replace` (dot-call)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 65, col 27 (function line +22) — `(.getFile` (dot-call)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 65, col 43 (function line +22) — `File/separatorChar` (static)
- `src/clj/clojure/java/io.clj` — `escaped-utf8-urlstring->str` — line 69, col 15 (function line +26) — `(.toURL` (dot-call)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 240, col 41 (function line +59) — `(.getProtocol` (dot-call)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 242, col 29 (function line +61) — `(.openStream` (dot-call)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 244, col 41 (function line +63) — `(.getProtocol` (dot-call)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 251, col 61 (function line +70) — `(.toURL` (dot-call)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 252, col 63 (function line +71) — `(.toURL` (dot-call)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 259, col 47 (function line +78) — `RT/toUrl` (static)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 264, col 49 (function line +83) — `RT/toUrl` (static)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 271, col 64 (function line +90) — `(.getInputStream` (dot-call)
- `src/clj/clojure/java/io.clj` — `outputstream->writer` — line 272, col 66 (function line +91) — `(.getOutputStream` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 306, col 28 (function line +1) — `Byte/TYPE` (static)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 308, col 18 (function line +3) — `(.read` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 310, col 15 (function line +5) — `(.write` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 314, col 34 (function line +1) — `Character/TYPE` (static)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 317, col 18 (function line +4) — `(.read` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 319, col 15 (function line +6) — `(.write` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 327, col 34 (function line +1) — `Character/TYPE` (static)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 330, col 18 (function line +4) — `(.read` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 333, col 13 (function line +7) — `(.write` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 335, col 11 (function line +9) — `(.flush` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 338, col 34 (function line +1) — `Character/TYPE` (static)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 340, col 18 (function line +3) — `(.read` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 342, col 15 (function line +5) — `(.write` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 360, col 14 (function line +3) — `(.size` (dot-call)
- `src/clj/clojure/java/io.clj` — `do-copy` — line 362, col 29 (function line +5) — `(.transferTo` (dot-call)
- `src/clj/clojure/java/io.clj` — `as-relative-path` — line 417, col 9 (function line +6) — `(.isAbsolute` (dot-call)
- `src/clj/clojure/java/io.clj` — `as-relative-path` — line 419, col 7 (function line +8) — `(.getPath` (dot-call)
- `src/clj/clojure/java/io.clj` — `delete-file` — line 437, col 7 (function line +4) — `(.delete` (dot-call)
- `src/clj/clojure/java/io.clj` — `make-parents` — line 446, col 21 (function line +5) — `(.getParentFile` (dot-call)
- `src/clj/clojure/java/io.clj` — `make-parents` — line 447, col 5 (function line +6) — `(.mkdirs` (dot-call)
- `src/clj/clojure/java/io.clj` — `resource` — line 453, col 20 (function line +4) — `(.getContextClassLoader` (dot-call)
- `src/clj/clojure/java/io.clj` — `resource` — line 453, col 45 (function line +4) — `Thread/currentThread` (static)
- `src/clj/clojure/java/io.clj` — `resource` — line 454, col 28 (function line +5) — `(.getResource` (dot-call)
- `src/clj/clojure/java/javadoc.clj` — `*core-java-api*` — line 22, col 10 (function line +1) — `System/getProperty` (static)
- `src/clj/clojure/java/javadoc.clj` — `fill-in-module-name` — line 64, col 7 (function line +4) — `(.contains` (dot-call)
- `src/clj/clojure/java/javadoc.clj` — `fill-in-module-name` — line 65, col 18 (function line +5) — `Class/forName` (static)
- `src/clj/clojure/java/javadoc.clj` — `fill-in-module-name` — line 66, col 23 (function line +6) — `(.getName` (dot-call)
- `src/clj/clojure/java/javadoc.clj` — `fill-in-module-name` — line 66, col 33 (function line +6) — `(.getModule` (dot-call)
- `src/clj/clojure/java/javadoc.clj` — `javadoc-url` — line 76, col 19 (function line +6) — `(.replace` (dot-call)
- `src/clj/clojure/java/javadoc.clj` — `javadoc-url` — line 76, col 42 (function line +6) — `File/separatorChar` (static)
- `src/clj/clojure/java/javadoc.clj` — `javadoc-url` — line 77, col 18 (function line +7) — `(.replace` (dot-call)
- `src/clj/clojure/java/javadoc.clj` — `javadoc-url` — line 79, col 37 (function line +9) — `(.exists` (dot-call)
- `src/clj/clojure/java/javadoc.clj` — `javadoc-url` — line 85, col 25 (function line +15) — `(.startsWith` (dot-call)
- `src/clj/clojure/java/javadoc.clj` — `javadoc` — line 100, col 31 (function line +8) — `(.getName` (dot-call)
- `src/clj/clojure/java/process.clj` — `null-file` — line 32, col 10 (function line +3) — `(.startsWith` (dot-call)
- `src/clj/clojure/java/process.clj` — `null-file` — line 32, col 24 (function line +3) — `System/getProperty` (static)
- `src/clj/clojure/java/process.clj` — `to-file` — line 43, col 8 (function line +7) — `ProcessBuilder$Redirect/appendTo` (static)
- `src/clj/clojure/java/process.clj` — `to-file` — line 44, col 8 (function line +8) — `ProcessBuilder$Redirect/to` (static)
- `src/clj/clojure/java/process.clj` — `from-file` — line 51, col 4 (function line +5) — `ProcessBuilder$Redirect/from` (static)
- `src/clj/clojure/java/process.clj` — `start` — line 78, col 31 (function line +25) — `ProcessBuilder$Redirect/PIPE` (static)
- `src/clj/clojure/java/process.clj` — `start` — line 79, col 34 (function line +26) — `ProcessBuilder$Redirect/INHERIT` (static)
- `src/clj/clojure/java/process.clj` — `start` — line 80, col 35 (function line +27) — `ProcessBuilder$Redirect/to` (static)
- `src/clj/clojure/java/process.clj` — `start` — line 83, col 5 (function line +30) — `(.directory` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 84, col 5 (function line +31) — `(.redirectInput` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 85, col 5 (function line +32) — `(.redirectOutput` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 87, col 23 (function line +34) — `(.redirectErrorStream` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 88, col 7 (function line +35) — `(.redirectError` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 90, col 7 (function line +37) — `(.clear` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 90, col 15 (function line +37) — `(.environment` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 92, col 20 (function line +39) — `(.environment` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 93, col 27 (function line +40) — `(.put` (dot-call)
- `src/clj/clojure/java/process.clj` — `start` — line 94, col 5 (function line +41) — `(.start` (dot-call)
- `src/clj/clojure/java/process.clj` — `stdin` — line 100, col 3 (function line +4) — `(.getOutputStream` (dot-call)
- `src/clj/clojure/java/process.clj` — `stdout` — line 106, col 3 (function line +4) — `(.getInputStream` (dot-call)
- `src/clj/clojure/java/process.clj` — `stderr` — line 112, col 3 (function line +4) — `(.getErrorStream` (dot-call)
- `src/clj/clojure/java/process.clj` — `exit-ref` — line 121, col 22 (function line +7) — `(.waitFor` (dot-call)
- `src/clj/clojure/java/process.clj` — `exit-ref` — line 125, col 11 (function line +11) — `(.waitFor` (dot-call)
- `src/clj/clojure/java/process.clj` — `exit-ref` — line 125, col 40 (function line +11) — `java.util.concurrent.TimeUnit/MILLISECONDS` (static)
- `src/clj/clojure/java/process.clj` — `exit-ref` — line 126, col 15 (function line +12) — `(.exitValue` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-thread-factory` — line 135, col 11 (function line +5) — `(.setName` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-thread-factory` — line 136, col 11 (function line +6) — `(.setDaemon` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-executor` — line 140, col 4 (function line +1) — `Executors/newCachedThreadPool` (static)
- `src/clj/clojure/java/process.clj` — `io-task` — line 146, col 13 (function line +4) — `(.submit` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-task` — line 155, col 23 (function line +13) — `(.isDone` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-task` — line 157, col 16 (function line +15) — `(.get` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-task` — line 158, col 29 (function line +16) — `(.get` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-task` — line 159, col 24 (function line +17) — `(.isCancelled` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-task` — line 160, col 19 (function line +18) — `(.isDone` (dot-call)
- `src/clj/clojure/java/process.clj` — `io-task` — line 161, col 30 (function line +19) — `(.cancel` (dot-call)
- `src/clj/clojure/java/shell.clj` — `aconcat` — line 41, col 10 (function line +6) — `System/arraycopy` (static)
- `src/clj/clojure/java/shell.clj` — `stream-to-bytes` — line 64, col 5 (function line +4) — `(.toByteArray` (dot-call)
- `src/clj/clojure/java/shell.clj` — `stream-to-string` — line 67, col 30 (function line +1) — `(.name` (dot-call)
- `src/clj/clojure/java/shell.clj` — `stream-to-string` — line 67, col 38 (function line +1) — `Charset/defaultCharset` (static)
- `src/clj/clojure/java/shell.clj` — `stream-to-string` — line 71, col 8 (function line +5) — `(.toString` (dot-call)
- `src/clj/clojure/java/shell.clj` — `sh` — line 113, col 14 (function line +34) — `(.exec` (dot-call)
- `src/clj/clojure/java/shell.clj` — `sh` — line 113, col 22 (function line +34) — `Runtime/getRuntime` (static)
- `src/clj/clojure/java/shell.clj` — `sh` — line 120, col 24 (function line +41) — `(.getOutputStream` (dot-call)
- `src/clj/clojure/java/shell.clj` — `sh` — line 122, col 7 (function line +43) — `(.close` (dot-call)
- `src/clj/clojure/java/shell.clj` — `sh` — line 122, col 15 (function line +43) — `(.getOutputStream` (dot-call)
- `src/clj/clojure/java/shell.clj` — `sh` — line 123, col 24 (function line +44) — `(.getInputStream` (dot-call)
- `src/clj/clojure/java/shell.clj` — `sh` — line 124, col 24 (function line +45) — `(.getErrorStream` (dot-call)
- `src/clj/clojure/java/shell.clj` — `sh` — line 127, col 23 (function line +48) — `(.waitFor` (dot-call)
