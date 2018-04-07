//inner classs can access enclosing instance members
class InstanceOuter {
  public InstanceOuter(int xx) { x = xx; }

  private static int x;

  class InstanceInner {
    public void printSomething() {
      System.out.println("The value of static x in my outer is " + x);
    }
  }
}

//static nested class can access enclosing static members, but not instance members
class StaticOuter {
  private static int x = 24;

  static class StaticInner {
    public void printSomething() {
      System.out.println("The value of static x in my outer is " + x);
    }
  }
}

public class InnerClassExamples {
  public static void main(String... args) {
    InstanceOuter io = new InstanceOuter(12);

    // Is this a compile error?
    InstanceOuter.InstanceInner ii = io.new InstanceInner();

    // What does this print?
    ii.printSomething(); // prints 12

    // What about this?
    StaticOuter.StaticInner si = new StaticOuter.StaticInner();
    si.printSomething(); // prints 24
  }
} 
