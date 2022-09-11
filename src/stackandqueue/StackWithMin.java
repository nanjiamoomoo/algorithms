package stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Enhance the stack implementation to support min() operation. min() should return the current minimum value in the stack. If the stack is empty, min() should return -1.
 *
 * push(int element) - push the element to top
 * pop() - return the top element and remove it, if the stack is empty, return -1
 * top() - return the top element without remove it, if the stack is empty, return -1
 * min() - return the current min value in the stack.
 */
public class StackWithMin {
    /*
        [ 2 3 2 1
        [ 2 2 1
        high level idea:
        push:
            when the new element is larger than the min stack top, we only push it in the stack, but don't push it in the min stack
            when the new element is smaller than or equals to the min stack top, we push it in both

        pop()
            when the top element is larger than the current min, we only pop from the stack
            when the top element is equal to the current min, we pop is from both stacks.
     */
    Deque<Integer> stack = new ArrayDeque<>();
    Deque<Integer> min = new ArrayDeque<>();


    /**
     * push the element to top
     * @param element
     */
    public void push(int element) {
        if (stack.isEmpty() || element <= min.peekFirst()) {
            min.offerFirst(element);
        }
        stack.offerFirst(element);
    }

    /**
     *
     * @return the top element and remove it, if the stack is empty, return -1
     */
    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        int res = stack.pollFirst();
        if (res == min.peekFirst()) {
            min.pollFirst();
        }
        return res;
    }

    /**
     *
     * @return the top element without remove it, if the stack is empty, return -1
     */
    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peekFirst();
    }

    /**
     *
     * @return the current min value in the stack.
     */
    public int min() {
        if (stack.isEmpty()) {
            return -1;
        }
        return min.peekFirst();
    }
}
