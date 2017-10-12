package clojure.lang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import java.util.regex.Matcher;

public class CljColl {
    static final int CHUNK_SIZE = 32;

    public ISeq chunkIteratorSeq(final Iterator iter) {
        if (iter.hasNext()) {
            return new LazySeq(new AFn() {
                public Object invoke() {
                    Object[] arr = new Object[CHUNK_SIZE];
                    int n = 0;
                    while (iter.hasNext() && n < CHUNK_SIZE)
                        arr[n++] = iter.next();
                    return new ChunkedCons(new ArrayChunk(arr, 0, n), chunkIteratorSeq(iter));
                }
            });
        }
        return null;
    }

    public ISeq seq(Object coll) {
        if (coll instanceof ASeq) {
            return (ASeq) coll;
        } else if (coll instanceof LazySeq) {
            return ((LazySeq) coll).seq();
        } else {
            return seqFrom(coll);
        }
    }

    public ISeq seqFrom(Object coll) {
        if (coll instanceof Seqable) {
            return ((Seqable) coll).seq();
        } else if (coll == null) {
            return null;
        } else if (coll instanceof Iterable) {
            return chunkIteratorSeq(((Iterable) coll).iterator());
        } else if (coll.getClass().isArray()) {
            return ArraySeq.createFromObject(coll);
        } else if (coll instanceof CharSequence) {
            return StringSeq.create((CharSequence) coll);
        } else if (coll instanceof Map) {
            return seq(((Map) coll).entrySet());
        } else {
            Class c = coll.getClass();
            Class sc = c.getSuperclass();
            throw new IllegalArgumentException("Don't know how to create ISeq from: " + c.getName());
        }
    }

    public boolean canSeq(Object coll) {
        return coll instanceof ISeq
                || coll instanceof Seqable
                || coll == null
                || coll instanceof Iterable
                || coll.getClass().isArray()
                || coll instanceof CharSequence
                || coll instanceof Map;
    }

