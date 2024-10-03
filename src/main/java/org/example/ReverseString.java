package org.example;

public class ReverseString {
    public static void main(String[] args) {
        reverseMyName();
    }

    //---------------------------------------------------------------------------------------------------
    //method to reverse the string using StringBuilder
    // StringBuilder is mutable, so we have used to reverse the string.String is immutable so we converted to mutable using StringBuilder


    public static void reverseMyName(){
        String name="JAYAPRIYA";
        StringBuilder reversedName=new StringBuilder(name);

     String value= String.valueOf(reversedName.reverse());
        reversedName.reverse();
       System.out.println("reversed Name: "+value);
        System.out.println("reversed Name is: "+reversedName.toString());
    }
    //method to reverse the string using charAt
    public static void reverseMyNameUsingCharAt(){
        String name="JAYAPRIYA";
        String reversedName="";
        for(int i=name.length()-1;i>=0;i--){
            reversedName=reversedName+name.charAt(i);
        }
        System.out.println("reversed Name: "+reversedName);
    }

    //method to reverse the string using collectors
    public static void reverseMyNameUsingCollectors(){
        String name="JAYAPRIYA";
        String reversedName= new StringBuilder(name).reverse().toString();
        System.out.println("reversed Name: "+reversedName);
    }

//----------------------------------------------------------------------------------------------------


}