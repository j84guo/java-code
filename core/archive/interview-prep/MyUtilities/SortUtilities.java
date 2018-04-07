package MyUtilities;

public class SortUtilities{

	public static void arraycopy(int[] from, int fromStart, int[] to, int toStart, int size){
		int toIndex = toStart;
		int fromIndex = fromStart;

		for(int i=0; i<size; i++){
			to[toIndex] = from[fromIndex];
			toIndex++;
			fromIndex++;
		}
	}

	public static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

  // inclusive boundaries
	public static int findMinIndex(int[] array, int start, int end){
		int minIndex = start; // set min to first element
		for(int j=start; j<end; j++){
			if(array[j+1] < array[minIndex]){
				minIndex = j+1;
			}
		}
		return minIndex;
	}
}
