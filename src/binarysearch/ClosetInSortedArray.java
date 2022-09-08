package binarysearch;

public class ClosetInSortedArray {

    /**
     * Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to T.
     * Assumption:
     * There can be duplicate elements in the array, and we can return any of the indices with same value.
     * Examples:
     * A = {1, 2, 3}, T = 2, return 1
     * A = {1, 4, 6}, T = 3, return 1
     * A = {1, 4, 6}, T = 5, return 1 or 2
     * A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2
     * @param array
     * @param target
     * @return
     */
    public int closest(int[] array, int target) {

        /*
           Use binary search
           if array[mid] == target, return index
           if array[mid] < target, left = mid;
           if array[mid] > target, right = mid;
           when there are only two elements left, we stop

         */
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left ) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return Math.abs(array[left] - target) <= Math.abs(array[right] - target) ? left : right;
    }
}
