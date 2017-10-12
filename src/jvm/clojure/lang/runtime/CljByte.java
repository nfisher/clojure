package clojure.lang.runtime;

import clojure.lang.RT;

public class CljByte {

    public byte byteCast(final Object x) {
        if (x instanceof Byte) {
            return ((Byte) x).byteValue();
        }
        long n = RT.longCast(x);
        if (n < Byte.MIN_VALUE || n > Byte.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for byte: " + x);
        }

        return (byte) n;
    }

    public byte byteCast(final byte x) {
        return x;
    }

    public byte byteCast(final short x) {
        byte i = (byte) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for byte: " + x);
        }
        return i;
    }

    public byte byteCast(final int x) {
        byte i = (byte) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for byte: " + x);
        }
        return i;
    }

    public byte byteCast(final long x) {
        byte i = (byte) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for byte: " + x);
        }
        return i;
    }

    public byte byteCast(final float x) {
        if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE) {
            return (byte) x;
        }
        throw new IllegalArgumentException("Value out of range for byte: " + x);
    }

    public byte byteCast(final double x) {
        if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE) {
            return (byte) x;
        }
        throw new IllegalArgumentException("Value out of range for byte: " + x);
    }

    public Number box(final byte x) {
        return x;
    }
}
