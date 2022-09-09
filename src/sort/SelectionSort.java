package sort;

public class SelectionSort {

    /**
     * Given an array of integers, sort the elements in the array in ascending order. The selection sort algorithm should be used to solve this problem.
     *
     * Examples:
     * {1} is sorted to {1}
     * {1, 2, 3} is sorted to {1, 2, 3}
     * {3, 2, 1} is sorted to {1, 2, 3}
     * {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
     * @param array
     * @return
     */
    public int[] selectionSort(int[] array) {
        /*
            Selection sort is to select the smallest element in the current sorting space and swap with the first index in the sorting space
         */
        if (array == null || array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int smallest = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[smallest]) {
                    smallest = j;
                }
            }
            swap(array, i, smallest);
        }
        return array;
    }

    private void swap(int[] array, int i , int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
