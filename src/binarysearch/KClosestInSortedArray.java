package binarysearch;

public class KClosestInSortedArray {

    /**
     * Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A. If there is a tie, the smaller elements are always preferred.
     *
     * Assumption:
     * A is not null
     * K is guaranteed to be >= 0 and K is guaranteed to be <= A.length
     *
     * Examples:
     * A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
     * A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}
     * @param array
     * @param target
     * @param k
     * @return A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T.
     */
    public int[] kClosest(int[] array, int target, int k) {
        /*
            step1: use BS to find the first closest element
            step2: then use two pointers and move the smaller one to find the k closest numbers
         */

        if (array == null || array.length == 0) {
            return new int[0];
        }

        int mid = findClosest(array, target);
        return findKClosest(array, mid, k, target);
    }

    private int[] findKClosest(int[] array, int mid, int k, int target) {
        int[] res = new int[k];
        int left = mid;
        int right =mid + 1;
        for (int i = 0; i < k; i++) {
            if (right >= array.length || left >= 0 && Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
                res[i] = array[left--];
            } else {
                res[i] = array[right++];
            }
        }
        return res;
    }

    private int findClosest(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
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
