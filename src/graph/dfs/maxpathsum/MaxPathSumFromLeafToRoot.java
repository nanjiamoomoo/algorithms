package graph.dfs.maxpathsum;


import Recursion.lca.TreeNode;

public class MaxPathSumFromLeafToRoot {

    /**
     * Given a binary tree in which each node contains an integer number. Find the maximum possible path sum from a leaf to root.
     *

     * Assumptions
     * The root of given binary tree is not null.
     *
     * Examples
     *
     *          10
     *        /    \
     *     -2       7
     *   /    \
     * 8      -4
     *
     * The maximum path sum is 10 + 7 = 17.
     * @param root
     * @return
     */
    public int maxPathSumFromLeafToRoot(TreeNode root) {

        /*
            use DFS with prefix sum
            define a method maxPathSumRootToLeaf(TreeNode root, int prefixSum, int[] globalMax)
                prefixSum the sum of nodes in the path from Tree root to current node (excluding)

            in current layer we add root.key to the prefixSum

            how many states to generate? left and right

            when to update globalMax?
                when we reach out to a leaf node
                globalMax = Math.max(globalMax, prefixSum + root.key)

            what if there is one branch is null: we don't update globalMax
         */
        int[] globalMax = {Integer.MIN_VALUE};
        maxPathSumRootToLeaf(root, 0, globalMax);
        return globalMax[0];
    }

    private void maxPathSumRootToLeaf(TreeNode root, int prefixSum, int[] globalMax) {
        if (root == null) {
            return;
        }
        //only update globalMax at leaf node
        if (root.left == null && root.right == null) {
            globalMax[0] = Math.max(globalMax[0], prefixSum + root.key);
        }
        //go left
        maxPathSumRootToLeaf(root.left, prefixSum + root.key, globalMax);
        maxPathSumRootToLeaf(root.right, prefixSum + root.key, globalMax);
    }
}
