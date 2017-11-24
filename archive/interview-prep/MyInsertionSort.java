/*

study of sorting algorithms :

bubble sort
***insertion sort***
merge sort
quick sort
selection sort
heap sort
radix sort

*/

import MyUtilities.*;

public class MyInsertionSort{
	public static void insertionSort(int[] array){
		if(array.length <= 1) return;

		int unsortedStart = 1;
		while(unsortedStart < array.length){
			int i = unsortedStart;
			while(i > 0 && array[i] < array[i-1]){
				SortUtilities.swap(array, i, i-1);
			}
			unsortedStart++;
		}
	}


	public static void main(String[] args){
		int[] array = {22, 1, 23, 109, 827, 34, 4, 51, 31, 77};
		insertionSort(array);

		for(int i=0; i<array.length; i++){
			System.out.print(array[i] + "  	");
		}
	}
}