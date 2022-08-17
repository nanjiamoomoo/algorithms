package dynamic_programming;

public class LargestSquareSurroundedByOne {

    /**
     * Determine the largest square surrounded by 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.
     * <p>
     * Assumptions
     * The given matrix is guaranteed to be of size M * N, where M, N >= 0
     * <p>
     * Examples
     * <p>
     * {{1, 0, 1, 1, 1},
     * <p>
     * {1, 1, 1, 1, 1},
     * <p>
     * {1, 1, 0, 1, 0},
     * <p>
     * {1, 1, 1, 1, 1},
     * <p>
     * {1, 1, 1, 0, 0}}
     * <p>
     * The largest square surrounded by 1s has length of 3.
     *
     * @param matrix
     * @return
     */
    public int largestSquareSurroundedByOne(int[][] matrix) {
        /*

            this question is related to the longest consecutive ones problem

               |    1  1  1   |
                    1     1
                    1
                    1
                   -->
             find the longest consecutive ones for each element in the left direction n ^ 2
             find the longest consecutive ones for each element in the up direction  n ^ 2

             for each bottom right vertex of the square : double for loop n ^ 2
                 for each possible length from big to small O(n)
                    we check if the top right vertex has at least length count of ones in the left direction O(1)
                    we check if the bottom left vertex has at least length count of ones in the up direction
                    if both conditions met, then we find the largest length square with the current bottom right vertex.


              total time complexity is O(n)

         */
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        //leftOnes[i][j] represents the longest consecutive ones in the left direction for the element at matrix[i][j] including itself
        int[][] leftOnes = new int[matrix.length][matrix[0].length];
        //upOnes[i][j] represents the longest consecutive ones in the up direction for the element at matrix[i][j] including itself
        int[][] upOnes = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            leftOnes[i][0] = matrix[i][0];
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                leftOnes[i][j] = matrix[i][j] == 0 ? 0 : leftOnes[i][j - 1] + 1;
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            upOnes[0][j] = matrix[0][j];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                upOnes[i][j] = matrix[i][j] == 0 ? 0 : upOnes[i - 1][j] + 1;
            }
        }

        int longestLength = 0;
        //for each element as the bottom-right vertex of the square
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    for (int len = Math.min(leftOnes[i][j], upOnes[i][j]); len >= 1; len--) {
                        // the square top right vertex (i - len + 1, j)
                        // the square bottom left vertex (i , j - len + 1)
                        if (leftOnes[i - len + 1][j] >= len && upOnes[i][j - len + 1] >= len) {
                            longestLength = Math.max(longestLength, len);
                            break;
                        }
                    }
                }
            }
        }

        return longestLength;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0}, {1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1}, {1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1}, {0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0}, {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1}, {0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1}};
        LargestSquareSurroundedByOne largestSquareSurroundedByOne = new LargestSquareSurroundedByOne();
        System.out.println(largestSquareSurroundedByOne.largestSquareSurroundedByOne(array));
    }
}
