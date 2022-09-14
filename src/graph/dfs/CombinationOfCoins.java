package graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class CombinationOfCoins {

    /**
     * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to pay a target number of cents.
     * Arguments:
     *  coins - an array of positive integers representing the different denominations of coins, there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
     *  target - a non-negative integer representing the target number of cents, eg. 99
     * Assumption:
     *  coins is not null and is not empty, all the numbers in coins are positive
     *  target >= 0
     *  You have infinite number of coins for each of the denominations, you can pick any number of the coins.
     *
     * Examples:
     * coins = {2, 1}, target = 4, the return should be=
     * [
     *   [0, 4],   (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)
     *   [1, 2],   (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)
     *   [2, 0]    (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)
     * ]
     *
     * @param target
     * @param coins
     * @return a list of ways of combinations of coins to sum up to be the target. Each way of combinations is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.
     */
    public List<List<Integer>> combinations(int target, int[] coins) {
        /*
            use DFS to solve the problem
                               99
      0          25     0    1   2   3
      1          10    99    74...

            on each recursion level i, we have [0, target / coins[i]] options to choose

            when the level = coins.length - 1, if the remaining target % coins[coins.length - 1] == 0. we found a valid result, otherwise we return
         */

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        findCombinations(res, solution, target, coins, 0);
        return res;
    }

    private void findCombinations(List<List<Integer>> res, List<Integer> solution, int target, int[] coins, int index) {
        if (index == coins.length - 1) {
            if (target % coins[coins.length - 1] == 0) {
                solution.add(target / coins[coins.length - 1]);
                res.add(new ArrayList<>(solution));
                solution.remove(solution.size() - 1);
            }
            return;
        }
        for (int i = 0; i <= target / coins[index]; i++) {
            solution.add(i);
            findCombinations(res, solution, target - i * coins[index], coins, index + 1);
            solution.remove(solution.size() - 1);
        }
    }
}
