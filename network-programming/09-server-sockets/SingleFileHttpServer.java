import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class SingleFileHttpServer {

  private static final Logger logger = Logger.getLogger("SingleFileHttpServer");
  private final byte[] content;
  private final byte[] header;
  private final int port;
  private final String encoding;

  public SingleFileHttpServer(String data, String encoding, String mimeType, int port){
      this(data.getBytes(), encoding, mimeType, port);
  }

  public SingleFileHttpServer(byte[] data, String encoding, String mimeType, int port){
    this.content = data;
    this.port = port;
    this.encoding = encoding;
    String header = "HTTP/1.0 200 OK\r\n"
    + "Content-length: "
    + this.content.length + "\r\n"
    + "Content-type: " + mimeType + "; charSet=" + encoding + "\r\n\r\n";
    this.header = header.getBytes(Charset.forName("US-ASCII"));
  }

  public void start(){
    ExecutorService pool = Executors.newFixedThreadPool(100);

    try(ServerSocket server = new ServerSocket(this.port)){
      while(true){
        logger.info("Accepting connections on port " + server.getLocalPort());
        logger.info("Data to be sent:");
        logger.info(new String(this.content, encoding));

        try{
          Socket connection = server.accept();
          pool.submit(new HttpHandler(connection));
        }catch(IOException e){
          logger.log(Level.WARNING, "Error accepting connection", e);
        }catch(RuntimeException e){
          logger.log(Level.SEVERE, "Unexpected error", e);
        }
      }
    }catch(IOException e){
      logger.log(Level.SEVERE, "Could not start server", e);
    }
  }

  private class HttpHandler implements Callable<Void>{
    private final Socket connection;

    HttpHandler(Socket connection){
      this.connection = connection;
    }

    @Override
    public Void call() throws IOException{
      try {
        OutputStream out = new BufferedOutputStream(connection.getOutputStream());
        InputStream in = new BufferedInputStream(connection.getInputStream());

        // read the first line
        StringBuilder request = new StringBuilder(80);
        while (true) {
          int c = in.read();
          if (c == '\r' || c == '\n' || c == -1) break;
          request.append((char) c);
        }
        // send a MIME header
        if (request.toString().indexOf("HTTP/") != -1) {
          out.write(header);
        }
        out.write(content);
        out.flush();
      } catch (IOException ex) {
        logger.log(Level.WARNING, "Error writing to client", ex);
      } finally {
        connection.close();
      }

      return null;
    }
  }

  public static void main(String[] args){
    // get port
    int port;
    try{
      port = Integer.parseInt(args[1]);
      if(port < 1 || port == 65535){
        port = 80;
      }
    }catch(Exception e){
      port = 80;
    }

    // get file encoding
    String encoding = "UTF-8";
    if(args.length > 2) encoding = args[2];

    // create server
    try{

      // file data
      Path path = Paths.get(args[0]);
      byte[] data = Files.readAllBytes(path);

      // content type
      String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);

      // server
      SingleFileHttpServer server = new SingleFileHttpServer(data, encoding, contentType, port);
      server.start();
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Usage: java SingleFileHttpServer filename port encoding");
    }catch(IOException e){
      logger.severe(e.getMessage());
    }
  }
}
