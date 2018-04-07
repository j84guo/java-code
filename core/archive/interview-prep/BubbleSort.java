/*

study of sorting algorithms :

*** bubble sort ***
insertion sort
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

best case the array is already sorted, so only 1 traversal is required
worst case the array is reversed, requiring something like (n+1)n/2 = O(n^2)

*************************************************
space : O(1)
constant space in addition to the given array

*************************************************/

import MyUtilities.*;

public class BubbleSort {

	public static void bubbleSort(int[] array){
		for(int i=0; i<array.length-1; i++){
			for(int j=0; j<array.length-1-i; j++){
				if(array[j] > array[j+1]){
					SortUtilities.swap(array, j, j+1);
				}
			}
		}
	}

	public static void main(String[] args){
		int[] array = {22, 1, 23, 109, 827, 34, 4, 51, 31, 77};
		bubbleSort(array);

		for(int i=0; i<array.length; i++){
			System.out.print(array[i] + " ");
		}
	}
}
