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

class Frame {
    final static Frame TOP = new Frame(PersistentHashMap.EMPTY, null);
    //Var->TBox
    Associative bindings;
    //Var->val
    //	Associative frameBindings;
    Frame prev;

    public Frame(Associative bindings, Frame prev) {
        this.bindings = bindings;
        this.prev = prev;
    }

    protected Object clone() {
        return new Frame(this.bindings, null);
    }

}
