package stackandqueue;

import java.util.LinkedList;

public class SortWithTwoStacks {

    /**
     * Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).
     * After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.
     *
     * Assumption:
     * The given stack is not null.
     * There can be duplicated numbers in the give stack.
     *
     * Requirements:
     * No additional memory, time complexity = O(n ^ 2).
     *
     * @param s1 : the stack contains sorted elements
     */
    public void sort(LinkedList<Integer> s1) {
        /*
            s1: [ 4 4 | 2 1 3
            s2: [
            Step1: We pop out every unsorted element from stack 1 and push into the stack 2. During this process, we maintain the global max and the count of globalMax
            Step2: Push the globalMax in the stack1. Pop out every element stack 2 back to stack 1, during this process ignore all elements with the value of globalMax

            During this process, we need to track the unsorted size of elements in stack1.
            Repeat step1 and step2 until the whole stack is sorted.

         */
        LinkedList<Integer> s2 = new LinkedList<>();
        int unsortedSize = s1.size();
        while (unsortedSize != 0) {
            int size = unsortedSize;
            int max = s1.peekFirst();
            int count = 0;
            //step1:
            while (size-- != 0) {
                int ele = s1.pollFirst();
                if (ele > max) {
                    max = ele;
                    count = 1;
                } else if (ele == max) {
                    count++;
                }
                s2.offerFirst(ele);
            }

            //step2:
            for (int i = 0; i < count; i++) {
                s1.offerFirst(max);
            }

            //step3:
            while (!s2.isEmpty()) {
                int ele = s2.pollFirst();
                if (ele != max) {
                    s1.offerFirst(ele);
                }
            }

            unsortedSize -= count;
        }
    }

    /*
        Another method:
        public void sort(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<Integer>();

        while (!s1.isEmpty()) {
            int globalMin = Integer.MAX_VALUE;
            while (!s1.isEmpty()) {
                int curr = s1.pollFirst();
                globalMin = Math.min(globalMin, curr);
                s2.offerFirst(curr);
            }
            int count = 0;
            while (!s2.isEmpty() && s2.peekFirst() >= globalMin) {
                int curr = s2.pollFirst();
                if (curr == globalMin) {
                    count++;
                    continue;
                }
                s1.offerFirst(curr);
            }
            for (int i = 0; i < count; i++) {
                s2.offerFirst(globalMin);
            }
        }
        while (!s2.isEmpty()) {
            s1.offerFirst(s2.pollFirst());
        }
    }
     */


}
