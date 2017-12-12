import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

// converts raw bytes (local encoding) to java's character set (utf-16)
public class InputOutputStreamReaderWriterTest {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try {
            isr = new InputStreamReader(new FileInputStream("data.txt"));
            osw = new OutputStreamWriter(new FileOutputStream("out.txt"));

            int c;

            // reads lower 8 bits of int so that an extra character -1 can be used for EOF
            while ((c = isr.read()) != -1) {

                // writes lower 16 bits of int
                osw.write(c);
            }
        } finally {
            if (isr != null) {
                isr.close();
            }
            if (osw != null) {
                osw.close();
            }
        }
    }
}
