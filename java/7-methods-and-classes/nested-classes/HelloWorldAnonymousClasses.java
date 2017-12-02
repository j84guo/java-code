public class HelloWorldAnonymousClasses {

    interface HelloWorld {
        public void greet();
        public void greetSomeone(String someone);
    }

	/*
	outer class method
	uses anonymous classes in the initialization statements of the local variables 
	frenchGreeting and spanishGreeting, but uses a local class for englishGreeting
	*/
    public void sayHello() {

		//local class
        class EnglishGreeting implements HelloWorld {
            String name = "world";
            public void greet() {
                greetSomeone("world");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }
      
        HelloWorld englishGreeting = new EnglishGreeting();
        
		/*
		anonymous classes (basically unnamed, single-use local class which implement some interface)
        while lc are class declarations, ac are expressions!
		
		the syntax of an anonymous class expression is like the invocation of a constructor, 
		except that there is a class definition contained in a block of code
		
		the expression contains : new, an interface/superclass name, parentheses with arguments (note
		that interfaces take no arguments!), a declarative body, semicolon to end the expression's 
		statement
		
		Capturing :
		-An anonymous class has access to the members of its enclosing class, like a local class.
		-An anonymous class cannot access local variables in its enclosing scope that are not 
		 declared as final or effectively final, like a local class.
		 
		Shadowing : 
		-Like a nested class, a declaration of a type in an anonymous class shadows any other declarations in the enclosing scope. 
		
		Member restrictions : 
		You cannot declare static initializers or member interfaces in an anonymous class. An anonymous class can have static members provided 
		that they are constant variables.
		
		Constructor :
		No constructor (no name!), anonymous class bodies are declarations and instantiations
		*/
		HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";
            public void greet() {
                greetSomeone("tout le monde");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };
        
        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";
            public void greet() {
                greetSomeone("mundo");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };
		
		//usage
        englishGreeting.greet();
        frenchGreeting.greetSomeone("Fred");
        spanishGreeting.greet();
    }

    public static void main(String... args) {
        HelloWorldAnonymousClasses myApp =
            new HelloWorldAnonymousClasses();
        myApp.sayHello();
    }            
}
