package binarysearch;

public class ShiftPosition {

    /**
     * Given an integer array A, A is sorted in ascending order first then shifted by an arbitrary number of positions, For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index of the smallest number.
     * Assumptions: There are no duplicate elements in the array
     * What if A is null or A is of zero length? We should return -1 in this case.
     * Examples:
     * A = {3, 4, 5, 1, 2}, return 3
     * A = {1, 2, 3, 4, 5}, return 0
     * @param array
     * @return
     */
    public int shiftPosition(int[] array) {
        /*
            we can use binary search
            if the searching array[left] < array[right], then we can just return left

            if array[mid] >= array[left], then left = mid + 1
            if array[mid] < array[left], then right = mid

         */
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left] < array[right]) {
                return left;
            }
            int mid = left + (right - left) / 2;
            if (array[mid] >= array[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
