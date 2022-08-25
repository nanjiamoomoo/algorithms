package merge;

import java.util.*;

public class CommonNumbers {

    /**
     * Find all numbers that appear in both of the two unsorted arrays, return the common numbers in increasing order.
     * Assumptions:
     * 1. Both arrays are not null.
     * 2. There are no duplicate numbers in each of the two arrays respectively
     * Examples:
     * A = {1, 2, 3}, B = {3, 1, 4}, return [1, 3]
     * A = {}, B = {3, 1, 4}, return []
     * @param one
     * @param two
     * @return
     */
    public List<Integer> commonNumbersI(int[] one, int[] two) {
        /*

                without sorting, we cannot compare one by one
                method1:
                    sort both array first, then use two pointers, to compare one by one
                method2(only valid if there are no duplicate numbers in each array):
                We can use a hashSet to keep all numbers in array one and then check array two for common numbers
                then sort the final result.
         */
        Set<Integer> set = new HashSet<>();
        for (int num : one) {
            set.add(num);
        }
        List<Integer> res = new ArrayList<>();
        for (int num: two) {
            if (set.contains(num)) {
                res.add(num);
            }
        }
        Collections.sort(res);
        return res;
    }
    //TC: nlogn
    //SC: O(n)

    /**
     * Find all numbers that appear in both of two unsorted arrays.
     * Assumption:
     * 1. Both of the two arrays are not null.
     * 2. In any of the two arrays, there could be duplicate numbers.
     * Examples:
     * A = {1, 2, 3, 2}, B = {3, 4, 2, 2, 2}, return [2, 2, 3] (there are both two 2s in A and B)
     * @param one
     * @param two
     * @return
     */
    public List<Integer> common(int[] one, int[] two) {
        /*method1: two pointers

                1. sort both array first
                2. use two pointer
          method2: hashmap to keep numbers in one array and count
         */

//        Arrays.sort(one);
//        Arrays.sort(two);
//        List<Integer> res = new ArrayList<>();
//        int i = 0;
//        int j = 0;
//        while (i < one.length && j < two.length) {
//            if (one[i] == two[j]) {
//                res.add(one[i]);
//                i++;
//                j++;
//            } else if (one[i] < two[j]) {
//                i++;
//            } else {
//                j++;
//            }
//        }
//        return res;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : one) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : two) {
            Integer count = map.get(num);
            if (count != null && count > 0) {
                res.add(num);
                map.put(num, --count);
            }
        }
        Collections.sort(res);
        return res;
    }

}
