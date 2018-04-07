import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PalindromePairs{

  private static boolean isPalindrome(String s){
    int a = 0;
    int b = s.length()-1;
    while(a < b){
      if(s.charAt(a) != s.charAt(b)){
        return false;
      }
      a++;
      b--;
    }
    return true;
  }

  public static ArrayList<String[]> getAllPalindromes(HashSet<String> set){
    ArrayList<String[]> results = new ArrayList<>();
    ArrayList<String> words = new ArrayList<>();
    for(String s : set) words.add(s);
    for(int i=0; i<words.size()-1; i++){
      for(int j=i; j<words.size(); j++){
        String a = words.get(i) + words.get(j);
        String b = words.get(j) + words.get(i);
        if(isPalindrome(a) || isPalindrome(b)){
          String[] pair = new String[2];
          pair[0] = words.get(i);
          pair[1] = words.get(j);
          results.add(pair);
        }
      }
    }
    return results;
  }

  /*
  Given a set of words, find the pairs which form a palindrome when
  concatenated together. I.e. a + b or b + a.
  */
  public static void main(String[] args){
    HashSet<String> set = new HashSet<>();
    set.add("jackson");
    set.add("noskcaj");
    set.add("java");
    set.add("python");
    set.add("lava");
    set.add("vaaj");
    set.add("nohtyp");
    set.add("aav");
    set.add("jaa");

    ArrayList<String[]> results = getAllPalindromes(set);
    for(String[] pair : results) System.out.println(pair[0] + " <-> " + pair[1]);
  }
}
