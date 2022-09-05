package recursion.lca;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LowestCommonAncestorVI {

    /**
     * Given M nodes in a K-nary tree, find their lowest common ancestor.
     *
     * Assumptions
     *
     * - M >= 2.
     *
     * - There is no parent pointer for the nodes in the K-nary tree.
     *
     * - The given M nodes are guaranteed to be in the K-nary tree.
     *
     * Examples
     *
     *         5
     *
     *       /   \
     *
     *      9   12
     *
     *    / | \      \
     *
     *   1 2 3     14
     *
     *
     *
     * The lowest common ancestor of 2, 3, 14 is 5.
     *
     * The lowest common ancestor of 2, 3, 9 is 9.
     * @param root
     * @param nodes list of nodes
     * @return the lowest common ancestor of the k nodes
     */
    public KnaryTreeNode lowestCommonAncestorVI(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
        /*
                semantics of the method:
                    return the lca of the nodes under the root.
                    1. if no node under the root, return null
                    2. if subset of given nodes under the root, return the LCA of the nodes under root
                    3. if all nodes under the root, return the LCA of all nodes

                 search the entire tree, if we find a node return the node
                    1. if all branches return null, we return null, means no nodes under current root
                    2. if only one branch return not null, we return the not null node, means is the lca of nodes under current root
                    3. if at least two branches return not null, we return root, means root is the lca of nodes under the root.
         */
        Set<KnaryTreeNode> set = new HashSet<>(nodes);
        return lCAVI(root, set);

    }

    private KnaryTreeNode lCAVI(KnaryTreeNode root, Set<KnaryTreeNode> nodes) {
        if (root == null || nodes.contains(root)) {
            return root;
        }

        KnaryTreeNode result = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode subResult = lCAVI(child, nodes);
            if (subResult != null) {
                if (result != null) {
                    //means current subResult is the second branch has a not null return
                    //this indicates that the lca of nodes under current root is root itself, we return root
                    return root;
                }
                //means current subResult is the first branch has a not null return
                result = subResult;
            }

        }
        return result;
    }
}
