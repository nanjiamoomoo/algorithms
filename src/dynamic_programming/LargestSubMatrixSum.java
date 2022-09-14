package dynamic_programming;

public class LargestSubMatrixSum {

    /**
     * Given a matrix that contains integers, find the sub-matrix with the largest sum.
     *
     * Return the sum of the sub-matrix.
     * Examples
     *
     * { {1, -2, -1, 4},
     *
     *   {1, -1,  1, 1},
     *
     *   {0, -1, -1, 1},
     *
     *   {0,  0,  1, 1} }
     *
     *   the largest sub-matrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
     *
     * @param matrix
     * @return
     */
    public int largestSubMatrixSum(int[][] matrix) {
        /*
                x  x  x  x
                x  x  x  x
                x  o  o  x
                x  o  o  x
                x  x  x  x

               Native method: how many sub-matrix we have: m ^ 2 * n ^ 2
                              calculate sum also takes (m + n)
                              total TC: m2n2*(m + n)

               Can we improve?
               if we have the prefix sum in the direction from up to bottom.
               if we can limit the top row and bottom row, then we can calculate the sum of elements in each column between the rows in O(1) time
               Then we will have a 1D array. THe problem becomes to calculate the maxSubArraySum(we can use DP to solve the problem)

               prefixSum is m * n
               limit rows is m ^ 2
                    get the 1D array is O(n)
                    and for each 1D array, to get the maxSubarraySum is O(n)

                Total is  m^2 * n

         */

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] prefixSumUpToBottom = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0) {
                    prefixSumUpToBottom[i][j] = matrix[i][j];
                } else {
                    prefixSumUpToBottom[i][j] = prefixSumUpToBottom[i - 1][j] + matrix[i][j];
                }
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for (int firstRow = 0; firstRow < row; firstRow++) {
            for (int lastRow = firstRow; lastRow < row; lastRow++) {
                /*
                    maxSubArraySum[i] represents the max sum of the sub array ending at index i, including index i
                 */
                int[] maxSubArraySum = new int[col];
                for (int j = 0; j < col; j++) {
                    int curr = prefixSumUpToBottom[lastRow][j] - prefixSumUpToBottom[firstRow][j] + matrix[firstRow][j];
                    if (j == 0) {
                        maxSubArraySum[0] = curr;
                    } else {
                        maxSubArraySum[j] = maxSubArraySum[j - 1] <= 0 ? curr : maxSubArraySum[j - 1] + curr;
                    }
                    maxSum = Math.max(maxSum, maxSubArraySum[j]);
                }
            }
        }
        return maxSum;
    }
}
