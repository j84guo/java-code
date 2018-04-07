import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;

/*
Permutation (anagram) Check :
Frequency table of characters for both words, decrement for other word.
I.e. check that the frequency tables are identical.

Dictionary of Words to Check :
Build a hashtable of frequency arrays.
For each pair of words, look up the frequency arrays and compare.
*/
public class PermutationPairs{

  // assuming a-z, alhabetic
  // toLowerCase(), isLetter(), !isDigit(), replace()
  public static HashMap<String, int[]> processWords(HashSet<String> set){
    HashMap<String, int[]> map = new HashMap<>();
    Iterator<String> it = set.iterator();
    String w;
    while(it.hasNext()){
      w = it.next();
      map.put(w, buildFrequencyTable(w));
    }
    return map;
  }

  private static int[] buildFrequencyTable(String word){
    int[] freq = new int[26];
    for(char c : word.toCharArray()){
      int i = c - 'a';
      freq[i]++;
    }
    return freq;
  }

  // assume n words of length m
  // O(n^2 * m)
  // note that in practice, the sameArray() method takes less than m iterations
  // this is as opposed to the naive solution of nested loops and a permutation
  // check from scratch, where each iteration of the n^2 loop goes through
  // 2 full iterations of m (second one could be slightly less)
  public static ArrayList<String[]> getAllPermutations(HashSet<String> set){
    HashMap<String, int[]> map = processWords(set);  // O(nm)
    ArrayList<String[]> permutations = new ArrayList<>();

    // easier to iterate through an ordered set
    ArrayList<String> words = new ArrayList<>();
    for(String w : map.keySet()) words.add(w); // O(n)

    for(int i=0; i<words.size()-1; i++){ // O(n^2 * m)
      for(int j=i+1; j<words.size(); j++){
        String a = words.get(i);
        String b = words.get(j);
        int[] aFreq = map.get(a);
        int[] bFreq = map.get(b);
        if(sameArray(aFreq, bFreq)){
          String[] pair = new String[2];
          pair[0] = a;
          pair[1] = b;
          permutations.add(pair);
        }
      }
    }

    return permutations;
  }

  private static boolean sameArray(int[] aFreq, int[] bFreq){
    for(int i=0; i<26; i++){
      if(aFreq[i] != bFreq[i]) return false;
    }
    return true;
  }

  public static void main(String[] args){
    HashSet<String> set = new HashSet<>();
    set.add("jackson");
    set.add("noskcaj");
    set.add("java");
    set.add("python");
    set.add("lava");
    set.add("vaaj");

    ArrayList<String[]> permutations = getAllPermutations(set);
    for(String[] pair : permutations) System.out.println(pair[0] + " <-> " + pair[1]);
  }
}
