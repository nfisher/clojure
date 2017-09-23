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
import java.util.Comparator;

public final class DefaultComparator implements Comparator, Serializable {
    public int compare(Object o1, Object o2) {
        return Util.compare(o1, o2);
    }

    private Object readResolve() throws ObjectStreamException {
        // ensures that we aren't hanging onto a new default comparator for every
        // sorted set, etc., we deserialize
        return RT.DEFAULT_COMPARATOR;
    }
}
