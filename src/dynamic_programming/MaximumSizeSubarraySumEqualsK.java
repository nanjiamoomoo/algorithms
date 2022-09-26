package dynamic_programming;

public class MaximumSizeSubarraySumEqualsK {

    /**
     * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
     *
     * Note:
     * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
     *
     * Example 1:
     *
     * Given nums = [1, -1, 5, -2, 3], k = 3,
     * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums, int k) {
    /*
      Step1: define an array prefixSum[i] represents the sum between 0th index to the ith index
      Step2：for each subarray, from longest to smallest to check if there is smum equals to k
        if we find one, return

      TC: O(n ^ 2)
    */
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        for (int len = nums.length; len >= 1; len--) {
            for (int end = nums.length - 1; end >= len - 1; end--) {
                int currSum = prefixSum[end] - prefixSum[end - len + 1] + nums[end - len + 1];
                if (currSum == k) {
                    return len;
                }
            }
        }
        return 0;
    }

    /*
        Can we do it in O(n) time

        We can use hashmap based on the prefixSum array
        prefixSum[j] - prefixSum[i] = k
        we can traverse the prefixSum[i] and add <prefixSum[i], i> in the map,
        as long as we can make sure for the same prefixSum, i is as small as possible.

        TC: O(n)
        SC: O(n)
        public int maxSubArrayLen(int[] nums, int k) {
         if (nums == null || nums.length == 0) {
          return 0;
        }
        int sum = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
          sum += nums[i];
          if (map.containsKey(sum - k)) {
            len = Math.max(len, i - map.get(sum- k));
          } else if (!map.containsKey(sum)) {
            map.put(sum, i);
          }
        }

        return len;
      }

     */

}
