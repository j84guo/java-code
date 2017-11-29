public class MyOuterClass {
	
	//private static string
	private static String str = "this is a private static string belonging to the outer class";
	
	//private static method
	private static String getStr(){
		return "this is a private static method beloning to the outer class";
	}
	
	//static nested class 
    static class StaticNestedClass {
        
		public void print(){
			System.out.println(str);
			System.out.println(getStr());
		}		
    }
	
	//inner class
	class InnerClass {
        public void print(){
			System.out.println(str);
			System.out.println(getStr());
		}		
    }
	
	public static void main(String[] args){
		
		//OuterName.StaticName <obj name> = new OuterName.StaticName();
		MyOuterClass.StaticNestedClass nestedObject = new MyOuterClass.StaticNestedClass();
		nestedObject.print();
		
		//first create outer object
		MyOuterClass outerObject = new MyOuterClass();
		
		//strange syntax
		//second OuterName.InnerName <inner obj name> = <outer obj name>.new InnerName()
		MyOuterClass.InnerClass innerObject = outerObject.new InnerClass();
		innerObject.print();
	}
}
