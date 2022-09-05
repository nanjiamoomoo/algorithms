package recursion.maxpathsum;

import recursion.lca.TreeNode;

public class MaxPathSumAnyToAny {

    /**
     * Given a binary tree in which each node contains an integer number. Find the maximum possible sum from any node to any node (the start node and the end node can be the same).
     *
     * Assumptions
     * The root of the given binary tree is not null
     *
     * Examples
     *
     *    -1
     *
     *   /    \
     *
     * 2      11
     *
     *      /    \
     *
     *     6    -14
     *
     * one example of paths could be -14 -> 11 -> -1 -> 2
     *
     * another example could be the node 11 itself
     *
     * The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
     * @param root
     * @return the maximum possible sum from any node to any node
     */
    public int maxPathSumAnyToAny(TreeNode root) {

        /*
                if we can define a method maxPathSumFromAnyToRoot(root)
                    this method returns the max path sum from any node to root

                on left child: we find int left = maxPathSumFromAnyToRoot(root.left), left is the max path sum from any node to the left branch root
                on right child: we find int right = maxPathSumFromAnyToRoot(root.right), right is the max path sum from any node to the right branch root

                we also maintain a globalmax to store the maximum possible sum from any node to any node
                on current layer: we update globalmax with the maximum possible sum path including current root
                    1. if left < 0 and right < 0, update globalmax with root.key only, return root
                    1. if one branch > 0, udpate globalmax with root.key + the branch sum, return root.key + the branch sum
                    if both > 0. update globalmax with root.key + both branches sum, return root.key + the bigger branch sum
         */
        int[] globalMax = {Integer.MIN_VALUE};
        maxPathSumFromAnyToRoot(root, globalMax);
        return globalMax[0];

    }

    private int maxPathSumFromAnyToRoot(TreeNode root, int[] globalMax) {
        //base case
        if (root == null) {
            return 0;
        }
        int left = maxPathSumFromAnyToRoot(root.left, globalMax);
        int right = maxPathSumFromAnyToRoot(root.right, globalMax);

        if (left <= 0 && right <= 0) {
            globalMax[0] = Math.max(globalMax[0], root.key);
            return root.key;
        }
        if (left <= 0 || right<=0) {
            globalMax[0] = Math.max(globalMax[0], root.key + Math.max(left, right));
            return root.key + Math.max(left, right);
        }
        globalMax[0] = Math.max(globalMax[0], root.key + left + right);
        return left >= right ? root.key + left : root.key + right;

        /*
                we can simply the code above
                left = left < 0 ? 0 : left;
                right = right < 0 ? 0 : right;
                globalMax[0] = Math.max(globalMax[0], root.key + left + right)
                return root.key + Math.max(left, right)
         */
    }
}
