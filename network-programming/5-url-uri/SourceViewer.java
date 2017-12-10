/*
The openStream() method connects to the resource referenced by the URL , performs
any necessary handshaking between the client and the server, and returns an Input
Stream from which data can be read. The data you get from this InputStream is the raw
(i.e., uninterpreted) content the URL references
*/

import java.io.*;
import java.net.*;

public class SourceViewer {

    public static void main(String[] args) {

        if (args.length > 0) {
            // byte stream
            InputStream in = null;
            try {
                // create url and open connection to the host
                URL u = new URL(args[0]);
                in = u.openStream();

                // buffer the input to increase performance
                in = new BufferedInputStream( in );

                // character stream
                Reader r = new InputStreamReader( in );
                int c;

                // print read file
                while ((c = r.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (MalformedURLException ex) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException ex) {
                System.err.println(ex);
            } finally {
                // release I/O resources
                if ( in != null) {
                    try {
                      in .close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
            }
        }
    }
}
