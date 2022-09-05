package recursion;


import recursion.lca.TreeNode;

public class ReverseBinaryTreeUpsideDown {

    /**
     * Given a binary tree where all the right nodes are leaf nodes, flip it upside down and turn it into a tree with left leaf nodes as the root.
     *
     * example:
     *           1
     *         2   5
     *       3  4
     *   is reversed to
     *          3
     *        2   4
     *      1  5
     * @param root
     * @return
     */
    public TreeNode reverseBinaryTreeUpsideDown(TreeNode root) {

        /*
        what are we expecting from child
                    3
                  2   4
            1.left = 2
            2.right = 5

         what should we do on current level:
          root.left.right = root.right;
          root.left.left = root;
          root.left = null;
          root.right = null;


         */
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = reverseBinaryTreeUpsideDown(root.left);
        root.left.right = root.right;
        root.left.left = root;
        root.left = null;
        root.right = null;
        return newRoot;

    }

    public TreeNode reverseBinaryTreeUpsideDownIteratively(TreeNode root) {

        //
        TreeNode prevRootRightChild = null;
        TreeNode prevRoot = null;
        while (root != null) {
            TreeNode currRootLeftChild = root.left;
            TreeNode currRootRightChild = root.right;
            root.right = prevRootRightChild;
            root.left = prevRoot;
            prevRoot = root;
            root = currRootLeftChild;
            prevRootRightChild = currRootRightChild;

        }
        return prevRoot;


    }


}
