import java.net.*;

public class OReillyByName {
  public static void main (String[] args) {
    if(args.length != 1){
      System.out.println("Usage : java OReillyByName <host>");
      return;
    }

    try {
      InetAddress address = InetAddress.getByName(args[0]);
      System.out.println("single : " + address);

      InetAddress[] addresses = InetAddress.getAllByName(args[0]);
      for (InetAddress a : addresses) {
        System.out.println("all : " + a);
      }

      address = InetAddress.getLocalHost();
      System.out.println(address);

      byte[] a = address.getAddress();
      for(Byte b : a) System.out.print(b) + " ";
    } catch (UnknownHostException ex) {
      System.out.println("Could not find www.oreilly.com");
    }
  }
}
