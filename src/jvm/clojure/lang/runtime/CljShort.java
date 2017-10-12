package clojure.lang.runtime;

import clojure.lang.RT;

public class CljShort {

    public short shortCast(final Object x) {
        if (x instanceof Short) {
            return ((Short) x).shortValue();
        }
        long n = RT.longCast(x);
        if (n < Short.MIN_VALUE || n > Short.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for short: " + x);
        }

        return (short) n;
    }

    public short shortCast(final byte x) {
        return x;
    }

    public short shortCast(final short x) {
        return x;
    }

    public short shortCast(final int x) {
        short i = (short) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for short: " + x);
        }
        return i;
    }

    public short shortCast(final long x) {
        short i = (short) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for short: " + x);
        }
        return i;
    }

    public short shortCast(final float x) {
        if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE) {
            return (short) x;
        }
        throw new IllegalArgumentException("Value out of range for short: " + x);
    }

    public short shortCast(final double x) {
        if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE) {
            return (short) x;
        }
        throw new IllegalArgumentException("Value out of range for short: " + x);
    }

    public Number box(final short x) {
        return x;
    }
}
