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
            assume a matrix with left-top vertex coordinate <k, l>
                                 right -bottom vertex coordinate <i, j>
            if we have an 2D array product[i][j] represents the area define by the top left vertex <0, 0> and bottom right vertex<i, j>
            the area defined by the <k, l> and <i, j> = product[i][j] * product[k][l] / product[k][j] / product[i][l]
         */
//        double[][] product = new double[matrix.length][matrix[0].length];
//        product[0][0] = matrix[0][0];
//        double largest = matrix[0][0];
//        for (int j = 1; j < matrix[0].length; j++) {
//            product[0][j] = product[0][j - 1] * matrix[0][j];
//            largest = Math.max(largest, product[0][j]);
//        }
//        for (int i = 1; i < matrix.length; i++) {
//            product[i][0] = product[i - 1][0] * matrix[i][0];
//            largest = Math.max(largest, product[i][0]);
//        }
//        for (int i = 1; i < matrix.length; i++) {
//            for (int j = 1; j < matrix[0].length; j++) {
//                product[i][j] = product[i - 1][j] * product[i][j - 1] * matrix[i][j] / product[i - 1][j - 1];
//                for (int k = 0; k < i; k++) {
//                    for (int l = 0; l < j; l++) {
//                        largest = Math.max(largest, product[i][j] * product[k][l] / (product[k][j] * product[i][l]));
//                    }
//                }
//            }
//        }
//        return largest;
        return -1;
    }
}
