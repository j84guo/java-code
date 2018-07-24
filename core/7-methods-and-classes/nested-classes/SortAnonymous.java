import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class SortAnonymous
{
  public static void main(String[] args)
  {
    ArrayList<Integer> a = new ArrayList<>();
    a.add(10);
    a.add(1);
    a.add(55);
    a.add(-123);
    a.add(142);
    a.add(1000);
    a.add(0);

    Collections.sort(a, new Comparator<Integer>(){
      public int compare(Integer x, Integer y)
      {
        return x - y;
      }
    });

    System.out.println(a);

    Integer x = null;
    Integer y = null;
    System.out.println(x == y);

    String s1 = "je";
    String s2 = "j" + "e";
    System.out.println(s1.equals(s2));

    ArrayList<? super Integer> l = new ArrayList<>();
    l.add(new Integer(1));
  }
}
