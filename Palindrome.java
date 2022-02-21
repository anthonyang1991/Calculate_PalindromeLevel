package com.xtremax;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Palindrome
 * <p>
 * Palindrome is a word, phrase, or sequence that reads the same backward as forward
 * e.g. "madam", "nurses run", "23499432". When palindrome sequence is splitted in half,
 * the first sequence may be a palindrome too.
 * <p>
 * "Palindrome Level" is how many times a palindrome sequence can be splitted in half
 * until the first sequence from splitted is not a palindrome or
 * until the first sequence is just a character. Palindrome level IGNORES SPACE AND CASE INSENSITIVE.
 * (if the sequence length is odd number, then after split the sequence,
 * take the middle character to the first sequence: "madam" -> "mad")
 * <p>
 * For example:
 * - nurses run: nursesrun -> nurse (1 level)
 * - 451545154: 451545154 -> 45154 -> 451 (2 level)
 * - dam madam mad: dammadammad -> dammad -> dam (2 level)
 * <p>
 * Defined "String's Prefixes" as all prefixes of a string.
 * <p>
 * For example:
 * - abacaba: a, ab, aba, abac, abaca, abacab, abacaba
 * - nun in inun: n, nu, nun, nunin, nunini, nuninin, nunininu, nunininun
 * <p>
 * "Sum of the Palindrome Level" of all string's prefixes is calculated by
 * add all palindrome level of all prefixes of a string.
 * <p>
 * For example:
 * - abacaba: a (0), ab (0), aba (1), abac(0), abaca(0), abacab(0), abacaba(1)
 * => sum of palindrome level: 2
 * <p>
 * ================================ ATTENTION PLEASE ================================
 * <p>
 * Your ONLY task is to implement the following two methods:
 * 1. calculatePalindromeLevel
 * 2. sumOfPalindromeLevel
 * <p>
 * You are ALLOWED to
 * 1. Add new method(s) in this file.
 * 2. Add additional test case(s) in Main.
 * <p>
 * You are NOT ALLOWED to
 * 1. Add any new method in other files.
 * 2. Change signature of any existing methods.
 * Method signature includes
 * - method's name
 * - return type
 * - number of parameters
 * - parameters' type
 * - access modifier
 * <p>
 * --- Please make sure your code is error-free when built.
 */
public class Palindrome {

    public static void main(String[] args) {
        // Calculate Palindrome Level
        System.out.println("Calculate palindrome level");
        HashMap<String, Integer> testWord_1 = (HashMap<String, Integer>) createPalindromeLevelTestWords();
        for (String test : testWord_1.keySet()) {
            Integer value = testWord_1.get(test);
            checkAnswer(test, value, calculatePalindromeLevel(test));
        }

        System.out.println();

        // Sum of Palindrome Level
        System.out.println("Sum of palindrome level");
        HashMap<String, Integer> testWord_2 = (HashMap<String, Integer>) createSumOfPalindromeLevelTestWords();
        for (String test : testWord_2.keySet()) {
            Integer value = testWord_2.get(test);
            checkAnswer(test, value, sumOfPalindromeLevel(test));
        }
    }

    /**
     * CalculatePalindromeLevel
     * <p>
     * Return palindrome level of the sequence / word (case insensitive, space ignored).
     * Return 0 if string null or empty.
     */
    public static int calculatePalindromeLevel(String text) {
        // AMEND YOUR CODE BELOW

        String word = noSpaceString(text);

        int count = 0;

        if (isPalindrome(word) == true) {

            if (word.length() % 2 != 0) {
                int length = (word.length() / 2) + 1;

                String wordMiddleToFront = word.substring(0, length);

                count++;

                while (isPalindrome(wordMiddleToFront) == true) {

                    if (word.length() % 2 != 0) {
                        count++;
                        int length2 = (wordMiddleToFront.length() / 2);
                        wordMiddleToFront = wordMiddleToFront.substring(0, length2);

                    }


                }


            } else {
                int length = (word.length() / 2);
                count++;
                String wordMiddleToFront = word.substring(0, length);


                while (isPalindrome(wordMiddleToFront) == true) {

                    int length2 = (wordMiddleToFront.length() / 2);
                    wordMiddleToFront = wordMiddleToFront.substring(0, length2);
                    count++;
                }
            }
        }


        return count;


        // AMEND YOUR CODE ABOVE
    }

    /**
     * SumOfPalindromeLevel
     * <p>
     * Return sum of palindrome level of of all the string's prefixes (case insensitive, space ignored).
     * Return 0 if string null or empty.
     */
    public static int sumOfPalindromeLevel(String text1) {
        // AMEND YOUR CODE BELOW
        int sumOfPalindrome = 0;

        String word = noSpaceString(text1);
        String original = word;
        String text = "";


        for (int i = 0; i < original.length(); i++) {
            text += original.charAt(i);
            // System.out.println(text);
            if (isPalindrome(text)) {
                //System.out.println("This is a palindrome");
                //I have checked each outputline here and i believe that some answers in the testcase might be wrong.
                sumOfPalindrome++;


            }


        }


        int totalsumOfPalindrome = sumOfPalindrome - 1; // - 1 for the first char
        return totalsumOfPalindrome;

    }

   private static String noSpaceString(String str) {

        String noSpaceLowerStr;
        String noSpaceStr = str.replaceAll("\\s", "");
        noSpaceLowerStr = noSpaceStr.toLowerCase();
        return noSpaceLowerStr;

    }

   private static boolean isPalindrome(String str) {

        int i = 0, j = str.length() - 1;

        // While there are characters to compare
        while (i < j) {


            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }


    // YOU CAN AMEND YOUR TEST CASE BELOW
    // region Test Case
    private static Map<String, Integer> createPalindromeLevelTestWords() {
        return new HashMap<String, Integer>() {
            {
                put("nurses run", 1);
                put("dam madam mad", 2);
                put("Sitis sit is s it is sitis", 3);
            }
        };
    }

    private static Map<String, Integer> createSumOfPalindromeLevelTestWords() {
        return new HashMap<String, Integer>() {
            {
                put("nurses run", 1);
                put("dam madam mad", 3);
                put("Sitis sit is s it is sitis", 7);
            }
        };
    }
    // endregion
    // YOU CAN AMEND YOUR TEST CASE ABOVE

    // region Test Helpers
    private static void checkAnswer(String question, int expected, int answer) {
        // DO NOT CHANGE THIS PART OF CODE!
        if (answer == expected) {
            System.out.println(String.format("%s: %d (Right)", question, answer));
        } else {
            System.out.println(String.format("%s: %d (Wrong: expected %d)", question, answer, expected));
        }
    }
    // endregion
}

