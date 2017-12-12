import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// write raw bytes
public class FileInputOutputStreamTest {

	public static void main(String[] args) throws IOException{
		FileInputStream in = null;
		FileOutputStream out = null;

		try{
			in = new FileInputStream("data.txt");
			out = new FileOutputStream("out.txt");
			int c;

			// reads lower bits from int (-1 as EOF)
			while((c = in.read()) != -1){

				// writes the lower 8 bits of the int
				out.write(c);
			}
		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
	}

}
