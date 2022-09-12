package graph.bst;

import graph.dfs.lca.TreeNode;

public class SearchInBST {

    /**
     * Find the target key K in the given binary search tree, return the node that contains the key if K is found, otherwise return null.
     * Assumption: There are no duplicate keys in the binary search tree
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode search(TreeNode root, int key) {
        while (root != null && root.key != key) {
            if (root.key < key) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }
}
