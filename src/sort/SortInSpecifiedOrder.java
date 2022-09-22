package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortInSpecifiedOrder {

    /**
     * Given two integer arrays A1 and A2, sort A1 in such a way that the relative order among the elements will be same as those are in A2.
     * <p>
     * For the elements that are not in A2, append them in the right end of the A1 in an ascending order.
     * <p>
     * Assumptions:
     * <p>
     * A1 and A2 are both not null.
     * There are no duplicate elements in A2.
     * Examples:
     * <p>
     * A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9}
     *
     * @param a1
     * @param a2
     * @return
     */
    public int[] sortInSpecialOrder(int[] a1, int[] a2) {
        /*
                We can use rainbow sort to solve the problem
                the original array can be split into a2.length + 1 sections.

                we can use an array partition[a2.length + 1] to store the border for each section
                [0, partition[0]) keeps value a2[0]
                [partition[0], partition[1]) keeps the value a2[1]
                ...
                [partition[i - 1], partition[i]) keep the value for a2[i]
                ..

                (partition[a2.length], a1.length - 1] keep all other elements that are not in a2

                note: partition[a2.length - 1], partition[a2.length] are the searching region

                we can traverse the original array
                for each value a1[i] -> we convert it to the partition[k - 1], partition[k] (if k is not the last partition (partition[a2.length], a1.length - 1]) (here we need a map to convert each unique value to a partition
                then all the partition[i] i between [i, a2.length - 1] need to + 1
                if the partition is partition[a2.length], which means this element is not in the a2, then we need to swap the value to end of array by doing swap(array, partition[a2.length] - 1p=, partition[a2.length]--);

                once we complete traverse, we assigns the value back to a1 and sort the right portion

                TC: O(n1 * n2) + klogk (k is the count of elements not in the a2)
                SC: O(n2)
         */

        if (a2.length == 0) {
            Arrays.sort(a1);
            return a1;
        }

        int[] partition = new int[a2.length + 1];
        partition[a2.length] = a1.length - 1;

        Map<Integer, Integer> valueToPartition = new HashMap<>();
        for (int i = 0; i < a2.length; i++) {
            valueToPartition.put(a2[i], i);
        }

        while (partition[a2.length - 1] <= partition[a2.length]) {
            Integer currPartition = valueToPartition.get(a1[partition[a2.length - 1]]);
            if (currPartition != null) {
                for (int j = currPartition; j < a2.length; j++) {
                    partition[j]++;
                }
                //only in this condition we need to check next
            } else {
                swap(a1, partition[a2.length - 1], partition[a2.length]--);
            }
        }

        int i = 0;
        for (int k = 0; k < a2.length; k++) {
            int count = k == 0 ? partition[0] : partition[k] - partition[k - 1];
            while (count-- != 0) {
                a1[i++] = a2[k];
            }
        }
        Arrays.sort(a1, partition[a2.length] + 1, a1.length);
        return a1;
    }

    public void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


}
