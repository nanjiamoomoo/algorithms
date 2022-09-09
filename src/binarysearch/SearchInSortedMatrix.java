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
}
