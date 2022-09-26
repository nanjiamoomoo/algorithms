package binarysearch;

public class SearchInSortedMatrix {

    /**
     * Given a 2D matrix that contains integers only, which each row is sorted in an ascending order. The first element of next row is larger than (or equal to) the last element of previous row.
     * Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.
     *
     * Assumption:
     * The given matrix is not null, and has size of N * M, where N >= 0 and M >= 0.
     *
     * Examples:
     * matrix = { {1, 2, 3}, {4, 5, 7}, {8, 9, 10} }
     * target = 7, return {1, 2}
     * target = 6, return {-1, -1} to represent the target number does not exist in the matrix.
     * @param matrix
     * @param target
     * @return
     */
    public int[] search(int[][] matrix, int target) {
        /*
            We can imagine the 2D array into a 1D array in ascending order and use BS to find the target
            once we find the index we return the array{index / col, index % col}
         */
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / matrix[0].length;
            int col = mid % matrix[0].length;
            if (matrix[row][col] == target) {
                return new int[]{row, col};
            } else if (matrix[row][col] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Given a 2D matrix that contains integers only, which each row is sorted in ascending order and each column is also sorted in ascending order.
     *
     * Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.
     *
     * Assumptions:
     *
     * The given matrix is not null.
     * Examples:
     *
     * matrix = { {1, 2, 3}, {2, 4, 5}, {6, 8, 10} }
     *
     * target = 5, return {1, 2}
     *
     * target = 7, return {-1, -1}
     * @param matrix
     * @param target
     * @return
     */
    public int[] searchII(int[][] matrix, int target) {
        /*
            Use BS to search each row
            if find return index, otherwise return -1

            TC:O(mlogn)
            SC: O(1)
        */
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{-1, -1};
        }

        for (int i = 0; i < matrix.length; i++) {
            int index = binarySearch(matrix[i], target);
            if (index != -1) {
                return new int[]{i, index};
            }
        }
        return new int[]{-1, -1};
    }

    private int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /*
         Best Solution:
          Search Space Reduction
        Intuition

        Because the rows and columns of the matrix are sorted (from left-to-right and top-to-bottom, respectively), we can prune \mathcal{O}(m)O(m) or \mathcal{O}(n)O(n) elements when looking at any particular value.

        Algorithm

        First, we initialize a (row, col)(row,col) pointer to the bottom-left of the matrix.
        Then, until we find target and return true (or the pointer points to a (row, col)(row,col) that lies outside of the dimensions of the matrix), we do the following:
        if the currently-pointed-to value is larger than target we can move one row "up".
        Otherwise, if the currently-pointed-to value is smaller than target, we can move one column "right".
        It is not too tricky to see why doing this will never prune the correct answer;
        because the rows are sorted from left-to-right, we know that every value to the right of the current value is larger.
        Therefore, if the current value is already larger than target, we know that every value to its right will also be too large.
        A very similar argument can be made for the columns, so this manner of search will always find target in the matrix (if it is present).

        TC: O(n + m)
        SC: O(1)

        public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }
     */
}
