package dynamic_programming;

public class LongestCrossOfOnes {

    /**
     * Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s, with the same arm lengths and the four arms joining at the central point.
     * Assumption: The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
     *
     * Examples
     *
     * { {0, 0, 0, 0},
     *
     *   {1, 1, 1, 1},
     *
     *   {0, 1, 1, 1},
     *
     *   {1, 0, 1, 1} }
     *
     * the largest cross of 1s has arm length 2.
     * @param matrix
     * @return Return the arm length of the largest cross.
     */
    public int longestCrossOfOnes(int[][] matrix) {
        /*
            For each position element if we can find the longest consecutive ones ending at the element in four directions
            Then we can traverse the entire matrix to find the global max
         */
        // left[i][j] represents the longest consecutive ones in the left direction, including current position
        int[][] left = new int[matrix.length][matrix[0].length];

        // right[i][j] represents the longest consecutive ones in the right direction, including current position
        int[][] right = new int[matrix.length][matrix[0].length];

        //up[i][j] represents the longest consecutive ones in the up direction, including current position
        int[][] up = new int[matrix.length][matrix[0].length];

        //down[i][j] represents the longest consecutive ones in the down direction, including current position
        int[][] down = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    up[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    up[i][j] = up[i - 1][j] + 1;
                }
                if (j == 0) {
                    left[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    left[i][j] = left[i][j - 1] + 1;
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (i == matrix.length - 1) {
                    down[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1){
                    down[i][j] = down[i + 1][j] + 1;
                }
                if (j == matrix[0].length - 1) {
                    right[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                }
            }
        }


        //traverse the entire matrix
        int maxlength = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int currMin = Math.min(Math.min(left[i][j], right[i][j]), Math.min(up[i][j], down[i][j]));
                maxlength = Math.max(currMin, maxlength);
            }
        }
        return maxlength;
    }
}
