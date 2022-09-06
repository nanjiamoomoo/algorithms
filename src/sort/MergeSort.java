package sort;

public class MergeSort {

    /**
     * Given an array of integers, sort the elements in the array in ascending order. The merge sort algorithm should be used to solve this problem.
     * Examples:
     * {1} is sorted to {1}
     * {1, 2, 3} is sorted to {1, 2, 3}
     * {3, 2, 1} is sorted to {1, 2, 3}
     * {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
     * Corner Case:
     * What if the given array is null? In this case, we do not need to do anything.
     * What if the given array is of length zero? In this case, we do not need to do anything.
     * @param array
     * @return
     */
    public int[] mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        /*
            for merge sort, we use recursion to solve the problem
            1. we split the array into left and right
            2. we sort left and right respectively
            3. we merge them together

            how to merge them together?
                we use two pointers and move the smaller one
                we also need another array to help us to achieve the merge process

            e.g.     4  2 -3 6 1
                [-3 2 4]  [6 1]
                then we have another helper array to help us to achieve merging
                after merging, the original array becomes[-3, 1, 2, 4, 6]
            TC: O(nlogn)
            SC: O(n)
         */

        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
        return array;
    }

    private void mergeSort(int[] array, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;

        //sort left
        mergeSort(array, helper, left, mid);
        //sort right
        mergeSort(array, helper, mid + 1, right);

        //merge
        merge(array, helper, left, mid, right);
    }

    private void merge(int[] array, int[] helper, int left, int mid, int right) {
       // copy left and right portion to the helper array
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }
        //we merge them into the original array
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (helper[i] <= helper[j]) {
                array[k++] = helper[i++];
            } else {
                array[k++] = helper[j++];
            }
        }
        //if there are remaining elements on the left size, we need to
        while (i <= mid) {
            array[k++] = helper[i++];
        }
        //if there are remaining elements on the right size, we don't need to do everything, since they already exist in the original array
    }
}
