package Recursion;

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }
}

/**
 * Includes all LCA related questions
 */
public class LowestCommonAncestor {

    /**
     * Given two nodes in a binary tree, find their lowest common ancestor.
     *
     * Assumption: assume the given two nodes are guaranteed to be in the binary tree
     *
     * @param root the root of the tree
     * @param one the first node
     * @param two the second node
     * @return
     */
    public TreeNode lowestCommonAncestorI(TreeNode root, TreeNode one, TreeNode two) {


        /*

                          x
                     x          x
                 x     o     x     x
              x    x
           o    x


         */

        if (root == null || root == one || root == two) {
            return root;
        }
        //what we are expecting from child: we are expecting either the target node or the lca, or null if nothing find
        //this question is similar to search in a tree and see if the node is exists in the tree
        //only difference, if exists, we return the node its self which is potentially the lca
        //if both branches find nodes, we return the lca
        TreeNode left = lowestCommonAncestorI(root.left, one, two);
        TreeNode right = lowestCommonAncestorI(root.right, one, two);
        if (left != null && right != null) {
            return root;
        }
        return left == null? right : left;
    }



}
