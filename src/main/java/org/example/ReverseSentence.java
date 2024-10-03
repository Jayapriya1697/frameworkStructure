package org.example;

public class ReverseSentence {
    public static void main(String[] args) {
        reverseSentenceByWord();
    }
//method to reverse the sentence using StringBuilder
    public static void reverseSentence(){
        String sentenceName="my name is jaya";
StringBuilder reversedSentence=new StringBuilder(sentenceName);
    String value = String.valueOf(reversedSentence.reverse());

        System.out.println("reversed Sentence: "+value);
        //output:reversed Sentence: ayaj si eman ym

    }

//method to reverse the word in sentence using StringBuilder


    public static void reverseSentenceByWord(){
        String sentenceName="my name is jaya";
   String[] reverseSpittedWord=sentenceName.split("");
   StringBuilder reversedSentence=new StringBuilder();
   for(int i=reverseSpittedWord.length-1;i>=0;i--){
       reversedSentence.append(reverseSpittedWord[i]).append(" ");
        }
        System.out.println("reversed number:"+reversedSentence.toString());
    }

}