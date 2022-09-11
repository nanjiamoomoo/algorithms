package datastructure_implementation;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Java: Implement a queue by using two stacks. The queue should provide size(), isEmpty(), offer(), poll() and peek() operations. When the queue is empty, poll() and peek() should return null.
 * Assumption:
 * The elements in the queue are all Integers.
 * size() should return the number of elements buffered in the queue.
 * isEmpty() should return true if there is no element buffered in the queue, false otherwise.
 */
public class QueueByTwoStacks {


    /*
        s1: [ 4
        s2: [3 2 1

        offer:
            push in s1
        poll:
            pop from s2
            if s2 is empty, move elements from s1 to s2
            if s2 is not empty, pop from s2

     */

    Deque<Integer> s1;
    Deque<Integer> s2;

    public QueueByTwoStacks() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }
    /**
     * offer the new element into queue
     * @param element
     */
    public void offer(int element) {
        s1.offerFirst(element);
    }

    /**
     * Remove one element from queue
     * @return the removed element, When the queue is empty return null
     */
    public Integer poll() {
        if (s2.isEmpty()) {
            move(s1, s2);
        }
        return s2.pollFirst();
    }

    private void move(Deque<Integer> src, Deque<Integer> dest) {
        while (!src.isEmpty()) {
            dest.offerFirst(s1.pollFirst());
        }
    }

    /**
     * Peek one element from queue
     * @return the peek element, When the queue is empty return null
     */
    public Integer peek() {
        if (s2.isEmpty()) {
            move(s1, s2);
        }
        return s2.peekFirst();
    }

    /**
     *
     * @return size of the queue
     */
    public int size() {
        return s1.size() + s2.size();
    }

    /**
     *
     * @return true if queue is empty. Otherwise return false
     */
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
