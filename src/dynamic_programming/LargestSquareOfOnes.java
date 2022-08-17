package dynamic_programming;

public class LargestSquareOfOnes {

    /**
     * Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.
     * Assumption: The given matrix is not null and guaranteed to be of size N * N, N >= 0
     *
     * @param matrix
     * @return the length of the largest square
     */
    public int largestSquareOfOnes(int[][] matrix) {
        /*
               largestSquareOnes[i][j] represents the largest square of ones formed with the bottom right corner vertex at [i, j]
               induction rule:
                    largesSquareOnes[i][j] = min of {largestSquareOnes[i - 1], largestSquareOnes[i][j - 1], largestSquareOnes[i - 1][j - 1]
         */
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int largestSquare = 0;
        int[][] largestSquareOnes = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            largestSquareOnes[i][0] = matrix[i][0];
            largestSquare = Math.max(largestSquare, matrix[i][0]);
        }
        for (int j = 0; j < matrix[0].length; j++) {
            largestSquareOnes[0][j] = matrix[0][j];
            largestSquare = Math.max(largestSquare, matrix[0][j]);
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    largestSquareOnes[i][j] = Math.min(largestSquareOnes[i - 1][j - 1], Math.min(largestSquareOnes[i - 1][j], largestSquareOnes[i][j- 1])) + 1;
                    largestSquare = Math.max(largestSquare, largestSquareOnes[i][j]);
                }

            }
        }
        return largestSquare;
    }
}
