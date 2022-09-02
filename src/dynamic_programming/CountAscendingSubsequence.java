package dynamic_programming;

public class CountAscendingSubsequence {

    /**
     * Given an array A[0]...A[n-1] of integers, count the number of ascending subsequences.
     * In case that the result is larger than 32-bit integer, return the result in 10^9+7 modulo
     * Assumption:
     *  A is not null
     *
     * Examples:
     * Input: A = {1,2,3}
     * Output: 7
     * Explanation: [1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]
     * @param array
     * @return
     */
    public int countAscendingSubsequence(int[] array) {

        /*
            We can use dp to solve the problem
            Define count[], count[i] represents the count of ascending sequence including ith index position
            count[i] =
                for each position j from 0 ~ j
                if (array[i] > array[j]), count[i] += count[j]
                        then we add 1, since itself can always be a solution

             return sum of count[i], i from 0 ~ array.length - 1

             above example:
             count[] =
             [1, 2, 4] = 7.
         */

        if (array.length == 0) {
            return 0;
        }
        int[] count = new int[array.length];
        int divisor = (int)(Math.pow(10, 9) + 7);
        count[0] = 1;
        for (int i = 1; i < count.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    count[i] += count[j];
                }
            }
            count[i]++;
            if (count[i] > divisor) {
                count[i] %= divisor;
            }
        }
        int res = 0;
        for (int i = 0; i < count.length; i++) {
            res += count[i];
        }
        return res % divisor;
    }
}
