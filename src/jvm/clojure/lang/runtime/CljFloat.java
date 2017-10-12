package clojure.lang.runtime;

public class CljFloat {

    public float floatCast(final Object x) {
        if (x instanceof Float) {
            return ((Float) x).floatValue();
        }

        double n = ((Number) x).doubleValue();
        if (n < -Float.MAX_VALUE || n > Float.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for float: " + x);
        }

        return (float) n;
    }

    public float floatCast(final byte x) {
        return x;
    }

    public float floatCast(final short x) {
        return x;
    }

    public float floatCast(final int x) {
        return x;
    }

    public float floatCast(final float x) {
        return x;
    }

    public float floatCast(final long x) {
        return x;
    }

    public float floatCast(final double x) {
        if (x < -Float.MAX_VALUE || x > Float.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for float: " + x);
        }

        return (float) x;
    }

    public Number box(final float x) {
        return x;
    }
}
