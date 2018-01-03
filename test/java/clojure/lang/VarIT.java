package clojure.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

class NewVar implements Callable<Integer> {
    private final int max;
    private final int id;

    public NewVar(final int max, final int id) {
        this.max = max;
        this.id = id;
    }

    @Override
    public Integer call() throws Exception {
        final Symbol nsSym = Symbol.intern("var-new-test");
        final Namespace ns = Namespace.findOrCreate(nsSym);
        int i = 0;
        int revCount;
        Var v = null;

        synchronized(Var.class) {
           revCount = Var.rev;
        }

        for (; i < max; i++) {
            final Symbol s = Symbol.intern("t" + id + "s" + i);

            v = new Var(ns, s, null);
        }

        return revCount;
    }
}

class UnbindVar implements Callable<Integer> {
    private final int max;
    private final int id;

    UnbindVar(final int max, int id) {
        this.max = max;
        this.id = id;
    }

    @Override
    public Integer call() throws Exception {
        final Symbol nsSym = Symbol.intern("var-unbind-test");
        final Namespace ns = Namespace.findOrCreate(nsSym);
        final Symbol s = Symbol.intern("t" + id);
        final Var v = new Var(ns, s, null);
        int i = 0;
        int revCount;

        synchronized(Var.class) {
            revCount = Var.rev;
        }

        for (; i < max; i++) {
            v.unbindRoot();
        }

        return revCount;
    }
}

public class VarIT {
    // 10s timeout
    @Test(timeout = 10*1000)
    public void Test_var_constructor_should_synchronise_rev() throws InterruptedException, ExecutionException {
        final int nThreads = (int) (Runtime.getRuntime().availableProcessors() * 1.25);
        final int max = 1000000 / nThreads;
        final ExecutorService svc = Executors.newFixedThreadPool(nThreads);
        final ArrayList<Callable<Integer>> callables = new ArrayList<Callable<Integer>>(nThreads);
        final int expected = max * nThreads;

        for (int i = 0; i < nThreads; i++) {
            callables.add(new NewVar(max, i));
        }

        final List<Future<Integer>> futures = svc.invokeAll(callables);
        svc.shutdownNow();

        int otherAllocations = -1;
        for (Future<Integer> f : futures) {
            final int c = f.get();
            if (c > otherAllocations) {
                otherAllocations = c;
            }
        }

        // without integer roll-over a miscount will generally be less than expected.
        // this could be a flakey assertion depending on a number of factors (e.g. JVM, cores, threads).
        assertThat("Var.rev does not contain the expected revision", Var.rev, is(greaterThan(expected)));
    }

    @Test(timeout = 10 * 1000)
    public void Test_var_unbindRoot_should_synchronise_rev() throws ExecutionException, InterruptedException {
        final int nThreads = (int) (Runtime.getRuntime().availableProcessors() * 1.25);
        final int max = 1000000 / nThreads;
        final ExecutorService svc = Executors.newFixedThreadPool(nThreads);
        final ArrayList<Callable<Integer>> callables = new ArrayList<Callable<Integer>>(nThreads);
        final int expected = max * nThreads;

        for (int i = 0; i < nThreads; i++) {
            callables.add(new UnbindVar(max, i));
        }

        final List<Future<Integer>> futures = svc.invokeAll(callables);
        svc.shutdownNow();

        int otherAllocations = -1;
        for (Future<Integer> f : futures) {
            final int c = f.get();
            if (c > otherAllocations) {
                otherAllocations = c;
            }
        }

        // without integer roll-over a miscount will generally be less than expected.
        // this could be a flakey assertion depending on a number of factors (e.g. JVM, cores, threads).
        assertThat("Var.rev does not contain the expected revision", Var.rev, is(greaterThan(expected)));
    }
}