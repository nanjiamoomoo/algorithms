package sum;

import java.util.Arrays;

public class ThreeSumClosest {
    /**
     * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
     *
     * Return the sum of the three integers.
     *
     * You may assume that each input would have exactly one solution.
     * Input: nums = [-1,2,1,-4], target = 1
     * Output: 2
     * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        /*
            Sort first
            then we fix one element (the most right duplicate elements)

            then we find the two sum closest to the complement target - array[i]
        */
        Arrays.sort(nums);
        int abs = Integer.MAX_VALUE;
        int[] res = new int[3];
        for (int i = 2; i < nums.length; i++) {
            while (i < nums.length - 1 && nums[i + 1] == nums[i]) {
                i++;
            }
            int left = 0;
            int right = i - 1;
            while (left < right) {
                int twoSum = nums[left] + nums[right];
                if (twoSum + nums[i] == target) {
                    return target;
                }
                if (Math.abs(target - nums[i] - twoSum) < abs) {
                    abs = Math.abs(target - nums[i] - twoSum);
                    res[0] = nums[left];
                    res[1] = nums[right];
                    res[2] = nums[i];
                }
                if (twoSum < target - nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }

        }
        return res[0] + res[1] + res[2];
    }
}
