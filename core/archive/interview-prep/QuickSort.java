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
	i.e. we've picked an extreme value as the pivot, causing each subproblem to decrease in size by 1 only
	with n recursive layers and O(n) cost per layer we get O(n^2) time
best : O(n*log(n))
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

		//we simply require that the element at pivotIndex is used to divide the arrays (it can be on either side)
		//that is, all elements less than the pivot are on the left and all elemenets greater than the pivot are on the right
		//the pivot index points to the partition between the two halves, but the element there is not necessarily correct
		//more specifically, pivotIndex points to the beginning of the right partition
		quickSort(array, left, pivotIndex-1);
		quickSort(array, pivotIndex, right);
	}

	//less chance of picking an extreme value given the tendency of arrays to be somewhat sorted
	//could also use an average of 3 (left, right, middle) method
	public static int partition_better(int arr[], int left, int right){
        int pivot = arr[(left+right)/2];

				//until left and right pointers meet or cross
				//they can only ever meet at the pivot element, in which case we return the pivot index
				while (left < right){

					  //find an element that is greater or equal
            while (arr[left] < pivot) left++;

						//find an element that is less or equal
            while (arr[right] > pivot) right--;

						// swap the elements pointed to if they are on the wrong side of each other
						// the pointers are equal at the pivot element
						// in this case you should return pivot element's index + 1 to account for
						// java rounding down when finding the midpoint
            if (left <= right){
                SortUtilities.swap(arr, left, right);
                left++;
                right--;
            }
        }

				//by now left points to the beginning of the right partition
        return left;
    }

	//note that this algorithm will produce O(n^2) for already sorted arrays!
	private static int partition(int[] array, int left, int right){
		int pivot = array[right]; // extreme value
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

    // return start of right portion
		return lowerTop + 1;
	}

	public static void main(String[] args){
		int[] array = {7, 2, 12, 19, 3, 31, 2, 1000, 1, 55};
		quickSort(array);

		for(int i=0; i<array.length; i++){
			System.out.print(array[i] + " ");
		}
	}
}
