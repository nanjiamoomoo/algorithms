package Recursion.maxpathsum;

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
                define a method maxSubPathSumFromAnyToRoot(root)
                    this method returns the max path sum from any node (on the path from leaf to root) to the root.

                on the left tree:
                    int left = maxSubPathSumFromAnyToRoot(root.left)
                        left is the max path sum from any node to the left root
                    int right = maxSubPathSumFromAnyToRoot(root.right)
                        right is the max path sum from any node to the right root

                 maintain a globalMax to store the maximum possible path sum
                 on every layer we update globalMax

                 on current layer
                    if left <= 0 and right <= 0.
                        update globalMax with root.key
                            return root.key
                    if left <= 0 and right > 0
                        update globalMax with root.key + right;
                            return root.key + right;
                    if left > 0 and right <= 0
                        update globalMax with root.key + left
                            return root.key + left;
                    if left > 0 and right > 0
                        update globalMax with root.key + Math.max(left, right)
                            return root.key + Math.max(left, right)

                    we can combine above logic into a simple one
                        left < 0 ? 0 : left
                        right < 0 ? 0:  right
                        update globalMax with root.key + Math.max(left, right)
                        return root.key + Math.max(left, right)

                //corner cases analysis to verify above algorithms will cover all cases
                 if (node == null) return 0
                 if a leaf node, return node.key
                 if only one child, return the other branch > 0 ? the other branch + root.key : root.key

                 above algorithms can cover all these corner cases
         */

        int[] globalMax = {Integer.MIN_VALUE};
        maxSubPathSumFromAnyToRoot(root, globalMax);
        return globalMax[0];
    }

    private int maxSubPathSumFromAnyToRoot(TreeNode root, int[] globalMax) {
        if (root == null) {
            return 0;
        }
        int left = maxSubPathSumFromAnyToRoot(root.left, globalMax);
        int right = maxSubPathSumFromAnyToRoot(root.right, globalMax);
        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;
        globalMax[0] = Math.max(globalMax[0], root.key + Math.max(left, right));
        return root.key + Math.max(left, right);
    }
}
