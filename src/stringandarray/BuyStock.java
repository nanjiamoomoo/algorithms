package stringandarray;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class BuyStock {

    /**
     * Given an array of positive integers representing a stock’s price on each day. On each day you can only make one operation: either buy or sell one unit of stock and you can make at most 1 transaction. Determine the maximum profit you can make.
     *
     * Assumption: The given array is not null and is length of at least 2.
     *
     * Examples:
     * {2, 3, 2, 1, 4, 5}, the maximum profit you can make is 5 - 1 = 4
     * @param array
     * @return
     */
    public int maxProfitI(int[] array) {
        /*
             we can an array lowestPrice[] to store the lowest price so far
             lowestPrice[i] represents the lowest price from day 0 to day i (inclusive)
             then the maximum profit we can make on that day is array[i] - lowestPrice[i].
               int[] lP = new int[array.length];
            lP[0] = array[0];
            int maxProfit = 0;
            for (int i = 0; i < array.length; i++) {
                lP[i] = Math.min(lP[i - 1], array[i]);
                maxProfit = Math.max(maxProfit, array[i] - lP[i]);
            }
               return maxProfit;


             Better solution:
             The solution becomes to find the current element - the min element so far
             TC: O(n)
             SC: O(1)
         */
         int currMin = array[0];
         int maxProfit = 0;
         for (int i = 1; i < array.length; i++) {
             currMin = Math.min(currMin, array[i]);
             maxProfit = Math.max(maxProfit, array[i] - currMin);
         }
         return maxProfit;

        /*
            another solution:
            dp[i] represents the maxProfit if we sell on day i
            case1 dp[i - 1] = 0, dp[i] = a[i] - a[i - 1] > 0 : a[i] - a[i - 1] : 0
            case2 dp[i - 1] > 0, dp[i] = dp[i - 1] + a[i] - a[i - 1] > 0 .....

            maintain globalMax
         */
    }

    /**
     * Given an array of positive integers representing a stock’s price on each day. On each day you can only make one operation: either buy or sell one unit of stock, you can make as many transactions you want, but at any time you can only hold at most one unit of stock. Determine the maximum profit you can make.
     * Assumptions
     * The give array is not null and is length of at least 2
     *
     * Examples
     * {2, 3, 2, 1, 4, 5}, the maximum profit you can make is (3 - 2) + (5 - 1) = 5
     *
     * @param array
     * @return
     */
    public int maxProfitII(int[] array) {
        /*
             the problem converts to find the all the ascending subarray and calculate the lastElement - firstElement for each ascending subarray

             Inorder to find all the ascending subarray we can use a deque data structure

             when we meet a larger or equal number we offer it in the deque
             when we meet a smaller number, we calculate the different between lastElement in the queue with the first element in the queue and add it in the final result
             then we offer the new element in

             note: when i = 0, we add it in the queue
                   when i = array.length, we remove everything from queue and update final result

             TC:O(n)
             SC:O(n)

             Actually we can further improve without using deque
             We can use two pointers, the first pointer points to the first element in the current subArray
             We use the second pointer to traverse
             if array[i] >= array[i - 1] i++
             else
                res = array[i - 1] - array[start]
                start = i

             int start = 0;
             for (int i = 1; i <= array.length; i++) {
                if (i == array.length || array[i] < array[i - 1]) {
                    res += array[i - 1] - array[start];
                    start = i;
                }
             }
             return res;

             TC: O(n)
             SC: O(1)
         */

        Deque<Integer> deque = new ArrayDeque<>();
        int res = 0;
        for (int i = 1; i <= array.length; i++) {
            if (!deque.isEmpty() && (i == array.length || array[i] < deque.peekLast())) {
                res += deque.peekLast() - deque.peekFirst();
                while (!deque.isEmpty()) {
                    deque.pollFirst();
                }
            }
            if (i != array.length) {
                deque.offerLast(array[i]);
            }
        }
        return res;
    }

    /**
     * Given an array of positive integers representing a stock’s price on each day. On each day you can only make one operation: either buy or sell one unit of stock, at any time you can only hold at most one unit of stock, and you can make at most 2 transactions in total. Determine the maximum profit you can make
     *
     * Assumptions
     * The give array is not null and is length of at least 2
     *
     * Examples
     * {2, 3, 2, 1, 4, 5, 2, 11}, the maximum profit you can make is (5 - 1) + (11 - 2) = 13
     *
     * @param array
     * @return
     */
    public int maxProfitIII(int[] array) {
        /*
            Can we find all the ascending subarray and choose the top 2 with the largest difference?
            No,
            e.g.3,4,1,2,6,2,3,5,1,7,3,8
            3 4 -> 1
            1 2 6 - > 5
            2 3 4 -> 3
            1 7 -> 6
            3 8 -> 5
            based on above logic, result is 11, however the actual result should be 12 [6 - 1] + [8 - 1]

            Then what is the solution?



         */
        Deque<Integer> deque = new ArrayDeque<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i <= array.length; i++) {
            if (!deque.isEmpty() && (i == array.length || array[i] < deque.peekLast())) {
                int currProfit = deque.peekLast() - deque.peekFirst();
                if (minHeap.size() < 2) {
                    minHeap.offer(currProfit);
                } else if (currProfit > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(currProfit);
                }
                while (!deque.isEmpty()) {
                    deque.pollFirst();
                }
            }
            if (i != array.length) {
                deque.offerLast(array[i]);
            }
        }
        int res = 0;
        while (!minHeap.isEmpty()){
            res += minHeap.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {3,4,1,2,6,2,3,5,1,7,3,8};
        BuyStock buyStock = new BuyStock();
        System.out.println(buyStock.maxProfitIII(a));
    }


}
