/**
 * Copyright (c) Rich Hickey. All rights reserved.
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution.
 * By using this software in any fashion, you are agreeing to be bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this software.
 **/

/* rich Mar 25, 2006 4:28:27 PM */

package clojure.lang;

import clojure.lang.runtime.CljBoolean;
import clojure.lang.runtime.CljByte;
import clojure.lang.runtime.CljChar;
import clojure.lang.runtime.CljDouble;
import clojure.lang.runtime.CljFloat;
import clojure.lang.runtime.CljInt;
import clojure.lang.runtime.CljLong;
import clojure.lang.runtime.CljShort;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectStreamException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class RT {
    public static final RT runtime = new RT();
    public static final CljBoolean cljBoolean = new CljBoolean();
    public static final CljByte cljByte = new CljByte();
    public static final CljChar cljChar = new CljChar();
    public static final CljDouble cljDouble = new CljDouble();
    public static final CljFloat cljFloat = new CljFloat();
    public static final CljInt cljInt = new CljInt();
    public static final CljLong cljLong = new CljLong();
    public static final CljShort cljShort = new CljShort();

    public static final CljSeq cljSeq = new CljSeq();

    public static final CljScript cljScript = new CljScript();

    static final public Boolean T = Boolean.TRUE;//Keyword.intern(Symbol.intern(null, "t"));
    static final public Boolean F = Boolean.FALSE;//Keyword.intern(Symbol.intern(null, "t"));
    static final public String LOADER_SUFFIX = "__init";

    //simple-symbol->class
    final static public IPersistentMap DEFAULT_IMPORTS = map(
            Symbol.intern("Boolean"), Boolean.class,
            Symbol.intern("Byte"), Byte.class,
            Symbol.intern("Character"), Character.class,
            Symbol.intern("Class"), Class.class,
            Symbol.intern("ClassLoader"), ClassLoader.class,
            Symbol.intern("Compiler"), Compiler.class,
            Symbol.intern("Double"), Double.class,
            Symbol.intern("Enum"), Enum.class,
            Symbol.intern("Float"), Float.class,
            Symbol.intern("InheritableThreadLocal"), InheritableThreadLocal.class,
            Symbol.intern("Integer"), Integer.class,
            Symbol.intern("Long"), Long.class,
            Symbol.intern("Math"), Math.class,
            Symbol.intern("Number"), Number.class,
            Symbol.intern("Object"), Object.class,
            Symbol.intern("Package"), Package.class,
            Symbol.intern("Process"), Process.class,
            Symbol.intern("ProcessBuilder"), ProcessBuilder.class,
            Symbol.intern("Runtime"), Runtime.class,
            Symbol.intern("RuntimePermission"), RuntimePermission.class,
            Symbol.intern("SecurityManager"), SecurityManager.class,
            Symbol.intern("Short"), Short.class,
            Symbol.intern("StackTraceElement"), StackTraceElement.class,
            Symbol.intern("StrictMath"), StrictMath.class,
            Symbol.intern("String"), String.class,
            Symbol.intern("StringBuffer"), StringBuffer.class,
            Symbol.intern("StringBuilder"), StringBuilder.class,
            Symbol.intern("System"), System.class,
            Symbol.intern("Thread"), Thread.class,
            Symbol.intern("ThreadGroup"), ThreadGroup.class,
            Symbol.intern("ThreadLocal"), ThreadLocal.class,
            Symbol.intern("Throwable"), Throwable.class,
            Symbol.intern("Void"), Void.class,
            Symbol.intern("Appendable"), Appendable.class,
            Symbol.intern("CharSequence"), CharSequence.class,
            Symbol.intern("Cloneable"), Cloneable.class,
            Symbol.intern("Comparable"), Comparable.class,
            Symbol.intern("Iterable"), Iterable.class,
            Symbol.intern("Readable"), Readable.class,
            Symbol.intern("Runnable"), Runnable.class,
            Symbol.intern("Callable"), Callable.class,
            Symbol.intern("BigInteger"), BigInteger.class,
            Symbol.intern("BigDecimal"), BigDecimal.class,
            Symbol.intern("ArithmeticException"), ArithmeticException.class,
            Symbol.intern("ArrayIndexOutOfBoundsException"), ArrayIndexOutOfBoundsException.class,
            Symbol.intern("ArrayStoreException"), ArrayStoreException.class,
            Symbol.intern("ClassCastException"), ClassCastException.class,
            Symbol.intern("ClassNotFoundException"), ClassNotFoundException.class,
            Symbol.intern("CloneNotSupportedException"), CloneNotSupportedException.class,
            Symbol.intern("EnumConstantNotPresentException"), EnumConstantNotPresentException.class,
            Symbol.intern("Exception"), Exception.class,
            Symbol.intern("IllegalAccessException"), IllegalAccessException.class,
            Symbol.intern("IllegalArgumentException"), IllegalArgumentException.class,
            Symbol.intern("IllegalMonitorStateException"), IllegalMonitorStateException.class,
            Symbol.intern("IllegalStateException"), IllegalStateException.class,
            Symbol.intern("IllegalThreadStateException"), IllegalThreadStateException.class,
            Symbol.intern("IndexOutOfBoundsException"), IndexOutOfBoundsException.class,
            Symbol.intern("InstantiationException"), InstantiationException.class,
            Symbol.intern("InterruptedException"), InterruptedException.class,
            Symbol.intern("NegativeArraySizeException"), NegativeArraySizeException.class,
            Symbol.intern("NoSuchFieldException"), NoSuchFieldException.class,
            Symbol.intern("NoSuchMethodException"), NoSuchMethodException.class,
            Symbol.intern("NullPointerException"), NullPointerException.class,
            Symbol.intern("NumberFormatException"), NumberFormatException.class,
            Symbol.intern("RuntimeException"), RuntimeException.class,
            Symbol.intern("SecurityException"), SecurityException.class,
            Symbol.intern("StringIndexOutOfBoundsException"), StringIndexOutOfBoundsException.class,
            Symbol.intern("TypeNotPresentException"), TypeNotPresentException.class,
            Symbol.intern("UnsupportedOperationException"), UnsupportedOperationException.class,
            Symbol.intern("AbstractMethodError"), AbstractMethodError.class,
            Symbol.intern("AssertionError"), AssertionError.class,
            Symbol.intern("ClassCircularityError"), ClassCircularityError.class,
            Symbol.intern("ClassFormatError"), ClassFormatError.class,
            Symbol.intern("Error"), Error.class,
            Symbol.intern("ExceptionInInitializerError"), ExceptionInInitializerError.class,
            Symbol.intern("IllegalAccessError"), IllegalAccessError.class,
            Symbol.intern("IncompatibleClassChangeError"), IncompatibleClassChangeError.class,
            Symbol.intern("InstantiationError"), InstantiationError.class,
            Symbol.intern("InternalError"), InternalError.class,
            Symbol.intern("LinkageError"), LinkageError.class,
            Symbol.intern("NoClassDefFoundError"), NoClassDefFoundError.class,
            Symbol.intern("NoSuchFieldError"), NoSuchFieldError.class,
            Symbol.intern("NoSuchMethodError"), NoSuchMethodError.class,
            Symbol.intern("OutOfMemoryError"), OutOfMemoryError.class,
            Symbol.intern("StackOverflowError"), StackOverflowError.class,
            Symbol.intern("ThreadDeath"), ThreadDeath.class,
            Symbol.intern("UnknownError"), UnknownError.class,
            Symbol.intern("UnsatisfiedLinkError"), UnsatisfiedLinkError.class,
            Symbol.intern("UnsupportedClassVersionError"), UnsupportedClassVersionError.class,
            Symbol.intern("VerifyError"), VerifyError.class,
            Symbol.intern("VirtualMachineError"), VirtualMachineError.class,
            Symbol.intern("Thread$UncaughtExceptionHandler"), Thread.UncaughtExceptionHandler.class,
            Symbol.intern("Thread$State"), Thread.State.class,
            Symbol.intern("Deprecated"), Deprecated.class,
            Symbol.intern("Override"), Override.class,
            Symbol.intern("SuppressWarnings"), SuppressWarnings.class
    );
    public static final Namespace CLOJURE_NS = Namespace.findOrCreate(Symbol.intern("clojure.core"));
    public final static Var OUT =
            Var.intern(CLOJURE_NS, Symbol.intern("*out*"), new OutputStreamWriter(System.out)).setDynamic();
    public final static Var IN =
            Var.intern(CLOJURE_NS, Symbol.intern("*in*"),
                    new LineNumberingPushbackReader(new InputStreamReader(System.in))).setDynamic();
    public final static Var ERR =
            Var.intern(CLOJURE_NS, Symbol.intern("*err*"),
                    new PrintWriter(new OutputStreamWriter(System.err), true)).setDynamic();
    public final static Var AGENT = Var.intern(CLOJURE_NS, Symbol.intern("*agent*"), null).setDynamic();
    public final static Var DATA_READERS = Var.intern(CLOJURE_NS, Symbol.intern("*data-readers*"), RT.map()).setDynamic();
    public final static Var DEFAULT_DATA_READER_FN = Var.intern(CLOJURE_NS, Symbol.intern("*default-data-reader-fn*"), RT.map()).setDynamic();
    public final static Var DEFAULT_DATA_READERS = Var.intern(CLOJURE_NS, Symbol.intern("default-data-readers"), RT.map());
    public final static Var SUPPRESS_READ = Var.intern(CLOJURE_NS, Symbol.intern("*suppress-read*"), null).setDynamic();
    public final static Var ASSERT = Var.intern(CLOJURE_NS, Symbol.intern("*assert*"), T).setDynamic();
    public final static Var MATH_CONTEXT = Var.intern(CLOJURE_NS, Symbol.intern("*math-context*"), null).setDynamic();
    public final static Var USE_CONTEXT_CLASSLOADER =
            Var.intern(CLOJURE_NS, Symbol.intern("*use-context-classloader*"), T).setDynamic();
    //boolean
    public static final Var UNCHECKED_MATH = Var.intern(Namespace.findOrCreate(Symbol.intern("clojure.core")),
            Symbol.intern("*unchecked-math*"), Boolean.FALSE).setDynamic();
    //symbol
    public static final Var CURRENT_NS = Var.intern(CLOJURE_NS, Symbol.intern("*ns*"),
            CLOJURE_NS).setDynamic();
    public static final Object[] EMPTY_ARRAY = new Object[]{};
    public static final Comparator DEFAULT_COMPARATOR = new DefaultComparator();
    static final Keyword TAG_KEY = Keyword.intern(null, "tag");
    static final Keyword CONST_KEY = Keyword.intern(null, "const");
    static final Symbol LOAD_FILE = Symbol.intern("load-file");
    static final Symbol IN_NAMESPACE = Symbol.intern("in-ns");
    static final Symbol NAMESPACE = Symbol.intern("ns");
    static final Symbol IDENTICAL = Symbol.intern("identical?");
    static final Var CMD_LINE_ARGS = Var.intern(CLOJURE_NS, Symbol.intern("*command-line-args*"), null).setDynamic();
    static final Var FLUSH_ON_NEWLINE = Var.intern(CLOJURE_NS, Symbol.intern("*flush-on-newline*"), T).setDynamic();

    static final Var PRINT_META = Var.intern(CLOJURE_NS, Symbol.intern("*print-meta*"), F).setDynamic();
    static final Var PRINT_READABLY = Var.intern(CLOJURE_NS, Symbol.intern("*print-readably*"), T).setDynamic();
    static final Var PRINT_DUP = Var.intern(CLOJURE_NS, Symbol.intern("*print-dup*"), F).setDynamic();
    static final Var WARN_ON_REFLECTION = Var.intern(CLOJURE_NS, Symbol.intern("*warn-on-reflection*"), F).setDynamic();
    static final Var ALLOW_UNRESOLVED_VARS = Var.intern(CLOJURE_NS, Symbol.intern("*allow-unresolved-vars*"), F).setDynamic();
    static final Var READER_RESOLVER = Var.intern(CLOJURE_NS, Symbol.intern("*reader-resolver*"), null).setDynamic();
    static final Var IN_NS_VAR = Var.intern(CLOJURE_NS, Symbol.intern("in-ns"), F);
    static final Var NS_VAR = Var.intern(CLOJURE_NS, Symbol.intern("ns"), F);
    static final Var FN_LOADER_VAR = Var.intern(CLOJURE_NS, Symbol.intern("*fn-loader*"), null).setDynamic();
    static final Var PRINT_INITIALIZED = Var.intern(CLOJURE_NS, Symbol.intern("print-initialized"));
    static final Var PR_ON = Var.intern(CLOJURE_NS, Symbol.intern("pr-on"));
    static final IFn inNamespace = new AFn() {
        public Object invoke(Object arg1) {
            Symbol nsname = (Symbol) arg1;
            Namespace ns = Namespace.findOrCreate(nsname);
            CURRENT_NS.set(ns);
            return ns;
        }
    };
    final static IFn bootNamespace = new AFn() {
        public Object invoke(Object __form, Object __env, Object arg1) {
            Symbol nsname = (Symbol) arg1;
            Namespace ns = Namespace.findOrCreate(nsname);
            CURRENT_NS.set(ns);
            return ns;
        }
    };
    // single instance of UTF-8 Charset, so as to avoid catching UnsupportedCharsetExceptions everywhere
    static public Charset UTF8 = Charset.forName("UTF-8");
    public static boolean checkSpecAsserts = Boolean.getBoolean("clojure.spec.check-asserts");
    static Object readeval = readTrueFalseUnknown(System.getProperty("clojure.read.eval", "true"));
    final static public Var READEVAL = Var.intern(CLOJURE_NS, Symbol.intern("*read-eval*"), readeval).setDynamic();
    static Keyword LINE_KEY = Keyword.intern(null, "line");
    static Keyword COLUMN_KEY = Keyword.intern(null, "column");
    static Keyword FILE_KEY = Keyword.intern(null, "file");
    static Keyword DECLARED_KEY = Keyword.intern(null, "declared");
    static Keyword DOC_KEY = Keyword.intern(null, "doc");
    static AtomicInteger id = new AtomicInteger(1);
    static volatile boolean CHECK_SPECS = false;

    static {
        Keyword arglistskw = Keyword.intern(null, "arglists");
        Symbol namesym = Symbol.intern("name");
        OUT.setTag(Symbol.intern("java.io.Writer"));
        CURRENT_NS.setTag(Symbol.intern("clojure.lang.Namespace"));
        AGENT.setMeta(map(DOC_KEY, "The agent currently running an action on this thread, else nil"));
        AGENT.setTag(Symbol.intern("clojure.lang.Agent"));
        MATH_CONTEXT.setTag(Symbol.intern("java.math.MathContext"));
        Var nv = Var.intern(CLOJURE_NS, NAMESPACE, bootNamespace);
        nv.setMacro();
        Var v;
        v = Var.intern(CLOJURE_NS, IN_NAMESPACE, inNamespace);
        v.setMeta(map(DOC_KEY, "Sets *ns* to the namespace named by the symbol, creating it if needed.",
                arglistskw, list(vector(namesym))));
        v = Var.intern(CLOJURE_NS, LOAD_FILE,
                new AFn() {
                    public Object invoke(Object arg1) {
                        try {
                            return Compiler.loadFile((String) arg1);
                        } catch (IOException e) {
                            throw Util.sneakyThrow(e);
                        }
                    }
                });
        v.setMeta(map(DOC_KEY, "Sequentially read and evaluate the set of forms contained in the file.",
                arglistskw, list(vector(namesym))));
        try {
            doInit();
        } catch (Exception e) {
            throw Util.sneakyThrow(e);
        }

        CHECK_SPECS = true;
    }

    static Object readTrueFalseUnknown(String s) {
        return runtime._readTrueFalseUnknown(s);
    }

    private Object _readTrueFalseUnknown(String s) {
        if (s.equals("true")) {
            return Boolean.TRUE;
        } else if (s.equals("false")) {
            return Boolean.FALSE;
        }
        return Keyword.intern(null, "unknown");
    }

    public static List<String> processCommandLine(String[] args) {
        return runtime._processCommandLine(args);
    }

    private List<String> _processCommandLine(String[] args) {
        List<String> arglist = Arrays.asList(args);
        int split = arglist.indexOf("--");
        if (split >= 0) {
            CMD_LINE_ARGS.bindRoot(RT.seq(arglist.subList(split + 1, args.length)));
            return arglist.subList(0, split);
        }
        return arglist;
    }

    // duck typing stderr plays nice with e.g. swank
    public static PrintWriter errPrintWriter() {
        return runtime._errPrintWriter();
    }

    private PrintWriter _errPrintWriter() {
        Writer w = (Writer) ERR.deref();
        if (w instanceof PrintWriter) {
            return (PrintWriter) w;
        } else {
            return new PrintWriter(w);
        }
    }

    static public void addURL(Object url) throws MalformedURLException {
        runtime._addURL(url);
    }

    private void _addURL(Object url) throws MalformedURLException {
        URL u = (url instanceof String) ? (new URL((String) url)) : (URL) url;
        ClassLoader ccl = Thread.currentThread().getContextClassLoader();
        if (ccl instanceof DynamicClassLoader) {
            ((DynamicClassLoader) ccl).addURL(u);
        } else {
            throw new IllegalAccessError("Context classloader is not a DynamicClassLoader");
        }
    }

    static public Keyword keyword(String ns, String name) {
        return Keyword.intern((Symbol.intern(ns, name)));
    }

    static public Var var(String ns, String name) {
        return Var.intern(Namespace.findOrCreate(Symbol.intern(null, ns)), Symbol.intern(null, name));
    }

    static public Var var(String ns, String name, Object init) {
        return Var.intern(Namespace.findOrCreate(Symbol.intern(null, ns)), Symbol.intern(null, name), init);
    }

    public static void loadResourceScript(String name) throws IOException {
        cljScript.loadResourceScript(name, true);
    }

    public static void maybeLoadResourceScript(String name) throws IOException {
        cljScript.loadResourceScript(name, false);
    }

    public static void loadResourceScript(String name, boolean failIfNotFound) throws IOException {
        cljScript.loadResourceScript(name, true);
    }

    public static void loadResourceScript(Class c, String name) throws IOException {
        // class is discarded, not clear what this is for except maybe legacy...
        cljScript.loadResourceScript(name, true);
    }

    public static void loadResourceScript(Class c, String name, boolean failIfNotFound) throws IOException {
        cljScript.loadResourceScript(name, failIfNotFound);
    }

    static public void init() {
        RT.errPrintWriter().println("No need to call RT.init() anymore");
    }

    static public long lastModified(URL url, String libfile) throws IOException {
        return cljScript.lastModified(url, libfile);
    }

    static void compile(String cljfile) throws IOException {
        cljScript.compile(cljfile);
    }

    static public void load(String scriptbase) throws IOException, ClassNotFoundException {
        cljScript.load(scriptbase, true);
    }

    static public void load(String scriptbase, boolean failIfNotFound) throws IOException, ClassNotFoundException {
        cljScript.load(scriptbase, failIfNotFound);
    }

    static void doInit() throws ClassNotFoundException, IOException {
        runtime._doInit();
    }

    private void _doInit() throws IOException, ClassNotFoundException {
        load("clojure/core");

        Var.pushThreadBindings(
                RT.mapUniqueKeys(CURRENT_NS, CURRENT_NS.deref(),
                        WARN_ON_REFLECTION, WARN_ON_REFLECTION.deref()
                        , RT.UNCHECKED_MATH, RT.UNCHECKED_MATH.deref()));
        try {
            Symbol USER = Symbol.intern("user");
            Symbol CLOJURE = Symbol.intern("clojure.core");

            Var in_ns = var("clojure.core", "in-ns");
            Var refer = var("clojure.core", "refer");
            in_ns.invoke(USER);
            refer.invoke(CLOJURE);
            maybeLoadResourceScript("user.clj");

            // start socket servers
            Var require = var("clojure.core", "require");
            Symbol SERVER = Symbol.intern("clojure.core.server");
            require.invoke(SERVER);
            Var start_servers = var("clojure.core.server", "start-servers");
            start_servers.invoke(System.getProperties());
        } finally {
            Var.popThreadBindings();
        }
    }

    static public int nextID() {
        return runtime._nextID();
    }

    private int _nextID() {
        return id.getAndIncrement();
    }

    // Load a library in the System ClassLoader instead of Clojure's own.
    public static void loadLibrary(String libname) {
        runtime._loadLibrary(libname);
    }

    private void _loadLibrary(String libname) {
        System.loadLibrary(libname);
    }


////////////// Collections support /////////////////////////////////

    public static ISeq chunkIteratorSeq(final Iterator iter) {
        return cljSeq.chunkIteratorSeq(iter);
    }

    static public ISeq seq(Object coll) {
        return cljSeq.seq(coll);
    }

    // N.B. canSeq must be kept in sync with this!
    static ISeq seqFrom(Object coll) {
        return cljSeq.seqFrom(coll);
    }

    static public boolean canSeq(Object coll) {
        return cljSeq.canSeq(coll);
    }

    static public Iterator iter(Object coll) {
        return cljSeq.iter(coll);
    }

    static public Object seqOrElse(Object o) {
        return cljSeq.seqOrElse(o);
    }

    static public ISeq keys(Object coll) {
        return cljSeq.keys(coll);
    }

    static public ISeq vals(Object coll) {
        return cljSeq.vals(coll);
    }

    static public IPersistentMap meta(Object x) {
        return runtime._meta(x);
    }

    private IPersistentMap _meta(Object x) {
        if (x instanceof IMeta) {
            return ((IMeta) x).meta();
        }
        return null;
    }

    public static int count(Object o) {
        return cljSeq.count(o);
    }

    static int countFrom(Object o) {
        return cljSeq.countFrom(o);
    }

    static public IPersistentCollection conj(IPersistentCollection coll, Object x) {
        return cljSeq.conj(coll, x);
    }

    static public ISeq cons(Object x, Object coll) {
        return cljSeq.cons(x, coll);

    }

    static public Object first(Object x) {
        return cljSeq.first(x);
    }

    static public Object second(Object x) {
        return cljSeq.second(x);
    }

    static public Object third(Object x) {
        return cljSeq.third(x);
    }

    static public Object fourth(Object x) {
        return cljSeq.fourth(x);
    }

    static public ISeq next(Object x) {
        return cljSeq.next(x);
    }

    static public ISeq more(Object x) {
        return cljSeq.more(x);
    }

    static public Object peek(Object x) {
        return cljSeq.peek(x);
    }

    static public Object pop(Object x) {
        return cljSeq.pop(x);
    }

    static public Object get(Object coll, Object key) {
        return cljSeq.get(coll, key);
    }

    static Object getFrom(Object coll, Object key) {
        return cljSeq.getFrom(coll, key);
    }

    static public Object get(Object coll, Object key, Object notFound) {
        return cljSeq.get(coll, key, notFound);
    }

    static Object getFrom(Object coll, Object key, Object notFound) {
        return cljSeq.getFrom(coll, key, notFound);
    }

    static public Associative assoc(Object coll, Object key, Object val) {
        return cljSeq.assoc(coll, key, val);
    }

    static public Object contains(Object coll, Object key) {
        return cljSeq.contains(coll, key);
    }

    static public Object find(Object coll, Object key) {
        return cljSeq.find(coll, key);
    }

    //returns tail starting at val of matching key if found, else null
    static public ISeq findKey(Keyword key, ISeq keyvals) {
        return cljSeq.findKey(key, keyvals);
    }

    //takes a seq of key,val,key,val

    static public Object dissoc(Object coll, Object key) {
        return cljSeq.dissoc(coll, key);
    }

    static public Object nth(Object coll, int n) {
        return cljSeq.nth(coll, n);
    }

    static Object nthFrom(Object coll, int n) {
        return cljSeq.nthFrom(coll, n);
    }

    static public Object nth(Object coll, int n, Object notFound) {
        return cljSeq.nth(coll, n, notFound);
    }

    static Object nthFrom(Object coll, int n, Object notFound) {
        return cljSeq.nthFrom(coll, n, notFound);
    }

    static public Object assocN(int n, Object val, Object coll) {
        return cljSeq.assocN(n, val, coll);
    }

    static boolean hasTag(Object o, Object tag) {
        return runtime._hasTag(o, tag);
    }

    private boolean _hasTag(Object o, Object tag) {
        return Util.equals(tag, cljSeq.get(_meta(o), TAG_KEY));
    }

    /**
     * ********************* Boxing/casts ******************************
     */
    static public Object box(final Object x) {
        return runtime._box(x);
    }

    private Object _box(Object x) {
        return x;
    }

    static public Character box(final char x) {
        return cljChar.box(x);
    }

    static public Object box(final boolean x) {
        return cljBoolean.box(x);
    }

    static public Object box(final Boolean x) {
        return cljBoolean.box(x);// ? T : null;
    }

    static public Number box(final byte x) {
        return cljByte.box(x);//Num.from(x);
    }

    static public Number box(final short x) {
        return cljShort.box(x);//Num.from(x);
    }

    static public Number box(final int x) {
        return cljInt.box(x);//Num.from(x);
    }

    static public Number box(final long x) {
        return cljLong.box(x);//Num.from(x);
    }

    static public Number box(final float x) {
        return cljFloat.box(x);//Num.from(x);
    }

    static public Number box(final double x) {
        return cljDouble.box(x);//Num.from(x);
    }

    static public char charCast(final Object x) {
        return cljChar.charCast(x);
    }

    static public char charCast(final byte x) {
        return cljChar.charCast(x);
    }

    static public char charCast(final short x) {
        return cljChar.charCast(x);
    }

    static public char charCast(final char x) {
        return cljChar.charCast(x);
    }

    static public char charCast(final int x) {
        return cljChar.charCast(x);
    }

    static public char charCast(final long x) {
        return cljChar.charCast(x);
    }

    static public char charCast(final float x) {
        return cljChar.charCast(x);
    }

    static public char charCast(final double x) {
        return cljChar.charCast(x);
    }

    static public boolean booleanCast(Object x) {
        return cljBoolean.booleanCast(x);
    }

    static public boolean booleanCast(boolean x) {
        return cljBoolean.booleanCast(x);
    }

    static public byte byteCast(Object x) {
        return cljByte.byteCast(x);
    }

    static public byte byteCast(byte x) {
        return cljByte.byteCast(x);
    }

    static public byte byteCast(short x) {
        return cljByte.byteCast(x);
    }

    static public byte byteCast(int x) {
        return cljByte.byteCast(x);
    }

    static public byte byteCast(long x) {
        return cljByte.byteCast(x);
    }

    static public byte byteCast(float x) {
        return cljByte.byteCast(x);
    }

    static public byte byteCast(double x) {
        return cljByte.byteCast(x);
    }

    static public short shortCast(Object x) {
        return cljShort.shortCast(x);
    }

    static public short shortCast(byte x) {
        return cljShort.shortCast(x);
    }

    static public short shortCast(short x) {
        return cljShort.shortCast(x);
    }

    static public short shortCast(int x) {
        return cljShort.shortCast(x);
    }

    static public short shortCast(long x) {
        return cljShort.shortCast(x);
    }

    static public short shortCast(float x) {
        return cljShort.shortCast(x);
    }

    static public short shortCast(double x) {
        return cljShort.shortCast(x);
    }

    static public int intCast(Object x) {
        return cljInt.intCast(x);
    }

    static public int intCast(char x) {
        return cljInt.intCast(x);
    }

    static public int intCast(byte x) {
        return cljInt.intCast(x);
    }

    static public int intCast(short x) {
        return cljInt.intCast(x);
    }

    static public int intCast(int x) {
        return cljInt.intCast(x);
    }

    static public int intCast(float x) {
        return cljInt.intCast(x);
    }

    static public int intCast(long x) {
        return cljInt.intCast(x);
    }

    static public int intCast(double x) {
        return cljInt.intCast(x);
    }

    static public long longCast(Object x) {
        return cljLong.longCast(x);
    }

    static public long longCast(byte x) {
        return cljLong.longCast(x);
    }

    static public long longCast(short x) {
        return cljLong.longCast(x);
    }

    static public long longCast(int x) {
        return cljLong.longCast(x);
    }

    static public long longCast(float x) {
        return cljLong.longCast(x);
    }

    static public long longCast(long x) {
        return cljLong.longCast(x);
    }

    static public long longCast(double x) {
        return cljLong.longCast(x);
    }

    static public float floatCast(Object x) {
        return cljFloat.floatCast(x);
    }

    static public float floatCast(byte x) {
        return cljFloat.floatCast(x);
    }

    static public float floatCast(short x) {
        return cljFloat.floatCast(x);
    }

    static public float floatCast(int x) {
        return cljFloat.floatCast(x);
    }

    static public float floatCast(float x) {
        return cljFloat.floatCast(x);
    }

    static public float floatCast(long x) {
        return cljFloat.floatCast(x);
    }

    static public float floatCast(double x) {
        return cljFloat.floatCast(x);
    }

    static public double doubleCast(Object x) {
        return cljDouble.doubleCast(x);
    }

    static public double doubleCast(byte x) {
        return cljDouble.doubleCast(x);
    }

    static public double doubleCast(short x) {
        return cljDouble.doubleCast(x);
    }

    static public double doubleCast(int x) {
        return cljDouble.doubleCast(x);
    }

    static public double doubleCast(float x) {
        return cljDouble.doubleCast(x);
    }

    static public double doubleCast(long x) {
        return cljDouble.doubleCast(x);
    }

    static public double doubleCast(double x) {
        return cljDouble.doubleCast(x);
    }

    static public byte uncheckedByteCast(Object x) {
        return ((Number) x).byteValue();
    }

    static public byte uncheckedByteCast(byte x) {
        return x;
    }

    static public byte uncheckedByteCast(short x) {
        return (byte) x;
    }

    static public byte uncheckedByteCast(int x) {
        return (byte) x;
    }

    static public byte uncheckedByteCast(long x) {
        return (byte) x;
    }

    static public byte uncheckedByteCast(float x) {
        return (byte) x;
    }

    static public byte uncheckedByteCast(double x) {
        return (byte) x;
    }

    static public short uncheckedShortCast(Object x) {
        return ((Number) x).shortValue();
    }

    static public short uncheckedShortCast(byte x) {
        return x;
    }

    static public short uncheckedShortCast(short x) {
        return x;
    }

    static public short uncheckedShortCast(int x) {
        return (short) x;
    }

    static public short uncheckedShortCast(long x) {
        return (short) x;
    }

    static public short uncheckedShortCast(float x) {
        return (short) x;
    }

    static public short uncheckedShortCast(double x) {
        return (short) x;
    }

    static public char uncheckedCharCast(Object x) {
        if (x instanceof Character) {
            return ((Character) x).charValue();
        }
        return (char) ((Number) x).longValue();
    }

    static public char uncheckedCharCast(byte x) {
        return (char) x;
    }

    static public char uncheckedCharCast(short x) {
        return (char) x;
    }

    static public char uncheckedCharCast(char x) {
        return x;
    }

    static public char uncheckedCharCast(int x) {
        return (char) x;
    }

    static public char uncheckedCharCast(long x) {
        return (char) x;
    }

    static public char uncheckedCharCast(float x) {
        return (char) x;
    }

    static public char uncheckedCharCast(double x) {
        return (char) x;
    }

    static public int uncheckedIntCast(Object x) {
        if (x instanceof Number)
            return ((Number) x).intValue();
        return ((Character) x).charValue();
    }

    static public int uncheckedIntCast(byte x) {
        return x;
    }

    static public int uncheckedIntCast(short x) {
        return x;
    }

    static public int uncheckedIntCast(char x) {
        return x;
    }

    static public int uncheckedIntCast(int x) {
        return x;
    }

    static public int uncheckedIntCast(long x) {
        return (int) x;
    }

    static public int uncheckedIntCast(float x) {
        return (int) x;
    }

    static public int uncheckedIntCast(double x) {
        return (int) x;
    }

    static public long uncheckedLongCast(Object x) {
        return ((Number) x).longValue();
    }

    static public long uncheckedLongCast(byte x) {
        return x;
    }

    static public long uncheckedLongCast(short x) {
        return x;
    }

    static public long uncheckedLongCast(int x) {
        return x;
    }

    static public long uncheckedLongCast(long x) {
        return x;
    }

    static public long uncheckedLongCast(float x) {
        return (long) x;
    }

    static public long uncheckedLongCast(double x) {
        return (long) x;
    }

    static public float uncheckedFloatCast(Object x) {
        return ((Number) x).floatValue();
    }

    static public float uncheckedFloatCast(byte x) {
        return x;
    }

    static public float uncheckedFloatCast(short x) {
        return x;
    }

    static public float uncheckedFloatCast(int x) {
        return x;
    }

    static public float uncheckedFloatCast(long x) {
        return x;
    }

    static public float uncheckedFloatCast(float x) {
        return x;
    }

    static public float uncheckedFloatCast(double x) {
        return (float) x;
    }

    static public double uncheckedDoubleCast(Object x) {
        return ((Number) x).doubleValue();
    }

    static public double uncheckedDoubleCast(byte x) {
        return x;
    }

    static public double uncheckedDoubleCast(short x) {
        return x;
    }

    static public double uncheckedDoubleCast(int x) {
        return x;
    }

    static public double uncheckedDoubleCast(long x) {
        return x;
    }

    static public double uncheckedDoubleCast(float x) {
        return x;
    }

    static public double uncheckedDoubleCast(double x) {
        return x;
    }

    static public IPersistentMap map(Object... init) {
        if (init == null)
            return PersistentArrayMap.EMPTY;
        else if (init.length <= PersistentArrayMap.HASHTABLE_THRESHOLD)
            return PersistentArrayMap.createWithCheck(init);
        return PersistentHashMap.createWithCheck(init);
    }

    static public IPersistentMap mapUniqueKeys(Object... init) {
        if (init == null)
            return PersistentArrayMap.EMPTY;
        else if (init.length <= PersistentArrayMap.HASHTABLE_THRESHOLD)
            return new PersistentArrayMap(init);
        return PersistentHashMap.create(init);
    }

    static public IPersistentSet set(Object... init) {
        return PersistentHashSet.createWithCheck(init);
    }

    static public IPersistentVector vector(Object... init) {
        return LazilyPersistentVector.createOwning(init);
    }

    static public IPersistentVector subvec(IPersistentVector v, int start, int end) {
        if (end < start || start < 0 || end > v.count())
            throw new IndexOutOfBoundsException();
        if (start == end)
            return PersistentVector.EMPTY;
        return new APersistentVector.SubVector(null, v, start, end);
    }

    /**
     * **************************************** list support *******************************
     */


    static public ISeq list() {
        return null;
    }

    static public ISeq list(Object arg1) {
        return new PersistentList(arg1);
    }

    static public ISeq list(Object arg1, Object arg2) {
        return listStar(arg1, arg2, null);
    }

    static public ISeq list(Object arg1, Object arg2, Object arg3) {
        return listStar(arg1, arg2, arg3, null);
    }

    static public ISeq list(Object arg1, Object arg2, Object arg3, Object arg4) {
        return listStar(arg1, arg2, arg3, arg4, null);
    }

    static public ISeq list(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
        return listStar(arg1, arg2, arg3, arg4, arg5, null);
    }

    static public ISeq listStar(Object arg1, ISeq rest) {
        return (ISeq) cons(arg1, rest);
    }

    static public ISeq listStar(Object arg1, Object arg2, ISeq rest) {
        return (ISeq) cons(arg1, cons(arg2, rest));
    }

    static public ISeq listStar(Object arg1, Object arg2, Object arg3, ISeq rest) {
        return (ISeq) cons(arg1, cons(arg2, cons(arg3, rest)));
    }

    static public ISeq listStar(Object arg1, Object arg2, Object arg3, Object arg4, ISeq rest) {
        return (ISeq) cons(arg1, cons(arg2, cons(arg3, cons(arg4, rest))));
    }

    static public ISeq listStar(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, ISeq rest) {
        return (ISeq) cons(arg1, cons(arg2, cons(arg3, cons(arg4, cons(arg5, rest)))));
    }

    static public ISeq arrayToList(Object[] a) {
        ISeq ret = null;
        for (int i = a.length - 1; i >= 0; --i)
            ret = (ISeq) cons(a[i], ret);
        return ret;
    }

    static public Object[] object_array(Object sizeOrSeq) {
        if (sizeOrSeq instanceof Number)
            return new Object[((Number) sizeOrSeq).intValue()];
        else {
            ISeq s = RT.seq(sizeOrSeq);
            int size = RT.count(s);
            Object[] ret = new Object[size];
            for (int i = 0; i < size && s != null; i++, s = s.next())
                ret[i] = s.first();
            return ret;
        }
    }

    static public Object[] toArray(Object coll) {
        if (coll == null)
            return EMPTY_ARRAY;
        else if (coll instanceof Object[])
            return (Object[]) coll;
        else if (coll instanceof Collection)
            return ((Collection) coll).toArray();
        else if (coll instanceof Iterable) {
            ArrayList ret = new ArrayList();
            for (Object o : (Iterable) coll)
                ret.add(o);
            return ret.toArray();
        } else if (coll instanceof Map)
            return ((Map) coll).entrySet().toArray();
        else if (coll instanceof String) {
            char[] chars = ((String) coll).toCharArray();
            Object[] ret = new Object[chars.length];
            for (int i = 0; i < chars.length; i++)
                ret[i] = chars[i];
            return ret;
        } else if (coll.getClass().isArray()) {
            ISeq s = (seq(coll));
            Object[] ret = new Object[count(s)];
            for (int i = 0; i < ret.length; i++, s = s.next())
                ret[i] = s.first();
            return ret;
        } else
            throw Util.runtimeException("Unable to convert: " + coll.getClass() + " to Object[]");
    }

    static public Object[] seqToArray(ISeq seq) {
        int len = length(seq);
        Object[] ret = new Object[len];
        for (int i = 0; seq != null; ++i, seq = seq.next())
            ret[i] = seq.first();
        return ret;
    }

    // supports java Collection.toArray(T[])
    static public Object[] seqToPassedArray(ISeq seq, Object[] passed) {
        Object[] dest = passed;
        int len = count(seq);
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

    static public Object seqToTypedArray(ISeq seq) {
        Class type = (seq != null && seq.first() != null) ? seq.first().getClass() : Object.class;
        return seqToTypedArray(type, seq);
    }

    static public Object seqToTypedArray(Class type, ISeq seq) {
        Object ret = Array.newInstance(type, length(seq));
        if (type == Integer.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, cljInt.intCast(seq.first()));
            }
        } else if (type == Byte.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, cljByte.byteCast(seq.first()));
            }
        } else if (type == Float.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, cljFloat.floatCast(seq.first()));
            }
        } else if (type == Short.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, cljShort.shortCast(seq.first()));
            }
        } else if (type == Character.TYPE) {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, cljChar.charCast(seq.first()));
            }
        } else {
            for (int i = 0; seq != null; ++i, seq = seq.next()) {
                Array.set(ret, i, seq.first());
            }
        }
        return ret;
    }

    static public int length(ISeq list) {
        int i = 0;
        for (ISeq c = list; c != null; c = c.next()) {
            i++;
        }
        return i;
    }

    static public int boundedLength(ISeq list, int limit) {
        int i = 0;
        for (ISeq c = list; c != null && i <= limit; c = c.next()) {
            i++;
        }
        return i;
    }

    static Character readRet(int ret) {
        if (ret == -1) {
            return null;
        }
        return cljChar.box((char) ret);
    }

