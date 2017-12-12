import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.List;

public class BinarySaver {


    public static void main(String[] args) {
        // for each url
        for (int i = 0; i < args.length; i++) {
            try {
                // fetch and save file
                URL root = new URL(args[i]);
                saveBinaryFile(root);
            } catch (MalformedURLException ex) {
                System.err.println(args[i] + " is not URL I understand.");
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    public static void saveBinaryFile(URL u) throws IOException {
        // get url connection
        URLConnection uc = u.openConnection();

        // getting headers implicitly connects
        String contentType = uc.getContentType();

        // print all headers
        // note that content-encoding usually specifies how bytes in the body are encoded, e.g. x-gzip
        Map <String, List <String>> map = uc.getHeaderFields();
        for (String headerName  : map.keySet()) {
          System.out.println(headerName + ": " + map.get(headerName));
        }

        // check binary file
        int contentLength = uc.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file.");
        }

        // read input stream into byte array
        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset = 0;

            // since read could fail before finishing, loop
            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1) break; // end of data
                offset += bytesRead;
            }

            // data length error
            if (offset != contentLength) {
                throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
            }

            // get last / seperated token in path
            String filename = u.getFile();
            filename = filename.substring(filename.lastIndexOf('/') + 1);
            try (FileOutputStream fout = new FileOutputStream(filename)) {
                fout.write(data);
                fout.flush();
            }
        }
    }

}
