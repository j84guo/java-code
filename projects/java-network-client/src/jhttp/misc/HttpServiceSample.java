// package jhttp;

import java.io.BufferedReader; // buffers a stream of characters
import java.io.OutputStreamWriter; // characters to bytes
import java.io.InputStreamReader; // input stream of characters
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpService {

	private final String USER_AGENT = "Mozilla/5.0";

	// http GET request
	private void sendGet() throws Exception {

		// 1. build url and get connection object
		String urlStr = "http://www.google.com/search?q=facial+recognition";
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 2. request method and headers
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// 3. send http request
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		// 4. read and print response
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
	}

	// http POST request
	private void sendPost() throws Exception {

		// 1. build url and get connection object
		String urlStr = "https://selfsolve.apple.com/wcResults.do";
		URL url = new URL(urlStr);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		// 2. request method, headers and body
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String body = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// 3. send http request
		con.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(body);
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post body : " + body);
		System.out.println("Response Code : " + responseCode);

		// 4. read and print response
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
	}

	public static void main(String[] args) throws Exception {
		HttpService http = new HttpService();
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
	}

}