///////////////////////////////// reader support ////////////////////////////////

    static public Character readChar(Reader r) throws IOException {
        int ret = r.read();
        return readRet(ret);
    }

    static public Character peekChar(Reader r) throws IOException {
        int ret;
        if (r instanceof PushbackReader) {
            ret = r.read();
            ((PushbackReader) r).unread(ret);
        } else {
            r.mark(1);
            ret = r.read();
            r.reset();
        }

        return readRet(ret);
    }

    static public int getLineNumber(Reader r) {
        if (r instanceof LineNumberingPushbackReader)
            return ((LineNumberingPushbackReader) r).getLineNumber();
        return 0;
    }

    static public int getColumnNumber(Reader r) {
        if (r instanceof LineNumberingPushbackReader)
            return ((LineNumberingPushbackReader) r).getColumnNumber();
        return 0;
    }

    static public LineNumberingPushbackReader getLineNumberingReader(Reader r) {
        if (isLineNumberingReader(r))
            return (LineNumberingPushbackReader) r;
        return new LineNumberingPushbackReader(r);
    }

    static public boolean isLineNumberingReader(Reader r) {
        return r instanceof LineNumberingPushbackReader;
    }

    static public boolean isReduced(Object r) {
        return r instanceof Reduced;
    }

    static public String resolveClassNameInContext(String className) {
        //todo - look up in context var
        return className;
    }

    static public boolean suppressRead() {
        return cljBoolean.booleanCast(SUPPRESS_READ.deref());
    }

    static public String printString(Object x) {
        try {
            StringWriter sw = new StringWriter();
            print(x, sw);
            return sw.toString();
        } catch (Exception e) {
            throw Util.sneakyThrow(e);
        }
    }

    static public Object readString(String s) {
        return readString(s, null);
    }

    static public Object readString(String s, Object opts) {
        PushbackReader r = new PushbackReader(new StringReader(s));
        return LispReader.read(r, opts);
    }

    static public void print(Object x, Writer w) throws IOException {
        //call multimethod
        if (PRINT_INITIALIZED.isBound() && cljBoolean.booleanCast(PRINT_INITIALIZED.deref()))
            PR_ON.invoke(x, w);
//*
        else {
            boolean readably = cljBoolean.booleanCast(PRINT_READABLY.deref());
            if (x instanceof Obj) {
                Obj o = (Obj) x;
                if (RT.count(o.meta()) > 0 &&
                        ((readably && cljBoolean.booleanCast(PRINT_META.deref()))
                                || cljBoolean.booleanCast(PRINT_DUP.deref()))) {
                    IPersistentMap meta = o.meta();
                    w.write("#^");
                    if (meta.count() == 1 && meta.containsKey(TAG_KEY))
                        print(meta.valAt(TAG_KEY), w);
                    else
                        print(meta, w);
                    w.write(' ');
                }
            }
            if (x == null)
                w.write("nil");
            else if (x instanceof ISeq || x instanceof IPersistentList) {
                w.write('(');
                printInnerSeq(seq(x), w);
                w.write(')');
            } else if (x instanceof String) {
                String s = (String) x;
                if (!readably)
                    w.write(s);
                else {
                    w.write('"');
                    //w.write(x.toString());
                    for (int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        switch (c) {
                            case '\n':
                                w.write("\\n");
                                break;
                            case '\t':
                                w.write("\\t");
                                break;
                            case '\r':
                                w.write("\\r");
                                break;
                            case '"':
                                w.write("\\\"");
                                break;
                            case '\\':
                                w.write("\\\\");
                                break;
                            case '\f':
                                w.write("\\f");
                                break;
                            case '\b':
                                w.write("\\b");
                                break;
                            default:
                                w.write(c);
                        }
                    }
                    w.write('"');
                }
            } else if (x instanceof IPersistentMap) {
                w.write('{');
                for (ISeq s = seq(x); s != null; s = s.next()) {
                    IMapEntry e = (IMapEntry) s.first();
                    print(e.key(), w);
                    w.write(' ');
                    print(e.val(), w);
                    if (s.next() != null)
                        w.write(", ");
                }
                w.write('}');
            } else if (x instanceof IPersistentVector) {
                IPersistentVector a = (IPersistentVector) x;
                w.write('[');
                for (int i = 0; i < a.count(); i++) {
                    print(a.nth(i), w);
                    if (i < a.count() - 1)
                        w.write(' ');
                }
                w.write(']');
            } else if (x instanceof IPersistentSet) {
                w.write("#{");
                for (ISeq s = seq(x); s != null; s = s.next()) {
                    print(s.first(), w);
                    if (s.next() != null)
                        w.write(" ");
                }
                w.write('}');
            } else if (x instanceof Character) {
                char c = ((Character) x).charValue();
                if (!readably)
                    w.write(c);
                else {
                    w.write('\\');
                    switch (c) {
                        case '\n':
                            w.write("newline");
                            break;
                        case '\t':
                            w.write("tab");
                            break;
                        case ' ':
                            w.write("space");
                            break;
                        case '\b':
                            w.write("backspace");
                            break;
                        case '\f':
                            w.write("formfeed");
                            break;
                        case '\r':
                            w.write("return");
                            break;
                        default:
                            w.write(c);
                    }
                }
            } else if (x instanceof Class) {
                w.write("#=");
                w.write(((Class) x).getName());
            } else if (x instanceof BigDecimal && readably) {
                w.write(x.toString());
                w.write('M');
            } else if (x instanceof BigInt && readably) {
                w.write(x.toString());
                w.write('N');
            } else if (x instanceof BigInteger && readably) {
                w.write(x.toString());
                w.write("BIGINT");
            } else if (x instanceof Var) {
                Var v = (Var) x;
                w.write("#=(var " + v.ns.name + "/" + v.sym + ")");
            } else if (x instanceof Pattern) {
                Pattern p = (Pattern) x;
                w.write("#\"" + p.pattern() + "\"");
            } else w.write(x.toString());
        }
        //*/
    }

    private static void printInnerSeq(ISeq x, Writer w) throws IOException {
        for (ISeq s = x; s != null; s = s.next()) {
            print(s.first(), w);
            if (s.next() != null)
                w.write(' ');
        }
    }

    static public void formatAesthetic(Writer w, Object obj) throws IOException {
        if (obj == null)
            w.write("null");
        else
            w.write(obj.toString());
    }

    static public void formatStandard(Writer w, Object obj) throws IOException {
        if (obj == null)
            w.write("null");
        else if (obj instanceof String) {
            w.write('"');
            w.write((String) obj);
            w.write('"');
        } else if (obj instanceof Character) {
            w.write('\\');
            char c = ((Character) obj).charValue();
            switch (c) {
                case '\n':
                    w.write("newline");
                    break;
                case '\t':
                    w.write("tab");
                    break;
                case ' ':
                    w.write("space");
                    break;
                case '\b':
                    w.write("backspace");
                    break;
                case '\f':
                    w.write("formfeed");
                    break;
                default:
                    w.write(c);
            }
        } else
            w.write(obj.toString());
    }

    static public Object format(Object o, String s, Object... args) throws IOException {
        Writer w;
        if (o == null)
            w = new StringWriter();
        else if (Util.equals(o, T))
            w = (Writer) OUT.deref();
        else
            w = (Writer) o;
        doFormat(w, s, ArraySeq.create(args));
        if (o == null)
            return w.toString();
        return null;
    }

    static public ISeq doFormat(Writer w, String s, ISeq args) throws IOException {
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i++);
            switch (Character.toLowerCase(c)) {
                case '~':
                    char d = s.charAt(i++);
                    switch (Character.toLowerCase(d)) {
                        case '%':
                            w.write('\n');
                            break;
                        case 't':
                            w.write('\t');
                            break;
                        case 'a':
                            if (args == null)
                                throw new IllegalArgumentException("Missing argument");
                            RT.formatAesthetic(w, RT.first(args));
                            args = RT.next(args);
                            break;
                        case 's':
                            if (args == null)
                                throw new IllegalArgumentException("Missing argument");
                            RT.formatStandard(w, RT.first(args));
                            args = RT.next(args);
                            break;
                        case '{':
                            int j = s.indexOf("~}", i);    //note - does not nest
                            if (j == -1)
                                throw new IllegalArgumentException("Missing ~}");
                            String subs = s.substring(i, j);
                            for (ISeq sargs = RT.seq(RT.first(args)); sargs != null; )
                                sargs = doFormat(w, subs, sargs);
                            args = RT.next(args);
                            i = j + 2; //skip ~}
                            break;
                        case '^':
                            if (args == null)
                                return null;
                            break;
                        case '~':
                            w.write('~');
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported ~ directive: " + d);
                    }
                    break;
                default:
                    w.write(c);
            }
        }
        return args;
    }

    static public Object[] setValues(Object... vals) {
        //ThreadLocalData.setValues(vals);
        if (vals.length > 0)
            return vals;//[0];
        return null;
    }
