import java.io.*;
import java.net.*;

public class SourceViewer {

    public static void main(String[] args) {

        if (args.length > 0) {
            try {
                // open the URLConnection for reading
                URL u = new URL(args[0]);
                URLConnection uc = u.openConnection();

                // use input stream to write to server
                try (InputStream raw = uc.getInputStream()) { // autoclose
                    InputStream buffer = new BufferedInputStream(raw);
                    
                    Reader reader = new InputStreamReader(buffer);
                    int c;
                    while ((c = reader.read()) != -1) {
                        System.out.print((char) c);
                    }
                }
            } catch (MalformedURLException ex) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
