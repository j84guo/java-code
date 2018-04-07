import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// write characters to (local encoding) bytes
public class FileReaderWriterTest {

    public static void main(String[] args) throws IOException {

        FileReader inputCharStream = null;
        FileWriter outputCharStream = null;

        try {
            inputCharStream = new FileReader("data.txt");
            outputCharStream = new FileWriter("out.txt");

            int c;

            // reads lower 16 bits of int (-1 as EOF)
            while ((c = inputCharStream.read()) != -1) {

                // writes lower 16 bits of the int
                outputCharStream.write(c);
            }
        } finally {
            if (inputCharStream != null) {
                inputCharStream.close();
            }
            if (outputCharStream != null) {
                outputCharStream.close();
            }
        }
    }
}
