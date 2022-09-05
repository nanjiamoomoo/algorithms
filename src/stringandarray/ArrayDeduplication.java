package stringandarray;

import java.util.Arrays;

public class ArrayDeduplication {

    /**
     * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep only one of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.
     * Assumption:
     * The array is not null
     *
     * Examples:
     * {1, 2, 2, 3, 3, 3} → {1, 2, 3}
     * @param array
     * @return
     */
    public int[] arrayDeduplicationI(int[] array) {
        /*
            Since original array is sorted. we can use two pointers
            1   2   3   3   3   3
                        i
                                  j

            [0, i) contains all the deduplicated characters that we will keep
            [i, j)... don't care
            [i, n - 1].. to explore
         */



        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (slow == 0 || array[fast] != array[slow - 1]) {
                array[slow++] = array[fast];
            }
        }
        return Arrays.copyOf(array, slow);
    }

    /**
     * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep at most two of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.
     * Assumption: The given array is not null
     * Examples: {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
     * @param array
     * @return
     */
    public int[] arrayDeduplicationII(int[] array) {
        /*
            Since original is sorted, we can use two pointers to solve the problem

            [0, i) contains all the deduplicated characters that we will keep
            [i, j)... don't care
            [i, n - 1].. to explore
            We traverse the array using j, and compare array[j] with array[i - 2] to determine if we already two duplicate elements stored.
            if array[j] == array[i - 2], it means we found the third duplicate elements? why?
                since original array is stored, so the [0, i) is also sorted. if array[i - 2] == array[j], means array[i - 1] has the same element.

            1   2   2   3    3   3
                                 i
                                    j
         */
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (slow < 2 || array[fast] != array[slow - 2]) {
                array[slow++] = array[fast];
            }
        }
        return Arrays.copyOf(array, slow);
    }

    /**
     * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.
     * Assumption: The given array is not null
     * Examples: {1, 2, 2, 3, 3, 3} → {1}
     * @param array
     * @return
     */
    public int[] arrayDeduplicationIII(int[] array) {
        /*
            We can use two pointers to solve the problem
             [0, i) contains all the deduplicated characters that we will keep
            [i, j)... don't care
            [i, n - 1].. to explore
            if (array[j] != array[i - 1]) we can copy the element and i++
            else, we need to pass all the duplicate character, then i--, to remove the previous stored duplicated elements

            1  2   2   3   3   3
               i
                       j
         */

        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (slow == 0 || array[fast] != array[slow - 1]) {
                array[slow++] = array[fast];
            } else {
                while (slow != 0 && fast < array.length && array[fast] == array[slow - 1]) {
                    fast++;
                }
                slow--;
                fast--;
            }
        }
        return Arrays.copyOf(array, slow);

        /*
            Another method
            3 pointers: 2 pointers + 1 inner point
            slow: all the elements to the left of slow are processed element that shall be kept
            fast: all the elements to the right of are elements to be processed
            begin: the first occurrence of the elements in the current section

             1   2   2   3   3   3
                 s
                                    f
                       begin

            int slow = 0;
            int fast = 0;
            while (fast < array.length) {
                int begin = fast;
                while (fast < array.length && array[fast] == array[begin]) {
                    fast++;
                }
                if (fast - begin == 1) {
                    array[slow++] = array[begin];
                }
            }
            return Arrays.copyOf(array, slow);
         */
    }

    /**
     * Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. For each group of elements with the same value do not keep any of them.
     * Do this in-place, using the left side of the original array. Return the array after deduplication.
     * Assumption: The given array is not null
     * Examples: {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
     * @param array
     * @return
     */
    public int[] arrayDeduplicationIV(int[] array) {
        /*
            We can use two pointers to solve the problem

            slow: all the elements to the left of slow are the elements that have been processed and shall be kept
            fast: all the elements to the right of fast are the elements to be explored
            as can compare array[fast] with array[slow - 1] to check if the current visiting element is the same as the previous kept elements.
            if they are the same, we shall bypass all the duplicate elements and move the slow pointer back to delete the previous stored elements

               1  2   3   3   3  2   2
                  s
                                       f

         */
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (slow == 0 || array[fast] != array[slow - 1]) {
                array[slow++] = array[fast++];
            } else {
                while (fast < array.length && array[fast] == array[slow - 1]) {
                    fast++;
                }
                slow--;
            }
        }
        return Arrays.copyOf(array, slow);
    }

    /**
     * Given an unsorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.
     * Assumption: The given array is not null
     * Examples: {1, 2, 2, 3, 3, 3} → {1}
     * @param array
     * @return
     */
    public int[] arrayDeduplicationV(int[] array) {
        /*
            3 pointers: 2 pointers + 1 inner point
            slow: all the elements to the left of slow are processed element that shall be kept
            fast: all the elements to the right of are elements to be processed
            begin: the first occurrence of the elements in the current section

             1  3   2   2   3    3   3    -> {1, 3}
                    s
                                        f
                          begin

         */
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            int begin = fast;
            while (fast < array.length && array[fast] == array[begin]) {
                fast++;
            }
            if (fast - begin == 1) {
                array[slow++] = array[begin];
            }
        }
        return Arrays.copyOf(array, slow);
    }
}
