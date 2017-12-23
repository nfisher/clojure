package tracing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Tracer {
    private final LinkedBlockingQueue<String> q;
    private Beaker beaker;
    private Thread thread;
    private final AtomicInteger missedTraceCount = new AtomicInteger(0);
    private static final Tracer instance = new Tracer();

    /** Tracer initialises the directory, queue, and writer thread.
     *
     */
    private Tracer() {
        q = new LinkedBlockingQueue<String>(8 * 1024);
    }

    /** create returns a singleton Tracer instance if it cannot access the target directory it returns null.
     *
     * @return
     */
    public static Tracer instance() {
        return instance;
    }

    /** start begins the writer thread.
     *
     */
    public void start(final String outputDir) throws IOException {
        final File dir = new File(outputDir);
        dir.mkdirs();
        File f = new File(dir, "profile-" + System.currentTimeMillis() + ".trace");
        beaker = new Beaker(q, new FileWriter(f));
        thread = new Thread(beaker);
        thread.start();
    }

    /**
     */
    public void trace(final String msg) {
        if (!q.offer(msg)) {
            missedTraceCount.incrementAndGet();
        }
    }

    /** getMissedTraceCount returns the number of traces that were dropped due to queue congestion.
     *
     * @return
     */
    public int getMissedTraceCount() {
        return missedTraceCount.get();
    }

    /** close inserts the close signal and busy-waits on the queue to empty.
     *
     */
    public void close() {
        synchronized (q) {
            q.add("]");
            while (q.isEmpty()) {
                try {
                    q.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
