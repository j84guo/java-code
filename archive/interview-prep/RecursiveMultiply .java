public class RecursiveMultiply {
	
	// approach : 
	// think of the product as the number of cells in an axb rectangle
	// shift a left to get (floored) half
	// case a even : count cells in half and add to itself
	// case b odd : count the floored half times b
	// then add to floored + 1 half times b 
	public static int multiply(int a, int b){
		if(a == 0 || b == 0){
			return 0;
		}else if(a == 1){
			return b;
		}else if(b == 1){
			return a;
		}

		int flooredHalf = a >> 1;
		int flooredHalfProduct = multiply(flooredHalf, b);

		if(a % 2 == 0){
			return flooredHalfProduct + flooredHalfProduct;
		}else{
			int ceilingHalfProduct = multiply(flooredHalf+1, b);
			return flooredHalfProduct + ceilingHalfProduct;
		}
	}

	public static void main(String[] args){
		System.out.println(multiply(3, 4));
	}
}