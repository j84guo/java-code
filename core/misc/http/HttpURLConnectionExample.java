import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {
		if(args.length == 0){
			System.out.println("Usage: java HttpURLConnectionExample url");
			return;
		}
		HttpURLConnectionExample http = new HttpURLConnectionExample();
		http.sendGet(args[0], "GET");
	}

	private void sendGet(String url, String httpMethod) throws Exception {

		// 1. build url and obtain connection object
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// 2. set http method
		httpMethod = httpMethod == null? "GET" : httpMethod;
		con.setRequestMethod(httpMethod);

		// 3. set http headers
		con.setRequestProperty("User-Agent", USER_AGENT);

		// 4. set http body

		// 5. send request
		con.connect();

		// 6. get response code and headers
		int responseCode = con.getResponseCode();

		// 7. Read response body, should read based on response Content-Type?
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
	}

	private void sendPost() throws Exception {

		// 1. build url and obtain connection object
		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// 2. set http method
		con.setRequestMethod("POST");

		// 3. set http headers
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// 4. set http body
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		// 5. send request
		con.connect();

		// 6. get response code and headers
		int responseCode = con.getResponseCode();

		// 7. Read response body, should read based on response Content-Type?
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
	}

}
