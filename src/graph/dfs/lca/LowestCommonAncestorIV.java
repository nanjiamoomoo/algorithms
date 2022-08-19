package graph.dfs.lca;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LowestCommonAncestorIV {
    /**
     *
     * Given K nodes in a binary tree, find their lowest common ancestor.
     *
     * Assumptions
     *
     * K >= 2
     *
     * There is no parent pointer for the nodes in the binary tree
     *
     * The given K nodes are guaranteed to be in the binary tree
     *
     * Examples
     *
     *         5
     *
     *       /   \
     *
     *      9     12
     *
     *    /  \      \
     *
     *   2    3      14
     *
     * The lowest common ancestor of 2, 3, 14 is 5
     *
     * The lowest common ancestor of 2, 3, 9 is 9
     *
     * @param root of the tree
     * @param nodes:  list of nodes
     * @return return the lCA of the k Nodes
     */
    public TreeNode lowestCommonAncestorIV(TreeNode root, List<TreeNode> nodes) {

        /*
              Semantics of the LCA function(root, nodes)
              case1: if all nodes are under root, return LCA of these nodes
              case2: if subset of the given nodes are under root, return the LCA of the nodes under root
              case3: if none of the nodes are under root, return null;

              search the entire tree
              if we find a node, we return the node
              1. if both branches return null, we return null
              2. if only one branches return not noll, we return the not null node (which means this node will be LCA of the nodes under this root)
              3. if both branches return not null, we return the root (means the root is the LCA of the nodes under it)
         */
        Set<TreeNode> set = new HashSet<>(nodes);
        return lCAIV(root, set);

    }

    private TreeNode lCAIV(TreeNode root, Set<TreeNode> nodes) {
        if (root == null || nodes.contains(root)) {
            return root;
        }
        TreeNode left = lCAIV(root.left, nodes);
        TreeNode right = lCAIV(root.right, nodes);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

}
