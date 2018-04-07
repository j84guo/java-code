import java.io.FileReader; // character stream (reader) wrapped around a byte stream from a file
import java.io.BufferedReader; // buffers character stream to read whole lines at a time

public class FileReaderDemo{
	public static void main(String[] args){
		try(
			BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		   ){
			String s;
			while((s = br.readLine()) != null){
				System.out.println(s);
			}


		}catch(Exception e){
		}
	}
}
