package graph.binarytree;

import graph.dfs.lca.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InOrderTraversalIteratively {

    /**
     * Implement an iterative, in-order traversal of a given binary tree, return the list of keys of each node in the tree as it is in-order traversed.
     * <p>
     * Examples
     * <p>
     * 5
     * <p>
     * /    \
     * <p>
     * 3        8
     * <p>
     * /   \        \
     * <p>
     * 1      4        11
     * <p>
     * In-order traversal is [1, 3, 4, 5, 8, 11]
     *
     * @param root
     * @return
     */
    public List<Integer> inOrder(TreeNode root) {
       /*
        In order traversal is go left as far as you can, then one step right. Repeat till the end of nodes in the tree.
       */
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.offerFirst(root);
                root = root.left;
            }
            TreeNode curr = stack.pollFirst();
            res.add(curr.key);
            root = curr.right;
        }
        return res;
    }

}
