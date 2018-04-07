import java.net.*; // Socket class
import java.io.*; // I/O streams

public class DaytimeClient {

  public static void main(String[] args){

    // known day time server
    String hostname = args.length > 0 ? args[0] : "time.nist.gov";

    // socket is declared outside so that finally can see it
    Socket socket = null;
    try{
      socket = new Socket(hostname, 13); // creates socket and connects to host (default TCP)
      socket.setSoTimeout(24000); // time a read() call blocks for

      InputStream in = socket.getInputStream(); // byte stream
      InputStreamReader reader = new InputStreamReader(in, "ASCII"); // wrap in a character stream

      // read and print response
      StringBuilder time = new StringBuilder();
      for(int c = reader.read(); c != -1; c = reader.read()){
        time.append((char) c);
      }
      System.out.println(time);
    }catch(IOException e){
      System.err.println(e);
    }finally{
      if(socket != null){
        try{
          socket.close();
        }catch(IOException e){
          System.err.println(e);
        }
      }
    }
  }
}
