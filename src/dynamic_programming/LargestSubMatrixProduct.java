package dynamic_programming;

public class LargestSubMatrixProduct {

    /**
     * Given a matrix that contains doubles, find the submatrix with the largest product.
     * <p>
     * Return the product of the submatrix.
     * <p>
     * Assumptions
     * <p>
     * The given double matrix is not null and has size of M * N, where M >= 1 and N >= 1
     * Examples
     * <p>
     * { {1, -0.2, -1},
     * <p>
     * {1, -1.5,  1},
     * <p>
     * {0,     0,  1} }
     * <p>
     * the largest submatrix product is 1 * 1 = 1.
     *
     * @param matrix
     * @return
     */
    public double largestProduct(double[][] matrix) {
        /*

                dp[i][j][l] represents the product of elements between row i ~ row j on column j

                then we can limit top row and bottom row and find the max sub matrix that has the largest product

               TC: O(n ^ 4)
               SC: O(n ^ 3)

         */

        double[][][] productUpToBot = new double[matrix.length][matrix.length][matrix[0].length];
        for (int l = 0; l < matrix[0].length; l++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int i = j; i >= 0; i--) {
                    if (i == j) {
                        productUpToBot[i][j][l] = matrix[i][l];
                    } else {
                        productUpToBot[i][j][l] = productUpToBot[i + 1][j][l] * matrix[i][l];
                    }
                }
            }
        }

        double largest = matrix[0][0];
        for (int top = 0; top < matrix.length; top++) {
            for (int bot = top; bot < matrix.length; bot++) {
                double currLargest = productUpToBot[top][bot][0];
                for (int l = 1; l < matrix[0].length; l++) {
                    double previousProduct = 1;
                    for (int k = l; k >= 0; k--) {
                        double currProduct = previousProduct * productUpToBot[top][bot][k];
                        currLargest = Math.max(currLargest, currProduct);
                        previousProduct = currProduct;
                    }
                }
                largest = Math.max(largest, currLargest);
            }
        }
        return largest;
    }
}
