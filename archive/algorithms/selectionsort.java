import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class selectionsort {

    public static void sort(int[] num) {
        int i, j, first, temp;
        for (i = num.length - 1; i > 0; i--) {
            first = 0; //initialize to subscript of first element
            for (j = 1; j <= i; j++) //locate largest element between positions 1 and i.
            {
                if (num[j] > num[first])
                    first = j;
            }
            temp = num[first]; //swap largest found with element in position i.
            num[first] = num[i];
            num[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {
            4,
            2,
            3,
            6,
            3,
            2,
            2,
            1,
            2,
            1,
            0,
            89
        };
        sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}