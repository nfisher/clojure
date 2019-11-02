package clojure.lang.core;

import clojure.lang.Keyword;

public class Keywords {
    public static final Keyword TAG_KEY = Keyword.intern(null, "tag");
    public static final Keyword CONST_KEY = Keyword.intern(null, "const");
    public static Keyword EVAL_FILE_KEY = Keyword.intern("clojure.core", "eval-file");
    public static Keyword LINE_KEY = Keyword.intern(null, "line");
    public static Keyword COLUMN_KEY = Keyword.intern(null, "column");
    public static Keyword FILE_KEY = Keyword.intern(null, "file");
    public static Keyword DECLARED_KEY = Keyword.intern(null, "declared");
    public static Keyword DOC_KEY = Keyword.intern(null, "doc");
}
