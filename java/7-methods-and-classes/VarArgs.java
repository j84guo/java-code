// variable-length arguments can be passed by using type ... name in the parameter list
// then they are accessed with normal array syntax
// note you can have normal and varargs at the same time
// however there can only be one varargs parameter and it must be last to prevent ambiguity
// overriding varargs methods can be done by type, but you must be careful about ambiguity
class VarArgs {
	// vaTest() now uses a vararg.
	static void vaTest(int ... v) {
		System.out.print("Number of args: " + v.length + " Contents: ");
		
		for(int x : v)
			System.out.print(x + " ");
		
		System.out.println();
	}

	public static void main(String args[]){
		
		// Notice how vaTest() can be called with a
		// variable number of arguments.
		vaTest(10);
		// 1 arg
		vaTest(1, 2, 3); // 3 args
		vaTest();
		// no args
	}
}