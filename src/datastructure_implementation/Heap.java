package datastructure_implementation;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Implement a binary min heap with several APIs including, offer, poll, update, etc
 */
public class Heap {
    private int[] array;
    private int size;
    private static final float RESIZE_FACTOR = 1.5f;

    public Heap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be <= 0!");
        }
        array = new int[capacity];
    }

    public Heap(int[] array) {
        Heapify(array);
    }


    private void Heapify(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty!");
        }
        this.array = array;
        this.size = array.length;
        /*
           We do percolate down of each parent node from bottom to top.
           We are using property that if the subtrees are binary heap, when we do percolate down, we can guarantee the new tree is also a binary heap
           what is the range of the parent index? [0, size / 2 - 1]
         */
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Offer a new element into the heap
     * @param val
     */
    public void offer(int val) {
        /*
            When we offer an element into the heap, we need to do percolate up operation.
            if the child index is i, the parent index in the heap is (i - 1) / 2
            while(i >= 1, and array[i - 1 / 2] > array[i]), then swap
         */
        if (size == array.length) {
            array = Arrays.copyOf(array, (int)(array.length * RESIZE_FACTOR));
        }
        array[size++] = val;
        percolateUp(size - 1);
    }

    /**
     * Peek the top (smallest) element from the heap
     */
    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty!");
        }
        return array[0];
    }

    /**
     * Poll the top element from the minHeap
     * @return the top smallest element
     */
    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty!");
        }
        /*
            step1: we swap the last element with the first element, and reduce the size of the heap by 1
            step2: we do percolate down operation to move the top element down
                property: if the subtrees are binary heap, when we do percolate down, we can guarantee the new tree is also a binary heap
                We should always swap the smaller child
                if parent index is i, left child is 2 * i + 1, the right child is 2 * i + 2

                The last parent index of binary tree is (size / 2 - 1)
                so the parent index should be
                    while (i <= size / 2 -1 )
                Please also note the right child may not exist when the parent index = size / 2 - 1
                we need to make sure 2 * i + 2 < size, otherwise if the smallest must be the left child
         */

        //step1: swap
        int res = array[0];
        swap(array, 0, --size);
        percolateDown(0);
        return res;
    }

    /**
     * Update the element at a specified index.
     * @param index
     * @param ele
     * @Return the old value
     */
    public int update(int index, int ele) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("invalid index range!");
        }

        /*
            in order to maintain the original property of the binary heap, we need to either do percolate up or percolate down
            int oldValue = array[index]
            array[index] = ele;
            if ele > oldValue, percolateDown
            else percolateUp
         */
        int oldValue = array[index];
        array[index] = ele;
        percolateUp(index);
        percolateDown(index);
        return oldValue;
    }

    /**
     * move the smaller elements up in the heap
     */
    private void percolateUp(int index) {
        while (index >= 1) {
            int parent = (index - 1) / 2;
            if (array[parent] > array[index]) {
                swap(array, index, parent);
            } else {
                break;
            }
            index = parent;
        }
    }

    /**
     * move the top elements down in the heap
     */
    private void percolateDown(int index) {
        while (index <= size / 2 - 1) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int minChildIndex = leftChildIndex;
            if (rightChildIndex < size && array[rightChildIndex] < array[leftChildIndex]) {
                minChildIndex = rightChildIndex;
            }
            if (array[index] > array[minChildIndex]) {
                swap(array, index, minChildIndex);
            } else {
                break;
            }
            index = minChildIndex;
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
