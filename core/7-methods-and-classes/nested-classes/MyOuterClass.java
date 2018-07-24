public class MyOuterClass {

	private static String str = "this is a private static string belonging to the outer class";

	private static String getStr(){
		return "this is a private static method beloning to the outer class";
	}

	private String prs = "private instance variable beloning to an instance of MyOuterClass";

	// static nested class
  static class StaticNestedClass {

		public void print(){
			System.out.println(str);
			System.out.println(getStr());
		}
  }

	// inner class
	class InnerClass {
    public void print(){
			System.out.println(str);
			System.out.println(getStr());

			System.out.println(prs);
		}
  }

	public static void main(String[] args){

		// OuterName.StaticName <obj name> = new OuterName.StaticName();
		MyOuterClass.StaticNestedClass nestedObject = new MyOuterClass.StaticNestedClass();
		nestedObject.print();

		// first create outer object
		MyOuterClass outerObject = new MyOuterClass();

		// strange syntax
		// second OuterName.InnerName <inner obj name> = <outer obj name>.new InnerName()
		MyOuterClass.InnerClass innerObject = outerObject.new InnerClass();
		innerObject.print();
	}
}
