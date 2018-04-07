/*

study of sorting algorithms :

bubble sort
insertion sort
merge sort
quick sort
***selection sort***
heap sort
counting sort
radix sort

*/

/*************************************************
worst : O(n^2)
best : O(n^2)
average : O(n^2)

for each element, calculate the extreme from the element on...
i.e. n + (n-1) + ... + 1 = n(n+1)/2
this happens no matter what the input, so best/worst/average are the same

*************************************************
space : O(1)
constant space in addition to the given array

*************************************************/

import MyUtilities.*;

public class MySelectionSort {

    public static void selectionSort(int[] array){
        for(int i=0; i<array.length-1; i++){
            int minIndex = SortUtilities.findMinIndex(array, i, array.length-1);
            SortUtilities.swap(array, minIndex, i);
        }
    }

    public static void main(String[] args){
        int[] array = {22, 1, 23, 109, 827, 34, 4, 51, 31, 77};
        selectionSort(array);

        for(int i=0; i<array.length; i++){
            System.out.print(array[i] + "   ");
        }
    }
}
