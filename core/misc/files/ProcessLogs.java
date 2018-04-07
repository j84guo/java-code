import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;

// Goal :
// print average successful requests per minute
// for each endpoint (first directory)
// and display in descending order of minute (endpoint if necessary)

// Approach :
// count requests per endpoint for each minute
// then calculate average for each
// sort and print
public class ProcessLogs{

  static class EndpointByMinute{
    String endpoint; // first directory
    String minuteTimestamp; // mm-dd-yyyy:HH:MM
    int hits;
    int successHits;

    EndpointByMinute(String endpoint, String minuteTimestamp){
      this.endpoint = endpoint;
      this.minuteTimestamp = minuteTimestamp;
      this.hits = 0;
      this.successHits = 0;
    }

    // todo : method to compute average successHits
    public double averageSuccessHits(){
      return (double) successHits * 100 / hits;
    }

    public String toString(){
      return endpoint + " " + minuteTimestamp + " " + averageSuccessHits() + "%";
    }
  }

  private static String getLogEndpoint(String line){
    int start = line.indexOf("]") + 7;
    return line.substring(start).split("/")[1];
  }

  private static String getLogMinuteTimestamp(String line){
    int square1 = line.indexOf("[");
    int square2 = line.indexOf("]");
    return line.substring(square1+1, square2-9);
  }

  private static boolean getLogSuccess(String line){
    int start = line.indexOf("HTTP") + 10;
    if(line.charAt(start) == '4' || line.charAt(start) == '5') return false;
    return true;
  }

  public static ArrayList<EndpointByMinute> getEndpointsByMinute(LinkedList<String> logs){
    ArrayList<EndpointByMinute> results = new ArrayList<>();
    HashMap<String, EndpointByMinute> map = new HashMap<>();
    EndpointByMinute ebm;

    for(String line : logs){
      String endpoint = getLogEndpoint(line);
      String minuteTimestamp = getLogMinuteTimestamp(line);
      boolean success = getLogSuccess(line);

      String key = endpoint + "-" + minuteTimestamp;
      if(map.containsKey(key)){
        ebm = map.get(key);
      }else{
        ebm = new EndpointByMinute(endpoint, minuteTimestamp);
        map.put(key, ebm);
      }

      ebm.hits++;
      if(success) ebm.successHits++;
    }

    for(String key : map.keySet()) results.add(map.get(key));

    Collections.sort(results, new Comparator<EndpointByMinute>(){
      public int compare(EndpointByMinute a, EndpointByMinute b){
        if(a.endpoint == b.endpoint){
          return b.minuteTimestamp.compareTo(a.minuteTimestamp);
        }else{
          return a.endpoint.compareTo(b.endpoint);
        }
      }
    });

    return results;
  }

  public static LinkedList<String> getLogs(String filename){
    LinkedList<String> logs = new LinkedList<>();
    try(
      BufferedReader br = new BufferedReader(new FileReader(filename));
    ){
      String s;
      while((s = br.readLine()) != null){
        logs.add(s);
      }
    }catch(Exception e){
    }
    return logs;
  }

  public static void main(String[] args){
    LinkedList<String> logs = getLogs("logs.txt");
    ArrayList<EndpointByMinute> ebmList = getEndpointsByMinute(logs);
    for(EndpointByMinute ebm : ebmList) System.out.println(ebm);
  }
}
