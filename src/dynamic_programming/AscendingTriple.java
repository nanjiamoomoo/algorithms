package dynamic_programming;

public class AscendingTriple {

    /**
     * Determine if the given integer array has three indices such that i < j < k and a[i] < a[j] < a[k].
     *
     * Assumptions:
     *
     * The given array is not null.
     * Examples:
     *
     * {1, 5, 2, 4}, return true since i = 0, j = 2, k = 3
     *
     * {4, 3, 2, 1}, return false
     * @param array
     * @return
     */
    public boolean existAscendingTriple(int[] array) {
        /*
            This problem converts to find the longest ascending sequence ending at each index position.
            If there is one length greater than or equal to 3. then we return true;
         */
        if (array.length <= 3) {
            return false;
        }

        int[] lAS = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int currMax = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    currMax = Math.max(currMax, lAS[j] + 1);
                }
            }
            if (currMax >= 3) {
                return true;
            }
            lAS[i] = currMax;
        }
        return false;
    }
}
