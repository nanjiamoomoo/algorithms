package Recursion.lca;

public class LowestCommonAncestorI {
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

              There are two different relationships
              1. one is under the other one
                    we search the entire tree, as long as we find node, we return it. It will be the lowest common Ancestor
              2. both of them belong to different branches.
                    we search the entire tree, as long as we find the node, we return it.
                        2.1 if only one branch returns a valid node, the other branch returns null. we keep returning the same node
                        2.2 if both branches returns valid node, then we return current root, since it will be he LCA

               Overall, we can see the case 1 is actually the same as 2.1
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
        //TC: O(n)
        //SC: O(h)
    }

}
