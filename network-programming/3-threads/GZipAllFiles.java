import java.io.*;
import java.util.concurrent.*;

public class GZipAllFiles {

  public final static int THREAD_COUNT = 4;

  public static void main(String[] args) {

    // creates thread pool object
    ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

    for (String filename : args) {
      File f = new File(filename);

      if (f.exists()) {
        if (f.isDirectory()) {
          File[] files = f.listFiles();

          // recurse sub directories
          for (int i = 0; i < files.length; i++) {

            // submit task to thread pool
            if (!files[i].isDirectory()) {
              Runnable task = new GZipRunnable(files[i]);
              pool.submit(task);
            }
          }
        } else {

          // submit task to thread pool
          Runnable task = new GZipRunnable(f);
          pool.submit(task);
        }
      }
    }

    // notifies the pool that no further tasks will be added to its internal queue
    pool.shutdown();
  }
}
