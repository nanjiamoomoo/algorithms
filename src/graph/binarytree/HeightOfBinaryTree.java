package graph.binarytree;

import graph.dfs.lca.TreeNode;

public class HeightOfBinaryTree {

    /**
     * Find the height of binary tree.
     * @param root
     * @return
     */
    public int getHeight(TreeNode root) {
        /*
            use recursion to solve the problem

         */
        if (root == null) {
            return 0;
        }
        int leftTreeHeight = getHeight(root.left);
        int rightTreeHeight = getHeight(root.right);
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
        //TC: O(n)
        //SC: O(h)
    }
}
