/*
* information needed to conduct http request
*/

// package jhttp;

import java.util.HashMap;

public class RequestOptions {

  String url;
  String httpMethod;
  HashMap<String, String> headers;
  String data;
  String fileName;

  // builder
  public RequestOptions() {
  }

  public RequestOptions setUrl(String url) {
    this.url=url;
    return this;
  }

  public RequestOptions setHttpMethod(String httpMethod) {
    this.httpMethod=httpMethod;
    return this;
  }

  public RequestOptions setHeaders(HashMap<String, String> headers) {
    this.headers=headers;
    return this;
  }

  public RequestOptions setData(String data) {
    this.data=data;
    return this;
  }

  public RequestOptions setFileName(String fileName) {
    this.fileName=fileName;
    return this;
  }

  public String toString(){
    return url + "\n" + httpMethod + "\n" + headers + "\n" + data + "\n" + fileName;
  }
  /*
  public static void main(String[] args){
    RequestOptions options = new RequestOptions()
      .setUrl("http://www.google.ca")
      .setHttpMethod("GET")
      .setHeaders(new HashMap<String, String>())
      .setData("name=value")
      .setFileName("file.txt");
  }
  */
}
