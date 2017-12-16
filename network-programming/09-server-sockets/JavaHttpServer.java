import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class JavaHttpServer {
  private static final int NUM_THREADS = 50;
  private static final String INDEX_FILE = "index.html";

  private final File rootDirectory;
  private final int port;

  public JavaHttpServer(File rootDirectory, int port) throws IOException{
    if(!rootDirectory.isDirectory()){
      throw new IOException(rootDirectory + "is not a valid directory.");
    }

    this.rootDirectory = rootDirectory;
    this.port = port;
  }

  public void start() throws IOException{
    ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
    try(ServerSocket server = new ServerSocket(port)){
      Socket connection = server.accept();
      Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, connection);
      pool.submit(r);
    }catch(IOException e){
      System.out.println("Error starting server." + e);
    }
  }

  public static void main(String[] args){

    // document root
    File rootDirectory;
    try{
      rootDirectory = new File(args[0]);
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Usage: java JavaHttpServer directory port");
      return;
    }

    // port
    int port;
    try{
      port = Integer.parseInt(args[1]);
      if(port < 1 || port > 65535) port = 80;
    }catch(RuntimeException e){
      port = 80;
    }

    try{
      JavaHttpServer webServer = new JavaHttpServer(rootDirectory, port);
      webServer.start();
    }catch(IOException e){
      System.out.println(e);
    }
  }








}
