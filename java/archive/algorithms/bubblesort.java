import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class bubblesort{

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

	public static void main(String[] args){
		int[] array = {4, 2, 3, 6, 3, 2, 2, 1, 2, 1, 0, 89};
		sort(array);
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i]);
		}
	}
}