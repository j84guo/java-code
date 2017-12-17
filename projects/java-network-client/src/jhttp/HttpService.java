/*
* http request template
*/

package jhttp;

import java.net.HttpURLConnection;
import java.net.URL;

import java.io.OutputStreamWriter;
import java.io.OutputStream;

import java.util.Date;
import java.util.Map;
import java.util.List;

public class HttpService {

  private static String USER_AGENT = "jhttp 1.0";
  private static String ACCEPT = "*/*";
  private static Date DATE = new Date();

  private RequestOptions options;

  public HttpService(RequestOptions options){
    this.options = options;
  }

  public Response getResponse() throws Exception{

    // 1. build url and obtain connection object
    URL obj = new URL(options.url);
    HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

    // 2. set http method
		options.httpMethod = options.httpMethod == null? "GET" : options.httpMethod;
		connection.setRequestMethod(options.httpMethod);

    // 3. set http headers and get their map
    setDefaultHeaders(obj, connection);
    for(String name : options.headers.keySet()){
  		connection.setRequestProperty(name, options.headers.get(name));
    }
    Map<String, List<String>> requestHeaderMap = connection.getRequestProperties();

    // 4. set http body
    if(options.data != null || options.fileName != null){
      connection.setDoOutput(true);
    }
    if(options.data != null){
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {
          out.write(options.data);
          out.write("\r\n");
      }
    }else if(options.fileName != null){
      // todo : write multipart form data

    }

    // 5. send request and return response object
		connection.connect();
    return new Response(requestHeaderMap, connection, options.outputFile);
  }

  private void setDefaultHeaders(URL obj, HttpURLConnection connection){
    connection.setRequestProperty("Accept", ACCEPT);
    connection.setRequestProperty("User-Agent", USER_AGENT);
    connection.setRequestProperty("Date", DATE.toString());
  }
}
















// end
