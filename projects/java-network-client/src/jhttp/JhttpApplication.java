// package jhttp;

import java.util.*;

public class JhttpApplication {

  public static void main(String[] args){

    HashMap<JhttpOption, String> argMap = CliParser.parseArguments(args);
    if(argMap == null){
      System.out.println("usage: jhttp <options> <url>");
      return;
    }

    // for(JhttpOption option : argMap.keySet()){
		//   System.out.println(option + ": " + argMap.get(option));
		// }

    HttpService service = new HttpService(argMap);
    service.run();
  }
}
