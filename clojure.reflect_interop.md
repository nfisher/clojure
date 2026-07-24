# Java interop occurrences for `clojure.reflect`

One line per direct Java interop occurrence in `src/clj/clojure`. Position is reported relative to the enclosing function/form.

## Distribution by operating type

Total interop occurrences: **30**.

| Operating type | Count |
| --- | ---: |
| `Unknown/dynamic instance receiver` | 22 |
| `Type` | 5 |
| `Opcodes` | 1 |
| `Thread` | 1 |
| `clojure.lang.RT` | 1 |

Classification note: static calls and constructors are classified by their Java class; instance dot-calls without an explicit class receiver are grouped as `Unknown/dynamic instance receiver` because the receiver type is not reliably encoded at the call site.

## Occurrences

- `src/clj/clojure/reflect/java.clj` — `<top-level>` — line 29, col 15 (function line +28) — `Type/getType` (static)
- `src/clj/clojure/reflect/java.clj` — `<top-level>` — line 34, col 8 (function line +33) — `(.getClassName` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `field-descriptor->class-symbol` — line 65, col 13 (function line +6) — `Type/getType` (static)
- `src/clj/clojure/reflect/java.clj` — `internal-name->class-symbol` — line 75, col 13 (function line +8) — `Type/getObjectType` (static)
- `src/clj/clojure/reflect/java.clj` — `constructor->map` — line 121, col 12 (function line +3) — `(.getName` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `constructor->map` — line 122, col 13 (function line +4) — `(.getDeclaringClass` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `constructor->map` — line 123, col 22 (function line +5) — `(.getParameterTypes` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `constructor->map` — line 124, col 22 (function line +6) — `(.getExceptionTypes` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `constructor->map` — line 125, col 17 (function line +7) — `(.getModifiers` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `declared-constructors` — line 132, col 9 (function line +5) — `(.getDeclaredConstructors` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `method->map` — line 140, col 12 (function line +3) — `(.getName` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `method->map` — line 141, col 13 (function line +4) — `(.getReturnType` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `method->map` — line 142, col 13 (function line +5) — `(.getDeclaringClass` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `method->map` — line 143, col 22 (function line +6) — `(.getParameterTypes` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `method->map` — line 144, col 22 (function line +7) — `(.getExceptionTypes` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `method->map` — line 145, col 17 (function line +8) — `(.getModifiers` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `declared-methods` — line 152, col 9 (function line +5) — `(.getDeclaredMethods` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `field->map` — line 160, col 12 (function line +3) — `(.getName` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `field->map` — line 161, col 13 (function line +4) — `(.getType` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `field->map` — line 162, col 13 (function line +5) — `(.getDeclaringClass` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `field->map` — line 163, col 17 (function line +6) — `(.getModifiers` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `declared-fields` — line 170, col 9 (function line +5) — `(.getDeclaredFields` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `typeref->class` — line 176, col 6 (function line +4) — `clojure.lang.RT/classForName` (static)
- `src/clj/clojure/reflect/java.clj` — `JavaReflector` — line 183, col 35 (function line +5) — `(.getModifiers` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `default-reflector` — line 189, col 22 (function line +1) — `(.getContextClassLoader` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `default-reflector` — line 189, col 47 (function line +1) — `Thread/currentThread` (static)
- `src/clj/clojure/reflect/java.clj` — `parse-method-descriptor` — line 193, col 40 (function line +2) — `Type/getArgumentTypes` (static)
- `src/clj/clojure/reflect/java.clj` — `parse-method-descriptor` — line 194, col 27 (function line +3) — `Type/getReturnType` (static)
- `src/clj/clojure/reflect/java.clj` — `parse-method-descriptor` — line 206, col 18 (function line +15) — `(.getResourceAsStream` (dot-call)
- `src/clj/clojure/reflect/java.clj` — `AsmReflector` — line 219, col 12 (function line +11) — `Opcodes/ASM4` (static)
