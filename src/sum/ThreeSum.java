package sum;

import java.util.*;

/**
 * Find three elements in a given array that sum to the given target number
 */
public class ThreeSum {
    /**
     * Find three elements in a given array that sum to the given target number
     * Assumption:
     * 1. The given array is not null and has length of at least 3
     * 2. No duplicate triples should be returned, order of the values in the tuple does not matter
     */
    public List<List<Integer>> threeSums(int[] array, int target) {
        /*
                a + b + c = target
                if we can constrain c, this problem becomes a two sum problem
                a + b = target - c

                how do we make sure there are no duplicate elements in the final result,
                we can sort the original array and make sure c is the last element of the repeat values

                for each c we can do two sum for the target - c
                a + b = twoSum
                we can use a HashMap<Integer, Boolean>
                the first integer is the value of the element visited
                the second integer is to represent if the current key has been added to the final result.
                    in the beginning, it is false, once we add it to the final result, we change it to true.
                    as long as map.containsKey(twoSum - b) and the boolean value of twoSum-b is false, we add to the final result
                for each new visited element, we only need to add the values that not in the map.

                TC: O(n ^ 2)
                SC: O(n)
         */
        Arrays.sort(array);
        List<List<Integer>> res = new ArrayList<>();
        for (int j = 2; j < array.length; j++) {
            //make sure j always points to the last consecutive repeated elements
            while (j < array.length && array[j] == array[j + 1]) {
                j++;
            }
            Map<Integer, Boolean> map = new HashMap<>();
            int twoSum = target - array[j];
            for (int i = 0; i < j; i++) {
                Boolean flag = map.containsKey(twoSum - array[i]);
                if (flag != null) {
                    if (!flag) {
                        res.add(Arrays.asList(array[i], twoSum - array[i], array[j]));
                        map.put(twoSum - array[i], true);
                    }
                    continue;
                }
                if (!map.containsKey(array[i])) {
                    map.put(array[i], false);
                }
            }
        }
        return res;

        /*
            Second solution:
               we can use a HashMap<Integer, Integer>
                the first integer is the value of the element visited
                the second integer is to represent the count of elements visited with current key
                    conditions to add in the final result
                    1. if x + y == twoSum, but x != y, then when count of x = 0, we can add in the result
                    2. if x + x = twoSum, then when count of x = 1, we can add it in the result.

            Map<Integer, Integer> map = new HashMap<>();
            int twoSum = target - array[j];
            for (int i = 0; i < j; i++) {
                Integer count = map.getOrDefault(array[i], 0);
                if (array[i] == twoSum - array[i] && count == 1) {
                     res.add(Arrays.asList(array[i], array[i], array[j]));
                } else if (map.containsKey(twoSum - array[i]) && count == 0) {
                    res.add(Arrays.asList(array[i], twoSum - array[i], array[j]));
                }
                map.put(array[i], count + 1);
                  TC: O(n ^ 2)
                SC: O(n)
            }
         */

        /*
            third solution:
             since the array has been sorted, we can use two pointer to solve the problem
               TC: O(n ^ 2)
                SC: O(1)
         */
    }
}
