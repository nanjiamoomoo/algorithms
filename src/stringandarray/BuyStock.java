package stringandarray;

import java.util.ArrayDeque;
import java.util.Arrays;
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

            We can partition the array into 2 section
            j = 0; j <= array.length - 2
            [0, j], [j + 1, array.length - 1]
            the problem changes to find the maxProfit we can make before day j
            and the maxProfit we can make after day j

            then we find the globalMax
            TC: O(n ^ 2)
            SC: O(1)

             int maxProfit = 0;
            for (int i = 1; i < array.length - 1; i++) {
                maxProfit = Math.max(maxProfit, maxProfit(array, 0, i) + maxProfit(array, i + 1, array.length - 1));
            }
            return maxProfit;

            private int maxProfit(int[] array, int i, int j) {
                int max = 0;
                int currMin = array[i];
                for (int k = i + 1; k <= j; k++) {
                    currMin = Math.min(currMin, array[k]);
                    max = Math.max(max, array[k] - currMin);
                }
                return max;
            }

           Better solution: Bidirectional Dynamic Programming
           firstTransaction[i] represents the maxProfit we can make until day i with one transaction
           firstTransaction[i] = Math.max(firstTransaction[i - 1], array[i] - lowestPrice so far)

           secondTransaction[i] represents the maxProfit we can make from day i to last day with one transaction
           secondTransaction[i] = Math.max(secondTraction[i + 1], maxPrice from right - array[i]

           Then we can partition the array into two section
           [0, i], [i + 1, j]
           find the globalMax of
                    maxProfit[i] + secondTraction[i + 1]

            TC: O(n)
            SC: O(n)


         */

        int[] firstTransaction = new int[array.length];
        firstTransaction[0] = 0;
        int lowestPrice = array[0];
        for (int i = 1; i < array.length; i++) {
            lowestPrice = Math.min(lowestPrice, array[i]);
            firstTransaction[i] = Math.max(firstTransaction[i - 1], array[i] - lowestPrice);
        }
        int[] secondTransaction = new int[array.length];
        secondTransaction[array.length - 1] = 0;
        int highestPrice = array[array.length - 1];
        for (int i = array.length - 2; i >= 0; i--) {
            highestPrice = Math.max(highestPrice, array[i]);
            secondTransaction[i] = Math.max(secondTransaction[i + 1], highestPrice - array[i]);
        }

        int maxProfit = firstTransaction[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            maxProfit = Math.max(maxProfit,firstTransaction[i] + secondTransaction[i + 1]);
        }
        return maxProfit;

        /*
            Best solution: one pass simulation solution
            The two transactions be decomposed into 4 actions: "buy of transaction #1", "sell of transaction #1", "buy of transaction #2" and "sell of transaction #2".
            we calculate 4 variables which correspond to the costs or the profits of each action respectively, as follows:

            t1_cost: the minimal cost of buying the stock in transaction #1. The minimal cost to acquire a stock would be the minimal price value that we have seen so far at each step.

            t1_profit: the maximal profit of selling the stock in transaction #1. Actually, at the end of the iteration, this value would be the answer for the maxProfit with one transaction only.

            t2_cost: the minimal cost of buying the stock in transaction #2, while taking into account the profit gained from the previous transaction #1. One can consider this as the cost of reinvestment. Similar with t1_cost, we try to find the lowest price so far, which in addition would be partially compensated by the profits gained from the first transaction.

            t2_profit: the maximal profit of selling the stock in transaction #2. With the help of t2_cost as we prepared so far, we would find out the maximal profits with at most two transactions at each step.

            TC: O(n)
            SC: O(1)

            int t1Cost = Integer.MAX_VALUE,
                t2Cost = Integer.MAX_VALUE;
            int t1Profit = Integer.MIN_VALUE,
                t2Profit = Integer.MIN_VALUE;

            for (int price : array) {
                t1Cost = Math.min(t1Cost, price);
                t1Profit = Math.max(t1Profit, price - t1Cost);

                t2Cost = Math.min(t2Cost, price - t1Profit);
                t2Profit = Math.max(t2Profit, price - t2Cost);
            }
            return t2Profit;


         */
    }
    public static void main(String[] args) {
        int[] a = {3,4,1,2,6,2,3,5,1,7,3,8};
        BuyStock buyStock = new BuyStock();
        System.out.println(buyStock.maxProfitIII(a));
    }


    /**
     * Given an array of integers representing a stock’s price on each day. On each day you can only make one operation: either buy or sell one unit of stock, and at any time you can only hold at most one unit of stock, and you can make at most K transactions in total. Determine the maximum profit you can make.
     * Assumption: The give array is not null and is length of at least 2
     * Examples: {2, 3, 2, 1, 4, 5, 2, 11}, K = 3, the maximum profit you can make is (3 - 2) + (5 - 1) + (11 - 2)= 14
     * @param array
     * @return
     */
    public int maxProfit(int[] array, int k ) {
        /*
            Extension for problem 3 using one pass simulation solution
            TC: O(k*n)
            SC: O(k)
         */
        //cost[i] represents the min cost of the ith transaction
        //profit[i] represents the max Profit of the ith transaction cost
        int[] cost = new int[k + 1];
        int[] profit = new int[k + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(profit, Integer.MIN_VALUE);

        profit[0] = 0;
        for (int price : array) {
            for (int i = 1; i <= k; i++) {
                cost[i] = Math.min(cost[i], price - profit[i - 1]);
                profit[i] = Math.max(profit[i], price - cost[i]);
            }
        }
        return profit[k];

        /*
            cost[i] = Math.min(cost[i], price - profit[i - 1]);
            profit[i] = Math.max(profit[i], price - cost[i]);
         */

    }






}
