public class InheritanceTest{
	public static void main(String[] args){
		A a = new A();
		B b = new B();

		// static members can be called using class or instance names
  		// a.fooA();
		// a.sfooA();
		// A.sfooA();
		// System.out.println(a.sa);

		// objects inherit instance and static methods from their parent class
		// once inherited, the same rules for instance and static member access apply
		b.fooB();
		b.fooA();
		System.out.println(b.a + "-" + b.b + "-" + b.sb + "/" + B.sb);

		// within a subclass, instance variables are accessed by this or name, static variables by this or name or class
		// inherited variables follow the same rules with the addition of super 
		b.internalAccess();
        }
}

class A{
	A(){
		System.out.println("constructing an A...");
	}
	char a = 'a';
        static char sa = 'A';

	void fooA(){
		System.out.println("in fooA()...");
	}	

	static void sfooA(){
		System.out.println("in static fooA()...");
	}
}

class B extends A{

        // when entering a subclass contructor, if not explicit call to super() is made, the JVM calls 
        // the parent class default constructor
	B(){
		super(); // redundant in this case 
		System.out.println("constructing a B...");
	}

	// instance 
	char b = 'b';
  
        // static 
        static char sb = 'B';

        // instance
        void fooB(){
		System.out.println("in fooB()...");

                // instance methods inherited from the parent can be called using this  
		this.fooA();
	} 
	
	void internalAccess(){
		System.out.println(this.b + " " + this.sb + " " + this.a + " " + this.sa);
		System.out.println(b + " " + sb + " " + a + " " + sa);
		System.out.println(B.sb + " " + A.sa);
		System.out.println(super.a + " " + super.sa);
	}
}
