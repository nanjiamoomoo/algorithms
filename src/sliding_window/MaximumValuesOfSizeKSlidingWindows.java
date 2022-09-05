package sliding_window;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaximumValuesOfSizeKSlidingWindows {

    /**
     * Given an integer array A and a sliding window of size K, find the maximum value of each window as it slides from left to right.
     * Assumption:
     * 1. The given array is not null and is not empty
     * 2. K >= 1, K <= A.length
     * Examples:
     * A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},
     * and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
     * @param array
     * @param k
     * @return
     */
    public List<Integer> maxWindows(int[] array, int k) {
        /*
                1   2   3   2    4   2   1
                            i

                We can maintain a virtual sliding window of size k, and we use a pointer to traverse the array
                when i <= k - 1, for each new element, we update globalMax, when i == k - 1, we add the globalMax to the final result
                when i > k - 1 && i < array.length, for each position, we take out one element from the sliding window and add a new element in the window
                For each position, we traverse the virtual window to find the globalMax
                Note: If there is no duplicate element in the array, we can use O(1) to find the max, but since there could be duplicate elements, we can only traverse the entire array
                TC: k + (n - k) * k = nk - k^2 + k = nk - k(k - 1) = k (n - k + 1) when k = n / 2, it is n ^ 2 / 2 = O(n ^ 2)
                Above algorithm is equivalent to update the globalMax for each virtual window size k. The right boundaries of the window are from index i = k - 1 ~ array.length - 1

                    List<Integer> res = new ArrayList<>();
                    for (int i = k - 1; i < array.length; i++) {
                        int globalMax = Integer.MIN_VALUE;
                        for (int j = i - k + 1; j <= i; j++) {
                            globalMax = Math.max(globalMax, array[j]);
                        }
                        res.add(globalMax);
                    }
                    return res;

                Better solution?
                Can we use a deque to keep the potential max value in the descending order only. why descending?
                since the old, smaller value will never be qualified to be the max value.
                For each element, we should take out all the smaller elements in the deque from the right side, then offer it in the deque.
                The deque will be definitely in the descending order.
                Since the deque is in the descending order, the left most value is the biggest element. But it is also possible that the left most is not
                in the sliding window anymore, so we need to check its index in the array as well. For convenience, we can add index in the deque instead.
                       0   1   2   3    4   5   6 index
                       1   2   3   2    4   2   1
                                                i
                [ 4  5  6

                3 3 4 4 4

         */
        List<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {
            //if the new element is bigger or equals
            while (!deque.isEmpty() && array[i] >= array[deque.peekLast()]) {
                deque.pollFirst();
            }
            //offer the new element and
            deque.offerFirst(i);
            //add the max value to the result
           if (i >= k - 1) {
               //if the biggest value is not in the sliding window, we poll it out
               if (i - k + 1 > deque.peekLast()) {
                   deque.pollLast();
               }
               res.add(array[deque.peekLast()]);
           }
        }
        return res;
    }
}
