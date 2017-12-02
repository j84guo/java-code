import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class quicksort{

	public static void sort(int[] array){
		sort(array, 0, array.length - 1);
	}
	
	public static void sort(int[] array, int left, int right){
		if(left >= right){
			return;
		}
		int pivot = array[(left+right) / 2];
		int index = partition(array, left, right, pivot);
		System.out.println(left + " " + right + " " + pivot + " " + index;
		sort(array, left, index-1);
		sort(array, index, right);
	}

	public static int partition(int[] array, int left, int right, int pivot){
		while(left <= right){
			while(array[left] < pivot){
				left++;
			}
			
			while(array[right] > pivot){
				right--;
			}
			
			if(left <= right){
				int tmp = array[left];
				array[left] = array[right];
				array[right] = tmp;
				left++;
				right--;
			}
		}
		return left;
	}

	public static void main(String[] args){
		int[] array = {2, 6, 33, 21, 8, 4, 5, 1, 7, 9, 60};
		sort(array);
		for (int i=0; i<array.length; i++){
			System.out.println(array[i]);
		}
	}
}