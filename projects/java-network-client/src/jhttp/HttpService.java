// package jhttp;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class HttpService {

	private static final String GET = "GET";
	private static final String POST = "POST";

	private HashMap<JhttpOption, String> argMap;
	private String urlStr;
	private String httpMethod;
	private String allHeaders;
	private String data;

	public HttpService(HashMap<JhttpOption, String> argMap){
		this.argMap = argMap;
	}

	public void run(){
		setFields();

		try{

			// create url and get connection
			URL url = new URL(this.urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// set http method
			if(this.httpMethod == null){
				this.httpMethod = GET;
			}

			connection.setRequestMethod(this.httpMethod);

			// set headers on connection
			setHeaders(connection);

			// todo : add body
			// url form encoded or multipart form data

			// print request headers
			System.out.println("Sending " + this.httpMethod + " request to URL : " + url);

			Map<String,List<String>> requestHeaders = connection.getRequestProperties();
			for(String headerName :  requestHeaders.keySet()){
				System.out.println(headerName + ": " + requestHeaders.get(headerName));
			}
			System.out.println("\n");

			// connect
			connection.connect();

			Map<String,List<String>> responseHeaders = connection.getHeaderFields();
			for(String headerName :  responseHeaders.keySet()){
				System.out.println(headerName + ": " + responseHeaders.get(headerName));
			}
			System.out.println("\n");

			// read and print response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void setHeaders(HttpURLConnection connection){
		HashMap<String, String> headers = buildHeaders();

		for(String headerName : headers.keySet()){
			String headerValue = headers.get(headerName);
			connection.setRequestProperty(headerName, headerValue);
		}
	}

	private HashMap<String, String> buildHeaders(){
		HashMap<String, String> headerMap = new HashMap<String, String>();
		if(allHeaders == null){
			return headerMap;
		}

		String[] pairs = allHeaders.split("&");
		for(String p : pairs){
			String[] pair = p.split("=");
			headerMap.put(pair[0], pair[1]);
 		}

		return headerMap;
	}

	private void setFields(){
		for(JhttpOption option : argMap.keySet()){
			String value = argMap.get(option);

			if(option == JhttpOption.URL){
				this.urlStr = value;
			}else if(option == JhttpOption.METHOD){
				this.httpMethod = value;
			}else if(option == JhttpOption.HEADER){
				this.allHeaders = value;
			}else if(option == JhttpOption.DATA){
				this.data = value;
			}
		}
	}
}