///////////////////////////////// values //////////////////////////

    static public ClassLoader makeClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Var.pushThreadBindings(RT.map(USE_CONTEXT_CLASSLOADER, RT.T));
//			getRootClassLoader();
                    return new DynamicClassLoader(baseLoader());
                } finally {
                    Var.popThreadBindings();
                }
            }
        });
    }

    static public ClassLoader baseLoader() {
        if (Compiler.LOADER.isBound())
            return (ClassLoader) Compiler.LOADER.deref();
        else if (cljBoolean.booleanCast(USE_CONTEXT_CLASSLOADER.deref()))
            return Thread.currentThread().getContextClassLoader();
        return Compiler.class.getClassLoader();
    }

    static public InputStream resourceAsStream(ClassLoader loader, String name) {
        if (loader == null) {
            return ClassLoader.getSystemResourceAsStream(name);
        } else {
            return loader.getResourceAsStream(name);
        }
    }

    static public URL getResource(ClassLoader loader, String name) {
        if (loader == null) {
            return ClassLoader.getSystemResource(name);
        } else {
            return loader.getResource(name);
        }
    }

    static public Class classForName(String name, boolean load, ClassLoader loader) {

        try {
            Class c = null;
            if (!(loader instanceof DynamicClassLoader))
                c = DynamicClassLoader.findInMemoryClass(name);
            if (c != null)
                return c;
            return Class.forName(name, load, loader);
        } catch (ClassNotFoundException e) {
            throw Util.sneakyThrow(e);
        }
    }

    static public Class classForName(String name) {
        return classForName(name, true, baseLoader());
    }

    static public Class classForNameNonLoading(String name) {
        return classForName(name, false, baseLoader());
    }

    static public Class loadClassForName(String name) {
        try {
            classForNameNonLoading(name);
        } catch (Exception e) {
            if (e instanceof ClassNotFoundException)
                return null;
            else
                throw Util.sneakyThrow(e);
        }
        return classForName(name);
    }

    static public float aget(float[] xs, int i) {
        return xs[i];
    }

    static public float aset(float[] xs, int i, float v) {
        xs[i] = v;
        return v;
    }

    static public int alength(float[] xs) {
        return xs.length;
    }

    static public float[] aclone(float[] xs) {
        return xs.clone();
    }

    static public double aget(double[] xs, int i) {
        return xs[i];
    }

    static public double aset(double[] xs, int i, double v) {
        xs[i] = v;
        return v;
    }

    static public int alength(double[] xs) {
        return xs.length;
    }

    static public double[] aclone(double[] xs) {
        return xs.clone();
    }

    static public int aget(int[] xs, int i) {
        return xs[i];
    }

    static public int aset(int[] xs, int i, int v) {
        xs[i] = v;
        return v;
    }

    static public int alength(int[] xs) {
        return xs.length;
    }

    static public int[] aclone(int[] xs) {
        return xs.clone();
    }

    static public long aget(long[] xs, int i) {
        return xs[i];
    }

    static public long aset(long[] xs, int i, long v) {
        xs[i] = v;
        return v;
    }

    static public int alength(long[] xs) {
        return xs.length;
    }

    static public long[] aclone(long[] xs) {
        return xs.clone();
    }

    static public char aget(char[] xs, int i) {
        return xs[i];
    }

    static public char aset(char[] xs, int i, char v) {
        xs[i] = v;
        return v;
    }

    static public int alength(char[] xs) {
        return xs.length;
    }

    static public char[] aclone(char[] xs) {
        return xs.clone();
    }

    static public byte aget(byte[] xs, int i) {
        return xs[i];
    }

    static public byte aset(byte[] xs, int i, byte v) {
        xs[i] = v;
        return v;
    }

    static public int alength(byte[] xs) {
        return xs.length;
    }

    static public byte[] aclone(byte[] xs) {
        return xs.clone();
    }

    static public short aget(short[] xs, int i) {
        return xs[i];
    }

    static public short aset(short[] xs, int i, short v) {
        xs[i] = v;
        return v;
    }

    static public int alength(short[] xs) {
        return xs.length;
    }

    static public short[] aclone(short[] xs) {
        return xs.clone();
    }

    static public boolean aget(boolean[] xs, int i) {
        return xs[i];
    }

    static public boolean aset(boolean[] xs, int i, boolean v) {
        xs[i] = v;
        return v;
    }

    static public int alength(boolean[] xs) {
        return xs.length;
    }

    static public boolean[] aclone(boolean[] xs) {
        return xs.clone();
    }

    static public Object aget(Object[] xs, int i) {
        return xs[i];
    }

    static public Object aset(Object[] xs, int i, Object v) {
        xs[i] = v;
        return v;
    }

    static public int alength(Object[] xs) {
        return xs.length;
    }

    static public Object[] aclone(Object[] xs) {
        return xs.clone();
    }

    private static final class DefaultComparator implements Comparator, Serializable {
        public int compare(Object o1, Object o2) {
            return Util.compare(o1, o2);
        }

        private Object readResolve() throws ObjectStreamException {
            // ensures that we aren't hanging onto a new default comparator for every
            // sorted set, etc., we deserialize
            return DEFAULT_COMPARATOR;
        }
    }
}

