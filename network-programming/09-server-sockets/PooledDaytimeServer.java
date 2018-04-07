import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class PooledDaytimeServer {
	public final static int PORT = 13;

	public static void main(String[] args) {

		// create thread pool
		ExecutorService pool = Executors.newFixedThreadPool(50);

		// create  server socket
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try {
					// accept connections
					Socket connection = server.accept();

					// callable actually returns a future if the run method has a return value
					// runnable does not iirc
					Callable <Void> task = new DaytimeTask(connection);
					pool.submit(task);
				} catch(IOException ex) {
					
				}
			}
		} catch(IOException ex) {
			System.err.println("Couldn't start server");
		}
	}

	private static class DaytimeTask implements Callable <Void> {
		private Socket connection;

		DaytimeTask(Socket connection) {
			this.connection = connection;
		}

		@Override
		public Void call() {
			try {
				Date now = new Date();

				// write date in ASCII encoding
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				out.write(now.toString() + "\r\n");
				out.flush();
			} catch(IOException ex) {
				System.err.println(ex);
			} finally {
				try {
					connection.close();
				} catch(IOException e) {
					// ignore;
				}
			}
			return null;
		}
	}
}
