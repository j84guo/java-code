import java.net.*;
import java.io.*;
import java.util.Date;

// server for day time protocol (returns some ascii text showing the current date and time)
public class DayTimeServer {

  public static final int PORT = 13; // port

  public static void main(String[] args){

    // bind server to local port
    try(ServerSocket server = new ServerSocket(PORT)){

      // listen for connections (sequential)
      while(true){

          // accept is a blocking call which returns a socket object
          try(Socket connection = server.accept()){
            Date now = new Date();

            // write date
            Writer out = new OutputStreamWriter(connection.getOutputStream());
            out.write(now.toString() +"\r\n");
            out.flush();

            // close socket
            connection.close();
          }catch(IOException e){
            System.err.println(e);
          }
      }
    }catch(IOException e){
      System.err.println(e);
    }
  }
}
