public class RadixSortTest{
	public static void radixSort(int[] arr){
		int m = arr[0];
		int exp = 1;
		int out[] = new int[arr.length];

		for(int i=1; i<arr.length; i++) if(arr[i] > m) m = arr[i];

		while(m / exp > 0){
			int[] buckets = new int[10];
			for (int i=0; i<arr.length; i++) buckets[(arr[i]/exp)%10]++;
			for (int i=1; i<buckets.length;i++) buckets[i] += buckets[i-1];

			// very important to go backwords so that we maintain stability
			// when the current digit is equal, use the order determined by the previous sort
			// largest values go to largest indexes
			for(int i=arr.length-1; i>=0; i--) out[--buckets[(arr[i]/exp)%10]] = arr[i];

			System.arraycopy(out, 0, arr, 0, out.length);
			exp *= 10;
		}
	}


	public static void main(String[] args){
		int[] arr = {12, 32, 124, 43, 6536, 23, 2, 33, 4, 9};
		radixSort(arr);
		for(int i=0;i<arr.length;i++) System.out.print(arr[i] + " ");
	}
}
