package clojure.lang.runtime;

public class CljArray {

    public float aget(float[] xs, int i) {
        return xs[i];
    }

    public float aset(float[] xs, int i, float v) {
        xs[i] = v;
        return v;
    }

    public int alength(float[] xs) {
        return xs.length;
    }

    public float[] aclone(float[] xs) {
        return xs.clone();
    }

    public double aget(double[] xs, int i) {
        return xs[i];
    }

    public double aset(double[] xs, int i, double v) {
        xs[i] = v;
        return v;
    }

    public int alength(double[] xs) {
        return xs.length;
    }

    public double[] aclone(double[] xs) {
        return xs.clone();
    }

    public int aget(int[] x, int i) {
        return x[i];
    }

    public int aset(int[] xs, int i, int v) {
        xs[i] = v;
        return v;
    }

    public int alength(int[] xs) {
        return xs.length;
    }

    public int[] aclone(int[] xs) {
        return xs.clone();
    }

    public long aget(long[] x, int i) {
        return x[i];
    }

    public long aset(long[] xs, int i, long v) {
        xs[i] = v;
        return v;
    }

    public int alength(long[] xs) {
        return xs.length;
    }

    public long[] aclone(long[] xs) {
        return xs.clone();
    }

    public char aget(char[] xs, int i) {
        return xs[i];
    }

    public char aset(char[] xs, int i, char v) {
        xs[i] = v;
        return v;
    }

    public int alength(char[] xs) {
        return xs.length;
    }

    public char[] aclone(char[] xs) {
        return xs.clone();
    }

    public byte aget(byte[] xs, int i) {
        return xs[i];
    }

    public byte aset(byte[] xs, int i, byte v) {
        xs[i] = v;
        return v;
    }

    public int alength(byte[] xs) {
        return xs.length;
    }

    public byte[] aclone(byte[] xs) {
        return xs.clone();
    }

    public short aget(short[] xs, int i) {
        return xs[i];
    }

    public short aset(short[] xs, int i, short v) {
        xs[i] = v;
        return v;
    }

    public int alength(short[] xs) {
        return xs.length;
    }

    public short[] aclone(short[] xs) {
        return xs.clone();
    }

    public boolean aget(boolean[] xs, int i) {
        return xs[i];
    }

    public boolean aset(boolean[] xs, int i, boolean v) {
        xs[i] = v;
        return v;
    }

    public int alength(boolean[] xs) {
        return xs.length;
    }

    public boolean[] aclone(boolean[] xs) {
        return xs.clone();
    }

    public Object aget(Object[] xs, int i) {
        return xs[i];
    }

    public Object aset(Object[] xs, int i, Object v) {
        xs[i] = v;
        return v;
    }

    public int alength(Object[] xs) {
        return xs.length;
    }

    public Object[] aclone(Object[] xs) {
        return xs.clone();
    }
}
