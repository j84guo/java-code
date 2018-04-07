import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Bubblesort{

	public static void sort(int[] array){
		boolean isSorted = false;
		while(!isSorted){
			isSorted = true;
			int lastUnsorted = array.length-1;

			for(int i=0; i<lastUnsorted; i++){
				if(array[i] > array[i+1]){
					int tmp = array[i];
					array[i] = array[i+1];
					array[i+1] = tmp;
					isSorted = false;
				}
			}
			lastUnsorted--;
		}
	}

	public static void simpleSort(int[] a){
		for(int i=0; i<a.length-1; i++){
			boolean isSorted = true;
			for(int j=0; j<a.length-1-i; j++){
				if(a[j] > a [j+1]){
					int temp = a[j+1];
					a[j+1] = a[j];
					a[j] = temp;
					isSorted = false;
				}
			}
			if(isSorted) return;
		}
	}

	public static void main(String[] args){
		int[] array = {4, 2, 3, 6, 3, 2, 2, 1, 2, 1, 0, 89};
		simpleSort(array);
		for(int i=0; i<array.length; i++){
			System.out.println(array[i]);
		}
	}
}
