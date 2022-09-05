package recursion.lca;

public class LowestCommonAncestorInBST {

    /**
     * Given two keys in a binary search tree, find their lowest common ancestor.
     *
     * Assumptions
     *
     * There is no parent pointer for the nodes in the binary search tree
     *
     * There are no duplicate keys in the binary search tree
     *
     * The given two nodes are guaranteed to be in the binary search tree
     *
     * Examples
     *
     *         5
     *
     *       /   \
     *
     *      2     12
     *
     *    /  \      \
     *
     *   1    3      14
     *
     * The lowest common ancestor of 1 and 14 is 5
     *
     * The lowest common ancestor of 1 and 3 is 2
     *
     * @param root
     * @param one
     * @param two
     * @return
     */
    public TreeNode lowestCommonAncestorInBST(TreeNode root, TreeNode one, TreeNode two) {

        /*
             the semantics of this method:
                return the lca of the nodes under the root

             since this is a BST
                if one.key > root and two.key < root, return the root, since it is the lca
                if one.key < root and two.key < root, return the search result the left tree
                if one.key > root and two.key > root, search the right tree

             we search based on the algorithm above, if we find one node, we return that node as the lca (the other node is guaranteed under it)
             why? because it is the BST, if the other node is not under it, then means they are in different branches with regard to the previous level root, we should have returned in prevous level
         */
        if (root == null || root == one || root == two) {
            return root;
        }
       if (Math.max(one.key, two.key) < root.key) {
           return lowestCommonAncestorInBST(root.left, one, two);
       } else if (Math.min(one.key, two.key) > root.key) {
           return lowestCommonAncestorInBST(root.right, one, two);
       }
       return root;

       //TC: O(h)
       /*
            Iteratively:
                int min = Math.min(one.key, two.key);
                int max = Math.max(one.key, two.key);
                while (true) {
                    if (root.key > max) {
                        root == root.left;
                    } else if (root.key < min) {
                        root = root.right;
                    } else {
                        return root;
                    }
                }
        */
    }
}
