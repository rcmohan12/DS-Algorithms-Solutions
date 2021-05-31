package com.rcm.dsa.arrays;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] stats = new int[]{0,1,0,2,1,0,3,1,0,1,2};
        System.out.println("Max water: "+getMaxWater(stats));
    }

    private static int getMaxWater(int[] stats) {
        if(stats.length < 3)
            return 0;
//        return bruteForce(stats);
        return optimised(stats);
    }

    /**
     * Overall time complexity = O(n2)
     * @param stats
     * @return
     */
    private static int bruteForce(int[] stats) {
        int maxWater  = 0;
        int maxLeft = 0;
        int maxRight = 0;
        for(int x = 0; x < stats.length; x++) { //O(n2)
            maxLeft = 0;
            maxRight = 0;
            //check for max left
            for(int l = x; l >=0; l--) { // O(n)
                    maxLeft = Math.max(maxLeft, stats[l]);
            }
            //check for max right
            for(int r = x; r < stats.length; r++) { // O(n)
                    maxRight = Math.max(maxRight, stats[r]);
            }

            int currWater = Math.min(maxLeft, maxRight) - stats[x];
            if(currWater > 0) {
                maxWater = maxWater+ currWater;
            }
        }
        return maxWater;
    }

    /**
     * Total Time complexity : O(n)
     * @param stats
     * @return
     */
    private static int optimised(int[] stats) {
        int maxWater = 0;
        int p1 = 0;
        int p2 = stats.length-1;
        int maxLeft = 0;
        int maxRight = 0;

        for(int x = 0; x <stats.length; x++) {
            if(stats[p1] < stats[p2]
            || stats[p1] == stats[p2]) {

                if(maxLeft > stats[p1]) {
                    maxWater = maxWater + ((maxLeft - stats[x]) > 0 ? (maxLeft - stats[x]) : 0);
                } else {
                    maxLeft = stats[p1];
                }

                p1++;

                continue;
            }

            if(stats[p1] > stats[p2]) {

                if(maxRight > stats[p2]) {
                    maxWater = maxWater + ((maxRight - stats[x]) > 0 ? (maxRight - stats[x]) : 0);
                } else {
                    maxRight = stats[p2];
                }

                p2--;

                continue;
            }


        }

        return maxWater;
    }


}
