package clojure.lang.runtime;

public class CljChar {

    public char charCast(final Object x) {
        if (x instanceof Character) {
            return ((Character) x).charValue();
        }

        long n = ((Number) x).longValue();
        if (n < Character.MIN_VALUE || n > Character.MAX_VALUE) {
            throw new IllegalArgumentException("Value out of range for char: " + x);
        }

        return (char) n;
    }

    public char charCast(final byte x) {
        char i = (char) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for char: " + x);
        }
        return i;
    }

    public char charCast(final short x) {
        char i = (char) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for char: " + x);
        }
        return i;
    }

    public char charCast(final char x) {
        return x;
    }

    public char charCast(final int x) {
        char i = (char) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for char: " + x);
        }
        return i;
    }

    public char charCast(final long x) {
        char i = (char) x;
        if (i != x) {
            throw new IllegalArgumentException("Value out of range for char: " + x);
        }
        return i;
    }

    public char charCast(final float x) {
        if (x >= Character.MIN_VALUE && x <= Character.MAX_VALUE) {
            return (char) x;
        }
        throw new IllegalArgumentException("Value out of range for char: " + x);
    }

    public char charCast(final double x) {
        if (x >= Character.MIN_VALUE && x <= Character.MAX_VALUE) {
            return (char) x;
        }
        throw new IllegalArgumentException("Value out of range for char: " + x);
    }

    public Character box(final char x) {
        return Character.valueOf(x);
    }

    public char uncheckedCharCast(Object x) {
        if (x instanceof Character) {
            return ((Character) x).charValue();
        }
        return (char) ((Number) x).longValue();
    }

    public char uncheckedCharCast(double x) {
        return (char) x;
    }

    public char uncheckedCharCast(float x) {
        return (char) x;
    }

    public char uncheckedCharCast(long x) {
        return (char) x;
    }

    public char uncheckedCharCast(int x) {
        return (char) x;
    }

    public char uncheckedCharCast(char x) {
        return x;
    }

    public char uncheckedCharCast(short x) {
        return (char) x;
    }

    public char uncheckedCharCast(byte x) {
        return (char) x;
    }
}
