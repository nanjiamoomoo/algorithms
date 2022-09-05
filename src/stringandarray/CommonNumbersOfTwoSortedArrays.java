package stringandarray;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).
 *
 * Assumption:
 * In each of the two sorted arrays, there could be duplicate numbers.
 * Both arrays are not null.
 *
 * Examples:
 * A = {1, 1, 2, 2, 3}
 * B = {1, 1, 2, 5, 6}
 * common numbers are [1, 1, 2
 *
 */
public class CommonNumbersOfTwoSortedArrays {
    public List<Integer> common (int[] a, int[] b) {
        //since two arrays are sorted, we can use two pointers to solve the problem
        //we can find pointer i in a[] and pointer j in b[]
        //i always points to the current smallest number in the a[]
        //j always points to the current smallest number in the b[]
        //as long as i and j are in bound, we compare a[i] with b[j]
        //case 1: if a[i] == b[j], then we find a common number and added it to the final result
        //case 2: if a[i] < b[j], then we can safely do i++ and move to the next element in a[]
        //case 3: if a[i] > b[j], then we can safely do j++ and move teh next element in b[j]

        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                res.add(a[i]);
                i++;
                j++;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }

        return res;
    }
}
