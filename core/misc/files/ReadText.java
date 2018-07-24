import java.util.Scanner;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class ReadText
{

  public static void main(String[] argv)
  {
    if(argv.length != 1)
    {
      System.out.println("Usage: java ReadText <filename>");
      return;
    }

    /*
    * 1) buffered reader around a file reader
    */
    try(BufferedReader br = new BufferedReader(new FileReader(argv[0])))
    {
      String s;

      while((s = br.readLine()) != null)
      {
        System.out.println(s);
      }
    }
    catch(Exception e)
    {
      System.err.println(e);
    }

    /*
    * 2) scanner around a file byte stream
    */
    try(Scanner sc = new Scanner(new FileInputStream(argv[0])))
    {
      while(sc.hasNextLine())
      {
        System.out.println(sc.nextLine());
      }
    }
    catch(Exception e)
    {
      System.err.println(e);
    }

    /*
    * 3) scanner around buffered reader around file reader
    */
    try
    (
      Scanner sc = new Scanner(new BufferedReader(new FileReader(argv[0])))
    )
    {
      while(sc.hasNextLine())
      {
        System.out.println(sc.nextLine());
      }
    }
    catch(Exception e)
    {
      System.err.println(e);
    }

    /*
    * 4) file input stream
    */
    try(FileInputStream fs = new FileInputStream(argv[0]))
    {
      int b;

      while((b = fs.read()) != -1)
      {
        System.out.print((char) b);
      }
    }
    catch(Exception e)
    {
      System.err.println(e);
    }
  }
}
