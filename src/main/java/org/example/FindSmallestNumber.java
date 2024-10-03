package org.example;

import java.util.Arrays;

public class FindSmallestNumber {
    public static void main(String[] args) {
        findSmallestNumber();
    }
//Find smallest using sort and first index
    public static void findSmallestNumber() {
        int[] arrayValues = {13, 7, 6, 18, 1};
Arrays.sort(arrayValues);
        System.out.println("Smallest Number: "+arrayValues[0]);

    }
    
}
