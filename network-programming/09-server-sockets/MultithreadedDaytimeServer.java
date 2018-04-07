import java.net.*;
import java.io.*;
import java.util.Date;

public class MultithreadedDaytimeServer{

  public static final int PORT = 13;

  public static void main(String[] args){

    // bind and listen
    try(ServerSocket server = new ServerSocket(PORT)){
      while(true){
        try{
          Socket connection = server.accept();
          Thread task = new DaytimeThread(connection);
          task.start();
        }
        // error accepting connection
        catch (Exception e) {
          System.err.println(e);
        }
      }
    }
    // error starting server
    catch(Exception e){
      System.err.println(e);
    }
  }

  private static class DaytimeThread extends Thread{
    private Socket connection;

    DaytimeThread(Socket connection){
      this.connection = connection;
    }

    @Override
    public void run(){
      try{
        Date now = new Date();
        Writer out = new OutputStreamWriter(connection.getOutputStream());
        out.write(now.toString() + "\r\n");
        out.flush();
      }catch(Exception e){
        System.err.println(e);
      }finally{
        try{
          // technically there's a possible NPE
          connection.close();
        }catch(Exception e){
          // error closing
        }
      }
    }
  }
}
