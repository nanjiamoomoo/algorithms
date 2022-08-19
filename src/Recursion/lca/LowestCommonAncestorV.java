package Recursion.lca;


public class LowestCommonAncestorV {
    /**
     * Given two nodes in a K-nary tree, find their lowest common ancestor.
     * <p>
     * Assumptions
     * <p>
     * -There is no parent pointer for the nodes in the K-nary tree.
     * <p>
     * -The given two nodes are guaranteed to be in the K-nary tree.
     * <p>
     * Examples
     * <p>
     * <p>
     * <p>
     * 5
     * <p>
     * /   \
     * <p>
     * 9   12
     * <p>
     * / | \      \
     * <p>
     * 1 2   3      14
     * <p>
     * <p>
     * <p>
     * The lowest common ancestor of 2 and 14 is 5.
     * <p>
     * The lowest common ancestor of 2 and 9 is 9.
     *
     * @param one
     * @param two
     * @return return the lCA of the two nodes
     */
    public KnaryTreeNode lowestCommonAncestorV(KnaryTreeNode root, KnaryTreeNode one, KnaryTreeNode two) {

        /*
              Semantics of the LCA function(root, one, two)
              case1: if both nodes are under root, return LCA of them
              case2: if only one is under the root, return the one under the root
              case3: if none of the nodes are under root, return null;

              search the entire tree
              if we find a node, we return the node
              1. if all branches return null, we return null (means no nodes are under the root)
              2. if only one branches return not noll, we return the not null node (which means that node will be the lCA of node under the root)
              3. if at least two branches return not null, we return the root (means the root is the LCA of the nodes under the root)
         */
        if (root == null || root == one || root == two) {
            return root;
        }
        KnaryTreeNode found = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode singleBranch = lowestCommonAncestorV(child, one, two);
            if (singleBranch == null) {
                continue;
            }
            //when found == null, means singleBranch is first not null return
            if (found == null) {
                found = singleBranch;
            } else {
                return root;
            }
        }
        return found;
    }
}
