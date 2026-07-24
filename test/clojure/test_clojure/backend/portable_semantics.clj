;   Copyright (c) Rich Hickey. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test-clojure.backend.portable-semantics
  (:require [clojure.test :refer :all]))

(deftest ^:portable-semantic symbol-and-keyword-contracts
  (testing "keywords are interned values with stable namespace/name lookup"
    (is (identical? :portable.semantic/example
                    (keyword "portable.semantic" "example")))
    (is (= :portable.semantic/example
           (find-keyword "portable.semantic" "example")))
    (is (nil? (find-keyword "portable.semantic" (str "missing-" (gensym))))))
  (testing "symbols preserve namespace, name, equality, and hash semantics"
    (let [s (symbol "portable.semantic" "example")]
      (is (= "portable.semantic" (namespace s)))
      (is (= "example" (name s)))
      (is (= s 'portable.semantic/example))
      (is (= (hash s) (hash 'portable.semantic/example))))))

(deftest ^:portable-semantic collection-construction-contracts
  (testing "portable vector, list, map, and set construction semantics"
    (is (= [:a :b :c] (vector :a :b :c)))
    (is (= [:a :b :c] (vec (list :a :b :c))))
    (is (= '(:a :b :c) (list :a :b :c)))
    (is (= {:a 1 :b 2} (array-map :a 1 :b 2)))
    (is (= (sorted-map :a 1 :b 2)
           (into (sorted-map) [[:b 2] [:a 1]])))
    (is (= (sorted-set 1 2 3)
           (into (sorted-set) [3 1 2 1]))))
  (testing "map entries expose key/value and sequence semantics"
    (let [entry (first (array-map :k 42))]
      (is (= :k (key entry)))
      (is (= 42 (val entry)))
      (is (= [:k 42] (vec entry)))
      (is (= entry (first {:k 42}))))))

(deftest ^:portable-semantic sequence-and-range-contracts
  (testing "range, subvec, seq, count, conj, assoc, and find expose stable public behavior"
    (is (= [0 1 2 3] (vec (range 4))))
    (is (= [5 4 3] (vec (range 5 2 -1))))
    (is (= [2 3] (subvec [1 2 3 4] 1 3)))
    (is (= '(1 2 3) (seq [1 2 3])))
    (is (= 3 (count [1 2 3])))
    (is (= [1 2 3] (conj [1 2] 3)))
    (is (= {:a 1 :b 2} (assoc {:a 1} :b 2)))
    (is (= [:b 2] (find {:a 1 :b 2} :b)))))

(deftest ^:portable-semantic equality-hashing-and-numeric-contracts
  (testing "portable equality, equivalence, identity, comparison, and hashing semantics"
    (is (= [1 2 3] (list 1 2 3)))
    (is (= {:a 1 :b 2} (array-map :a 1 :b 2)))
    (is (= (hash [1 2 3]) (hash (vec (list 1 2 3)))))
    (is (= (hash {:a 1 :b 2}) (hash (array-map :a 1 :b 2))))
    (is (identical? :portable.semantic/example :portable.semantic/example))
    (is (neg? (compare 1 2)))
    (is (pos? (compare 2 1)))
    (is (zero? (compare :a :a))))
  (testing "integer predicates and numeric ordering remain target semantic contracts"
    (is (integer? 0))
    (is (integer? 42N))
    (is (not (integer? 1.5)))
    (is (< -1 0 1))
    (is (> 3 2 1))))
