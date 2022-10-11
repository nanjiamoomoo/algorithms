package graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    /**
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums toT. The same repeated number may be chosen from C unlimited number of times.
     *
     * All numbers (including target) will be positive integers.
     *
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
     *
     * The solution set must not contain duplicate combinations.
     *
     * Assumption: if there is no such combination, return empty set
     *
     * Example: given candidate set 2,3,6,7 and target 7,
     * A solution set is:
     *      [7]
     *      [2, 2, 3]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
     /*
        we can use DFS to solve the problem
        define a dfs method
          dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> combination, int cumulativeSum， int index) {
            // combination contains elements we added so far
            //cumulativeSum represents the current totol sum of elements added
            //index is the first index of the possible elements to be added so far in current layer

          }
        since the question requires the all elements in combination must be in non descending order, so we need to sort candidates first

        what is the base case: when sum == target, add to result, return
                               when sum > target, return, since all elements are positive
     */
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        allCombinations(candidates, target, res, combination, 0, 0);
        return res;
    }

    private void allCombinations(int[] candidates, int target, List<List<Integer>> res, List<Integer> combination, int cumulativeSum, int index) {
        if (cumulativeSum == target) {
            res.add(new ArrayList<>(combination));
            return;
        }
        if (cumulativeSum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            combination.add(candidates[i]);
            allCombinations(candidates, target, res, combination, cumulativeSum + candidates[i], i);
            combination.remove(combination.size() - 1);
        }
    }
}
