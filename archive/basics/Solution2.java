import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2 {
    
    /***
	
	anagrams have the same letters occuring at the same frequency
	ascii 97 to 122
	ascii code - 97 to get index
    
    ***/

	//static method belongs to class as opposed to the object
    public static int numberNeeded(String first, String second) {
   		
   		int fl = first.length();
   		int sl = second.length();
   		int[] farr = new int[26];
   		int[] sarr = new int[26];

   		//java int defaults to zero, use java.utils.Arrays.fill(a, val) for demostration
   		Arrays.fill(farr, 0);
   		Arrays.fill(sarr, 0);

   		for (int i = 0; i < fl; i++){
			int index = (int) first.charAt(i) - 97; //alternatively, substring(i, 1)
			farr[index]++;  					
   		}
   		for (int i = 0; i < sl; i++){
			int index = (int) second.charAt(i) - 97; //alternatively, substring(i, 1)
			sarr[index]++;
   		}

   		int total = 0;
   		for(int i = 0; i < 26; i++){
   			if(farr[i] != sarr[i]){
   				total += Math.abs(farr[i] - sarr[i]);
   			}
   		}

   		return total;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
