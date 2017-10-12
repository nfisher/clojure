package clojure.lang.runtime;

import clojure.lang.BigInt;
import clojure.lang.Ratio;

import java.math.BigInteger;

public class CljLong {
    public long longCast(final Object x) {
        if (x instanceof Integer || x instanceof Long) {
            return ((Number) x).longValue();
        } else if (x instanceof BigInt) {
            BigInt bi = (BigInt) x;
            if (bi.bipart == null) {
                return bi.lpart;
            } else {
                throw new IllegalArgumentException("Value out of range for long: " + x);
            }
        } else if (x instanceof BigInteger) {
            BigInteger bi = (BigInteger) x;
            if (bi.bitLength() < 64) {
                return bi.longValue();
            } else {
                throw new IllegalArgumentException("Value out of range for long: " + x);
            }
        } else if (x instanceof Byte || x instanceof Short) {
            return ((Number) x).longValue();
        } else if (x instanceof Ratio) {
            return longCast(((Ratio) x).bigIntegerValue());
        } else if (x instanceof Character) {
            return longCast(((Character) x).charValue());
        } else {
            return longCast(((Number) x).doubleValue());
        }
    }

    public long longCast(final byte x) {
        return x;
    }

    public long longCast(final short x) {
        return x;
    }

    public long longCast(final int x) {
        return x;
    }

    public long longCast(final float x) {
        if (x < Long.MIN_VALUE || x > Long.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for long: " + x);
        }
        return (long) x;
    }

    public long longCast(final long x) {
        return x;
    }

    public long longCast(final double x) {
        if (x < Long.MIN_VALUE || x > Long.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for long: " + x);
        }
        return (long) x;
    }

    public Number box(final long x) {
        return x;
    }
}
