package sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumClosest {

    /**
     * Find the pair of elements in a given array that sum to a value that is closest to the given target number. Return the values of the two numbers.
     * Assumption:
     * The given array is not null and has length of at least 2
     *
     * Examples:
     * A = {1, 4, 7, 13}, target = 7, closest pair is 1 + 7 = 8, return [1, 7].
     *
     * @param array
     * @param target
     * @return
     */
    public List<Integer> closest(int[] array, int target) {

        /*
            Step1: sort
            Step2: use two pointers one on the left and one on the right to find the closes pairs


         */
        Arrays.sort(array);
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = array.length - 1;
        int abs = Math.abs(array[left] + array[right] - target);
        res.add(array[left]);
        res.add(array[right]);
        while (left < right) {
            int twoSum = array[left] + array[right];
            if (twoSum == target) {
                res.clear();
                res.add(array[left]);
                res.add(array[right]);
                return res;
            }

            if (Math.abs(twoSum - target) < abs) {
                res.clear();
                res.add(array[left]);
                res.add(array[right]);
            }
            if (twoSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
