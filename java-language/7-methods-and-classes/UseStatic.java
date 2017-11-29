// static members belong to the class and are shared between instances
// they can be called without an object reference (although using a class name)
// final variables are initialized once (at declaration or in constructor) while final classes have no children
class UseStaticAndFinal {
	
	static final int f = 100;
	static int a = 3;
	static int b;

	static void meth(int x){
		System.out.println("x = " + x);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}

	public static void main(String args[]) {
		meth(42);
	}
}