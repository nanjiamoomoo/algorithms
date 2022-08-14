package Recursion;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraversal {

    /**
     * Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.
     * <p>
     * Assumption:
     * 1. The 2D array is not null and has size of N * N where N >= 0
     * <p>
     * Example
     * {
     * {1 ,2 ,3},
     * {4, 5, 6},
     * {7, 8, 9}
     * }
     * The traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5}
     */
    public List<Integer> spiralOrderTraversalI(int[][] matrix) {

        /*
            x x x x
            x x x x
            x x x x
            x x x x

            if we have an offset
            the top-left corner coordinate is (offset, offset)
            the top-right corner coordinate is (offset, n - 1 - offset)
            the bottom-left corner coordinate is (n - 1 - offset, offset)
            the bottom-right corner coordinate is (n - 1 - offset, n - 1 - offset)

            we can use recursion to solve this problem
                                           ""
            level 0: we traverse the most outer circle, which is when offset = 0
                  the top edge: (offset, [offset, offset + 1, .... n - 2 - offset]
                  the right edge: ([offset, offset + 1, .... n - 2 - offset], n - 1 - offset)
                  the bottom edge: (n - 1 - offset, [n - 1 - offset, n - 2 - offset, .... offset + 1])
                  the left edge: ([n - 1 - offset, n - 2 - offset,... offset + 1], offset)

            level 1....
         */

        List<Integer> res = new ArrayList<>();
        recursion(0, matrix, res, matrix.length);
        return res;
    }


    private void recursion(int offset, int[][] matrix, List<Integer> res, int size) {
        //base case
        //if size is odd number, when offset = size / 2, we need to add the last element in the result and return
        //if the size is even number, offset = size / 2, we return

        if (offset == size / 2) {
            if (size % 2 != 0) {
                res.add(matrix[offset][offset]);
            }
            return;
        }

        for (int j = offset; j < size - 1 - offset; j++) {
            res.add(matrix[offset][j]);
        }
        for (int i = offset; i < size - 1 - offset; i++) {
            res.add(matrix[i][size - 1 - offset]);
        }
        for (int j = size - 1 - offset; j > offset; j--) {
            res.add(matrix[size - 1 - offset][j]);
        }
        for (int i = size - 1 - offset; i > offset; i--) {
            res.add(matrix[i][offset]);
        }
        recursion(offset + 1, matrix, res, size);
    }


}
