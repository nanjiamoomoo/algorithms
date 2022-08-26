package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {

    /**
     * Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest rectangular area that can be formed in the histogram.
     * Assumption: The given array is not null or empty
     * Examples: { 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4)
     * @param array
     * @return
     */
    public int largestRectangleInHistogram(int[] array) {

        /*
                    x
                x x x
            x   x x x
            x x x x x
            for each position, we need to find the first bar index1 on the left side has a less height than current bar
            and the first bar index2 on the right side has a less height than current bar, then we can calculate the max rectangular area by using (index2 - index1 - 1) * height
            we can maintain a globalMax

            The time complexity is O(n^2)

            since we are going to find the first smaller element on the left, we can use monotonic stack to solve the problem
            we can use a stack to keep the index for the bars with monotonic increasing height.
            For each element in the stack, its previous element in the stack is the index of the first lower bar on the left side.

               2  1   3   3   4
                  i
               [0

               when current bar height is less than the stack top, then we calculate the max rectangular histogram formed at the stack top position, since we know its first index on the left
               and right side with lower height.

               if (stack.isEmpty() || array[i] >= stack.peek()) {
                    offer new element

               } else  {
                    while (!stack.isEmpty() && array[i] < stack.peek()) {
                        pop elements from stack and update global max.
                        what if the stack is empty after pop, then the index1 can be -1.

                        when the i reaches to the end of the array, we need to pop all elements from stack to update globalMax,
                        in this case, i = array.length, we can give it a smaller value to guarantee it smaller than any values in the array
                        since this is a non-negative integer, we can give it value - 1.

                    }
               }


         */

        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i <= array.length; i++) {
            int curr = i == array.length ? -1 : array[i];
            while (!stack.isEmpty() && curr < array[stack.peekFirst()]) {
                int height = array[stack.pollFirst()];
                int left = stack.isEmpty() ? -1 : stack.peekFirst();
                maxArea = Math.max(maxArea, height * (i - left - 1));
            }
            stack.offerFirst(i);
        }
        return maxArea;
    }
}
