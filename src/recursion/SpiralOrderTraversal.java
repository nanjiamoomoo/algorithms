package recursion;

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

    /**
     * Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.
     * Assumption: The 2D array is not null and has size of M * N where M, N >= 0
     * @param matrix
     * @return the traversal sequence
     */
    public List<Integer> spiralOrderTraversalII(int[][] matrix) {

        /*
               offset
                 o x x x x
                 x o x x x
                 x x o x x
                 x x x x x
                 x x x x x
                 x x x x x
                 //use recursion to solve the problem
                 define the offset as the distance between start point on each level to the left edge

               top left corner coordinate (offset, offset)
               top right corner coordinate (offset, col - 1 - offset)
               bottom right corner coordinate (row - 1 - offset, col - 1 - offset)
               bottom left corner coordinate (row - 1 - offset, offset)
         */

        List<Integer> res = new ArrayList<>();
        recursionII(matrix, res, 0);
        return res;
    }

    private void recursionII(int[][] matrix, List<Integer> res, int offset) {
        int row = matrix.length;
        int col = matrix[0].length;

        //base case
        int smaller = Math.min(row, col);

        if (offset == smaller / 2) {
            if (smaller % 2 == 0) {
                return;
            } else {
                if (col >= row) {
                    for (int j = offset; j <= col - 1 - offset; j++) {
                        res.add(matrix[offset][j]);
                    }
                    return;
                } else {
                    for (int i = offset; i <= row - 1 - offset; i++) {
                        res.add(matrix[i][col - 1 - offset]);
                    }
                    return;
                }
            }

        }

        //top
        for (int j = offset; j < col - 1 - offset; j++) {
            res.add(matrix[offset][j]);
        }
        //right
        for (int i = offset; i < row - 1 - offset; i++) {
            res.add(matrix[i][col - 1 - offset]);
        }
        //bottom
        for (int j = col - 1 - offset; j > offset; j--) {
            res.add(matrix[row - 1 - offset][j]);
        }
        //left
        for (int i = row - 1 - offset; i > offset; i--) {
            res.add(matrix[i][offset]);
        }
        recursionII(matrix, res, offset + 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}};
        SpiralOrderTraversal spiralOrderTraversal = new SpiralOrderTraversal();
        System.out.println(spiralOrderTraversal.spiralOrderTraversalII(matrix));
    }

}
