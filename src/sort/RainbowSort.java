package sort;

import java.util.HashMap;
import java.util.Map;

public class RainbowSort {

    /**
     * Given an array of balls, where the color of the balls can only be Red, Green or Blue, sort the balls such that all the Red balls are grouped on the left side, all the Green balls are grouped in the middle and all the Blue balls are grouped on the right side. (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).
     * <p>
     * Assumption:
     * The input array is not null.
     * <p>
     * Examples:
     * {0} is sorted to {0}
     * {1, 0} is sorted to {0, 1}
     * {1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}
     *
     * @param array
     * @return
     */
    public int[] rainbowSortI(int[] array) {

        /*
            We can use 3 pointers to separate the original array into 4 sections;
            [0, i) should contains only -1
            [i, j) should contains only 0
            [j, k] are the section we need to explore
            (k, n - 1] should contain only 1

            -1 -1 -1 0 0 x x x 1 1 1
                     i   j   k
            if (array[j] == -1),
         */

        int i = 0;
        int j = 0;
        int k = array.length - 1;
        while (j <= k) {
            if (array[j] == -1) {
                swap(array, i++, j++);
            } else if (array[j] == 0) {
                j++;
            } else {
                swap(array, j, k--);
            }
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Given an array of balls, where the color of the balls can only be Red, Green, Blue or Black, sort the balls such that all balls with same color are grouped together and from left to right the order is Red->Green->Blue->Black. (Red is denoted by 0, Green is denoted by 1,  Blue is denoted by 2 and Black is denoted by 3).
     * Examples:
     * {0} is sorted to {0}
     * {1, 0} is sorted to {0, 1}
     * {1, 3, 1, 2, 0} is sorted to {0, 1, 1, 2, 3}
     * Assumption: The input array is not null.
     *
     * @param array
     * @return
     */
    public int[] rainbowSortII(int[] array) {
        /*
            We can use four pointers, i, j, k, l
            [0, i) contains only 0
            [i, j) contains only 1
            [j, k) contains only 2
            (l, n - 1] contains only 3
            [k, l] are searching space

            0 0 0 0 1 1 1 1 2 2 2 2xxx 333
                    i       j      k  l

            travers using k and l


         */
        int i = 0;
        int j = 0;
        int k = 0;
        int l = array.length - 1;
        while (k <= l) {
            if (array[k] == 0) {
                swap(array, j, k++);
                swap(array, i++, j++);
            } else if (array[k] == 1) {
                swap(array, j++, k++);
            } else if (array[k] == 2) {
                k++;
            } else {
                swap(array, k, l--);
            }
        }
        return array;
    }


    /**
     * Given an array of balls with k different colors denoted by numbers 1- k, sort the balls.
     * Examples:
     * k=1, {1} is sorted to {1}
     * k=3, {1, 3, 2, 1, 2} is sorted to {1, 1, 2, 2, 3}
     * k=5, {3, 1, 5, 5, 1, 4, 2} is sorted to {1, 1, 2, 3, 4, 5, 5}
     * <p>
     * Assumption:
     * The input array is not null.
     * k is guaranteed to be >= 1.
     * k << logn.
     *
     * @param array
     * @param k
     * @return
     */
    public int[] rainbowSortIII(int[] array, int k) {
        /*
            Solution1 : Use a map to store the count for each element
            TC:O(n)
            SC:O(n)
         */
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : array) {
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//
//        int j = 0;
//        for (int i = 1; i <= k; i++) {
//            Integer count = map.get(i);
//            if (count == null) {
//                continue;
//            }
//            while (count-- != 0) {
//                array[j++] = i;
//            }
//        }
//        return array;


    /*
        Solution2: use an array of size k + 1 to keep the boundaries of each element
        [0, boundary[1]) keeps 1
        [boundary[1], boundary[2]) keeps 2
        ...
        (boundary[k - 1], boundary[k]) keeps k

        for each array[i] {
            for (int j = array[i]; j <= k; j++) {
                boundary[j]++;
            }
        }

        int index = 0;
        for (int i = 1; i <=k; i++) {
            for (int j = 0; j < boundary[i] - boundary[i - 1]; j++) {
                array[index++] = i;
            }
        }
        return array;

        TC: O(kn)
        SC: O(k)
     */
        int[] boundaries = new int[k + 1];
        for (int i = 0; i < array.length; i++) {
            for (int j = array[i]; j <= k; j++) {
                boundaries[j]++;
            }
        }
        int index = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < boundaries[i] - boundaries[i - 1]; j++) {
                array[index++] = i;
            }
        }
        return array;
    }
}
