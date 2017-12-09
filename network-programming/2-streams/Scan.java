// Objects of type Scanner are useful for breaking down formatted input into tokens and translating individual tokens according to their data type.
import java.io.*;
import java.util.Scanner;

public class Scan {
    public static void main(String[] args) throws IOException {

        Scanner s = null;

        try {
    	          // scanners parse text and convert tokens to Java primitives
                s = new Scanner(
    			             // buffered readers improve efficiency by limiting I/O operations
    			             new BufferedReader(
    				                // character reader
    				                new FileReader("data.txt")
    			             )
    		        );

                while (s.hasNext()) {
                    System.out.println(s.next());
                }

        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}
