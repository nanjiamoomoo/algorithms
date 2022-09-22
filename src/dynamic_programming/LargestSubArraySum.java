package dynamic_programming;

public class LargestSubArraySum {

    /**
     * Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.
     *
     * Example:
     * {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5
     * @param array
     * @return
     */
    public int largestSubarraySum(int[] array) {

        //largestSubarraySum[i] represents the largest subarray sum ending at index i, including ith position
        int[] largestSubarraySum = new int[array.length];

        /*
            induction rule
                largestSubarraySum[i] =
                    as long as the largest subarray sum at i - 1 is not smaller than zero, we should inherit the previous sum
                    if (largestSubarraySum[i - 1]>= 0 largestSubarraySum = largestSubarraySum[i - 1] + array[i]
         */
        int largestSum = array[0];
        largestSubarraySum[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            if (largestSubarraySum[i - 1] >= 0) {
                largestSubarraySum[i] = largestSubarraySum[i - 1] + array[i];

            } else {
                largestSubarraySum[i] = array[i];
            }
            largestSum = Math.max(largestSum, largestSubarraySum[i]);
        }
        return largestSum;
    }

    /**
     * Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum and the indices of the left and right boundaries of the subarray. If there are multiple solutions, return the leftmost subarray.
     *
     * Assumptions
     *
     * The given array is not null and has length of at least 1.
     * Examples
     *
     * {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5. The indices of the left and right boundaries are 0 and 2, respectively.
     *
     * {-2, -1, -3}, the largest subarray sum is -1. The indices of the left and right boundaries are both 1
     *
     * @param array
     * @return the result in a array as [sum, left, right]
     */
    public int[] largestSubArraySumII(int[] array) {
        /*
            use dp to solve the problem
            if dp[i - 1] < 0, dp[i] = array[i]
            if dpi[i - 1] >= 0, dp[i] = dp[i - 1] + array[i]

            we can further optimize using another variable prev to represents dp[i -1]

            TC: O(n)
            SC: O(1)
         */

        int[] res = {Integer.MIN_VALUE, 0, 0};
        int prev = 0;
        for (int i = 0; i < array.length; i++) {
            int curr;
            if (prev >= 0) {
                curr = prev + array[i];
                if (curr > res[0]) {
                    res[0] = curr;
                    res[2] = i;
                }
            } else {
                curr = array[i];
                if (curr > res[0]) {
                    res[0] = curr;
                    res[1] = i;
                    res[2] = i;
                }
            }
            prev = curr;
        }
        return res;
    }
}
