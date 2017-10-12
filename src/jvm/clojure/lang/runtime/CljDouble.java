package clojure.lang.runtime;

public class CljDouble {

    public double doubleCast(final byte x) {
        return x;
    }

    public double doubleCast(final short x) {
        return x;
    }

    public double doubleCast(final int x) {
        return x;
    }

    public double doubleCast(final float x) {
        return x;
    }

    public double doubleCast(final long x) {
        return x;
    }

    public double doubleCast(final double x) {
        return x;
    }

    public double doubleCast(final Object x) {
        return ((Number)x).doubleValue();
    }

    public Number box(final double x) {
        return x;
    }
}
