
/*
A nested class is a member of its enclosing class. 

-> Non-static nested classes (inner classes) have access to other members of the enclosing class, 
even if those members are declared private (like a private method). 

-> Static nested classes can access static members, but do not have access to instance members of the enclosing class.

As a member of the OuterClass, a nested class can be declared private, public, protected, or 
package private. (Recall outer classes can only be declared public or package private.)
*/
public class OuterClass {
    
	/*
	In effect, a static nested class is behaviorally a top-level class that has been nested in 
	another top-level class for packaging convenience
	
	The quirck is that static nested class can access ALL static members, even if they're private! 
	Also doesn't have to use OuterName.StaticField syntax
	*/
    static class StaticNestedClass {
        
    }
	
	/*
	An inner class is associated with an instance of its enclosing class and has direct 
	access to that object's (instance and static) methods and fields. 
	
	It cannot define any static members itself.
	The idea behind inner classes is to operate in the context of the enclosing instance. 
	Allowing static variables and methods contradicts this motivation.
	*/
	class InnerClass {
       
    }
	
	public static void main(String[] args){
		
		//instantiate static nested class
		//access using enclosing class NAME
		OuterClass.StaticNestedClass nestedObject =
			new OuterClass.StaticNestedClass();
			
		//instantiate inner class
		//access using enclosing class INSTANCE
		OuterClass outerObject = new OuterClass();
		OuterClass.InnerClass innerObject = 
			outerObject.new InnerClass();
			
	}
}