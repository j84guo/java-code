import java.io.*;
import java.net.*;

public class SourceViewerResponseHeader {

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {

            try {
                // Open the URLConnection for reading
                URL u = new URL(args[i]);
                HttpURLConnection uc = (HttpURLConnection) u.openConnection();

                // implicitly connects
                int code = uc.getResponseCode();
                String response = uc.getResponseMessage();
                System.out.println("HTTP/1.x " + code + " " + response);

                // get all headers
                for (int j = 1;; j++) {
                    String header = uc.getHeaderField(j);
                    String key = uc.getHeaderFieldKey(j);
                    if (header == null || key == null) break;
                    System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
                }
                System.out.println();

                // read response body
                try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
                    Reader r = new InputStreamReader( in );
                    int c;
                    while ((c = r.read()) != -1) {
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
