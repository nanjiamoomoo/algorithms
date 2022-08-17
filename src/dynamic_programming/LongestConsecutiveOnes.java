package dynamic_programming;

public class LongestConsecutiveOnes {

    /**
     * Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.
     *
     * Assumptions
     * The given array is not null
     *
     * Examples
     * {0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
     *
     * @param array
     * @return
     */
    public int longestConsecutiveOnes(int[] array) {
        /*
                longestOnes[i] represents the longest consecutive ones at i position, including i

                induction rules:
                    1. if (array[i] == 1), longestOnes[i - 1] + 1;
                    2. if (array[i] == 0), 0
         */
        if (array == null || array.length == 0) {
            return 0;
        }
        int globalMax = array[0];
        int[] longestOnes = new int[array.length];
        longestOnes[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                longestOnes[i] = 0;
            } else {
                longestOnes[i] = longestOnes[i - 1] + 1;
                globalMax = Math.max(globalMax, longestOnes[i]);
            }
        }
        return globalMax;
        //above solution can be further optimized due to the current state only relevant to the previous, we can reduce the SC to O(1) by using a variable
    }
}
