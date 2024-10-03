package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

public class RemoveDuplicateInArray {
    public static void main(String[] args) {
        removeDuplicate();
    }

    public static void removeDuplicate() {
   ArrayList<Integer> value = new ArrayList<>(Arrays.asList(13, 7, 6, 18, 1,1,2,34,77));
        LinkedHashSet<Integer> element = new LinkedHashSet<>(value);
        value.clear();
        value.addAll(element);
       // Collections.sort(value);
        System.out.println("After removing duplicate: "+value);


    }
}
