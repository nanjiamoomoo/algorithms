package recursion.maxpathsum;

import recursion.lca.TreeNode;

public class MaxPathSumFromLeafToLeaf {

    /**
     * Given a binary tree in which each node contains an integer number. Find the maximum possible sum from one leaf node to another leaf node. If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).
     *
     * Examples
     *
     *   -15
     *
     *   /    \
     *
     * 2      11
     *
     *      /    \
     *
     *     6     14
     *
     * The maximum path sum is 6 + 11 + 14 = 31.
     * @param root
     * @return
     */
    public int maxPathSumFromLeafToLeaf(TreeNode root) {
        /*
            we can define a method maxPathSumFromLeafToRoot(root)
                this method returns the max possible sum from leaf to root

            on the left tree: int left = maxPathSumFromLeafToRoot(root.left), left is the max path sum from leaf to root on the left subtree
            on the right tree: int right = maxPathSumFromLeafToRoot(root.right), right is the max path sum from leaf to root on the right subtree

            we maintain a global max to keep the maximum possible sum from one leaf node to another leaf node

            on current layer
            1. update globalMax with left + right + root.key
            2. return root.key + Math.max(left, right)

            corner cases:
            if a root is a leaf node: we return root.key
            if a root has only one child branch, we don't update global max on current layer since there is no leaf to leaf
            if a root has both children, we can follow the algorithm above
         */
        int[] globalMax = {Integer.MIN_VALUE};
        maxPathSumFromLeafToRoot(root, globalMax);
        return globalMax[0];

    }

    public int maxPathSumFromLeafToRoot(TreeNode root, int[] globalMax) {
        //base case
        if (root == null) {
            return 0;
        }
        //leaf node
        if (root.left == null && root.right == null) {
            return root.key;
        }
        int left = maxPathSumFromLeafToRoot(root.left, globalMax);
        int right =  maxPathSumFromLeafToRoot(root.right, globalMax);
        //one branch is null;
        if (root.left == null || root.right == null) {
            return root.left == null ? right + root.key : left + root.key;
        }
        globalMax[0] = Math.max(globalMax[0], left + right + root.key);
        return root.key + Math.max(left, right);
    }
}
