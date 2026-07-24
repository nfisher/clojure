# Critical-path interop test coverage plan

This plan is a review artifact for building a quality regression suite before substantial implementation changes. It pairs a human-readable plan with the machine-readable `interop_critical_path_coverage.edn` matrix and proceeds with organization around future backend suites.

## Scope

Per review direction, this first pass is intentionally limited to the Option D critical path rather than all 2,221 reported Java interop occurrences:

1. `compiler-backend` — JVM compiler/backend and host-member resolution boundaries.
2. `runtime-data-model` — shared runtime semantics candidates that every backend must preserve.
3. `host-service` — IO, process, environment, concurrency, reflection/classloading, and other capability boundaries.

The matrix is conservative. Every critical-path entry starts as `:manual-review-required` unless a later pass explicitly links it to a behavioral assertion. Existing tests may cover many entries indirectly, but indirect coverage is not treated as sufficient for backend-split work.

## Machine-readable companion

`interop_critical_path_coverage.edn` contains 284 explicit critical-path callsites extracted from the current `*_interop.md` reports.

| Semantic category | Callsites | Initial coverage status |
| --- | ---: | --- |
| `runtime-data-model` | 163 | `:manual-review-required` |
| `host-service` | 65 | `:manual-review-required` |
| `compiler-backend` | 56 | `:manual-review-required` |
| **Total** | **284** | conservative review required |

Each entry preserves the report, source file, enclosing function/form, line, column, snippet, interop kind, operating type, semantic category, future backend suite, candidate test files, and notes.

## Current coverage signal

The existing test tree already has useful coverage signals, but it is mostly organized around public behavior and current JVM implementation areas rather than the future backend split:

| Area | Existing coverage signal | Why it matters |
| --- | --- | --- |
| compiler/backend | `test/clojure/test_clojure/compilation.clj`, `test/clojure/test_clojure/java_interop.clj`, protocol generation tests | Protects compiler resolution, type hints, reflection warnings, generated classes, and JVM host interop. |
| runtime/data model | `test/clojure/test_clojure/data_structures.clj`, `sequences.clj`, `protocols.clj`, `rt.clj`, `vars.clj`, `keywords.clj` | Protects shared semantics that future JS/browser/Node backends must match. |
| host services | `test/clojure/test_clojure/java/io.clj`, `java/process.clj`, `server.clj`, `repl/deps.clj` | Protects JVM-specific capabilities and gives a template for Node/browser capability gates. |

## Conservative coverage workflow

1. Start from `interop_critical_path_coverage.edn`.
2. For each callsite, identify the externally observable behavior it supports.
3. Link the callsite to an existing test only if the test has a direct behavioral assertion for that behavior.
4. Otherwise leave the entry as `:manual-review-required` and add a proposed test.
5. Promote statuses only after review:
   - `:covered-behavioral-public-api`
   - `:covered-behavioral-internal`
   - `:covered-compile-only`
   - `:covered-indirect`
   - `:missing-high-risk`
   - `:missing-medium-risk`
   - `:jvm-only-acceptable`
   - `:manual-review-required`

## Trade-offs: organizing by current namespace

### Benefits

- Low disruption to the existing test layout.
- Easier for maintainers to find tests near the current source namespace.
- Works well for incremental hardening of existing JVM behavior.
- Natural fit for current Ant/Maven test execution.
- Reduces the chance of accidentally changing test semantics while creating the coverage map.

### Costs

- Makes it harder to see which tests are portable semantic contracts versus JVM backend details.
- Host-specific tests may remain mixed with target-independent behavior.
- Future JS/browser/Node runners may need filtering rules that are not obvious from file paths.
- Coverage reports may answer “which namespace is tested?” better than “which backend contract is protected?”

### Best use

Use current namespace organization for near-term JVM regression hardening and for tests that are tightly coupled to existing namespace APIs.

## Trade-offs: organizing by future backend suite

### Benefits

- Makes the Option D architecture explicit in the test suite.
- Separates portable semantic tests from JVM backend and host capability tests.
- Gives future JS/browser/Node targets a clear conformance test entry point.
- Reduces the risk that browser-incompatible capabilities leak into portable core.
- Encourages precise contracts before implementation refactoring.

### Costs

- Requires more up-front taxonomy work.
- May duplicate some setup or fixture code from existing namespace tests.
- Can be harder to adopt incrementally if current tests are deeply namespace-oriented.
- May require build/test runner changes to select suites by backend or capability.

### Best use

