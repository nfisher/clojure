package clojure.lang;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeywordTable {
	private static final KeywordTable _instance = new KeywordTable();
	private final ConcurrentHashMap<Symbol, Reference<Keyword>> table;
	private final ReferenceQueue rq;

	protected KeywordTable() {
		this.table = new ConcurrentHashMap<Symbol, Reference<Keyword>>();
		this.rq = new ReferenceQueue();
	}

	public static KeywordTable instance() {
		return _instance;
	}

	public static Keyword find(Symbol sym) {
		Reference<Keyword> ref = instance().get(sym);
		if (ref != null) {
			return ref.get();
		} else {
			return null;
		}
	}

	public static Keyword find(String ns, String name) {
		return find(Symbol.intern(ns, name));
	}

	public static Keyword find(String nsname) {
		return find(Symbol.intern(nsname));
	}

	public static Keyword intern(Symbol sym) {
	    KeywordTable table = instance();

		Keyword k = null;
		Reference<Keyword> existingRef = table.get(sym);
		if (existingRef == null) {
			table.clear();
			if (sym.meta() != null) {
				sym = (Symbol) sym.withMeta(null);
			}
			k = new Keyword(sym);
			existingRef = table.putIfAbsent(sym, k);
		}
		if (existingRef == null) {
			return k;
		}
		Keyword existingk = existingRef.get();
		if (existingk != null) {
			return existingk;
		}
		//entry died in the interim, do over
		table.remove(sym, existingRef);
		return intern(sym);
	}

	public static Keyword intern(String ns, String name) {
		return intern(Symbol.intern(ns, name));
	}

	public static Keyword intern(String nsname) {
		return intern(Symbol.intern(nsname));
	}

	public Reference<Keyword> get(final Symbol sym) {
		return table.get(sym);
	}

	public boolean remove(final Symbol sym, final Reference<Keyword> ref) {
		return table.remove(sym, ref);
	}

	public Reference<Keyword> putIfAbsent(final Symbol sym, final Keyword key) {
		return table.putIfAbsent(sym, new WeakReference<Keyword>(key, rq));
	}

	public void clear() {
		if (rq.poll() != null) {
			while (rq.poll() != null) {
			}
			for (Map.Entry<Symbol, Reference<Keyword>> e : table.entrySet()) {
				final Reference<Keyword> val = e.getValue();
				if (val != null && val.get() == null) {
					table.remove(e.getKey(), val);
				}
			}
		}
	}
}
