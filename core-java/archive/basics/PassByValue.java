public class PassByValue {
	//clearly primitives are passed by value in Java (objects too)  
    public static void main(String args[]) {
       int number = 3;
       printNext(number);
       System.out.println("number Inside main(): "+number);
    }
  
    public static void printNext(int number){
        number++;
        System.out.println("number Inside printNext(): "+number);
    }
  
}


