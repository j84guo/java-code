import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static String processUserPhoneNumber(String line){
        if(line == null || line.length() == 0){
            return "";
        }
        line = line.replace(" ", "");
        if(line.charAt(0) == '(') line = line.substring(1);
        line = line.replace("(", "-").replace(")", "-");
        
        int dash = 0;
        
        ArrayList<Character> str = stringToList(line);
        if(str.get(str.size()-5) != '-'){
            str.add(str.size()-4, '-');
        }
        for(int i = str.size()-1; i>= 0; i--){
            if(i <= str.size()-5){
                
                if(Character.isDigit(str.get(i))){
                    str.set(i, '*');
                    dash++;
                    
                    if(dash == 3 && i != 0 && str.get(i-1) != '-'){
                        str.add(i, '-');
                        dash = 0;
                    }
                }
            }
        }
        
        return listToString(str);
    }
    
    private static String listToString(ArrayList<Character> str){
        StringBuilder sb = new StringBuilder();
        for(char c : str){
            sb.append(c);
        }
        return sb.toString();
    }
    
    private static ArrayList<Character> stringToList(String str){
        ArrayList<Character> list = new ArrayList<Character>();
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            list.add(c);
        }
        
        return list;
    } 
    
    private static String processUserEmail(String line){
        if(line == null || line.length() == 0){
            return "";
        }
        line = line.replace(" ", "");
        int atIndex = line.indexOf("@");
            
        String first = line.substring(0, 1);
        
        String last = line.substring(atIndex-1, atIndex);
        String asterisks = "*****";
        if(atIndex == 1){
            last = ""; 
            asterisks = "";
        }
        
        String r = first + asterisks + last + line.substring(atIndex);
        return r;
    } 
        
    private static ArrayList<String> processUserData(ArrayList<String> inputList){
        
        ArrayList<String> results = new ArrayList<String>();
        
        for(String line : inputList){
            String r = "";
            
            if(line.substring(0,1).equals("E")){
                r = processUserEmail(line.substring(2));
            }else{
                r = processUserPhoneNumber(line.substring(2));
            }
            results.add(line.substring(0, 2) + r);
        }
        
        return results;
    }
    
    public static void main(String args[] ) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        
        ArrayList<String> inputList = new ArrayList<String>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            inputList.add(line);
        }
        
        ArrayList<String> results = processUserData(inputList);
        for(String r : results){
            System.out.println(r);
        }
    }
}