/**
 * Beaker provides the runnable thread that writes out trace data in a Google's
 * format compatible with Google's chrome://tracing.
 */
package tracing;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;

/** Beaker catches all the trace data and stores it.
 *
 */
public class Beaker implements Runnable {
    private final Writer os;
    private final BlockingQueue<String> q;
    private final int BLOCK_SIZE = 1024 * 8;

    Beaker(final BlockingQueue<String> q, final Writer w) throws IOException {
        this.os = w;
        this.q = q;
    }

    @Override
    public void run() {
        try {
            boolean first = true;
            os.write("[");
            final StringBuilder sb = new StringBuilder();
            for (;;) {
                final String msg = q.take();
                if (null == msg || "]" == msg) {
                    break;
                }
                sb.setLength(0);

                if (!first) {
                    sb.append(',');
                }
                first = false;
                sb.append("{");
                sb.append(msg);
                sb.append("}\n");

                os.write(sb.toString());
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
}
