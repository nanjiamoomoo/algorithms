package graph.binarytree;

import graph.dfs.lca.TreeNode;

public class SymmetricBinaryTree {

    /**
     * Check if a given binary tree is symmetric.
     * Examples
     *         5
     *
     *       /    \
     *
     *     3        3
     *
     *   /   \    /   \
     *
     * 1      4  4      1
     * is symmetric.
     *
     *         5
     *
     *       /    \
     *
     *     3        3
     *
     *   /   \    /   \
     *
     * 1      4  1      4
     * is not symmetric.
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        /*
            If two trees are symmetric, it must satisfy
            1. root are equal
            2. one.left is symmetric to two.right and one.right is symmetric to two.left
            TC: O(n)
            SC: O(h)
         */
        if (root == null) {
            return true;
        }
        return areSymmetric(root.left, root.right);
    }

    public boolean areSymmetric(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        return one.key == two.key && areSymmetric(one.left, two.right) && areSymmetric(one.right, two.left);
    }
}
