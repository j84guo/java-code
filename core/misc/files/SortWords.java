import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortWords{

  public static void main(String[] args){
    ArrayList<String> words = new ArrayList<>();

    try(
      BufferedReader br = new BufferedReader(new FileReader("data.txt"));
    ){
      String s;
      while((s = br.readLine()) != null){
        String[] tokens = s.split(" ");
        for(String t : tokens) words.add(t.toLowerCase());
      }

      // descending sort
      Collections.sort(words, new Comparator<String>(){
        public int compare(String s1, String s2){
          return s2.compareTo(s1);
        }
      });

      System.out.println(words);
    }catch(Exception e){
    }
  }
}
