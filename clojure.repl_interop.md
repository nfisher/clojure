# Java interop occurrences for `clojure.repl`

One line per direct Java interop occurrence in `src/clj/clojure`. Position is reported relative to the enclosing function/form.

## Distribution by operating type

Total interop occurrences: **6**.

| Operating type | Count |
| --- | ---: |
| `Unknown/dynamic instance receiver` | 3 |
| `RT` | 2 |
| `Thread` | 1 |

Classification note: static calls and constructors are classified by their Java class; instance dot-calls without an explicit class receiver are grouped as `Unknown/dynamic instance receiver` because the receiver type is not reliably encoded at the call site.

## Occurrences

- `src/clj/clojure/repl/deps.clj` — `add-loader-url` — line 25, col 30 (function line +3) — `RT/toUrl` (static)
- `src/clj/clojure/repl/deps.clj` — `add-loader-url` — line 26, col 30 (function line +4) — `(.getContextClassLoader` (dot-call)
- `src/clj/clojure/repl/deps.clj` — `add-loader-url` — line 26, col 55 (function line +4) — `Thread/currentThread` (static)
- `src/clj/clojure/repl/deps.clj` — `add-loader-url` — line 27, col 31 (function line +5) — `(.getParent` (dot-call)
- `src/clj/clojure/repl/deps.clj` — `add-loader-url` — line 32, col 7 (function line +10) — `(.addURL` (dot-call)
- `src/clj/clojure/repl/deps.clj` — `add-libs` — line 51, col 51 (function line +16) — `RT/toUrl` (static)
