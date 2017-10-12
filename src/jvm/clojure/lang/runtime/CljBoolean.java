package clojure.lang.runtime;

import clojure.lang.RT;

public class CljBoolean {

    public boolean booleanCast(final Object x) {
        if (x instanceof Boolean) {
            return ((Boolean) x).booleanValue();
        }
        return x != null;
    }

    public boolean booleanCast(final boolean x) {
        return x;
    }

    public Object box(final boolean x) {
        return x ? RT.T : RT.F;
    }

    public Object box(final Boolean x) {
        return x;
    }
}
