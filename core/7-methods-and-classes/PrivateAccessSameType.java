class A 
{
  private int x;

  public void useOther(A a)
  {
    System.out.println(a.x);
  }
};

public class PrivateAccessSameType
{

  public static void main(String[] args)
  {
    A a = new A();
    A b = new A();
    a.useOther(b);
  }
}
