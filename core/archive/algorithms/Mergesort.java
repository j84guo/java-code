import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Mergesort{
	public static void sort(int[] array){
		//calls overloaded function with array, temporary array, leftStart and rightEnd indices
		sort(array, new int[array.length], 0, array.length-1);
	}

	public static void sort(int[] array, int[] tmp, int leftStart, int rightEnd){
		if(leftStart >= rightEnd){
			return;
		}
		int middle = (leftStart + rightEnd) / 2;
		sort(array, tmp, leftStart, middle);
		sort(array, tmp, middle + 1, rightEnd);
		merge(array, tmp, leftStart, rightEnd);
	}

	//merges 2 sorted arrays into one
	public static void merge(int[] array, int[] tmp, int leftStart, int rightEnd){
		int leftEnd = (leftStart + rightEnd)/2;
		int rightStart = leftEnd + 1;
		int size = rightEnd - leftStart + 1;

		int left = leftStart;
		int right = rightStart;
		int index = leftStart;

		while (left <= leftEnd && right <= rightEnd){
			if(array[left] <= array[right]){
				tmp[index] = array[left];
				left++;
			}else{
				tmp[index] = array[right];
				right++;
			}
			index++;
		}

		//either left or right array will have elements left
		System.arraycopy(array, left, tmp, index, leftEnd - left + 1);
		System.arraycopy(array, right, tmp, index, rightEnd - right + 1);
		System.arraycopy(tmp, leftStart, array, leftStart, size);
	}

	public static void main(String[] args){
		int[] array = {4, 2, 10, 7, 900, 101, 1};
		sort(array);
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i]);
		}
	}
}
