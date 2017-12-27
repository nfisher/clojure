/**
 *   Copyright (c) Rich Hickey. All rights reserved.
 *   The use and distribution terms for this software are covered by the
 *   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *   which can be found in the file epl-v10.html at the root of this distribution.
 *   By using this software in any fashion, you are agreeing to be bound by
 * 	 the terms of this license.
 *   You must not remove this notice, or any other, from this software.
 **/

package clojure;

import clojure.lang.RT;
import clojure.lang.Symbol;
import clojure.lang.Var;
import tracing.Tracer;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

import static tracing.Event.duration;
import static tracing.Event.startTime;
import static tracing.Event.uptime;
import static tracing.JsonKeyPair.jsonPair;

public class main{

    public static void legacy_repl(String[] args) {
        final Var REQUIRE = RT.var("clojure.core", "require");
        final Var LEGACY_REPL = RT.var("clojure.main", "legacy-repl");
        final Symbol CLOJURE_MAIN = Symbol.intern("clojure.main");

        REQUIRE.invoke(CLOJURE_MAIN);
        LEGACY_REPL.invoke(RT.seq(args));
    }

    public static void legacy_script(String[] args) {
        final Var REQUIRE = RT.var("clojure.core", "require");
        final Var LEGACY_SCRIPT = RT.var("clojure.main", "legacy-script");
        final Symbol CLOJURE_MAIN = Symbol.intern("clojure.main");
        REQUIRE.invoke(CLOJURE_MAIN);
        LEGACY_SCRIPT.invoke(RT.seq(args));
    }

    public static void main(String[] args) {
        final Tracer tracer = Tracer.instance();
        final long startTime = startTime();

        try {
            tracer.start("trace");
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Var REQUIRE = RT.var("clojure.core", "require");
        final Var MAIN = RT.var("clojure.main", "main");
        final Symbol CLOJURE_MAIN = Symbol.intern("clojure.main");

        REQUIRE.invoke(CLOJURE_MAIN);
        MAIN.applyTo(RT.seq(args));

        // putting this before exit makes it easier to find in the graph
        tracer.trace(duration(startTime).toString());

        tracer.trace(uptime().toString());

        exit(tracer);
        tracer.close();
    }

    static void exit(final Tracer tracer) {
        final long startTime = startTime();

        final Runtime runtime = Runtime.getRuntime();

        final long freeMemory = runtime.freeMemory();
        final long maxMemory = runtime.maxMemory();
        final long totalMemory = runtime.totalMemory();
        final long processors = runtime.availableProcessors();

        long garbageCollections = 0;
        long garbageCollectionTime = 0;


        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            final long count = gc.getCollectionCount();

            if (count >= 0) {
                garbageCollections += count;
            }

            final long time = gc.getCollectionTime();

            if (time >= 0) {
                garbageCollectionTime += time;
            }
        }

        tracer.trace(duration(startTime).addRaw("args",
                jsonPair()
                        .add("rc", 1)
                        .add("numGC", garbageCollections)
                        .add("inGC", garbageCollectionTime)
                        .add("freeMemory", freeMemory)
                        .add("maxMemory", maxMemory)
                        .add("totalMemory", totalMemory)
                        .add("processors", processors)
                        .add("missedTraces", tracer.getMissedTraceCount())
                        .add("maxDepth", tracer.maxDepth())
                        .toMapString())
                .toString());
    }
}
