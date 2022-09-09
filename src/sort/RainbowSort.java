package sort;

public class RainbowSort {

    /**
     * Given an array of balls, where the color of the balls can only be Red, Green or Blue, sort the balls such that all the Red balls are grouped on the left side, all the Green balls are grouped in the middle and all the Blue balls are grouped on the right side. (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).
     *
     * Assumption:
     * The input array is not null.
     *
     * Examples:
     * {0} is sorted to {0}
     * {1, 0} is sorted to {0, 1}
     * {1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}
     *
     * @param array
     * @return
     */
    public int[] rainbowSort(int[] array) {

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

    private void swap(int[] array, int i , int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
