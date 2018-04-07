import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class BufferedInputOutputStream {

	public static void main(String[] args) throws IOException{

		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try{
			in = new BufferedInputStream(new FileInputStream("data.txt"));
			out = new BufferedOutputStream(new FileOutputStream("out.txt"));
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
