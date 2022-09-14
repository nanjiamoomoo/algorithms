package dynamic_programming;

public class LargestXOfOnes {

    /**
     * Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.
     *
     * Return the arm length of the largest X shape.
     *
     * Assumption:
     * The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
     *
     * Examples:
     * { {0, 0, 0, 0},
     *   {1, 1, 1, 1},
     *   {0, 1, 1, 1},
     *   {1, 0, 1, 1} }
     *
     * the largest X of 1s has arm length 2.
     *
     * @param matrix
     * @return
     */
    public int largestXOfOnes(int[][] matrix) {
        /*
            Step1: for each position we find the longest consecutive ones in four directions, i.e., topLeft, topRight, bottomLeft, bottomRight
            Step2: For each position element, we find the smallest consecutive ones in four directions and find the globalMax

            o o o
            o x x
            o x x
         */

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] topLeft = new int[row][col];
        int[][] topRight = new int[row][col];

        int[][] bottomLeft = new int[row][col];
        int[][] bottomRight = new int[row][col];

        for (int i = 0; i < row; i++) {
            //topLeft
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        topLeft[i][j] = matrix[i][j];
                    } else {
                        topLeft[i][j] =topLeft[i - 1][j - 1] + 1;
                    }
                }
            }
            //topRight
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == col - 1) {
                        topRight[i][j] = matrix[i][j];
                    } else {
                        topRight[i][j] =topRight[i - 1][j + 1] + 1;
                    }
                }
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            //bottomLeft
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    if (i == row - 1 || j == 0) {
                        bottomLeft[i][j] = matrix[i][j];
                    } else {
                        bottomLeft[i][j] =bottomLeft[i + 1][j - 1] + 1;
                    }
                }
            }
            //bottomRight
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i == row - 1 || j == col - 1) {
                        bottomRight[i][j] = matrix[i][j];
                    } else {
                        bottomRight[i][j] =bottomRight[i + 1][j + 1] + 1;
                    }
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int currMin = Math.min(Math.min(topLeft[i][j], topRight[i][j]), Math.min(bottomLeft[i][j], bottomRight[i][j]));
                maxLength = Math.max(currMin, maxLength);
            }
        }
        return maxLength;
    }
}
