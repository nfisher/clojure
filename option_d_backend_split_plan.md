# Option D backend split review plan

This document is a review plan for Option D: separating shared language/runtime semantics from target-specific compiler and host backends. It builds on the existing Java interop reports, the critical-path coverage matrix, the future backend suite taxonomy, and the initial JVM backend regression tests.

## Executive summary

Option D should proceed as a staged backend split, not as a direct translation of JVM implementation code to JavaScript. The target architecture is:

1. a portable semantic layer for language/runtime behavior,
2. a JVM backend for bytecode, Java interop, reflection, classloading, and JVM implementation details,
3. host-service backends for IO, process, environment, concurrency, resources, and diagnostics,
4. future JS browser, JS Node, and other backends that implement the portable contracts and supported host capabilities.

The review strategy is to promote critical interop callsites from `:manual-review-required` to explicit regression coverage before substantial implementation changes. The first backend suite is `:jvm-backend`; it already has initial tests for selected compiler/backend contracts.

## Goals

- Preserve JVM Clojure behavior while creating seams for additional targets.
- Identify which behavior is portable semantics versus JVM backend implementation versus host capability.
- Build regression coverage before refactoring implementation code.
- Avoid browser or Node support accidentally inheriting JVM-only assumptions.
- Keep the migration reviewable through small artifacts, explicit suite assignments, and staged gates.

## Non-goals for the first implementation phase

- Do not rewrite `clojure.lang.Compiler` wholesale.
- Do not attempt to make Java interop portable to non-JVM targets.
- Do not move large runtime data structures before semantic tests exist.
- Do not require browser/Node test runners before the portable contract suite is defined.
- Do not treat dynamic receiver interop as classified until local semantic intent is reviewed.

## Proposed backend suite taxonomy

| Suite | Purpose | Initial target behavior |
| --- | --- | --- |
| `:portable-semantic` | Cross-target language/runtime semantics | Must eventually pass on JVM, browser JS, Node JS, and future targets. |
| `:jvm-backend` | JVM compiler, bytecode, Java interop, reflection, classloading | JVM-only; protects current implementation during extraction. |
| `:host-service` | Capability APIs: IO, process, resources, env, time, concurrency, diagnostics | Target-dependent; browser/Node/JVM support may differ. |
| `:dynamic-review` | Ambiguous dynamic receiver callsites | Holding area until callsites are classified into a real suite. |

## Current review inputs

| Artifact | Role in Option D review |
| --- | --- |
| `*_interop.md` | Source-level inventory of Java interop callsites. |
| `interop_summary.md` | Operating-type distribution and package-level prioritization. |
| `interop_critical_path_coverage.edn` | Machine-readable critical-path callsite matrix. |
| `interop_test_coverage_plan.md` | Human-readable coverage workflow and suite organization. |
| `test/clojure/test_clojure/backend/jvm_backend.clj` | First implemented JVM backend regression suite. |

## Phase 1: stabilize JVM backend coverage

### Purpose

Before introducing target abstractions, protect the current JVM compiler/backend behavior that could be disrupted by moving code behind interfaces or service boundaries.

### Scope

Prioritize `:compiler-backend` callsites in the critical-path matrix:

- `clojure.lang.Compiler`
- `clojure.lang.Compiler$HostExpr`
- ASM `Type`, `Opcodes`, `ClassWriter`, and generator helpers
- Java reflection metadata such as `Method` and `Modifier`
- class/member resolution helpers
- generated class and bytecode emission helpers

### Deliverables

1. Expand `test/clojure/test_clojure/backend/jvm_backend.clj` to cover remaining high-risk compiler/backend helpers.
2. Mark callsites as `:covered-behavioral-internal` only when a direct behavioral assertion exists.
3. Keep unproven callsites as `:manual-review-required`.
4. Add focused tests for bytecode-visible behavior rather than asserting only implementation constants.

### Review gate

Do not extract compiler/backend seams until the high-risk JVM compiler/backend callsites have either:

- direct JVM backend tests,
- a documented reason they are implementation details covered by a broader behavioral test,
- or an explicit follow-up issue.

## Phase 2: define portable semantic contracts

### Purpose

Create a target-independent behavioral contract for runtime semantics that should hold across JVM, browser JS, Node JS, and future targets.

### Scope

Promote selected `:runtime-data-model` callsites into `:portable-semantic` coverage:

- symbols and keywords,
- vars and namespaces where portable,
- persistent collection semantics,
- equality and hashing,
- sequence behavior,
- invocation semantics,
- records/types at the language level,
- numeric behavior where cross-target semantics are intended.

### Deliverables

1. Define portable semantic test namespaces under the future backend suite structure.
2. Use public API tests when possible rather than testing JVM implementation classes.
3. Document where JVM behavior is intentionally not portable.
4. Add edge-case tests for equality, hashing, metadata, seqs, invocation, and numeric boundaries.

