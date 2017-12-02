/*

study of sorting algorithms :

bubble sort
insertion sort
*** merge sort *** 
quick sort
selection sort
heap sort
counting sort
radix sort

*/

/*************************************************
worst : O(n*log(n))
best : O(n*log(n))
average : O(n*log(n))

T(n) = 2T(n/2) + O(n)
dividing = O(1)
recursive calls = 2*T(n/2)
merging = O(n)

there are O(log(n)) layers of recursive calls
each layer of recursive calls takes O(n) 

*************************************************
space : O(n)
array and temp

*************************************************/

import MyUtilities.*;

public class MergeSort {

	public static void mergeSort(int[] array){
		mergeSort(array, new int[array.length], 0, array.length-1);
	}

	private static void mergeSort(int[] array, int[] temp, int left, int right){
		if(left >= right){
			return;
		}

		int midpoint = (left + right)/2;

		mergeSort(array, temp, left, midpoint);
		mergeSort(array, temp, midpoint+1, right);
		mergeHalves(array, temp, left, right);
	}

	private static void mergeHalves(int[] array, int[] temp, int left, int right){
		int midpoint = (left + right)/2;

		int index = left;
		int leftIndex = left;
		int rightIndex = midpoint+1;

		while(leftIndex <= midpoint && rightIndex <= right){
			if(array[leftIndex] < array[rightIndex]){
				temp[index] = array[leftIndex];
				leftIndex++;
			}else{
				temp[index] = array[rightIndex];
				rightIndex++;
			}
			index++;
		} 

		SortUtilities.arraycopy(array, leftIndex, temp, index, midpoint-leftIndex+1);
		SortUtilities.arraycopy(array, rightIndex, temp, index, right-rightIndex+1);
		SortUtilities.arraycopy(temp, left, array, left, right-left+1);
	}

	public static void main(String[] args){
		/*
		Scanner sc = new Scanner(System.in);

		int size = sc.nextInt();
		int[] array = new int[size];

		for(int i=0; i<size; i++){
			array[i] = sc.nextInt();
		}
		*/

		int[] array = {22, 1, 23, 109, 827, 34, 4, 51, 31, 77};
		mergeSort(array);

		for(int i=0; i<array.length; i++){
			System.out.print(array[i] + "  	");
		}
	}
}