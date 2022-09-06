package graph.bst;

import graph.dfs.lca.TreeNode;

public class LargestSmallerInBST {

    /**
     * In a binary search tree, find the node containing the largest number smaller than the given target number.
     * If there is no such number, return -2^31.
     * Assumption:
     * The given root is not null.
     * There are no duplicate keys in the binary search tree.
     * Examples
     *
     *     5
     *
     *   /    \
     *
     * 2      11
     *
     *      /    \
     *
     *     6     14
     * largest number smaller than 10 is 6     *
     * largest number smaller than 6 is 5
     * @param root
     * @param target
     * @return
     */
    public int largestSmaller(TreeNode root, int target) {
        /*
            if current root is larger than or equal to the target, go left
            if current root is smaller than target, we update the result and go left

            We do above until there is no way to go
            TC: O(h)
            SC: O(h)
         */

        int result = Integer.MIN_VALUE;
        while (root != null) {
            if (root.key >= target) {
                root = root.left;
            } else {
                result = root.key;
                root = root.right;
            }
        }
        return result;
    }
}
