// byte streams perform i/o of 8 bit bytes, the base classes are InputStream and OutputStream
// FileInputStream and FileOutputStream are used for file data
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {

	public static void main(String[] args) throws IOException{

		FileInputStream in = null;
		FileOutputStream out = null;

		try{
			in = new FileInputStream("data.txt");
			out = new FileOutputStream("out.txt");
			int c;

			while((c = in.read()) != -1){
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
