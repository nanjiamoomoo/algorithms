package dynamic_programming;

public class LargestSquareOfMatches {

    /**
     * Determine the largest square surrounded by a bunch of matches (each match is either horizontal or vertical), return the length of the largest square.
     *
     * The input is a matrix of points. Each point has one of the following values:
     *
     * 0 - there is no match to its right or bottom.
     *
     * 1 - there is a match to its right.
     *
     * 2 - there is a match to its bottom.
     *
     * 3 - there is a match to its right, and a match to its bottom.
     *
     * Assumptions
     * The given matrix is guaranteed to be of size M * N, where M, N >= 0
     *
     *
     * Examples
     *
     * {{3, 1, 1, 3, 0, 1, 1, 0},
     *
     *  {2, 0, 0, 2, 0, 0, 0, 0},
     *
     *  {3, 1, 3, 0, 0, 0, 0, 0},
     *
     *  {2, 0, 2, 0, 0, 0, 0, 0},
     *
     *  {1, 1, 0, 0, 0, 0, 0, 0}}
     *
     *Examples
     *
     * {{3, 1, 1, 3, 0, 1, 1, 0},
     *
     *  {2, 0, 0, 2, 0, 0, 0, 0},
     *
     *  {3, 1, 3, 0, 0, 0, 0, 0},
     *
     *  {2, 0, 2, 0, 0, 0, 0, 0},
     *
     *  {1, 1, 0, 0, 0, 0, 0, 0}}
     *
     * @param matrix
     * @return
     */
    public int largestSquareOfMatches(int[][] matrix) {
        /*
              the square top left corner must have the value 3, since it needs to have both bottom and right matches

              in order to find the length of the largest square
              the question converts to find the longest consecutive matches on the right direction and on the bottom direction

              we can use dp to solve the problem
              right[i][j] represents the longest consecutive matches on the right direction
              right[i][col - 1] = 0
                   induction rule:
                        right[i][j] =
                            if matrix[i][j] = 1 or 3, = right[i][j + 1] + 1
                            else = 0

              bottom[i][j] represents the longest consecutive matches on the bottom direction
              bottom[row - 1][j] = 0
                   induction rule:
                        bottom[i][j] =
                            if matrix[i][j] = 2 or 3, = bottom[i + 1][j] + 1
                            else = 0;

              main algorithm
              for each points at <i, j> with value 3
                we check the longest consecutive matches on the right and longest consecutive matches on the bottom and choose the smaller one.
                for (int len = smaller ; len >= 1; len--)
                    we check if it can form a square
                    top right vertex <i, j + len> we check if the bottom direction has the longest consecutive matches of len (bottom[i][j + len] >= len)
                    bottom left vertex < i + len, j>, we check if the right direction has the longest consecutive matches of len (right[i + len][j] >= len)
                    if yes, we update globalMax
                    if no, we go to next cycle.

         */

        int row = matrix.length;
        int col = matrix[0].length;
        if (row <= 1 || col <= 1) {
            return 0;
        }

        int[][] right = new int[row][col];
        int[][] bottom = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = col - 2; j >= 0; j--) {
                if (matrix[i][j] == 1 || matrix[i][j] == 3) {
                    right[i][j] = right[i][j + 1] + 1;
                }
            }
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 2 || matrix[i][j] == 3) {
                    bottom[i][j] = bottom[i + 1][j] + 1;
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 3) {
                    int maxLen = Math.min(right[i][j], bottom[i][j]);
                    for (int len = maxLen; len >= 1; len--) {
                        if (bottom[i][j + len] >= len && right[i + len][j] >= len) {
                            maxLength = Math.max(maxLength, len);
                            break;
                        }
                    }
                }
            }
        }
        return maxLength;
    }
}
