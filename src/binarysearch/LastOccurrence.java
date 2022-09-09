package binarysearch;

public class LastOccurrence {

    /**
     * Given a target integer T and an integer array A sorted in ascending order, find the index of the last occurrence of T in A or return -1 if there is no such index.
     * Assumption:
     * There can be duplicate elements in the array.
     * A = {1, 2, 3}, T = 2, return 1
     * A = {1, 2, 3}, T = 4, return -1
     * A = {1, 2, 2, 2, 3}, T = 2, return 3
     * @param array
     * @param target
     * @return
     */
    public int lastOccurrence(int[] array, int target) {
        /*
            We can use binary search
            if array[mid] = target, left = mid, since result can be itself or on its right
            if array[mid] < target, left = mid + 1, since the result must be on the right
            if array[mid] > target, right = mid - 1, since the result can only be on the left side

            over all,
            if =<, left = mid,
            if > right = mid - 1,

            Termination: when there are two elements left
            return
                if array[right] = target, return right,
                if array[left] = target, return left
                else return -1

         */
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) /2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return array[right] == target ? right : array[left] == target ? left : -1;
    }
}
