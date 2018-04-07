import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

// find the list of numerical positions of a given word in a file
public class WordPosition{

  public static HashMap<String, ArrayList<Integer>> processText(String fileName){
    HashMap<String, ArrayList<Integer>> wordMap = new HashMap<>();
    try(
      Scanner sc = new Scanner(new FileInputStream(fileName));
    ){
      int i = 0;
      while(sc.hasNext()){
        String[] a = replaceCharacters(sc.next());
        for(String s : a){
          updateWordMap(s, i, wordMap);
          i++;
        }
      }
    }catch(Exception e){
    }
    return wordMap;
  }

  private static void updateWordMap(String s, int i, HashMap<String, ArrayList<Integer>> wordMap){
    ArrayList<Integer> list;
    if(wordMap.containsKey(s)){
      list = wordMap.get(s);
    }else{
      list = new ArrayList<>();
      wordMap.put(s, list);
    }
    list.add(i);
  }

  private static String[] replaceCharacters(String s){
    s = s.replace(",", "");
    s = s.replace(".", "");
    s = s.replace("!", "");
    s = s.replace("?", "");
    s = s.replace(";", "");
    s = s.replace("--", " ");
    s = s.replace("-", " ");
    s = s.toLowerCase();
    return s.split(" ");
  }

  public static ArrayList<Integer> wordIndex(String s, HashMap<String,ArrayList<Integer>> indexMap){
    if(indexMap.containsKey(s)){
      return indexMap.get(s);
    }else{
      return new ArrayList<Integer>();
    }
  }

  public static void main(String[] args){
    String fileName = "tender_is_the_night";
    HashMap<String, ArrayList<Integer>> indexMap = processText(fileName);
    System.out.println(wordIndex("rosemary", indexMap));
    System.out.println(wordIndex("and", indexMap));
    System.out.println(wordIndex("bubbled", indexMap));
  }
}
