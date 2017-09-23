/**
 * Copyright (c) Rich Hickey. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/
package clojure.lang;

public class Unbound extends AFn {
    final public Var v;

    public Unbound(Var v) {
        this.v = v;
    }

    public String toString() {
        return "Unbound: " + v;
    }

    public Object throwArity(int n) {
        throw new IllegalStateException("Attempting to call unbound fn: " + v);
    }
}
