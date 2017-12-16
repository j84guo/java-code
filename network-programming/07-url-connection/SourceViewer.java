import java.io.*;
import java.net.*;

public class SourceViewer {

    public static void main(String[] args) {

        if (args.length > 0) {
            try {
                // open the URLConnection for reading
                URL u = new URL(args[0]);
                URLConnection uc = u.openConnection();

                // byte stream from server, wrapped in buffer and reader
                try (InputStream raw = uc.getInputStream()) {
                    InputStream buffer = new BufferedInputStream(raw);
                    Reader reader = new InputStreamReader(buffer);

                    int c;
                    while ((c = reader.read()) != -1) { // how does java interpret the incoming bytes
                        System.out.print((char) c); // print stream converts characters to bytes in the local character set
                    }
                } // autoclose
            } catch (MalformedURLException ex) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
