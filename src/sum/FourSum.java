package sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Find four elements in a given array that sum to the given target number
 */
public class FourSum {
    /**
     * Determine if there exists a set of four elements in a given array that sum to the given target number.
     * Assumption: The given array is not null and has length of at least 4
     * @param array
     * @param target
     * @return return true if there is such a set.
     */
    public boolean fourSum(int[] array, int target) {
        /*
            try to convert the problem into a three sum problem
            TC: O(n ^ 3)
         */
        /*
        Arrays.sort(array);
        for (int k = 3; k < array.length; k++) {
            while (k < array.length - 1 && array[k] == array[k + 1]) {
                k++;
            }
            for (int j = 2; j < k; j++) {
                while (j < k - 1 && array[j] == array[j + 1]) {
                    j++;
                }
               int left = 0;
                int right = j - 1;
                while (left < right) {
                    if (array[left] + array[right] == target - array[k] - array[j]) {
                        return true;
                    } else if (array[left] + array[right] < target - array[k] - array[j]) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return false;
        */

        /*
            can we convert the problem into twoSum questions?
            we can find two pairs<i1, j1> < i2, j2> such that array[i1] + array[j1] + array[i2] + array[j2] = target
            in the meanwhile i1 < j1 < i2 < j2

            we store <i1, j1> pair sum in the hashMap, in details, we want to make sure j1 is as small as possible.
            Then we can use two sum to try to find another pair sum that equals to the target - previous pair and also the i2 > j1, then we find a valid pair
         */
        Map<Integer, Pair> map = new HashMap<>();
        for (int j = 1; j < array.length; j++) {
            for (int i = 0; i < j; i++) {
                if (!map.containsKey(array[i] + array[j])) {
                    map.put(array[i] + array[j], new Pair(i, j));
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (map.containsKey(target - array[i] - array[j]) && i > map.get(target - array[i] - array[j]).j) {
                    return true;
                }
            }
        }
        return false;
    }

    class Pair {
        int i;
        int j;
        public Pair(int i , int j) {
            this.i = i;
            this.j = j;
        }
    }
}
