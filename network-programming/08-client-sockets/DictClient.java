import java.io.*;
import java.net.*;

public class DictClient{

  public static final String SERVER = "dict0.us.dict.org";
  public static final int PORT = 2628;
  public static final int TIMEOUT = 30000;

  public static void main(String[] args){

    Socket socket = null;
    try{

      // create socket
      socket = new Socket(SERVER, PORT);
      socket.setSoTimeout(TIMEOUT);

      // output byte stream wrapped in writer and buffer
      OutputStream out = socket.getOutputStream();
      Writer writer = new OutputStreamWriter(out, "UTF-8");
      writer = new BufferedWriter(writer);

      // input stream wrapped in reader and buffer
      InputStream in = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

      for(String word : args){
        define(word, writer, reader);
      }
    }catch(Exception e){
      System.out.println(e);
    }finally{
      try{
        if (socket != null) socket.close();
      }catch (IOException e){
        System.out.println(e);
      }
    }
  }

  static void define(String word, Writer writer, BufferedReader reader) throws Exception{

    System.out.println("defining " + word);

    // write request and flush
    writer.write("DEFINE eng-lat " + word + "\r\n");
    writer.flush();

    // read and print response
    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
      if (line.startsWith("250 ")) { // OK
        return;
      }
      else if (line.startsWith("552 ")) { // no match
        System.out.println("No definition found for " + word);
        return;
      }
      else if (line.matches("\\d\\d\\d .*")) continue;
      else if (line.trim().equals(".")) continue;
      else System.out.println(line);
    }
  }
}
