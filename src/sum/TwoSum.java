package sum;

import java.util.*;

/**
 * Find two elements that equal to a target number related problem
 */
public class TwoSum {

    /**
     * Determine if there exist two elements in a given array, the sum of which is the given target number.
     * Assumption: The given array is not null and has length of at least 2
     * Examples:
     * A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)
     * A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)
     * A = {2, 4, 1}, target = 4, return false
     * @param array
     * @param target
     * @return Return true if there exists the sum. Return false if does not exist
     */
    public boolean existSum(int[] array, int target) {
        /*
                The naive way is to pick to elements and check with the target, but the time complexity is n ^ 2.

                The better way:
                    a + b = target --> target - a = b
                    a is the current number we are visiting, b is a number that we have visited
                    can we use a set to keep all the visited number,
                    as long as target - a is in the set, means here exist two elements in a given array, the sum of which is the given target number.\

                    TC: O(n)
                    SC: O(n)
         */
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (visited.contains(target - array[i])) {
                return true;
            } else {
                visited.add(array[i]);
            }
        }
        return false;

        /*
               Arrays.sort(array);
               int i = 0;
               int j = array.length - 1;
               while (i < j) {
                    if (array[i] + array[j] == target) {
                           true;
                    } else if (array[i] + array[j] < target) {
                        i++;
                    }
                    j--;
               }
               return false;
         */
    }



    /**
     * Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.
     * Assumptions: The given array is not null and has length of at least 2.
     * Examples:
     * 1. A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
     * 2. A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
     * @param array
     * @param target
     * @return Return all the pairs of indices
     */
    public List<List<Integer>> twoSumALLPairs(int[] array, int target) {
       /*
            Can we just use a HashSet to store all the elements visited t
            N, there are Two reasons:
               we need to find indices
               there might be duplicate elements, Set does not all duplicate elements

            Solution:
            We can use HashMap<Integer, List<Integer>> map to solve the problem/
            The map contains the <Value, List of Indices> pair, so we can still can use the same idea
            a + b = target --> target - a = b
            b is the visited element, we store b as the key in the map with all the indices in the array with value b.
            for each element we are visiting , if target - a is in the map, then we combine all the indices of b with a. And add each pair in the final result.
        */
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            List<Integer> indices = map.get(target - array[i]);
            if (indices != null) {
                for (Integer index : indices) {
                    res.add(Arrays.asList(index, i));
                }
            }
            /*
                no matter if we find a valid result, we need to add current visiting element in the map.
             */
            List<Integer> curr = map.get(array[i]);
            if (curr == null) {
                curr = new ArrayList<>();
                map.put(array[i], curr);
            }
            curr.add(i);
        }
        return res;
    }

    /**
     * Find all pairs of elements in a given array that sum to the pair the given target number. Return all the distinct pairs of values.
     * Assumption:
     *  The given array is not null and has length of at least 2
     *  The order of the values in the pair does not matter
     * Examples:
     *  A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
     * @param array
     * @param target
     * @return Return all the distinct pairs of values.
     */
    public List<List<Integer>> twoSumALLPairsII(int[] array, int target) {
       /*

            we can use a set to store all the visited elements
            once we find a valid pair, we should
                1. add it in the final result
                2. we should mark the element in the set, so that it will never be used to generate a valid pair
                3. we don't need to add the current element in the set, because we will not generate duplicate values

             Based on above analysis, we can use map<Integer, Boolean>
             if boolean is false, it indicates this element has never been paired
             if boolean is true, means it has been paired, should not be used again

             TC: O(n)
             SS: O(n)
        */
        Map<Integer, Boolean> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(target - array[i])) {
                boolean flag = map.get(target - array[i]);
                if (!flag) {
                    res.add(Arrays.asList(target - array[i], array[i]));
                    map.put(target - array[i], true);
                }
                continue;
                /*
                     do we need to add the curren element in the map?
                     if we don't add, will it cause issues?
                     what if we will visit another target - array[i], will it cause issue?
                        no, because we will never add it to the result any way and based on the logic below, we will not re-add it to the map either

                 */
            }
            //we should only add new element
            if (!map.containsKey(array[i])) {
                map.put(array[i], false);
            }
        }
        return res;


        /*
            Solution 2:
                we also can use two Hashset as well, one is to store all the visited elements, the other one to store all the printed elements.
         */
    }

}
