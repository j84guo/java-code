/*

study of sorting algorithms :

bubble sort
insertion sort
merge sort
quick sort
selection sort
heap sort
***counting sort***
radix sort

*/

/************************************************
worst : O(n + k)
best : O(n + k)
average : O(n + k)
space : O(k)

O(n) observe input keys
O(k) initialize buckets
O(n) first counting pass
O(k) generate sums
O(n) build output array

used for integers only
takes advantage of the integer itself as an array index
requires knowing range of keys
better when the range of keys is not large

*************************************************
space : O(1)
*************************************************/

public class CountingSort{

	public static void countingSort(int[] array){
		int[] frequency = new int[10];
		countFrequency(array, frequency);

		// indices for each key
		for(int i=1; i<frequency.length; i++) frequency[i] += frequency[i-1];

		int[] temp = new int[array.length];
		for(int i=0; i<array.length; i++){
			int index = frequency[array[i]]-1; // minus 1 because frequency was counting occurences
			temp[index] = array[i];
			frequency[array[i]]--;
		}
		System.arraycopy(temp, 0, array, 0, temp.length);
	}

	private static void countFrequency(int[] array, int[] frequency){
		for(int i=0; i<array.length; i++) frequency[array[i]]++;
	}

	public static void main(String[] args){
		int[] array = {9, 2, 3, 3, 4, 2, 8, 5, 6, 7, 4, 4};
		countingSort(array);
		for(int i=0; i<array.length; i++) System.out.println(array[i] + " ");
	}
}
