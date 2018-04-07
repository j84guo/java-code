/**
 ** Java Program to Implement Radix Sort
 **/
 
import java.util.Scanner;
 

public class MyRadixSort {

    public static void sort( int[] a){

        int i; //for loops
        int m = a[0] //max
        int exp = 1 //digit
        int n = a.length; //size
        int[] b = new int[10]; //output
        
        //get max
        for (i = 1; i < n; i++)
            if (a[i] > m)
                m = a[i];

        //iterate through width
        while (m / exp > 0){

            //create buckets
            int[] bucket = new int[10];
 
            //use current digit as index in count array 
            for (i = 0; i < n; i++)
                bucket[(a[i] / exp) % 10]++;
            
            //sum
            for (i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            
            //fill output
            for (i = n - 1; i >= 0; i--)
                b[--bucket[(a[i] / exp) % 10]] = a[i];
            
            //copy output to original
            for (i = 0; i < n; i++)
                a[i] = b[i];
            
            //next digit
            exp *= 10;        
        }
    }

    public static void main(String[] args) {

        int arr[] = {123, 13, 3, 34, 983, 29381, 31, 998, 2};
        sort(arr);       
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i]+" ");                             
    }    
}