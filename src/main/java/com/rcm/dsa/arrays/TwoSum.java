package com.rcm.dsa.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers, return the indices of the two numbers that add up to a given target.
 *
 * QUE --> [1,3,7,9,2] --> 11
 * ANS --> [3,4]
 *
 * Constrains :
 * 1. Are all numbers +ve's or can there be -ve's?
 * 2. Are there duplicate numbers?
 * 3. Will there always be a solution available?
 * 4. What to return when there is no solution?
 * 5. Can there be multiple pairs that add up to the required target?
 * 6. Collect test cases
 *
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] input = new int[]{1,3,7,9,2};
        int requiredSum = 11;
        System.out.println("RES -->"+twoSum(input, requiredSum));
    }

    public static int[] twoSum(int[] input, int requiredSum) {

        //Cover all edge cases
        if(input.length == 0 || input.length == 1)
            return null;
        if(input.length == 2) {
            if(input[0] + input[1] == requiredSum) {
                return new int[]{0,1};
            } else {
                return null;
            }
        }

//        return bruteForce(input, requiredSum);
        return optimised(input, requiredSum);

    }

    /**
     * One pointer approach
     * Space complexity = time complexity = O(n)
     * @param input
     * @param requiredSum
     * @return
     */
    private static int[] optimised(int[] input, int requiredSum) {
        Map<Integer, Integer> complementMap = new HashMap<>(); // O(n)

        for(int p1 = 0; p1 < input.length; p1++) {  // O(n)
            int complement = requiredSum - input[p1];
            if(complementMap.containsKey(input[p1])) {
                System.out.println("Indices = "+p1+", "+complementMap.get(input[p1]));
                return new int[]{p1, complementMap.get(p1)};
            } else {
                complementMap.put(complement,p1);
            }
        }

        return null;
    }


    /**
     * Double pointer technique
     * Time complexity --> O(n2)
     * Space complexity --> O(1) (This is usually a sign that we can compromise on this value to improve time complexity)
     * @param input
     * @param requiredSum
     * @return
     */
    private static int[] bruteForce(int[] input, int requiredSum) {
        for(int p1 = 0; p1 < input.length; p1++) { //O(n)
            if(p1+1<input.length) {
                int toFind = requiredSum - input[p1];
                for(int p2 = p1+1; p2 < input.length; p2++) {  //O(n-p) --> O(n)
                    if(input[p2] == toFind) {
                        System.out.println("Indices = "+p1+", "+p2);
                        return new int[]{p1, p2};
                    }
                }
            } else {
                return null;
            }
        }
        return null;
    }

}
