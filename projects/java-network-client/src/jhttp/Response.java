// package jhttp;

import java.net.HttpURLConnection;

import java.util.Map;
import java.util.List;

public class Response {
    public Map<String, List<String>> requestHeaderMap;
    public HttpURLConnection connection;

    public Response(Map<String, List<String>> requestHeaderMap, HttpURLConnection connection){
      this.requestHeaderMap = requestHeaderMap;
      this.connection = connection;
    }
}
