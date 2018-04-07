import java.io.*;
import java.net.*;

public class RandomPort {
public static void main(String[] args) {
try {
ServerSocket server = new ServerSocket(0); // picks a random port
System.out.println("This server runs on port " + server.getLocalPort());
} catch (IOException ex) {
System.err.println(ex);
}
}
}
