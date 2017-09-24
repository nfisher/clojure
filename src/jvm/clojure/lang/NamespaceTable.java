package clojure.lang;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class NamespaceTable {
    private static final NamespaceTable _instance = new NamespaceTable();

    private final ConcurrentHashMap<Symbol, Namespace> namespaces;

    private NamespaceTable() {
        this.namespaces = new ConcurrentHashMap<Symbol, Namespace>();
    }

    public static NamespaceTable instance() {
        return _instance;
    }

    public static ISeq all() {
        return RT.seq(instance().values());
    }

    public static Namespace findOrCreate(final Symbol name) {
        NamespaceTable namespaces = instance();
        Namespace ns = namespaces.get(name);
        if (ns != null) {
            return ns;
        }
        final Namespace newns = new Namespace(name);
        ns = namespaces.putIfAbsent(name, newns);
        return ns == null ? newns : ns;
    }

    public static Namespace find(final Symbol name) {
        return instance().get(name);
    }

    public Collection<Namespace> values() {
        return namespaces.values();
    }

    public Namespace get(final Symbol sym) {
        return namespaces.get(sym);
    }

    public Namespace remove(final Symbol sym) {
        if (sym.equals(RT.CLOJURE_NS.name)) {
            throw new IllegalArgumentException("Cannot remove clojure namespace");
        }
        return namespaces.remove(sym);
    }

    public Namespace putIfAbsent(final Symbol sym, final Namespace ns) {
        return namespaces.putIfAbsent(sym, ns);
    }
}
