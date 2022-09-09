package binarysearch;

public class FirstOccurrence {

    /**
     * Given a target integer T and an integer array A sorted in ascending order, find the index of the first occurrence of T in A or return -1 if there is no such index.
     * Assumption:
     * There can be duplicate elements in the array.
     *
     * Examples:
     * A = {1, 2, 3}, T = 2, return 1
     * A = {1, 2, 3}, T = 4, return -1
     * A = {1, 2, 2, 2, 3}, T = 2, return 1
     * @param array
     * @param target
     * @return
     */
    public int firstOccurrence(int[] array, int target) {
        /*
            What if array is null or empty, return -1

            We use binary search
            if (array[mid] == target), right = mid, since the return still can be on the left side
            if (array[mid] > target), right = mid - 1, since the return can only be on the left side
            if (array[mid] < target), left = mid + 1, since the return can only be on the right

            overall,
            if >= , right = mid
            if < left = mid + 1

            Termination? when there is only one element left.

            return
                if array[left] == target, return left : -1
         */

        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return array[left] == target ? left : -1;
    }

}