    public Iterator iter(Object coll) {
        if (coll instanceof Iterable) {
            return ((Iterable) coll).iterator();
        } else if (coll == null) {
            return new Iterator() {
                public boolean hasNext() {
                    return false;
                }

                public Object next() {
                    throw new NoSuchElementException();
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        } else if (coll instanceof Map) {
            return ((Map) coll).entrySet().iterator();
        } else if (coll instanceof String) {
            final String s = (String) coll;
            return new Iterator() {
                int i = 0;

                public boolean hasNext() {
                    return i < s.length();
                }

                public Object next() {
                    return s.charAt(i++);
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        } else if (coll.getClass().isArray()) {
            return ArrayIter.createFromObject(coll);
        } else {
            return iter(seq(coll));
        }
    }

    public Object seqOrElse(Object o) {
        return seq(o) == null ? null : o;
    }

    public ISeq keys(Object coll) {
        if (coll instanceof IPersistentMap) {
            return APersistentMap.KeySeq.createFromMap((IPersistentMap) coll);
        } else {
            return APersistentMap.KeySeq.create(seq(coll));
        }
    }

    public ISeq vals(Object coll) {
        if (coll instanceof IPersistentMap) {
            return APersistentMap.ValSeq.createFromMap((IPersistentMap) coll);
        } else {
            return APersistentMap.ValSeq.create(seq(coll));
        }
    }

    public int count(Object o) {
        if (o instanceof Counted) {
            return ((Counted) o).count();
        }
        return countFrom(Util.ret1(o, o = null));
    }

    public int countFrom(Object o) {
        if (o == null) {
            return 0;
        } else if (o instanceof IPersistentCollection) {
            ISeq s = seq(o);
            o = null;
            int i = 0;
            for (; s != null; s = s.next()) {
                if (s instanceof Counted)
                    return i + s.count();
                i++;
            }
            return i;
        } else if (o instanceof CharSequence) {
            return ((CharSequence) o).length();
        } else if (o instanceof Collection) {
            return ((Collection) o).size();
        } else if (o instanceof Map) {
            return ((Map) o).size();
        } else if (o instanceof Map.Entry) {
            return 2;
        } else if (o.getClass().isArray()) {
            return Array.getLength(o);
        }

        throw new UnsupportedOperationException("count not supported on this type: " + o.getClass().getSimpleName());
    }

    public IPersistentCollection conj(IPersistentCollection coll, Object x) {
        if (coll == null) {
            return new PersistentList(x);
        }
        return coll.cons(x);
    }

    public ISeq cons(Object x, Object coll) {
        //ISeq y = seq(coll);
        if (coll == null) {
            return new PersistentList(x);
        } else if (coll instanceof ISeq) {
            return new Cons(x, (ISeq) coll);
        } else {
            return new Cons(x, seq(coll));
        }
    }

    public Object first(Object x) {
        if (x instanceof ISeq) {
            return ((ISeq) x).first();
        }

        ISeq seq = seq(x);
        if (seq == null) {
            return null;
        }
        return seq.first();
    }

    public Object second(Object x) {
        return first(next(x));
    }

    public Object third(Object x) {
        return first(next(next(x)));
    }

    public Object fourth(Object x) {
        return first(next(next(next(x))));
    }

    public ISeq next(Object x) {
        if (x instanceof ISeq) {
            return ((ISeq) x).next();
        }
        ISeq seq = seq(x);
        if (seq == null) {
            return null;
        }
        return seq.next();
    }

    public ISeq more(Object x) {
        if (x instanceof ISeq) {
            return ((ISeq) x).more();
        }
        ISeq seq = seq(x);
        if (seq == null) {
            return PersistentList.EMPTY;
        }
        return seq.more();
    }

    public Object peek(Object x) {
        if (x == null) {
            return null;
        }
        return ((IPersistentStack) x).peek();
    }

    public Object pop(Object x) {
        if (x == null) {
            return null;
        }
        return ((IPersistentStack) x).pop();
    }

    public Object get(Object coll, Object key) {
        if (coll instanceof ILookup) {
            return ((ILookup) coll).valAt(key);
        }
        return getFrom(coll, key);
    }

    public Object getFrom(Object coll, Object key) {
        if (coll == null) {
            return null;
        } else if (coll instanceof Map) {
            Map m = (Map) coll;
            return m.get(key);
        } else if (coll instanceof IPersistentSet) {
            IPersistentSet set = (IPersistentSet) coll;
            return set.get(key);
        } else if (key instanceof Number && (coll instanceof String || coll.getClass().isArray())) {
            int n = ((Number) key).intValue();
            if (n >= 0 && n < count(coll))
                return nth(coll, n);
            return null;
        }

        return null;
    }

    public Object get(Object coll, Object key, Object notFound) {
        if (coll instanceof ILookup) {
            return ((ILookup) coll).valAt(key, notFound);
        }
        return getFrom(coll, key, notFound);
    }

    public Object getFrom(Object coll, Object key, Object notFound) {
        if (coll == null) {
            return notFound;
        } else if (coll instanceof Map) {
            Map m = (Map) coll;
            if (m.containsKey(key))
                return m.get(key);
            return notFound;
        } else if (coll instanceof IPersistentSet) {
            IPersistentSet set = (IPersistentSet) coll;
            if (set.contains(key))
                return set.get(key);
            return notFound;
        } else if (key instanceof Number && (coll instanceof String || coll.getClass().isArray())) {
            int n = ((Number) key).intValue();
            return n >= 0 && n < count(coll) ? nth(coll, n) : notFound;
        }
        return notFound;
    }

    public Associative assoc(Object coll, Object key, Object val) {
        if (coll == null) {
            return new PersistentArrayMap(new Object[]{key, val});
        }
        return ((Associative) coll).assoc(key, val);
    }

    public Object contains(Object coll, Object key) {
        if (coll == null) {
            return RT.F;
        } else if (coll instanceof Associative) {
            return ((Associative) coll).containsKey(key) ? RT.T : RT.F;
        } else if (coll instanceof IPersistentSet) {
            return ((IPersistentSet) coll).contains(key) ? RT.T : RT.F;
        } else if (coll instanceof Map) {
            Map m = (Map) coll;
            return m.containsKey(key) ? RT.T : RT.F;
        } else if (coll instanceof Set) {
            Set s = (Set) coll;
            return s.contains(key) ? RT.T : RT.F;
        } else if (key instanceof Number && (coll instanceof String || coll.getClass().isArray())) {
            int n = ((Number) key).intValue();
            return n >= 0 && n < count(coll);
        }
        throw new IllegalArgumentException("contains? not supported on type: " + coll.getClass().getName());
    }

    public Object find(Object coll, Object key) {
        if (coll == null) {
            return null;
        } else if (coll instanceof Associative) {
            return ((Associative) coll).entryAt(key);
        } else {
            Map m = (Map) coll;
            if (m.containsKey(key)) {
                return MapEntry.create(key, m.get(key));
            }
            return null;
        }
    }

    public ISeq findKey(Keyword key, ISeq keyvals) {
        while (keyvals != null) {
            ISeq r = keyvals.next();
            if (r == null) {
                throw Util.runtimeException("Malformed keyword argslist");
            }
            if (keyvals.first() == key) {
                return r;
            }
            keyvals = r.next();
        }
        return null;
    }

    public Object dissoc(Object coll, Object key) {
        if (coll == null) {
            return null;
        }
        return ((IPersistentMap) coll).without(key);
    }

    public Object nth(Object coll, int n) {
        if (coll instanceof Indexed) {
            return ((Indexed) coll).nth(n);
        }
        return nthFrom(Util.ret1(coll, coll = null), n);
    }

    public Object nthFrom(Object coll, int n) {
        if (coll == null) {
            return null;
        } else if (coll instanceof CharSequence) {
            return Character.valueOf(((CharSequence) coll).charAt(n));
        } else if (coll.getClass().isArray()) {
            return Reflector.prepRet(coll.getClass().getComponentType(), Array.get(coll, n));
        } else if (coll instanceof RandomAccess) {
            return ((List) coll).get(n);
        } else if (coll instanceof Matcher) {
            return ((Matcher) coll).group(n);
        } else if (coll instanceof Map.Entry) {
            Map.Entry e = (Map.Entry) coll;
            if (n == 0) {
                return e.getKey();
            } else if (n == 1) {
                return e.getValue();
            }

            throw new IndexOutOfBoundsException();
        } else if (coll instanceof Sequential) {
            ISeq seq = seq(coll);
            coll = null;
            for (int i = 0; i <= n && seq != null; ++i, seq = seq.next()) {
                if (i == n) {
                    return seq.first();
                }
            }
            throw new IndexOutOfBoundsException();
        } else {
            throw new UnsupportedOperationException(
                    "nth not supported on this type: " + coll.getClass().getSimpleName());
        }
    }

    public Object nth(Object coll, int n, Object notFound) {
        if (coll instanceof Indexed) {
            Indexed v = (Indexed) coll;
            return v.nth(n, notFound);
        }
        return nthFrom(coll, n, notFound);
    }

    public Object nthFrom(Object coll, int n, Object notFound) {
        if (coll == null) {
            return notFound;
        } else if (n < 0) {
            return notFound;
        } else if (coll instanceof CharSequence) {
            CharSequence s = (CharSequence) coll;
            if (n < s.length()) {
                return Character.valueOf(s.charAt(n));
            }
            return notFound;
        } else if (coll.getClass().isArray()) {
            if (n < Array.getLength(coll)) {
                return Reflector.prepRet(coll.getClass().getComponentType(), Array.get(coll, n));
            }
            return notFound;
        } else if (coll instanceof RandomAccess) {
            List list = (List) coll;
            if (n < list.size()) {
                return list.get(n);
            }
            return notFound;
        } else if (coll instanceof Matcher) {
            Matcher m = (Matcher) coll;
            if (n < m.groupCount()) {
                return m.group(n);
            }
            return notFound;
        } else if (coll instanceof Map.Entry) {
            Map.Entry e = (Map.Entry) coll;
            if (n == 0) {
                return e.getKey();
            } else if (n == 1) {
                return e.getValue();
            }
            return notFound;
        } else if (coll instanceof Sequential) {
            ISeq seq = seq(coll);
            coll = null;
            for (int i = 0; i <= n && seq != null; ++i, seq = seq.next()) {
                if (i == n) {
                    return seq.first();
                }
            }
            return notFound;
        } else {
            throw new UnsupportedOperationException(
                    "nth not supported on this type: " + coll.getClass().getSimpleName());
        }
    }

    public Object assocN(int n, Object val, Object coll) {
        if (coll == null) {
            return null;
        } else if (coll instanceof IPersistentVector) {
            return ((IPersistentVector) coll).assocN(n, val);
        } else if (coll instanceof Object[]) {
            //hmm... this is not persistent
            Object[] array = ((Object[]) coll);
            array[n] = val;
            return array;
        } else {
            return null;
        }
    }

    public IPersistentMap map(Object[] init) {
        if (init == null) {
            return PersistentArrayMap.EMPTY;
        } else if (init.length <= PersistentArrayMap.HASHTABLE_THRESHOLD) {
            return PersistentArrayMap.createWithCheck(init);
        }
        return PersistentHashMap.createWithCheck(init);
    }

    public IPersistentMap mapUniqueKeys(Object[] init) {
        if (init == null) {
            return PersistentArrayMap.EMPTY;
        } else if (init.length <= PersistentArrayMap.HASHTABLE_THRESHOLD) {
            return new PersistentArrayMap(init);
        }
        return PersistentHashMap.create(init);
    }

    public ISeq list(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
        return listStar(arg1, arg2, arg3, arg4, arg5, null);
    }

    public ISeq list(Object arg1, Object arg2, Object arg3, Object arg4) {
        return listStar(arg1, arg2, arg3, arg4, null);
    }

    public ISeq list(Object arg1, Object arg2, Object arg3) {
        return listStar(arg1, arg2, arg3, null);
    }

    public ISeq list(Object arg1, Object arg2) {
        return listStar(arg1, arg2, null);
    }

    public ISeq list(Object arg1) {
        return new PersistentList(arg1);
    }

    public ISeq list() {
        return null;
    }

    public IPersistentMap meta(Object x) {
        if (x instanceof IMeta) {
            return ((IMeta) x).meta();
        }
        return null;
    }

    public PersistentHashSet set(Object[] init) {
        return PersistentHashSet.createWithCheck(init);
    }

    public IPersistentVector vector(Object[] init) {
        return LazilyPersistentVector.createOwning(init);
    }

    public IPersistentVector subvec(IPersistentVector v, int start, int end) {
        if (end < start || start < 0 || end > v.count()) {
            throw new IndexOutOfBoundsException();
        }
        if (start == end) {
            return PersistentVector.EMPTY;
        }
        return new APersistentVector.SubVector(null, v, start, end);
    }

    public ISeq listStar(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, ISeq rest) {
        return (ISeq) cons(arg1, cons(arg2, cons(arg3, cons(arg4, cons(arg5, rest)))));
    }

    public ISeq listStar(Object arg1, Object arg2, Object arg3, Object arg4, ISeq rest) {
        return (ISeq) cons(arg1, cons(arg2, cons(arg3, cons(arg4, rest))));
    }

    public ISeq listStar(Object arg1, Object arg2, Object arg3, ISeq rest) {
        return (ISeq) cons(arg1, cons(arg2, cons(arg3, rest)));
    }

    public ISeq listStar(Object arg1, Object arg2, ISeq rest) {
        return (ISeq) cons(arg1, cons(arg2, rest));
    }

    public ISeq listStar(Object arg1, ISeq rest) {
        return (ISeq) cons(arg1, rest);
    }

    public ISeq arrayToList(Object[] a) {
        ISeq ret = null;
        for (int i = a.length - 1; i >= 0; --i) {
            ret = (ISeq) RT.cons(a[i], ret);
        }
        return ret;
    }

    public Object[] object_array(Object sizeOrSeq) {
        if (sizeOrSeq instanceof Number) {
            return new Object[((Number) sizeOrSeq).intValue()];
        } else {
            ISeq s = RT.seq(sizeOrSeq);
            int size = RT.count(s);
            Object[] ret = new Object[size];
            for (int i = 0; i < size && s != null; i++, s = s.next()) {
                ret[i] = s.first();
            }
            return ret;
        }
    }

    public Object[] toArray(Object coll) {
        if (coll == null) {
            return RT.EMPTY_ARRAY;
        } else if (coll instanceof Object[]) {
            return (Object[]) coll;
        } else if (coll instanceof Collection) {
            return ((Collection) coll).toArray();
        } else if (coll instanceof Iterable) {
            ArrayList ret = new ArrayList();
            for (Object o : (Iterable) coll) {
                ret.add(o);
            }
            return ret.toArray();
        } else if (coll instanceof Map) {
            return ((Map) coll).entrySet().toArray();
        } else if (coll instanceof String) {
            char[] chars = ((String) coll).toCharArray();
            Object[] ret = new Object[chars.length];
            for (int i = 0; i < chars.length; i++) {
                ret[i] = chars[i];
            }
            return ret;
        } else if (coll.getClass().isArray()) {
            ISeq s = (RT.seq(coll));
            Object[] ret = new Object[RT.count(s)];
            for (int i = 0; i < ret.length; i++, s = s.next()) {
                ret[i] = s.first();
            }
            return ret;
        } else {
            throw Util.runtimeException("Unable to convert: " + coll.getClass() + " to Object[]");
        }
    }

    public Object[] seqToArray(ISeq seq) {
        final int len = RT.length(seq);
        final Object[] ret = new Object[len];
        for (int i = 0; seq != null; ++i, seq = seq.next()) {
            ret[i] = seq.first();
        }
        return ret;
    }

    public Object[] seqToPassedArray(ISeq seq, Object[] passed) {
        Object[] dest = passed;
        int len = RT.count(seq);
        if (len > dest.length) {
            dest = (Object[]) Array.newInstance(passed.getClass().getComponentType(), len);
        }
        for (int i = 0; seq != null; ++i, seq = seq.next())
            dest[i] = seq.first();
        if (len < passed.length) {
            dest[len] = null;
        }
        return dest;
    }

    public Object seqToTypedArray(ISeq seq) {
        Class type = (seq != null && seq.first() != null) ? seq.first().getClass() : Object.class;
        return RT.seqToTypedArray(type, seq);
    }

    public Object seqToTypedArray(Class type, ISeq seq) {
        Object ret = Array.newInstance(type, RT.length(seq));
        if (type == Integer.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, RT.INT.intCast(seq.first()));
            }
        } else if (type == Byte.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, RT.BYTE.byteCast(seq.first()));
            }
        } else if (type == Float.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, RT.FLOAT.floatCast(seq.first()));
            }
        } else if (type == Short.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, RT.SHORT.shortCast(seq.first()));
            }
        } else if (type == Character.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, RT.CHAR.charCast(seq.first()));
            }
        } else {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, seq.first());
            }
        }
        return ret;
    }

    public int length(ISeq list) {
        int i = 0;
        for (ISeq c = list; c != null; c = c.next()) {
            i++;
        }
        return i;
    }

    public int boundedLength(ISeq list, int limit) {
        int i = 0;
        for (ISeq c = list; c != null && i <= limit; c = c.next()) {
            i++;
        }
        return i;
    }
}
