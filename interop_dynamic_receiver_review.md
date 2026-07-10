# Dynamic receiver interop review

This is the next Option D migration artifact: a semantic review queue for direct Java interop dot-calls whose receiver type was not explicit in the original reports. These callsites remain proposed classifications until reviewed.

## Summary

- Total dynamic receiver dot-calls: **1659**.
- Focus-file dynamic receiver dot-calls: **1394**.
- Companion machine-readable artifact: `interop_dynamic_receiver_review.edn`.

| Proposed suite | Count |
| --- | ---: |
| `host-service` | 187 |
| `jvm-backend` | 600 |
| `manual-review` | 72 |
| `mixed-review` | 503 |
| `portable-semantic` | 297 |

## Focus file review queue

| Source file | Dynamic calls | Proposed suite counts | Top snippets |
| --- | ---: | --- | --- |
| `src/clj/clojure/core.clj` | 512 | `host-service` 6, `jvm-backend` 3, `mixed-review` 503 | `(.` (288), `(.nth` (7), `(.deref` (6), `(.reduce` (5), `(.toArray` (5) |
| `src/clj/clojure/core_deftype.clj` | 59 | `jvm-backend` 59 | `(.sym` (8), `(.protocol` (5), `(.methodk` (5), `(.__methodImplCache` (5), `(.valAt` (4) |
| `src/clj/clojure/core_print.clj` | 63 | `portable-semantic` 63 | `(.write` (45), `(.getName` (6), `(.append` (5), `(.isNaN` (2), `(.isArray` (1) |
| `src/clj/clojure/java/io.clj` | 29 | `host-service` 29 | `(.read` (4), `(.write` (4), `(.getProtocol` (3), `(.toURL` (3), `(.replace` (1) |
| `src/clj/clojure/java/process.clj` | 26 | `host-service` 26 | `(.environment` (2), `(.waitFor` (2), `(.isDone` (2), `(.get` (2), `(.startsWith` (1) |
| `src/clj/clojure/pprint/cl_format.clj` | 63 | `portable-semantic` 63 | `(.write` (15), `(.length` (9), `(.toLowerCase` (6), `(.flush` (5), `(.indexOf` (4) |
| `src/clj/clojure/pprint/dispatch.clj` | 25 | `portable-semantic` 25 | `(.write` (18), `(.col_write` (2), `(.` (1), `(.getName` (1), `(.isArray` (1) |
| `src/clj/clojure/reflect/java.clj` | 22 | `jvm-backend` 22 | `(.getModifiers` (4), `(.getName` (3), `(.getDeclaringClass` (3), `(.getParameterTypes` (2), `(.getExceptionTypes` (2) |
| `src/clj/clojure/genclass.clj` | 232 | `jvm-backend` 232 | `(.` (205), `(.getName` (6), `(.checkCast` (4), `(.getParameterTypes` (3), `(.getReturnType` (3) |
| `src/clj/clojure/core_proxy.clj` | 157 | `jvm-backend` 157 | `(.` (145), `(.getName` (5), `(.replace` (2), `(.checkCast` (2), `(.lastIndexOf` (1) |
| `src/clj/clojure/gvec.clj` | 127 | `jvm-backend` 127 | `(.nth` (17), `(.aset` (15), `(.arr` (14), `(.alength` (12), `(.array` (12) |
| `src/clj/clojure/string.clj` | 79 | `portable-semantic` 79 | `(.toString` (24), `(.length` (10), `(.charAt` (7), `(.indexOf` (6), `(.append` (4) |

## Review rules before promotion

1. Promote a dynamic receiver cluster only after the receiver role is understood from local code context.
2. Prefer `:portable-semantic` only for public language/runtime behavior that should apply across targets.
3. Use `:jvm-backend` for compiler, bytecode, generated class, Java reflection, and classloading behavior.
4. Use `:host-service` for IO, processes, environment, time, concurrency, UI, resources, and diagnostics.
5. Keep `:mixed-review` clusters out of the coverage matrix until split into smaller reviewed clusters.

## Recommended next implementation step

Start with `src/clj/clojure/java/process.clj` and `src/clj/clojure/java/io.clj` because they are strongly host-service oriented and already have backend-suite tests. After that, review `src/clj/clojure/genclass.clj`, `src/clj/clojure/core_proxy.clj`, and `src/clj/clojure/reflect/java.clj` for JVM-backend promotion.
