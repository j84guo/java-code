import java.util.*;

public class RecursiveMultiply {
	
	// approach : 
	// think of the product as the number of cells in an axb rectangle
	// shift a left to get (floored) half
	// case a even : count cells in half and add to itself
	// case b odd : count the floored half times b
	// then add to floored + 1 half times b 

	public static int multiply(int a, int b){
		int[] cache = new int[a+1];
		Arrays.fill(cache, -1);
		return multiply(a, b, cache);
	}

	private static int multiply(int a, int b, int[] cache){
		if(a == 0 || b == 0){
			return 0;
		}else if(a == 1){
			return b;
		}else if(b == 1){
			return a;
		}

		if(cache[a] != -1){
			return cache[a];
		}

		int flooredHalf = a >> 1;
		int flooredHalfProduct = multiply(flooredHalf, b);

		int result = 0;
		if(a % 2 == 0){
			result = flooredHalfProduct + flooredHalfProduct;
		}else{
			int ceilingHalfProduct = multiply(flooredHalf+1, b);
			result = flooredHalfProduct + ceilingHalfProduct;
		}

		cache[a] = result;
		return result;
	}

	private static int multiplyOptimal(int a, int b){
		if(a == 0 || b == 0){
			return 0;
		}else if(a == 1){
			return b;
		}else if(b == 1){
			return a;
		}

		int flooredHalf = a >> 1;
		int flooredHalfProduct = multiply(flooredHalf, b);

		int result = 0;
		if(a % 2 == 0){
			result = flooredHalfProduct + flooredHalfProduct;
		}else{
			result = flooredHalfProduct + flooredHalfProduct + b;
		}
		return result;
	}

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		
		while(true){
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println(multiplyOptimal(a, b));
		}
	}
}