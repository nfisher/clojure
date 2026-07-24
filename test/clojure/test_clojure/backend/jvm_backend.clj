;   Copyright (c) Rich Hickey. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test-clojure.backend.jvm-backend
  (:require [clojure.test :refer :all]
            [clojure.test-helper :refer [should-not-reflect]])
  (:import (clojure.asm ClassWriter)
           (clojure.lang Compiler Compiler$HostExpr)))

(deftest ^:jvm-backend host-expr-class-resolution-contract
  (testing "special tags resolve to the JVM primitive and array classes used by host expr analysis"
    (are [tag expected] (= expected (Compiler$HostExpr/maybeSpecialTag tag))
      'long Long/TYPE
      'double Double/TYPE
      'objects (class (object-array 0))
      'ints (class (int-array 0))))
  (testing "class-looking symbols resolve to JVM classes without loading target classes unnecessarily"
    (are [form expected] (= expected (Compiler$HostExpr/maybeClass form false))
      'java.lang.String String
      'java.util.Map java.util.Map
      String String))
  (testing "symbols that are not class names or imports do not resolve as classes"
    (is (nil? (Compiler$HostExpr/maybeClass 'not-a-class false)))))

(deftest ^:jvm-backend compiler-resolution-contract
  (testing "maybeResolveIn preserves JVM class and var resolution behavior used by core/ns-resolve"
    (is (= String (Compiler/maybeResolveIn (the-ns 'clojure.core) 'java.lang.String)))
    (is (= #'clojure.core/map (Compiler/maybeResolveIn (the-ns 'clojure.core) 'map)))
    (is (nil? (Compiler/maybeResolveIn (the-ns 'clojure.core) 'missing.symbol.ClassName)))))

(deftest ^:jvm-backend host-member-hint-regressions
  (testing "explicit JVM type hints keep host member calls off reflective dispatch"
    (should-not-reflect #(let [^String s "abcdef"] (.substring s 1 3)))
    (should-not-reflect #(let [^java.util.Collection c (java.util.ArrayList.)] (.size c)))
    (should-not-reflect #(let [^long x -42] (Math/abs x)))))


(deftest ^:jvm-backend host-expr-array-class-contract
  (testing "array class symbols encode JVM array descriptors consistently"
    (are [sym descriptor] (= descriptor (Compiler$HostExpr/buildArrayClassDescriptor sym))
      'java.lang.String/1 "[Ljava.lang.String;"
      'long/2 "[[J")
    (is (true? (Compiler$HostExpr/looksLikeArrayClass 'java.lang.String/1)))
    (is (false? (Compiler$HostExpr/looksLikeArrayClass 'java.lang.String)))
    (is (= (class (make-array String 0))
           (Compiler$HostExpr/maybeArrayClass 'java.lang.String/1)))
    (is (= (class (make-array Long/TYPE 0 0))
           (Compiler$HostExpr/maybeArrayClass 'long/2)))))

(deftest ^:jvm-backend compiler-primitive-and-classwriter-contract
  (testing "primitive tag resolution remains stable for JVM backend analysis"
    (are [tag expected] (= expected (Compiler/primClass tag))
      'int Integer/TYPE
      'long Long/TYPE
      'float Float/TYPE
      'double Double/TYPE
      'char Character/TYPE
      'short Short/TYPE
      'byte Byte/TYPE
      'boolean Boolean/TYPE
      'void Void/TYPE)
    (is (nil? (Compiler/primClass 'not-primitive))))
  (testing "classWriter returns the ASM class writer used by JVM bytecode emission"
    (is (instance? ClassWriter (Compiler/classWriter)))))

(deftest ^:jvm-backend compiler-munge-contract
  (testing "JVM backend name munging remains stable for generated class and member names"
    (are [source munged] (= munged (Compiler/munge source))
      "clojure.core/+" "clojure.core_SLASH__PLUS_"
      "valid-name?" "valid_name_QMARK_"
      "set!" "set_BANG_")
    (are [source] (= source (Compiler/demunge (Compiler/munge source)))
      "plain"
      "alpha-beta"
      "predicate?"
      "bang!")))
