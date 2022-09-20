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
                if sum == target, return
                otherwise we narrow down our search space

            Eventually there are two potential pairs we need to compare
            e.g. 1  4  7, target = 9
            [1, 7] and and [4 7]


         */
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        boolean foundSmaller = false;
        boolean foundLarger = false;
        List<Integer> largerPair = new ArrayList<>();
        List<Integer> smallerPair = new ArrayList<>();
        while (left < right) {
            int sum = array[left] + array[right];
            if (sum == target) {
                return Arrays.asList(array[left], array[right]);
            } else if (sum < target) {
                smallerPair.clear();;
                smallerPair.add(array[left]);
                smallerPair.add(array[right]);
                foundSmaller = true;
                if (foundLarger) {
                    break;
                }
                left++;
            } else {
                largerPair.clear();;
                largerPair.add(array[left]);
                largerPair.add(array[right]);
                foundLarger = true;
                if (foundSmaller) {
                    break;
                }
                right--;
            }
        }
        int larger =  largerPair.size() == 0? Integer.MAX_VALUE : Math.abs(largerPair.get(0) + largerPair.get(1) - target);
        int smaller =  smallerPair.size() == 0? Integer.MAX_VALUE : Math.abs(smallerPair.get(0) + smallerPair.get(1) - target);
        return smaller <= larger ? smallerPair : largerPair;
    }
}
