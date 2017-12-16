import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class insertionsort{

	//reference of object passed by value to method
	public static void sort(int[] array){
		if(array.length <= 1){
			return;
		}
		
		int unsorted = 1;

		//we iterate until the entire array is sorted
		while (unsorted < array.length){
			int i = unsorted;
			while(i > 0){
				if(array[i-1] > array[i]){
					int tmp = array[i];
					array[i] = array[i-1];
					array[i-1] = tmp;
				}
				i--;
			}
			unsorted++;
		}
	}

	//main method is like any other static method, except it also serves as starting point for JVM
	public static void main(String[] args){
		int[] array = {11, 4, 3, 22, 14, 7, 9, 5, 1};
		sort(array);
		for (int i=0; i<array.length; i++){
			System.out.println(array[i]);
		}
	}	
}