public class Static{

	public static void main(String[] args){
		A aobj = new A();		
    
                // in Java, a static variable can be accessed through the class name or the object name
		print(A.sa);
		print(aobj.sa);

		// instance variables are accessed through the object
		print(aobj.a);
                print(aobj.aa);

		// static variables can be accessed by this, ClassName or staticVariableName
		aobj.getStatic();
	}

	static void print(String s){
		System.out.println(s);
	}
}

class A{
	static String sa = "I'm a static String variable from class A";

        // notice that instance variables are declared in the class definition
        // but can be instantiated in either the class definition or contructor (or methods) 
	String a = "I'm an instance String variable, a, from an object of type A";
	String aa;

	A(){
		this.aa = "I'm an instance String variable, aa, from an object of type A";
	}

	void getStatic(){
		System.out.println("I can access static members using this: " + this.sa + " or by class name: " + A.sa + " or simply variable name: " + sa);
	}
}
