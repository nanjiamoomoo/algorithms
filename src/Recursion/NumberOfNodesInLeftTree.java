package Recursion;


class TreeNodeLeft {
    public int key;
    public TreeNodeLeft left;
    public TreeNodeLeft right;
    public int numNodesLeft;
    public TreeNodeLeft(int key) {
        this.key = key;
    }
}
public class NumberOfNodesInLeftTree {


    /**
     * Given a binary tree,count the number of nodes in each node's left subtree, and store in the numNodesLeft field
     * @param node
     */
    public void storeNumberOfNodesInLeftSubtree(TreeNodeLeft node) {

        /*
           if I can define a method that returns the number of nodes in the tree
           numOfNodes()

           we can get the count left subtree by using
           int numsOfNodesInLeftSubtree = numOfNodes(node.left)
           node.numNodesLeft = numsOfNodesInLeftSubtree

           //we need to return the nodes count of current tree
           int numsOfNodesInRightSubtree = numOfNodes(node.right)
           return numsOfNodesInLeftSubtree + numsOfNodesInRightSubtree + 1;

           Summary:
           Why can't we define the method to return the number of nodes in the left subtree
           the problem is what are we expecting from our child subtree?
           are we only expecting the node count in the left subtree of the child subtree? no.
           we are expecting the total nodes count of the child subtree.
           so we have to define the recursion function to return total count of the nodes under the tree

         */
        numOfNodesInTree(node);
    }


    private int numOfNodesInTree(TreeNodeLeft node) {
        //base case
        if (node == null) {
            return 0;
        }

        int numsOfNodesInLeftSubtree = numOfNodesInTree(node.left);
        int numsOfNodesInRightSubtree = numOfNodesInTree(node.right);
        node.numNodesLeft = numsOfNodesInLeftSubtree;
        return numsOfNodesInLeftSubtree + numsOfNodesInRightSubtree + 1;

    }
}
