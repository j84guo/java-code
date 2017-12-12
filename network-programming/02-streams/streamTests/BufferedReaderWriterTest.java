import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

// java characters (utf-16) to bytes (local encoding) using a buffer to limit I/O overhead
public class BufferedReaderWriterTest {

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        OutputStream os = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));

            os = new FileOutputStream("out.txt");
            bw = new BufferedWriter(new OutputStreamWriter(os));

            int c;

            // reads lower 16 bits of int
            while ((c = br.read()) != -1) {

                // writes lower 16 bits of int
                bw.write(c);
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
        }
    }
}
