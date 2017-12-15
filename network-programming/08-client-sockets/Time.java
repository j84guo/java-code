import java.net.*;
import java.text.*;
import java.util.Date;
import java.io.*;

public class Time{

  private static String HOSTNAME = "localhost";

  public static void main(String[] args) throws IOException, ParseException{
    Date d = Time.getDateFromNetwork();
    System.out.println("It is " + d);
  }

  public static Date getDateFromNetwork(){

    // time protocol starts from 1900, Java Date class at 1970
    long differenceBetweenEpochs = 2208988800L;


    Date time = null;
    Socket socket = null;
    try{
      socket = new Socket(HOSTNAME, 37);
      socket.setSoTimeout(24000);

      InputStream raw = socket.getInputStream();

      long secondsSince1900 = 0;

      // Java uses signed integer primitives only
      // therefore we need to read the 4 bytes into a long, to interpret it as an unsigned integer
      for(int i=0; i<4; i++){
        secondsSince1900 = (secondsSince1900 << 8) | raw.read();
      }

      long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
      long msSince1970 = secondsSince1970 * 1000;
      time = new Date(msSince1970);
    }catch(IOException e){
      System.out.println(e);
    }finally{
      try{
        if(socket != null) socket.close();
      }catch(IOException e){
        System.out.println(e);
      }
    }

    return time;
  }
}
