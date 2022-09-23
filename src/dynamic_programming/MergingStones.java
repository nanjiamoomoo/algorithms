package dynamic_programming;

public class MergingStones {
    /**
     * We have a list of piles of stones, each pile of stones has a certain weight, represented by an array of integers. In each move, we can merge two adjacent piles into one larger pile, the cost is the sum of the weights of the two piles. We merge the piles of stones until we have only one pile left. Determine the minimum total cost.
     *
     * Assumptions
     *
     * stones is not null and is length of at least 1
     * Examples
     *
     * {4, 3, 3, 4}, the minimum cost is 28
     *
     * merge first 4 and first 3, cost 7
     *
     * merge second 3 and second 4, cost 7
     *
     * merge two 7s, cost 14
     *
     * total cost = 7 + 7 + 14 = 28
     * @param stones
     * @return
     */
    public int minCost(int[] stones) {
        /*
            we can use dp to solve this problem
            we can define array minCost[i][j] represents the total min cost to merge stones from ith position to jth position
            minCost[i][i] = 0
            minCost[i][i + 1] = stones[i] + stones[i + 1]

            minCost[i][j] = min of {
                for k from i ~ j - 1
                minCost[i][k] + minCost[k+ 1][j] + subSum[i~j]
            }
            TC: O(n^3)
            SC: O(n^2)
         */

        int[] prefixSum = new int[stones.length];
        prefixSum[0] = stones[0];

        int[][] minCost = new int[stones.length][stones.length];
        for (int j = 0; j < stones.length; j++) {
            if (j != 0) {
                prefixSum[j] = prefixSum[j - 1] + stones[j];
            }
            for (int i = j; i >= 0; i--) {
                int subArraySum = prefixSum[j] - prefixSum[i] + stones[i];
                if (i == j) {
                    minCost[i][j] = 0;
                    continue;
                }
                minCost[i][j] = minCost[i][i] + minCost[i + 1][j] + subArraySum;
                for (int k = i + 1; k < j; k++) {
                    minCost[i][j] = Math.min(minCost[i][j], minCost[i][k] + minCost[k + 1][j] + subArraySum);
                }
            }
        }
        return minCost[0][stones.length - 1];
    }

    public static void main(String[] args) {
        int[] stones = {4, 1, 3, 2};
        MergingStones mergingStones = new MergingStones();
        mergingStones.minCost(stones);
    }
}
