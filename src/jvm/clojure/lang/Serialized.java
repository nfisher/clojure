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

import java.io.ObjectStreamException;
import java.io.Serializable;

/***
 Note - serialization only supports reconnecting the Var identity on the deserializing end
 Neither the value in the var nor any of its properties are serialized
 ***/

public class Serialized implements Serializable {
    public Serialized(Symbol nsName, Symbol sym) {
        this.nsName = nsName;
        this.sym = sym;
    }

    private Symbol nsName;
    private Symbol sym;

    private Object readResolve() throws ObjectStreamException {
        return Var.intern(nsName, sym);
    }
}
