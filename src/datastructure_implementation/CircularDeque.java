package datastructure_implementation;

/**
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Your implementation should support following operations:
 *
 * MyCircularDeque(k): Constructor, set the size of the deque to be k.
 * insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
 * insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
 * deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
 * deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
 * getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
 * getRear(): Gets the last item from Deque. If the deque is empty, return -1.
 * isEmpty(): Checks whether Deque is empty or not.
 * isFull(): Checks whether Deque is full or not.
 */
public class CircularDeque {

    /*      f
            o o o o o o
            r
     */
    private int[] array;
    //points to the first element that can be dequeued in the front side
    private int front;
    //points to the position that we can enqueue in the rear side
    private int rear;
    public CircularDeque(int capacity) {
        array = new int[capacity + 1];
    }

    /**
     * insert an element in the front side
     * @param value
     * @return return true if insert successfully
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + array.length) % array.length;
        array[front] = value;
        return true;
    }

    /**
     * insert an element in the rear side
     * @param value
     * @return return true if insert successfully
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        array[rear] = value;
        rear = (rear + 1) % array.length;
        return true;
    }

    /**
     * delete an element from the front side
     * @return return true if insert successfully
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % array.length;
        return true;
    }

    /**
     * delete an element from the rear side
     * @return return true if delete successfully
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + array.length) % array.length;
        return true;
    }

    /**
     * Get the front element from the Deque
     * @return
     */
    public int getFront() {
        return isEmpty() ? -1 : array[front];
    }

    /**
     * Get the last element from the Deque
     * @return
     */
    public int getRear() {
        return isEmpty() ? -1 : array[(rear - 1 + array.length) % array.length];
    }

    /**
     *
     * @return return true if the deque is empty
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     *
     * @return return true if the deque is full
     */
    public boolean isFull() {
        return size() == array.length - 1;
    }

    /**
     *
     * @return return size of the Deque
     */
    public int size() {
        return (rear - front + array.length) % array.length;
    }

}
