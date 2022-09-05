package sliding_window;

import java.util.ArrayDeque;
import java.util.Queue;

public class KClosestInSortedArrayVariation {
    /**
     * Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A. If there is a tie, the smaller elements are always preferred.
     * Assumption:
     * A is not null
     * K is guaranteed to be >= 0 and K is guaranteed to be <= A.length
     * Examples
     * A = {1, 2, 3}, T = 2, K = 3, return {1 2 3}
     * A = {1, 4, 6, 8}, T = 3, K = 3, return {1, 4, 6}
     * @param array
     * @param target
     * @param k
     * @return A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order.
     */
    public int[] kCloset(int[] array, int target, int k) {
        /*
            This problem can be solved using sliding window of K, but this may be not the optimized solution for this problem. Maybe binary search is better depends on the value of k.

            We can maintain the sliding window of size k to keep the current closest k elements
            1. if current window size is smaller than k, we add the new element in the window
            2. if current window size is k, we compare distance between the new element to the target with the distance between the most left element in the window to the target
                if the new element is closer, we add it to the result
                otherwise, we can stop since we found the k closest elements.
            TC: O(n)
            SC: O(k)
         */
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {
            if (queue.size() < k) {
                queue.offer(array[i]);
            } else {
                int left = queue.peek();
                if (Math.abs(array[i] - target) < Math.abs(left - target)) {
                    queue.poll();
                    queue.offer(array[i]);
                } else {
                    break;
                }
            }
        }
        int[] res = new int[queue.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = queue.poll();
        }
        return res;
    }
}
