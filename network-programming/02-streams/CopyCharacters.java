// Java stores characers as 16 point unicode
// Character streams convert between utf-16 and the local encoding (note that non-unicode character sets are often the same as their encoding!)
// Character streams are descended from Reader and Writer
// the difference between this class and CopyBytes is that FileReader reads a 16 bit integer whereas an FileInputStream reads 8 bits
// Note : input stream reader was designed to convert bytes to characters from any input stream, while FileReader is specifically for files
// Therefore : alternative is to use FileInputStream and InputStream
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
