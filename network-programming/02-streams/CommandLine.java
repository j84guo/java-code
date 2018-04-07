// Command line I.O can be done through standard streams
// Standard byte streams System.in and System.out are automatically created and don't need to be opened
// To read input, it's advisable to wrap the byte stream with a scanner or buffered reader

import java.io.*;

public class CommandLine {

	public static void main(String[] args) throws IOException {
		String name = null;
		int number;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		name = in.readLine();
		number = Integer.parseInt(in.readLine());
		System.out.println("Name "+name+"\t number "+number);

		// buffered reader buffers a character stream
		// wrap input stream reader around standard input stream
		// note that buffered reader is thread safe
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your name: ");

		name = reader.readLine();
		System.out.println("Your name is: " + name);
	}
}
