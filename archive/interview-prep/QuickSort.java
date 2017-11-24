/*

study of sorting algorithms :

bubble sort
insertion sort
merge sort 
*** quick sort ***
selection sort
heap sort
counting sort
radix sort

*/

/*************************************************
worst : O(n^2)
	T(n) = T(n-1) + O(n)
	i.e. we've picked an extreme value, causing each subproblem to decrease in size by 1 only 
	with n recursive layers and O(n) cost per layer we get O(n^2) time
best : O(n*log  (n))
&
average : O(n*log(n))
	log(n) recursive layers since picking the median gives 2 subprblems of T(n/2)
	each layer takes O(n) time


*************************************************
space : O(log(n))
*************************************************/

import MyUtilities.*;

public class QuickSort {
	public static void quickSort(int[] array){
		quickSort(array, 0, array.length-1);
	}

	private static void quickSort(int[] array, int left, int right){
		if(left >= right){
			return;
		}

		int pivotIndex = partition_better(array, left, right);
		quickSort(array, left, pivotIndex-1);
		quickSort(array, pivotIndex+1, right);
	}

	//less chance of picking an extreme value given the tendency of arrays to be somewhat sorted
	//could also use an average of 3 method 
	public static int partition_better(int arr[], int left, int right) {
        int pivot = arr[(left + right) / 2]; 
        System.out.println("pivot index is " + (left+right)/2);
        System.out.print(pivot + " ");

        while (left <= right) { // Until we've gone through the whole array
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }

            if (left <= right) {
                SortUtilities.swap(arr, left, right);
                left++;
                right--;
            }
        }
        System.out.print(arr[left] + "\n");
        System.out.println(left);
       	
       	//how is it that this is the pivot value???
        return left;
    }

	//note that this algorithm will produce O(n^2) for already sorted arrays!
	private static int partition(int[] array, int left, int right){
		int pivot = array[right];
		int lowerTop = left-1;
		int upperTop = left;

		while(upperTop < right){
			if(array[upperTop] < pivot){
				lowerTop++;
				SortUtilities.swap(array, lowerTop, upperTop);
			}
			upperTop++;
		}
		SortUtilities.swap(array, lowerTop+1, right);
		return lowerTop + 1;
	}

	public static void main(String[] args){
		int[] array = {7, 2, 12, 19, 3, 31, 2};
		quickSort(array);

		for(int i=0; i<array.length; i++){
			System.out.print(array[i] + "  	");
		}
	}
}