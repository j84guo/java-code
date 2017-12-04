import java.util.function.Predicate;

interface MyNumber {
	int getValue();
}

interface NumericTest {
	boolean test(int n);
}

interface NumericTest2 {
	boolean test(int n, int d);
}

// from java.util.Predicate
// public interface Predicate <T> {
//     boolean test(T t);
// }

public class Lambda {
	public static void main(String[] args){
		
		MyNumber num1 = () -> 9;
		MyNumber num2 = () -> { return 5; };
		System.out.println(num1.getValue()); //prints 9
		System.out.println(num2.getValue()); //prints 5
		
		NumericTest isEven = (n) -> (n % 2)==0;
		if(isEven.test(10)) System.out.println("10 is even");
		if(!isEven.test(9)) System.out.println("9 is not even");
		
		NumericTest isNonNeg = (n) -> n >= 0;
		if(isNonNeg.test(1)) System.out.println("1 is non-negative");
		if(!isNonNeg.test(-1)) System.out.println("-1 is negative");
		
		NumericTest2 isFactor = (n, d) -> (n % d) == 0;
		if(isFactor.test(10, 2)) System.out.println("2 is a factor of 10");
		if(!isFactor.test(10, 3)) System.out.println("3 is not a factor of 10");
			
		Predicate<String> start = s -> s.startsWith("O");
		System.out.println(start.test("OCAJP8")); // prints true
		
		
	}
}





