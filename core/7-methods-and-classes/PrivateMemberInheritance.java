class A
{
  private int a;
  
  public int getA()
  {
    return a;
  }
}

class B extends A
{
  /*
  * private members are not inherited, so the derived class must use the parent's public accessor
  */
  public int getA()
  {
    return super.getA();
  }
}

public class PrivateMemberInheritance
{
  public static void main(String[] args)
  {
    A a = new A();
    B b = new B();
    System.out.println(b.getA());
  }
}
