# Java interop occurrences for `clojure.test`

One line per direct Java interop occurrence in `src/clj/clojure`. Position is reported relative to the enclosing function/form.

## Distribution by operating type

Total interop occurrences: **4**.

| Operating type | Count |
| --- | ---: |
| `Unknown/dynamic instance receiver` | 4 |

Classification note: static calls and constructors are classified by their Java class; instance dot-calls without an explicit class receiver are grouped as `Unknown/dynamic instance receiver` because the receiver type is not reliably encoded at the call site.

## Occurrences

- `src/clj/clojure/test/junit.clj` — `package-class` — line 84, col 11 (function line +2) — `(.lastIndexOf` (dot-call)
- `src/clj/clojure/test/junit.clj` — `package-class` — line 87, col 8 (function line +5) — `(.substring` (dot-call)
- `src/clj/clojure/test/junit.clj` — `package-class` — line 87, col 30 (function line +5) — `(.substring` (dot-call)
- `src/clj/clojure/test/tap.clj` — `print-tap-diagnostic` — line 56, col 16 (function line +5) — `(.split` (dot-call)
