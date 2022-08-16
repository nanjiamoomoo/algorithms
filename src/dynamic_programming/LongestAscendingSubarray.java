package dynamic_programming;

public class LongestAscendingSubarray {
    /**
     * Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.
     * Example:
     * {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
     * @param array
     * @return
     */
    public int longestAscendingSubarray(int[] array) {
        // the subarray must be consecutive in the original array
        // we can use dp to solve the problem
        // dp[i] represents the longest ascending subarray ends at i-th index, including i position
        if (array.length == 0) {
            return 0;
        }
        int[] dp = new int[array.length];
        dp[0] = 1;
        //induction rules
        /*
            dp[i] =
                if(array[i] >= array[i - 1]) {
                dp[i] = dp[i - 1] + 1; //inherit
                else
                dp[i] = 0
         */

        int longestSubarray = 1;
        for (int i = 1; i < dp.length; i++) {
            if (array[i] > array[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            longestSubarray = Math.max(longestSubarray, dp[i]);
        }
        return longestSubarray;
    }
}
