package sort;


public class MoveZerosToTheEnd {

    /**
     * Given an array of integers, move all the 0s to the right end of the array.
     * The relative order of the elements in the original array does not need to be maintained.
     *
     * Assumptions:
     * The given array is not null.
     * {1} --> {1}
     * {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}
     * Examples:
     *
     * @param array
     * @return
     */
    public int[] moveZerosToTheEndI(int[] array) {
        /*

                Since the original order does not need to maintain, we can use two pointers and move towards to each other
                1  0   3  0   0
                   i          j
                [0, i) contains all non zero elements
                [i, j] represents the space that haven't been explored
                (j, array.length -1] contains all zero elements

                if (array[i] != 0) {
                i++;}
                else if (array[j] != 0 ) {
                    i++;
                    j--;
                } else {
                j--}
                when i == j, we can stop. no need to check i == j

         */
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            if (array[i] != 0) {
                i++;
            } else if (array[j] != 0) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            } else {
                j--;
            }
        }
        return array;
    }

    /**
     * Given an array of integers, move all the 0s to the right end of the array.
     *
     * The relative order of the elements in the original array need to be maintained.
     *
     * Assumptions:
     * The given array is not null.
     * {1} --> {1}
     * {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}
     *
     * @param array
     * @return
     */
    public int[] moveZero(int[] array) {
        /*
            since we need to maintain the original order
            The regular rainbow sort will change the original order, so we cannot use

            We still can use two pointers that move in the same direction
            We can traverse the array and only keep the nonzero elements, then we assign the array into 0s
            [0, i) represents the space where we keep all non-zero elements
            [i, j) represents the space that we don't care
            [i, array.length - 1] represents the space that we are going to explore. j is the position that we can currently visiting

         */

        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (array[j] != 0) {
                array[i] = array[j];
                i++;
            }
            j++;
        }
        for (; i < array.length; i++) {
            array[i] = 0;
        }
        return array;

        /*
            another solution:
            when we find an element is nonzero, we can swap it with the i position element. The benefit of this that we can maintain the relative order of the NON_ZERO elements
            int slow = 0;
            for (int fast = 0; fast < array.length; fast++) {
		        if (array[fast] != 0) {
		            swap(array, slow++, fast);
                }
            }

         */

    }

}
