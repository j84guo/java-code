import java.io.*;

/*
-use try with resources to avoid manually closing streams
-BufferedReader around a FileReader to readLine() a string
-FileReader to read() a character
*/

public class InterviewFileLines{

	public static void main(String[] args){
		String file = "data.txt";
		
		try(
			BufferedReader br = new BufferedReader(new FileReader("data.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
		){
                        String l;
			while((l = br.readLine()) != null){
				System.out.println(l);
			 	bw.write(l);
			}
		
		  
		}catch(Exception e){

		}		
	}
}
