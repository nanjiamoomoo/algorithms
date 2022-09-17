package binarysearch;

public class TotalOccurrence {

    /**
     * Given a target integer T and an integer array A sorted in ascending order, Find the total number of occurrences of T in A.
     * Examples:
     * A = {1, 2, 3, 4, 5}, T = 3, return 1
     * A = {1, 2, 2, 2, 3}, T = 2, return 3
     * A = {1, 2, 2, 2, 3}, T = 4, return 0
     * Corner Cases: What if A is null? We should return 0 in this case.
     * @param array
     * @param target
     * @return
     */
    public int totalOccurrence(int[] array, int target) {

        /*
            use binary search
            find the first equal and last equal
            the count = last - first + 1

            first equal:
            if array[mid] = target, right = mid
            if array[mid] < target, left = mid + 1
            if array[mid] > target, right = mid

            last equal:
            if array[mid] = target, left = mid
            if array[mid] < target, left = mid
            if array[mid] > target, right = mid - 1
         */
        if (array == null || array.length == 0) {
            return 0;
        }
        int first = firstEqual(array, target, 0, array.length - 1);
        if (first == -1) {
            return 0;
        }
        int last = lastEqual(array, target, 0, array.length - 1);
        return last - first + 1;
    }

    private int firstEqual(int[] array, int target, int left, int right) {
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

    private int lastEqual(int[] array, int target, int left, int right) {
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return array[right] == target ? right : array[left] == target ? left : -1;
    }
}
