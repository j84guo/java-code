import java.net.HttpURLConnection;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.Map;
import java.util.List;

public class ResponseViewer {

  private HttpURLConnection connection;
  private Map<String, List<String>> requestHeaderMap;

  public ResponseViewer(Response response){
    this.connection = response.connection;
    this.requestHeaderMap = response.requestHeaderMap;
  }

  public void printRequestAndResponse(){
    printRequest();
    printResponse();
  }

  public void printRequest(){
    String path = connection.getURL().getFile();
    if(path.equals("")) path = "/";
    System.out.println("> " + connection.getRequestMethod() + " " + path + " HTTP/1.1");
    for (Map.Entry<String, List<String>> entry : requestHeaderMap.entrySet()) {
      for(String value : entry.getValue()){
        System.out.println("> " + entry.getKey() + ": " + value);
      }
    }
    System.out.println(">");
  }

  public void printResponse(){
    System.out.println("< " + connection.getHeaderField(0));
    for (int i = 1;; i++) {
        String header = connection.getHeaderField(i);
        if (header == null) break;
        System.out.println("< " + connection.getHeaderFieldKey(i) + ": " + header);
    }
    System.out.println("<");
    try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
      String inputLine;
  		StringBuilder response = new StringBuilder();
  		while ((inputLine = in.readLine()) != null) {
  			response.append(inputLine);
  		}
  		System.out.println(response.toString());
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
