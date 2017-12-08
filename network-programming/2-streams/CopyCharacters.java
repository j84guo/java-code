// Java stores characers as 19 point unicode
// Character streams convert between unicode and the local character set
// Character streams are descended from Reader and Writer
// the difference between this class and CopyBytes is that FileReader reads a 16 bit integer whereas an FileInputStream reads 8 bits

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters {
    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("data.txt");
            outputStream = new FileWriter("out.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
