package binarysearch;

public class SmallestElementLargerThanTarget {

    /**
     * Given a target integer T and an integer array A sorted in ascending order, find the index of the first smallest element in A that is larger than T or return -1 if there is no such index.
     * Assumptions
     * There can be duplicate elements in the array.
     *
     * Examples
     * A = {1, 2, 3}, T = 1, return 1
     * A = {1, 2, 3}, T = 3, return -1
     * A = {1, 2, 2, 2, 3}, T = 1, return 1
     *
     * @param array
     * @param target
     * @return
     */
    public int smallestElementLargerThanTarget(int[] array, int target) {
        /*
            We can use BS to reduce the searching space by half each cycle
            if array[mid] <= target, left = mid + 1;
            if array[mid] > target, right = mid, we cannot delete right since we don't know if right is the result.

            when does dead loop happen? when left = right
            when there is only one element left, it is either the first smallest element larger than target, or there is no such element.
         */
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return array[left] > target ? left : -1;
    }
}
