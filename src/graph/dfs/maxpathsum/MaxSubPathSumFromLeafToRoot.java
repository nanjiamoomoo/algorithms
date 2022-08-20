package graph.dfs.maxpathsum;

import Recursion.lca.TreeNode;

public class MaxSubPathSumFromLeafToRoot {

    /**
     * Given a binary tree in which each node contains an integer number. Find the maximum possible subpath sum(both the starting and ending node of the subpath should be on the same path from root to one of the leaf nodes, and the subpath is allowed to contain only one node).
     *
     * Assumptions
     *
     * The root of given binary tree is not null
     * Examples
     *
     *    -5
     *
     *   /    \
     *
     * 2      11
     *
     *      /    \
     *
     *     6     14
     *
     *            /
     *
     *         -3
     *
     * The maximum path sum is 11 + 14 = 25
     * @param root
     * @return
     */
    public int maxSubPathSumFromLeafToRoot(TreeNode root) {
        /*
                we can use dfs with prefixSum
                define a method maxSubPathSumFromAnyToRoot(TreeNode root, int prefixSum, int[] globalMax)
                    the prefixSum is the sum of nodes in the path from top (any node is on the top) to current root(excluding)

                int current layer
                    if prefixSum <= 0, prefixSum = root.key
                    else we prefixSum += root.key

                how many states to generate?
                    left and right

                when do we need to update globalMax?
                    on each dfs level


                what if we reach to null, just return

         */
        int[] globalMax = {Integer.MIN_VALUE};
        maxSubPathSumFromAnyToRoot(root, 0, globalMax);
        return globalMax[0];

    }
    private void maxSubPathSumFromAnyToRoot(TreeNode root, int prefixSum, int[] globalMax) {
        if (root == null) {
            return;
        }
        prefixSum = prefixSum < 0 ? 0 : prefixSum;
        //update globalMax
        globalMax[0] = Math.max(globalMax[0], prefixSum + root.key);
        //how many states to generate
        maxSubPathSumFromAnyToRoot(root.left, prefixSum + root.key, globalMax);
        maxSubPathSumFromAnyToRoot(root.right, prefixSum + root.key, globalMax);

    }
}
