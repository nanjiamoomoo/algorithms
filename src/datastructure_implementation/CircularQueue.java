package datastructure_implementation;

/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 * <p>
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 * <p>
 * Your implementation should support following operations:
 * <p>
 * MyCircularQueue(k): Constructor, set the size of the queue to be capacity.
 * Front: Get the front item from the queue. If the queue is empty, return -1.
 * Rear: Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * isEmpty(): Checks whether the circular queue is empty or not.
 * isFull(): Checks whether the circular queue is full or not.
 */
public class CircularQueue {
    //rear points to the next element that can be dequeued.
    private int front;
    //front points to the position that we can enqueue an element
    private int rear;
    private int[] array;

    public CircularQueue(int capacity) {
        array = new int[capacity + 1];
    }

    /**
     * @return the front element of the queue.
     */
    public int front() {
        return isEmpty() ? - 1:  array[front];
    }

    /**
     * @return the rear element of the queue
     */
    public int rear() {
        return isEmpty() ? -1: array[(rear - 1 + array.length) % array.length];
    }

    /**
     * Insert an element into the circular queue.
     *
     * @param value
     * @return Return true if the operation is successful; else return false;
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        array[rear] = value;
        rear = (rear + 1) % array.length;
        return true;
    }

    /**
     * Delete an element from the circular queue
     *
     * @return Return true if the operation is successful; else return false;
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % array.length;
        return true;
    }

    /**
     * Get size of the circular queue
     *
     * @return size of the circular queue
     */
    public int size() {
        /*      f
                x x x x o
                        r
         */
        return (rear - front + array.length) % (array.length);
    }

    /**
     * @return Return true if the circular queue is empty; else return false.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return Return true if the circular queue is full; else return empty.
     */
    public boolean isFull() {
        return size() == array.length - 1;
    }
}
