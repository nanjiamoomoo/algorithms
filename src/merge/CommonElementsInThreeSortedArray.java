package merge;

import java.util.*;

public class CommonElementsInThreeSortedArray {

    /**
     * Find all common elements in 3 sorted arrays.
     * Assumptions:
     * The 3 given sorted arrays are not null
     * There could be duplicate elements in each of the arrays
     * Examples:
     * A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]
     * @param one
     * @param two
     * @param three
     * @return
     */
    public List<Integer> common(int[] one, int[] two, int[] three) {
        /*
              Sort 3 arrays
              and user 3 points and move the smaller one
               e.g.
                2  2  3
                        i
                2  3  4
                   j
                2  3  5
                   k

                2  3
         */
        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < one.length && j < two.length && k < three.length) {
            if (one[i] == two[j] && two[j] == three[k]) {
                res.add(one[i]);
                i++;
                j++;
                k++;
            } else if (one[i] <= Math.min(two[j], three[k])) {
                i++;
            } else if (two[j] <= Math.min(one[i], three[k])) {
                j++;
            } else {
                k++;
            }
        }
        return res;

        //TC: O(n)
        //SC: O(1)
    }

    /*
     method 2, Iterative Reduction. If the original is not sorted, we can use this method.
     */
    public List<Integer> commonI(int[] one, int[] two, int[] three) {
        //find common in one and two first
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: one) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> commonOneAndTwo = new ArrayList<>();
        for (int num : two) {
            Integer count = map.get(num);
            if (count != null && count > 0) {
                commonOneAndTwo.add(num);
                map.put(num, --count);
            }
        }
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num : commonOneAndTwo) {
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : three) {
            Integer count = map2.get(num);
            if (count != null && count > 0) {
                res.add(num);
                map2.put(num, --count);
            }
        }
        return res;
    }
    //TC: O(n)

}