### Review gate

A runtime/data-model implementation seam should not be introduced until the affected behavior is represented in `:portable-semantic` tests or explicitly documented as JVM-only.

## Phase 3: define host service contracts

### Purpose

Separate host capabilities from portable core so browser, Node, JVM, and future targets can expose only supported capabilities.

### Scope

Classify and test host-service areas:

- IO/resources,
- process execution,
- environment and system properties,
- time,
- concurrency/scheduling,
- classloading/reflection,
- diagnostics and stack traces.

### Deliverables

1. Define a host capability matrix for JVM, browser JS, Node JS, and future targets.
2. Add JVM host-service tests for existing behavior.
3. Define expected unsupported behavior for browser-only gaps such as process execution.
4. Separate URL/resource behavior that may be portable from file/process behavior that is host-specific.

### Review gate

No host-service abstraction should be introduced until the target capability decision is documented and the JVM behavior is protected by tests.

## Phase 4: reduce dynamic receiver uncertainty

### Purpose

The existing reports classify many instance calls as dynamic receiver callsites. These must be semantically classified before they can safely drive backend work.

### Scope

Start with high-density and high-risk files:

- `src/clj/clojure/core.clj`,
- `src/clj/clojure/core_deftype.clj`,
- `src/clj/clojure/core_print.clj`,
- `src/clj/clojure/java/io.clj`,
- `src/clj/clojure/java/process.clj`,
- `src/clj/clojure/pprint/*`,
- `src/clj/clojure/reflect/java.clj`.

### Deliverables

1. Move reviewed callsites from `:dynamic-review` into `:portable-semantic`, `:jvm-backend`, or `:host-service`.
2. Add candidate test files and proposed assertions for each moved cluster.
3. Keep unverifiable callsites conservative.

### Review gate

Dynamic receiver clusters should not be refactored until they have a semantic suite assignment.

## Phase 4 artifact status

The first dynamic receiver review pass is captured in `interop_dynamic_receiver_review.md` and `interop_dynamic_receiver_review.edn`. These artifacts summarize the 1,659 dynamic receiver dot-calls from the generated interop reports, identify 1,394 calls in the current focus files, and propose initial backend-suite classifications for review before any matrix promotion or source refactoring.

## Phase 5: introduce seams incrementally

### Purpose

Once tests exist, begin introducing backend seams in small, reversible changes.

### Candidate seams

| Seam | First backend | Future backend value |
| --- | --- | --- |
| compiler host resolution | JVM backend | Lets JS/analyzer backends implement target-specific host resolution. |
| bytecode emission | JVM backend | Keeps ASM isolated from portable semantics. |
| classloading/reflection | JVM host service | Allows browser/Node to omit or replace reflection behavior. |
| resources/IO | host service | Enables Node/browser-specific resource capabilities. |
| diagnostics | shared model plus backend extraction | Allows target-specific stack/cause details. |

### Review gate

Each seam PR should include:

- before/after callsite mapping,
- tests moved or added,
- matrix status updates,
- confirmation that unsupported target behavior is explicit.

## Suggested implementation order

1. Finish high-priority `:jvm-backend` tests.
2. Add the first `:portable-semantic` test namespace for runtime/data-model behavior.
3. Add the first `:host-service` test namespace for JVM IO/resources and process behavior.
4. Classify the top dynamic receiver clusters.
5. Extract the smallest JVM compiler/backend seam that is already covered by tests.
6. Repeat with narrow seams and matrix updates.

## Review checklist for each future PR

- Does the PR identify the affected suite: `:portable-semantic`, `:jvm-backend`, `:host-service`, or `:dynamic-review`?
- Does the PR update `interop_critical_path_coverage.edn` when coverage status changes?
- Are new tests backend-suite organized, even if existing namespace tests remain in place?
- Are JVM-only assumptions documented?
- Are browser and Node implications explicitly called out where relevant?
- Are dynamic receiver callsites classified before refactoring?
- Is the change small enough to revert without invalidating unrelated suites?

## Risks and mitigations

| Risk | Mitigation |
| --- | --- |
| Overfitting tests to JVM internals | Prefer public behavior for portable semantics; keep internals under `:jvm-backend`. |
| Premature abstraction | Require a test-backed seam and review gate before extraction. |
| Browser-incompatible APIs leaking into portable core | Use `:host-service` capability matrix and unsupported-behavior tests. |
| Dynamic receiver misclassification | Keep conservative status until local semantic review is complete. |
| Test suite fragmentation | Use backend suite metadata and the EDN matrix as the organizing layer. |

## Immediate next actions for review

1. Approve this Option D staged plan.
2. Decide the next suite to expand after `:jvm-backend`: recommended next suite is `:portable-semantic` for runtime/data-model contracts.
3. Pick the first runtime/data-model cluster to promote from the matrix.
4. Continue updating coverage statuses only when tests make direct behavioral assertions.
