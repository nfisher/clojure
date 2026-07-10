;   Copyright (c) Rich Hickey. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test-clojure.backend.host-services
  (:require [clojure.java.io :as jio]
            [clojure.java.process :as p]
            [clojure.string :as str]
            [clojure.test :refer :all]))

(defn- temp-file
  [prefix]
  (doto (java.io.File/createTempFile prefix ".tmp")
    (.deleteOnExit)))

(deftest ^:host-service process-file-redirect-contracts
  (testing "process redirects expose a JVM host-service contract for files"
    (let [out (temp-file "clj-process-out")
          err (temp-file "clj-process-err")]
      (is (zero? @(p/exit-ref
                    (p/start {:out (p/to-file out)
                              :err (p/to-file err)
                              :env {"CLOJURE_PROCESS_TEST" "ok"}}
                             "bash" "-c"
                             "printf '%s' \"$CLOJURE_PROCESS_TEST\"; printf '%s' err >&2"))))
      (is (= "ok" (slurp out)))
      (is (= "err" (slurp err)))
      (is (zero? @(p/exit-ref
                    (p/start {:out (p/to-file out :append true)}
                             "bash" "-c" "printf '%s' '+append'"))))
      (is (= "ok+append" (slurp out))))))

(deftest ^:host-service process-input-and-discard-contracts
  (testing "process input files and discard redirects are explicit host capabilities"
    (let [in (temp-file "clj-process-in")]
      (spit in "from-file")
      (is (= "from-file" (p/exec {:in (p/from-file in)} "bash" "-c" "cat")))
      (is (zero? @(p/exit-ref
                    (p/start {:out :discard :err :discard}
                             "bash" "-c" "printf '%s' discarded; printf '%s' err >&2")))))))

(deftest ^:host-service process-exit-timeout-contract
  (testing "exit refs support blocking deref with timeout for process scheduling"
    (let [proc (p/start "bash" "-c" "sleep 0.2")]
      (is (= :timeout (deref (p/exit-ref proc) 1 :timeout)))
      (is (zero? @(p/exit-ref proc))))))

(deftest ^:host-service process-stderr-stdout-contract
  (testing "stderr can be redirected to captured stdout"
    (is (= "err-to-out"
           (str/trim (p/exec {:err :stdout} "bash" "-c" "printf '%s' err-to-out >&2"))))))
