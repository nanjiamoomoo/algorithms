package heap;

public class DetermineIfArrayIsMinHeap {

    /**
     * Determine if the given integer array is min heap.
     * Assumption:
     * array is not null and is not empty.
     * @param array
     * @return
     */
    public boolean isMinHeap(int[] array) {
        /*
            if an array is minHeap, then for every index from [0, size / 2 - 1], its value must be smaller or equal to its both child
         */

        int lastParentIndex = array.length / 2 - 1;
        for (int i = 0; i <= lastParentIndex; i++) {
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;
            int min = array[leftChildIndex];
            if (rightChildIndex < array.length && array[rightChildIndex] < array[leftChildIndex]) {
                min = array[rightChildIndex];
            }
            if (array[i] > min) {
                return false;
            }
        }
        return true;
    }

}
