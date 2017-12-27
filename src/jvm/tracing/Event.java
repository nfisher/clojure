package tracing;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import static tracing.JsonKeyPair.jsonPair;

/** Event encapsulates the common event types for Chromes Trace Event format.
 *  Only 3 events were implemented; started, finished, and complete.
 *  See: https://docs.google.com/document/d/1CvAClvFfyA5R-PhYUmn5OOQtYMH4h6I0nSsKchNAySU/preview
 *  Traces can be loaded via chrome://tracing/
 */
public class Event {
    private static final String BEGIN = "B";
    private static final String COMPLETE = "X";
    private static final String DURATION = "dur";
    private static final String END = "E";
    private static final String EVENT_TYPE = "ph";
    private static final String PROCESS_ID = "pid";
    private static final String THREAD_ID = "tid";
    private static final String TIMESTAMP = "ts";
    private static final String NAME = "name";

    public static long startTime() {
        return System.currentTimeMillis();
    }

    public static JsonKeyPair duration(final long startTime) {
        final long finished = System.currentTimeMillis();
        return jsonPair()
                .add(NAME, getName())
                .add(TIMESTAMP, startTime * 1000)
                .add(EVENT_TYPE, COMPLETE)
                .add(THREAD_ID, Thread.currentThread().getId())
                .add(DURATION, (finished - startTime) * 1000)
                .add(PROCESS_ID, 0);
    }

    public static JsonKeyPair duration(final String name, final long startTime) {
        final long finished = System.currentTimeMillis();
        return jsonPair()
                .add(NAME, name)
                .add(TIMESTAMP, startTime * 1000)
                .add(EVENT_TYPE, COMPLETE)
                .add(THREAD_ID, Thread.currentThread().getId())
                .add(DURATION, (finished - startTime) * 1000)
                .add(PROCESS_ID, 0);
    }

    public static JsonKeyPair uptime() {
        final RuntimeMXBean mgmt = ManagementFactory.getRuntimeMXBean();
        return jsonPair()
                .add(NAME, "java -jar")
                .add(TIMESTAMP, mgmt.getStartTime() * 1000)
                .add(EVENT_TYPE, COMPLETE)
                .add(THREAD_ID, Thread.currentThread().getId())
                .add(DURATION, mgmt.getUptime() * 1000)
                .add(PROCESS_ID, 0);
    }

    public static String getName() {
        final Thread t = Thread.currentThread();
        // 3 is the depth in the call stack where the caller name exists
        final StackTraceElement stack = t.getStackTrace()[3];
        return stack.getClassName() + " " + stack.getMethodName();
    }
}
