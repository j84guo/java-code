import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static class AveragePerMinute {
        String endpoint;
        String minuteTimestamp;
        
        int totalHits;
        int successHits;
        
        AveragePerMinute(String endpoint, String minuteTimestamp){
            this.endpoint = endpoint;
            this.minuteTimestamp = minuteTimestamp;
            
            this.totalHits = 0;
            this.successHits = 0;
        }
        
        public String getAverageString(){
            if(totalHits == 0) return "0.00";
            double d = ((double) successHits) * 100 / totalHits;
            return String.format("%.2f", d);
        }
    } 
    
    public static void main(String args[] ) throws Exception {
        
        ArrayList<String> logs = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            logs.add(line);
        }
        
        ArrayList<AveragePerMinute> results = getMinuteAverages(logs); 
        for(AveragePerMinute apm : results){
            System.out.println(apm.minuteTimestamp + " " + apm.endpoint + " " + apm.getAverageString());
        }
    }
    
    private static String getMinuteTimestamp(String line){
        int b1 = line.indexOf("[");
        int b2 = line.indexOf("]");
        String fullstamp = line.substring(b1 + 1, b1 + 18);
        
        String dateString = "error parsing date : " + fullstamp;
        try{
            SimpleDateFormat inFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm");
            SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            
            Date date = inFormat.parse(fullstamp); 
            dateString = outFormat.format(date);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return dateString;
    }
    
    private static String getEndpoint(String line){
        
        int slash = line.indexOf("/", line.indexOf("]")); 
        int http = line.indexOf("HTTP");
        String endpoint = line.substring(slash, http-1);
        if(endpoint.indexOf("?") != -1) endpoint = endpoint.substring(0, endpoint.indexOf("?"));
        return endpoint;
    }
    
    private static boolean requestSuccess(String line){
        int http = line.indexOf("HTTP");
        String code = line.substring(http+10, http+13); 
        
        if(code.charAt(0) == '5'){
            return false;
        }
        
        return true;
    }
    
    public static ArrayList<AveragePerMinute> getMinuteAverages(ArrayList<String> logs) {
         
        // key is endpoint concatenated with minuteTimestamp
        HashMap<String, AveragePerMinute> results = new HashMap<String, AveragePerMinute>();
    
        // parse logs line by line
        for(String line : logs){
            
            String minuteTimestamp = getMinuteTimestamp(line); // todo
            String endpoint = getEndpoint(line); // todo
            boolean success = requestSuccess(line); // todo
            
            String key = endpoint + "-" + minuteTimestamp;
            AveragePerMinute apm;
            
            if(results.containsKey(key)){
                apm = results.get(key);
            }else{
                apm = new AveragePerMinute(endpoint, minuteTimestamp);
                results.put(key, apm);
            }
            
            apm.totalHits++;
            if(success) apm.successHits++;
        }
        
        ArrayList<AveragePerMinute> output = new ArrayList<AveragePerMinute>();
        
        Set<String> keys = results.keySet();
        for(String key : keys){
            AveragePerMinute apm = results.get(key);
            output.add(apm);
        }
        
        Collections.sort(output, new Comparator<AveragePerMinute>(){
            public int compare(AveragePerMinute a1, AveragePerMinute a2){
                if(a1.minuteTimestamp.equals(a2.minuteTimestamp)){
                    return a1.endpoint.compareTo(a2.endpoint);
                }else{
                    return a1.minuteTimestamp.compareTo(a2.minuteTimestamp);
                }    
            }
        });
        
        return output;
    }
}