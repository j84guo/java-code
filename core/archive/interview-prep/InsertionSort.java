/*

study of sorting algorithms :

bubble sort
***insertion sort***
merge sort
quick sort
selection sort
heap sort
counting sort
radix sort

*/

/*************************************************
worst : O(n^2)
best : O(n)
average : O(n^2)

best case the array is already sorted, so each iteration requires 1 comparison and 0 swaps
worst case the array is reversed, requiring something like (n+1)n/2 = O(n^2)

*************************************************
space : O(1)
constant space in addition to the given array

*************************************************/

import MyUtilities.*;

public class InsertionSort{
	public static void insertionSort(int[] array){
		if(array.length <= 1) return;

		int unsortedStart = 1;
		while(unsortedStart < array.length){
			int i = unsortedStart;
			while(i > 0 && array[i] < array[i-1]){ // ascending sort
				SortUtilities.swap(array, i, i-1);
			}
			unsortedStart++;
		}
	}


	public static void main(String[] args){
		int[] array = {22, 1, 23, 109, 827, 34, 4, 51, 31, 77};
		insertionSort(array);

		for(int i=0; i<array.length; i++){
			System.out.print(array[i] + " ");
		}
	}
}
