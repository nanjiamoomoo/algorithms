package sort;

public class HeapSort {

    /**
     * Heap Sort is a comparison based sorting algorithm with O(nlogn) time and O(1) space.
     *
     * Requirements:
     * You have to do it in place, extra space used is no more than O(1).
     * Time complexity is O(nlogn).
     * @param array
     * @return
     */
    public int[] heapSort(int[] array) {

        /*
            Step1: build maxHeap using Heapify
            Step2: swap the top element with the last element and reduce the size of the array by 1. Then percolate down the top element to maintain the heap property.
            Step3: do step 2 until the array size is 1.
         */
        if (array == null || array.length <= 0) {
            return array;
        }
        int size = array.length;
        //step1:
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(array, array.length, i);
        }
        //step2:
        while (size-- != 1) {
            swap(array, 0, size);
            percolateDown(array, size,0);
        }
        return array;

    }

    private void percolateDown(int[] array, int size, int index) {
        while (index <= size / 2 - 1) {
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = index * 2 + 2;
            int maxChildIndex = leftChildIndex;
            if (rightChildIndex < size && array[rightChildIndex] > array[leftChildIndex]) {
                maxChildIndex = rightChildIndex;
            }
            if (array[index] < array[maxChildIndex]) {
                swap(array, index, maxChildIndex);
            } else {
                break;
            }
            index = maxChildIndex;
        }
    }

    private void swap(int[] array, int i , int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 2};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(array);
    }
}
