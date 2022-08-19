package graph.dfs.lca;

public class LowestCommonAncestorII {

    /**
     * Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.
     *
     * Assumptions
     *
     * There is parent pointer for the nodes in the binary tree
     *
     * The given two nodes are not guaranteed to be in the binary tree
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
     * The lowest common ancestor of 2 and 14 is 5
     *
     * The lowest common ancestor of 2 and 9 is 9
     *
     * The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
     * @param one
     * @param two
     * @return
     */
    public TreeNodeP lowestCommonAncestorII(TreeNodeP one, TreeNodeP two) {
        /*

                this question is easier than the LCA I, since this tree has additional parent node. The only difficulty is that the nodes are not guaranteed in the tree.
                Can we improve the TC to better than the O(n)?

                what if the two nodes are on the same level
                    1. then if we search up in the same pace, as long as they are in the same tree, they will eventually meet at the same node which is the LCA

                how to make the two nodes on the same level?
                    we can find the height of the nodes and climb the deeper node up.

         */
        int oneHeight = getHeight(one);
        int twoHeight = getHeight(two);
        if (oneHeight > twoHeight) {
            one = climbUp(one, oneHeight, twoHeight);
        } else {
            two = climbUp(two, twoHeight, oneHeight);
        }

        //TC: O(h)
        while (one != two) {
            one = one.parent;
            two = two.parent;
        }
        //if one is null, then they are not in the same tree, otherwise the returned one is the LCA
        return one;

    }

    //TC: O(h)
    private TreeNodeP climbUp(TreeNodeP node, int high, int low) {
        int steps = high - low;
        while (steps-- != 0) {
            node = node.parent;
        }
        return node;
    }
    //TC: O(h)
    private int getHeight(TreeNodeP node) {
        int height = 0;
        while (node != null) {
            node = node.parent;
            height++;
        }
        return height;
    }

}
