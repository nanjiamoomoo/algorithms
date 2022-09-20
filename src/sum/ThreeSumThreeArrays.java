package sum;

import java.util.HashSet;
import java.util.Set;

public class ThreeSumThreeArrays {

    /**
     * Given three arrays, determine if a set can be made by picking one element from each array that sums to the given target number.
     * Assumptions:
     * The three given arrays are not null and have length of at least 1
     *
     * Examples
     * A = {1, 3, 5}, B = {8, 2}, C = {3}, target = 14, return true(pick 3 from A, pick 8 from B and pick 3 from C)
     * @param a
     * @param b
     * @param c
     * @param target
     * @return
     */
    public boolean exist(int[] a, int[] b, int[] c, int target) {
        /*
            if we fix one element in an array and do two sum for the other two arrays.
            there might be duplicate elements in the array A, so we can put elements in a set

            when we do two sum, we can add elements in one array into another set to reduce the time complexity

         */

        Set<Integer> setA = new HashSet<>();
        for (int num : a) {
            setA.add(num);
        }

        Set<Integer> setB = new HashSet<>();
        for (int num : b) {
            setB.add(num);
        }

        for (int numA : setA) {
            for (int i = 0; i < c.length; i++) {
                if (setB.contains(target - c[i] - numA)) {
                    return true;
                }
            }
        }
        return false;
    }
}
