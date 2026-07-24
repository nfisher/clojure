# Java interop occurrences for `clojure.tools`

One line per direct Java interop occurrence in `src/clj/clojure`. Position is reported relative to the enclosing function/form.

## Distribution by operating type

Total interop occurrences: **2**.

| Operating type | Count |
| --- | ---: |
| `Unknown/dynamic instance receiver` | 2 |

Classification note: static calls and constructors are classified by their Java class; instance dot-calls without an explicit class receiver are grouped as `Unknown/dynamic instance receiver` because the receiver type is not reliably encoded at the call site.

## Occurrences

- `src/clj/clojure/tools/deps/interop.clj` — `invoke-tool` — line 72, col 14 (function line +31) — `(.write` (dot-call)
- `src/clj/clojure/tools/deps/interop.clj` — `invoke-tool` — line 73, col 14 (function line +32) — `(.write` (dot-call)
