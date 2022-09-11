package stackandqueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Implement a stack containing integers using queue(s). The stack should provide push(x), pop(), top() and isEmpty() operations.
 *
 * In java: if the stack is empty, then top() and pop() will return null.
 */
public class StackByQueues {
    /*
        q1:  3
        q2:  2 1

        Push: we offer the new element into q1

        pop: we poll all elements except the last one from q1 and offer them into q2, then poll the last element from q1.
             then we switch the reference of q1 and q2

     */
    Queue<Integer> q1;
    Queue<Integer> q2;

    public StackByQueues() {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }

    /**
     * push a new element in the stack
     * @param element
     */
    public void push(int element) {
        q1.offer(element);
    }

    /**
     * Remove the top element
     * @return the top element in the stack. If the stack is empty, return null
     */
    public Integer pop() {
        move(q1, q2);
        Integer res = q1.poll();
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
        return res;
    }

    private void move(Queue<Integer> src, Queue<Integer> dest) {
        while (src.size() > 1) {
            dest.offer(src.poll());
        }
    }

    /**
     * peek the top element without removing it
     * @return the top element in the stack. If the stack is empty, return -1
     */
    public Integer top() {
        Integer element = pop();
        if (element != null) {
            push(element);
        }
        return element;
    }

    /**
     *
     * @return true of the stack is empty, otherwise return false;
     */
    public boolean isEmpty() {
        return q1.isEmpty();
    }

}
