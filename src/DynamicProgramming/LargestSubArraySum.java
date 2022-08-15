package DynamicProgramming;

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
}
