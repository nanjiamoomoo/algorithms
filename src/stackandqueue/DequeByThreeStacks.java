package stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implement a deque by using three stacks. The queue should provide size(), isEmpty(), offerFirst(), offerLast(), pollFirst(), pollLast(), peekFirst() and peekLast() operations. When the queue is empty, pollFirst(), pollLast(), peekFirst() and peek() should return null.
 * Assumption:
 * The elements in the queue are all Integers.
 * size() should return the number of elements buffered in the queue.
 * isEmpty() should return true if there is no element buffered in the queue, false otherwise.
 * The amortized time complexity of all operations should be O(1).
 */
public class DequeByThreeStacks {
    /*
         first                   last
       s1:        ][ 4  5  6 :s2
       s3 [

       offerFirst:
            push in stack1
       pollFirst:
            pop from stack1

       offerLast:
            push in stack2
       pollLast:
            pop from stack2

       What if when we pollFirst, but stack 1 is empty
            step1: move half elements from stack2 in to stack3(buffer)
            step2: move the remaining elements from stack2 to stack1
            step3: move the elements from buffer back to stack2

            amortized TC:
                assume there are n elements (all in stack2)
                in the worst scenario:

                We do pollFirst:
                    the first pollFirst: n + n + n + 1
                    the next n / 2 - 1 operations, we do pollFirst, the total time is 3n + n/2. Average time is (3n + n/2) / (n/2)

                Then we do pollLast:
                    the first pollLast: n/2 + n/2 + n/ 2 + 1
                    the next n / 4 - 1 operations, we do pollLast, the total time is 3n/2 + n/4. Average time is (3n/2 + n/4) / (n/4)

                Total amortized time is O(7)


     */

    Deque<Integer> s1;
    Deque<Integer> s2;
    Deque<Integer> buffer;

    public DequeByThreeStacks() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
        buffer = new ArrayDeque<>();
    }
    /**
     * Offer an element into the Queue from First end
     * @param element
     */
    public void offerFirst(int element) {
        s1.offerFirst(element);
    }

    /**
     * Offer an element into the Queue from Last end
     * @param element
     */
    public void offerLast(int element) {
        s2.offerFirst(element);
    }

    /**
     * Move half elements from src to destination
     * @param src
     * @param dest
     */
    private void move(Deque<Integer> src, Deque<Integer> dest) {
        //step1: move half elements from src to buffer
        int size = src.size();
        for (int i = 0; i < size / 2; i++) {
            buffer.offerFirst(src.pollFirst());
        }
        while (!src.isEmpty()) {
            dest.offerFirst(src.pollFirst());
        }
        while (!buffer.isEmpty()) {
            src.offerFirst(buffer.pollFirst());
        }
    }

    /**
     * Poll an element from First end
     * @return the element to be polled. Return null if the deque is empty.
     */
    public Integer pollFirst() {
        if (!s1.isEmpty()) {
            return s1.pollFirst();
        }
        move(s2, s1);
        return s1.pollFirst();
    }

    /**
     * Poll an element from the Last end
     * @return the element to be polled. Return null if the deque is empty.
     */
    public Integer pollLast() {
        if (!s2.isEmpty()) {
            return s2.pollFirst();
        }
        move(s1, s2);
        return s2.pollFirst();
    }

    /**
     * Peek the element from the First end
     * @return the element peeked. Return null if the deque is empty.
     */
    public Integer peekFirst() {
        if (!s1.isEmpty()) {
            return s1.peekFirst();
        }
        move(s2, s1);
        return s1.peekFirst();
    }

    /**
     * Peek the element from the Last end
     * @return the element peeked. Return null if the deque is empty.
     */
    public Integer peekLast() {
        if (!s2.isEmpty()) {
            return s2.peekFirst();
        }
        move(s1, s2);
        return s2.peekFirst();
    }

    /**
     *
     * @return size of the deque
     */
    public int size() {
        return s1.size() + s2.size();
    }

    /**
     *
     * @return true if the deque is empty. Otherwise return false.
     */
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
