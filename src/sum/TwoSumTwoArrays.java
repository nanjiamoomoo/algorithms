package sum;

import java.util.HashSet;
import java.util.Set;

public class TwoSumTwoArrays {

    /**
     * Given two arrays A and B, determine whether or not there exists a pair of elements, one drawn from each array, that sums to the given target number.
     * The two given arrays are not null and have length of at least 1
     * @param a
     * @param b
     * @param target
     * @return
     */
    public boolean existSum(int[] a, int[] b, int target) {
        /*
            Brutal force: O(m * n)

            Better solution:
            Sort first
            Then use priority queue to find the next smaller elements:
            worst scenario: mn*log(m + n), but it will much likely take less time

            Best solution:
            add all element in array a[] in a set
            then check if there is an element in b such that Xa + Xb = target
            O(max(m, n))
         */
        Set<Integer> set = new HashSet<>();
        for (int num : a) {
            set.add(num);
        }

        for (int num : b) {
            if (set.contains(target - num)) {
                return true;
            }
        }
        return false;
    }
}
