/**
 *   Copyright (c) Rich Hickey. All rights reserved.
 *   The use and distribution terms for this software are covered by the
 *   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *   which can be found in the file epl-v10.html at the root of this distribution.
 *   By using this software in any fashion, you are agreeing to be bound by
 * 	 the terms of this license.
 *   You must not remove this notice, or any other, from this software.
 **/

package clojure;

import clojure.lang.Symbol;
import clojure.lang.Var;
import clojure.lang.RT;

public class main{


public static void legacy_repl(String[] args) {
    final Symbol CLOJURE_MAIN = Symbol.intern("clojure.main");
    final Var REQUIRE = RT.var("clojure.core", "require");
    final Var LEGACY_REPL = RT.var("clojure.main", "legacy-repl");

    REQUIRE.invoke(CLOJURE_MAIN);
    LEGACY_REPL.invoke(RT.seq(args));
}

public static void legacy_script(String[] args) {
    final Symbol CLOJURE_MAIN = Symbol.intern("clojure.main");
    final Var REQUIRE = RT.var("clojure.core", "require");
    final Var LEGACY_SCRIPT = RT.var("clojure.main", "legacy-script");

    REQUIRE.invoke(CLOJURE_MAIN);
    LEGACY_SCRIPT.invoke(RT.seq(args));
}

public static void main(String[] args) {
    PrefetchThread pt = new PrefetchThread();
    pt.start();

    final Symbol CLOJURE_MAIN = Symbol.intern("clojure.main");
    final Var REQUIRE = RT.var("clojure.core", "require");
    final Var MAIN = RT.var("clojure.main", "main");

    REQUIRE.invoke(CLOJURE_MAIN);
    MAIN.applyTo(RT.seq(args));

    synchronized (pt) {
        if (pt.length < 0) {
            System.out.println("Hello world");
        }
    }
}
}
