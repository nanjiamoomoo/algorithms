package recursion;

public class RotateMatrix {

    /**
     * Rotate an N * N matrix clockwise 90 degrees.
     * Assumption:The matrix is not null and N >= 0
     * Examples:
     * { {1,  2,  3}
     * <p>
     * {8,  9,  4},
     * <p>
     * {7,  6,  5} }
     * <p>
     * after rotation is
     * <p>
     * { {7,  8,  1}
     * <p>
     * {6,  9,  2},
     * <p>
     * {5,  4,  3} }
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        /*
                1 2 3
                8 9 4
                7 6 5

                top left -> top right < offset, offset> - > <offset, n - 1 - offset>
                top right -> bottom right < offset, n - 1 - offset> -> < n - 1 - offset, n - 1 - offset>
                bottom right -> bottom left < n - 1 - offset, n - 1 - offset> -> < n - 1 - offset, offset>
                bottom left -> top left < n - 1 - offset, offset> -> < offset, offset>

                the element to the right of the top left
                        <offset, offset + 1> -> < offset + 1, n - 1 - offset>
                        <offset + 1, n - 1 - offset> -> < n - 1 - offset, n - 1 - offset - 1>
                        <n - 1 - offset, n - 1 - offset - 1> --> < n - 1 - offset - 1, offset>
                        < n - 1 - offset - 1, offset> -> <offset, offset + 1

                general rules
                for (int j = 0; j + offset < n  - 1 - offset; j++) {
                     <offset, offset + j> -> < offset + j, n - 1 - offset>
                        <offset + j, n - 1 - offset> -> < n - 1 - offset, n - 1 - offset - j>
                        <n - 1 - offset, n - 1 - offset - j> --> < n - 1 - offset - j, offset>
                        < n - 1 - offset - j, offset> -> <offset, offset + j>

               Then we can solve this problem either recursively or iteratively

         */
        if (matrix == null || matrix.length <= 1) {
            return;
        }
        int offset = 0; //represents the x-coordinate distance from the top left corner of the matrix to the top left corner of the current square we are going to rotate
        int n = matrix.length;
        while (offset < n / 2) {
            //j represents the x-coordinate distance from the top left corner of the current square represented by the offset
            for (int j = 0; j < n - 1 - 2 * offset; j++) {
                int tmp = matrix[offset][offset + j];
                matrix[offset][offset + j] = matrix[n - 1 - offset - j][offset];
                matrix[n - 1 - offset - j][offset] = matrix[n - 1 - offset][n - 1 - offset - j];
                matrix[n - 1 - offset][n - 1 - offset - j] = matrix[offset + j][n - 1 - offset];
                matrix[offset + j][n - 1 - offset] = tmp;
            }
            offset++;
        }
    }
}
