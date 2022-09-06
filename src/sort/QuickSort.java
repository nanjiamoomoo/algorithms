package sort;

public class QuickSort {

    /**
     * Given an array of integers, sort the elements in the array in ascending order. The quick sort algorithm should be used to solve this problem.
     * Examples
     * {1} is sorted to {1}
     * {1, 2, 3} is sorted to {1, 2, 3}
     * {3, 2, 1} is sorted to {1, 2, 3}
     * {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
     * Corner Cases
     * What if the given array is null? In this case, we do not need to do anything.
     * What if the given array is of length zero? In this case, we do not need to do anything.
     * @param array
     * @return
     */
    public int[] quickSort(int[] array) {
        /*
            For every recursion level we are going to choose a random pivot
            we are going to reorganize the current array so that all the elements smaller than the pivot value shall be on the left side
            and all the elements bigger than the pivot value shall be on the right side

            Then we do the same thing for the left side and right side until there are only one element left
            Details:
            After choose the pivot we swap the pivot with the most right elements
            Then we use two pointers, one on the left side, one on the right side
            1. all the elements to the left side [0, left) are smaller elements
            2. all the elements to the right side (right, n - 1] are bigger elements

            while (left <= right) {
                if (array[left] <= pivotValue) {
                    left++
                } else if (array[right] <= pivotValue) {
                    swap(left, right)
                    left++;
                    right--;
                } else {
                    right--;
                }
            }
            then we swap(left, the most right pivot)
            Then we do the same for the left space and right space until the entire array is sorted.
         */
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = partition(array, left, right);

        quickSort(array, left, mid - 1);
        quickSort(array, mid + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);
        pivotIndex = right;
        right -= 1;
        while (left <= right) {
            if (array[left] <= pivot) {
                left++;
            } else if (array[right] <= pivot) {
                swap(array, left++, right--);
            } else {
                right--;
            }
        }
        swap(array, left, pivotIndex);
        return left;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
