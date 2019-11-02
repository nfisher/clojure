package clojure.lang.core;

import clojure.lang.*;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Vars {
    public static final Boolean T = Boolean.TRUE; // Keyword.intern(Symbol.intern(null, "t"));
    public static final Boolean F = Boolean.FALSE; // Keyword.intern(Symbol.intern(null, "t"));
    public static final Namespace CLOJURE_NS = Namespace.findOrCreate(Symbol.intern("clojure.core"));
    public static final Var PR_ON = Var.intern(CLOJURE_NS, Symbol.intern("pr-on"));
    public static final Var PRINT_INITIALIZED = Var.intern(CLOJURE_NS, Symbol.intern("print-initialized"));
    public static final Var FN_LOADER_VAR =
        Var.intern(CLOJURE_NS, Symbol.intern("*fn-loader*"), null).setDynamic();
    public static final Var NS_VAR = Var.intern(CLOJURE_NS, Symbol.intern("ns"), F);
    public static final Var IN_NS_VAR = Var.intern(CLOJURE_NS, Symbol.intern("in-ns"), F);
    public static final Var READER_RESOLVER =
            Var.intern(CLOJURE_NS, Symbol.intern("*reader-resolver*"), null).setDynamic();
    public static final Var ALLOW_UNRESOLVED_VARS =
                Var.intern(CLOJURE_NS, Symbol.intern("*allow-unresolved-vars*"), F).setDynamic();
    public static final Var WARN_ON_REFLECTION =
                    Var.intern(CLOJURE_NS, Symbol.intern("*warn-on-reflection*"), F).setDynamic();
    public static final Var PRINT_DUP = Var.intern(CLOJURE_NS, Symbol.intern("*print-dup*"), F).setDynamic();
    // final static public Var CURRENT_MODULE = Var.intern(Symbol.intern("clojure.core",
    // "current-module"),
    //                                                    Module.findOrCreateModule("clojure/user"));
    public static final Var PRINT_READABLY =
        Var.intern(CLOJURE_NS, Symbol.intern("*print-readably*"), T).setDynamic();
    public static final Var PRINT_META =
            Var.intern(CLOJURE_NS, Symbol.intern("*print-meta*"), F).setDynamic();
    static final Var FLUSH_ON_NEWLINE =
        Var.intern(CLOJURE_NS, Symbol.intern("*flush-on-newline*"), T).setDynamic();
    public static final Var CMD_LINE_ARGS =
            Var.intern(CLOJURE_NS, Symbol.intern("*command-line-args*"), null).setDynamic();
    // symbol
    public static final Var CURRENT_NS =
        Var.intern(CLOJURE_NS, Symbol.intern("*ns*"), CLOJURE_NS).setDynamic();
    // boolean
    public static final Var UNCHECKED_MATH =
        Var.intern(
                CLOJURE_NS,
                Symbol.intern("*unchecked-math*"),
                Boolean.FALSE)
            .setDynamic();
    public static final Var USE_CONTEXT_CLASSLOADER =
        Var.intern(CLOJURE_NS, Symbol.intern("*use-context-classloader*"), T).setDynamic();
    public static final Var MATH_CONTEXT =
        Var.intern(CLOJURE_NS, Symbol.intern("*math-context*"), null).setDynamic();
    public static final Var ASSERT =
        Var.intern(CLOJURE_NS, Symbol.intern("*assert*"), T).setDynamic();
    public static final Var SUPPRESS_READ =
        Var.intern(CLOJURE_NS, Symbol.intern("*suppress-read*"), null).setDynamic();
    public static final Var DEFAULT_DATA_READERS =
        Var.intern(CLOJURE_NS, Symbol.intern("default-data-readers"), RT.map());
    public static final Var DEFAULT_DATA_READER_FN =
        Var.intern(CLOJURE_NS, Symbol.intern("*default-data-reader-fn*"), RT.map()).setDynamic();
    public static final Var DATA_READERS =
        Var.intern(CLOJURE_NS, Symbol.intern("*data-readers*"), RT.map()).setDynamic();
    public static final Var AGENT =
        Var.intern(CLOJURE_NS, Symbol.intern("*agent*"), null).setDynamic();
    public static final Var ERR =
        Var.intern(
                CLOJURE_NS,
                Symbol.intern("*err*"),
                new PrintWriter(new OutputStreamWriter(System.err), true))
            .setDynamic();
    public static final Var IN =
        Var.intern(
                CLOJURE_NS,
                Symbol.intern("*in*"),
                new LineNumberingPushbackReader(new InputStreamReader(System.in)))
            .setDynamic();
    public static final Var OUT =
        Var.intern(CLOJURE_NS, Symbol.intern("*out*"), new OutputStreamWriter(System.out))
            .setDynamic();
}
