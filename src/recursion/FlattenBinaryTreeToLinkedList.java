package recursion;

import recursion.lca.TreeNode;

/**
 * Flatten a binary tree into a linked list in place.
 */
public class FlattenBinaryTreeToLinkedList {

    /**
     * Given a binary tree, flatten it to a linked list in-place.
     * For example,
     * Given
     *          1
     *         / \
     *        2   5
     *       / \   \
     *      3   4   6
     * The flattened tree should look like:
     *    1
     *     \
     *      2
     *       \
     *        3
     *         \
     *          4
     *           \
     *            5
     *             \
     *              6
     * @param root
     * @return
     */
    public TreeNode flatten(TreeNode root) {

        /*
            This is preorder traversal. As long as we can keep track of the previous node,
            we will be able to link all the nodes together.
            what we do on each level
            prev.right = node
            prev = node
            dfs(node.left...)
            dfs(node.right...)

                    3
                  2   4

               prev = null
               root = 3

               prev = 3
               root = 2
               3 - 2
               prev = 2
               ...
               root 4
              3 -2 - 4
              prev = 4


         */

        TreeNode[] prev = {null};
        helper(root, prev);
        return root;
    }

    private void helper(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }
        /*
            why do we need to keep the right ?
            since helper(root.left, prev) changes the root structure, so the root.right will be changed.
         */
        TreeNode right = root.right;
        if (prev[0] != null) {
            prev[0].right = root;
            prev[0].left = null;
        }
        prev[0] = root;
        helper(root.left, prev);
        helper(right, prev);
    }
}
