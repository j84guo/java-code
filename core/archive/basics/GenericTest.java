import java.util.LinkedList;

public class GenericTest
{

  public static void main(String[] args)
  {
    LinkedList<? super Object> l = new LinkedList<>();

    int x = 10;
    String s = "test";

    /*
    * - objects cannot be added to a collection using the unbounded wildcard
    *   since the collection could have been given any generic type argument
    * - producer extend, consumer super
    * - if the collection produces, the generic type parameter should extend
    *   upper bound ensure the collection can provide our object
    * - if the collection consumes, the generic type parameter should super
    *   lower bound ensures the colletion will take our object
    */
    // l.add(s);
  }
}