Use future backend suite organization for new regression tests created specifically to support the compiler/runtime/backend split.

## Decision: organize around future backend suites

Proceed with future backend suite organization as the primary structure. Existing namespace tests can remain in place for now, but new regression work should be planned, tagged, and reported by backend suite first. Current namespace paths are supporting metadata, not the organizing principle.

Implementation guidance:

1. Treat `interop_critical_path_coverage.edn` as the source of truth for suite assignment.
2. Promote runtime/data-model behavior into `:portable-semantic` when it is expected across JVM, browser JS, Node.js, and future targets.
3. Keep compiler bytecode, Java interop, reflection, and classloading assertions under `:jvm-backend`.
4. Keep IO, process, environment, time, and concurrency checks under `:host-service`, with explicit target availability expectations.
5. Leave ambiguous dynamic receiver callsites under `:dynamic-review` until they can be assigned to one of the suites above.
6. Link existing namespace tests into these suites through metadata or the EDN matrix rather than reorganizing all existing test files immediately.

Suite labels:

| Suite | Purpose |
| --- | --- |
| `:portable-semantic` | Behavior expected on JVM, browser JS, Node.js, and future targets. |
| `:jvm-backend` | JVM compiler, bytecode, Java interop, reflection, and classloading behavior. |
| `:host-service` | Capability-specific APIs such as files, processes, environment, time, and concurrency. |
| `:dynamic-review` | Dynamic receiver callsites that need local semantic classification before test assignment. |

## Critical-path regression backlog

### 1. Compiler/backend

- Map `clojure.lang.Compiler`, `clojure.lang.Compiler$HostExpr`, `Method`, `Modifier`, `GeneratorAdapter`, `Opcodes`, and `Type` callsites to compiler and Java interop tests.
- Add explicit tests for host expression resolution, type-hint-driven dispatch, reflection warning boundaries, primitive return handling, generated method/member behavior, and bytecode-visible public behavior.
- Keep these tests JVM-only unless they describe analyzer-level semantics independent of bytecode.

### 2. Runtime/data model

- Map `clojure.lang.RT`, `clojure.lang.Util`, `Var`, `Namespace`, `Keyword`, `Symbol`, persistent collection classes, hashing, equality, and numeric helper callsites to public semantic tests.
- Promote stable behavior to `:portable-semantic` where it should apply across JVM, browser JS, Node.js, and future targets.
- Add edge-case tests for equality, hashing, metadata, sequence behavior, invocation, namespace/var lookup, records/types, and numeric tower behavior.

### 3. Host services

- Map `System`, `Thread`, `ProcessBuilder$Redirect`, `TimeUnit`, `File`, `Runtime`, `Executors`, URL encoding/decoding, classloading, and reflector callsites to host-service tests.
- Split these tests by capability: IO/resources, process execution, environment/runtime, concurrency/scheduling, and reflection/classloading.
- Decide which capabilities are JVM-only, Node-compatible, browser-limited, or unsupported.

## Initial implemented regression tests

The first concrete test implementation starts with the `:jvm-backend` suite. `test/clojure/test_clojure/backend/jvm_backend.clj` adds JVM backend regression tests for host expression class and array resolution, primitive tag resolution, compiler class writer construction, compiler namespace/class resolution, type-hint-driven host member calls that should not reflect, and compiler name munging. The machine-readable matrix now marks the directly covered compiler callsites as `:covered-behavioral-internal` while leaving the rest conservatively at `:manual-review-required`.

The first `:portable-semantic` implementation is `test/clojure/test_clojure/backend/portable_semantics.clj`. It covers public contracts for symbols, keywords, collection construction, map entries, ranges, sequence behavior, equality, hashing, comparison, and integer/numeric predicates. Matrix entries covered by this public API suite are marked `:covered-behavioral-public-api`.

The first `:host-service` implementation is `test/clojure/test_clojure/backend/host_services.clj`. It covers public process host capabilities for file redirects, append redirects, input file redirects, discard redirects, stderr-to-stdout redirection, process environment overrides, and exit-ref timeout behavior. Matrix entries covered by this host API suite are marked `:covered-behavioral-public-api`.

## Review checkpoints before implementation changes

1. Approve the status taxonomy.
2. Approve the critical-path category mapping in `interop_critical_path_coverage.edn`.
3. Decide whether to tag tests with metadata, external EDN mappings, or both.
4. Pick the first high-risk future backend suite to expand into actual regression tests. Recommended first suite: `:jvm-backend`.
5. Only after the first category has explicit regression tests should implementation refactoring begin.
