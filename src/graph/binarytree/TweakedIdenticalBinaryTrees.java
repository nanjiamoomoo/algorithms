package graph.binarytree;

import graph.dfs.lca.TreeNode;

public class TweakedIdenticalBinaryTrees {

    /**
     * Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed. A tweak is defined as a swap of the children of one node in the tree.
     * Examples:
     *          5
     *
     *       /    \
     *
     *     3        8
     *
     *   /   \
     *
     * 1      4
     *
     * and
     *
     *         5
     *
     *       /    \
     *
     *     8        3
     *
     *            /   \
     *
     *           1     4
     *
     * the two binary trees are tweaked identical.
     * @param one
     * @param two
     * @return
     */
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        /*
            if two trees are tweaked identical, it must satisfy:
            1. root value are the same
            2. one.left is tweakedIdentical to two.left and one.right is tweakedIdentical to two.right
                or one.left is tweakedIdentical to two.right and one.right is tweakedIdentical to two.left
         */

        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        return one.key == two.key && (isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left)
                || isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right));
    }
}
