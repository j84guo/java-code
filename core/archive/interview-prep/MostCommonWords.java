import java.io.FileInputStream;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MostCommonWords{

  static class Word{
    String value;
    int count;

    Word(String value){
      this.value = value;
      this.count = count;
    }
  }

  public static ArrayList<Word> getMostCommonWords(String fileName){
    ArrayList<Word> words = new ArrayList<>();
    HashMap<String, Word> wordMap = new HashMap<>();
    Word w;
    String s;

    try(
      Scanner sc = new Scanner(new FileInputStream(fileName));
    ){
      while(sc.hasNext()){
        s = sc.next();

        if(wordMap.containsKey(s)){
          w = wordMap.get(s);
        }else{
          w = new Word(s);
          wordMap.put(s, w);
        }

        w.count++;
      }
    }catch(Exception e){
    }

    for(String k : wordMap.keySet()) words.add(wordMap.get(k));
    Collections.sort(words, new Comparator<Word>(){
      public int compare(Word a, Word b){
        return b.count - a.count;
      }
    });

    return words;
  }

  public static void main(String[] args){
    String fileName = "linux.txt";
    ArrayList<Word> words = getMostCommonWords(fileName);
    for(int i=0; i<words.size(); i++) System.out.println(words.get(i).value + " : " + words.get(i).count);
  }
}
