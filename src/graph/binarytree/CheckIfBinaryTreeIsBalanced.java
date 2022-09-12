package graph.binarytree;

import graph.dfs.lca.TreeNode;

public class CheckIfBinaryTreeIsBalanced {

    /**
     * Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of every node’s left and right subtree differ by at most 1.
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        /*
            We can define a method: int getHeightOrUnbalanced(TreeNode root)
            this function returns the height of the tree if the tree is balanced, otherwise it returns -1 indicate it is an unbalanced tree

            ont current layer, if both left and right tree are balanced and the height of the left and right tree differ by less than 1, then we return the height,
            otherwise return -1
            TC: O(n)
            SC: O(h)
         */
        return getHeightOrUnbalanced(root) == -1 ? false : true;
    }

    private int getHeightOrUnbalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeightOrUnbalanced(root.left);
        int right = getHeightOrUnbalanced(root.right);
        if (left != -1 && right != -1 && Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        }
        return -1;
    }
}
