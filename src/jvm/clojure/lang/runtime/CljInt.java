package clojure.lang.runtime;

import clojure.lang.RT;

public class CljInt {

    public int intCast(final Object x) {
        if (x instanceof Integer) {
            return ((Integer) x).intValue();
        }
        if (x instanceof Number) {
            long n = RT.longCast(x);
            return RT.intCast(n);
        }
        return ((Character) x).charValue();
    }

    public int intCast(final char x) {
        return x;
    }

    public int intCast(final byte x) {
        return x;
    }

    public int intCast(final short x) {
        return x;
    }

    public int intCast(final int x) {
        return x;
    }

    public int intCast(final float x) {
        if (x < Integer.MIN_VALUE || x > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for int: " + x);
        }
        return (int) x;
    }

    public int intCast(final long x) {
        int i = (int) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for int: " + x);
        }
        return i;
    }

    public int intCast(final double x) {
        if (x < Integer.MIN_VALUE || x > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for int: " + x);
        }
        return (int) x;
    }

    public Number box(final int x) {
        return x;
    }

    public int uncheckedIntCast(final double x) {
        return (int) x;
    }

    public int uncheckedIntCast(final char x) {
        return x;
    }

    public int uncheckedIntCast(final short x) {
        return x;
    }

    public int uncheckedIntCast(final byte x) {
        return x;
    }

    public int uncheckedIntCast(final Object x) {
        if (x instanceof Number) {
            return ((Number) x).intValue();
        }
        return ((Character) x).charValue();
    }

    public int uncheckedIntCast(final int x) {
        return x;
    }

    public int uncheckedIntCast(final long x) {
        return (int) x;
    }

    public int uncheckedIntCast(final float x) {
        return (int) x;
    }
}
