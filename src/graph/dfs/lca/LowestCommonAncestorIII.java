package graph.dfs.lca;

public class LowestCommonAncestorIII {
    /**
     * Given two nodes in a binary tree, find their lowest common ancestor (the given two nodes are not guaranteed to be in the binary tree).
     *
     * Return null If any of the nodes is not in the tree.
     *
     * Assumptions
     *
     * There is no parent pointer for the nodes in the binary tree
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
    public TreeNode lowestCommonAncestorIII(TreeNode root, TreeNode one, TreeNode two) {

        /*
                case 1: both are in the tree
                    1.1 two is under one, one is the LCA
                        we search the tree and find node one and return node one
                    1.2 one and two are under different branches
                        we search the tree
                           1.2.1: if only one branch return a not null, then we return the not null
                           1.2.2: if both branches return a not null, we return the root.
                case 2: one node is not in the tree
                    based on the algorithm, we will find one node
                case 3: both are not in the tree:
                    based on the algorithm, we will get null

                how to we tell if they are both under the tree or not?
                based on the return
                    1. if the returned LCA are not null and not equal to both
                        we can tell they are in the tree
                    2. if the returned LCA equals to one of them
                        we have to further confirm if one node is under the other
                            if yes, they are in the same tree
                            otherwise, they are not
                    3. if the returned LCA equals to null, then both of them are not in the tree.
         */
        TreeNode lCA = mayNotBeInTheTreeLCA(root, one, two);
        if (lCA == one) {
            if (findNode(one, two) == null) {
                return null;
            }
        }
        if (lCA == two) {
            if(findNode(two, one) == null) {
                return null;
            }
        }
        // lCA == null, return null, or lCA == one, return one, or lCA == two, return two, or lCA != one && lCA != two, return lCA
        return lCA;
    }

    //TC: O(n)
    //SC: O(h)
    private TreeNode mayNotBeInTheTreeLCA(TreeNode root, TreeNode one, TreeNode two) {
        //base case
        if (root == null || root == one || root == two) {
            return root;
        }
        TreeNode left = mayNotBeInTheTreeLCA(root.left, one, two);
        TreeNode right = mayNotBeInTheTreeLCA(root.right, one, two);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    //TC: O(n)
    //SC: O(h)
    private TreeNode findNode(TreeNode root, TreeNode node) {
        if (root == null || root == node) {
            return root;
        }
        TreeNode left = findNode(root.left, node);
        TreeNode right = findNode(root.right, node);
        return left == null ? right : left;
    }

}
