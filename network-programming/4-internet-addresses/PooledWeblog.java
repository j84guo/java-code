import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class PooledWeblog {
    private final static int NUM_THREADS = 4;
    public static void main(String[] args) throws IOException {

        // thread pool
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        Queue <LogEntry> results = new LinkedList <LogEntry>();

        // read
        // byte stream chained to character stream chained to buffer
        try (BufferedReader in = new BufferedReader(
            new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));) {
              
            for (String entry = in.readLine(); entry != null; entry = in.readLine()) {
                // submit task
                LookupTask task = new LookupTask(entry);

                // create log entry result
                Future <String> future = executor.submit(task);
                LogEntry result = new LogEntry(entry, future);

                // add to list
                results.add(result);
            }
        }

        // print results
        for (LogEntry result: results) {
            try {
                // future.get() is a blocking call
                System.out.println(result.future.get());
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(result.original);
            }
        }
        executor.shutdown();
    }

    // wraps a future object
    private static class LogEntry {
        String original;
        Future<String> future;

        LogEntry(String original, Future <String> future) {
            this.original = original;
            this.future = future;
        }
    }
}
