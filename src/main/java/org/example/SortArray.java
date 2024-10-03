package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.sort;

public class SortArray {
    public static void main(String[] args) {

        sortArrayList();
    }
    public static void sortArray() {
        int[] arrayValues = {13, 7, 6, 18, 1};
        Arrays.sort(arrayValues);
        System.out.println("Sorted Array: "+Arrays.toString(arrayValues));
    }
    public static void sortArrayList() {
       ArrayList<Integer> value= new ArrayList<>(Arrays.asList(13, 7, 6, 18, 1));

   value.add(1);
    value.add(2);
        Collections.sort(value);
        System.out.println("Sorted Array: "+value);

    }

}
