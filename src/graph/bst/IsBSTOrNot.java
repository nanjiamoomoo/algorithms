package graph.bst;

import graph.dfs.lca.TreeNode;

public class IsBSTOrNot {

    /**
     * Determine if a given binary tree is binary search tree.There should no be duplicate keys in binary search tree.
     * Assumption: You can assume the keys stored in the binary search tree can not be Integer.MIN_VALUE or Integer.MAX_VALUE.
     * @param root
     * @return
     */
    public boolean isBST(TreeNode root) {
        /*
            BST: for every node, all the elements on its left tree must be smaller than itself and all the elements on its right tree mush be larger than itself

            method1: in order traverse the tree and make sure the in order traverse is in the ascending order

            method2: pre-order traverse the tree, and set up boundary of each node. As long as all the node value falls in the boundary then the tree is BST

                define method isBST(TreeNode root, int min, int max)
                    min: the min value of root
                    max: the max value of root
                    root.value must be in the (min, max) in order to satisfy the property of BST

                then left tree range is (min, root)
                     right tree range is (root, max)

         */
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.key <= min || root.key >= max) {
            return false;
        }
        return isBST(root.left, min, root.key) && isBST(root.right, root.key, max);
    }
}
