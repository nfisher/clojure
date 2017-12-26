/**
 * Beaker provides the runnable thread that writes out trace data in a Google's
 * format compatible with Google's chrome://tracing.
 */
package tracing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/** Beaker catches all the trace data and stores it.
 *
 */
public class Beaker implements Runnable {
    private final Writer os;
    private final BlockingQueue<String> q;
    private final int BLOCK_SIZE = 1024 * 16;
    private final AtomicInteger maxDepth;

    Beaker(final BlockingQueue<String> q, final Writer w) throws IOException {
        this.os = new BufferedWriter(w, BLOCK_SIZE);
        this.q = q;
        maxDepth = new AtomicInteger(0);
    }

    @Override
    public void run() {
        try {
            boolean first = true;
            os.write("[");
            for (;;) {
                int cur = q.size();
                int prevMax = maxDepth.get();
                if (cur > prevMax) {
                    maxDepth.set(cur);
                }

                final String msg = q.take();
                if (null == msg || "]" == msg) {
                    break;
                }

                if (!first) {
                    os.write(',');
                }
                first = false;
                os.write("{");
                os.write(msg);
                os.write("}\n");
            }
            os.write("]");
            os.flush();
            os.close();
            synchronized (q) {
                q.notify();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int maxDepth() {
        return maxDepth.get();
    }
}
