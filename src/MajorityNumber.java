import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityNumber {

    /**
     * Given an integer array of length L, find the number that occurs more than 0.5 * L times.
     *
     * Assumption:
     * The given array is not null or empty
     * It is guaranteed there exists such a majority number
     *
     * Examples:
     * A = {1, 2, 1, 2, 1}, return 1
     * @param array
     * @return
     */
    public int majorityI(int[] array) {
        /*
            Naive solution: count each element count using hashmap. find the one with count more than 0.5L

            We can use majority voting algorithm
            if an element's count is bigger than 0.5L, if we neutralize the element with other elements, the remaining element will be the target
         */

        int count = 0;
        int target = array[0];
        for (int i = 0; i < array.length; i++) {
            if (target == array[i]) {
                count++;
            } else if (count == 0) {
                target = array[i];
            } else {
                count--;
            }
        }
        return target;
    }

    /**
     * Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.
     * Assumption:
     * The given array is not null
     *
     * Examples:
     * A = {1, 2, 1, 2, 1}, return [1, 2]
     * A = {1, 2, 1, 2, 3, 3, 1}, return [1]
     * A = {1, 2, 2, 3, 1, 3}, return []
     * @param array
     * @return
     */
    public List<Integer> majority(int[] array) {
        /*
            naive method: use hashmap
         */
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>();
//        for (int i = 0; i < array.length; i++) {
//            count.put(array[i], count.getOrDefault(array[i], 0) + 1);
//        }
//
//        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
//            if (entry.getValue() > array.length / 3) {
//                res.add(entry.getKey());
//            }
//        }
//        return res;
//
        /*
            Actually we don't have to traverse twice. we can only traverse once to get the result
            if an integer is in the map, but has a null value, means this integer occurs more than 1/3 * L times and we have added it in the result
         */
        int limit = array.length / 3;
        for (int num : array) {
            if (!count.containsKey(num)) {
                //if an integer is not in the map, we should add it in the map. Should we add it in the result? it depends.
                //if 1 > limit, we should add
                if (1 > limit) {
                    count.put(num, null);
                    res.add(num);
                } else {
                    count.put(num, 1);
                }
            } else {
                Integer numCount = count.get(num);
                //we only care when the numCount != null
                if (numCount != null) {
                    numCount++;
                    if (numCount > limit) {
                        count.put(num, null);
                        res.add(num);
                    } else {
                        count.put(num, numCount);
                    }
                }
            }
        }
        return res;
    }

    /**
     * Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.
     *
     * Assumption:
     * The given array is not null or empty
     * K >= 2
     *
     * Examples:
     * A = {1, 2, 1, 2, 1}, K = 3, return [1, 2]
     * A = {1, 2, 1, 2, 3, 3, 1}, K = 4, return [1, 2, 3]
     * A = {2, 1}, K = 2, return []
     * @param array
     * @param k
     * @return
     */
    public List<Integer> majority(int[] array, int k) {
        /*
             we can use a hashmap to record the count of each element.
             if an element count is over 1/k * L, we can change the count to null to indicate we have added to the result
         */
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>();
        int limit = array.length / k;
        for (int num : array) {
            if (!count.containsKey(num)) {
                //if an integer is not in the map, we should add it in the map. Should we add it in the result? it depends.
                //if 1 > limit, we should add
                if (1 > limit) {
                    count.put(num, null);
                    res.add(num);
                } else {
                    count.put(num, 1);
                }
            } else {
                Integer numCount = count.get(num);
                //we only care when the numCount != null
                if (numCount != null) {
                    numCount++;
                    if (numCount > limit) {
                        count.put(num, null);
                        res.add(num);
                    } else {
                        count.put(num, numCount);
                    }
                }
            }
        }
        return res;
    }
}


