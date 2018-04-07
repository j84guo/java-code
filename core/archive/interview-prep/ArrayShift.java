import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ArrayShift {

    static void leftShift(int[] arr, int d){
        if(arr.length == 0 || arr == null){
            return;
        }
        
        for (int i=0; i<d; i++){
            int temp = arr[0];
            for(int j=1; j<arr.length; j++){
                arr[j-1] = arr[j];
            }
            arr[arr.length-1] = temp;
        }
    }

    static void leftShiftAlt(int[] arr, int d){
        if (arr.length == 0 || arr == null){
            return;
        }        

        int[] temp = arr.clone();

        for(int i=0; i<temp.length; i++){
            arr[(i-d+temp.length)%temp.length] = temp[i];
        }
    }
    
    static void rightShift(int[] arr, int d){
        if (arr.length == 0 || arr == null){
            return;
        }        

        int[] temp = arr.clone();

        for(int i=0; i<temp.length; i++){
            arr[(i+d)%temp.length] = temp[i];
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int arr[] = new int[n];
        for(int i=0; i < n; i++){
            arr[i] = in.nextInt();
        }
        
        leftShiftAlt(arr, d);
        
        for(int i=0; i<n; i++){
            System.out.print(arr[i] + " ");
        }

    }
}
