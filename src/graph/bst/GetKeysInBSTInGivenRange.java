package graph.bst;

import graph.dfs.lca.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GetKeysInBSTInGivenRange {

    /**
     * Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max are inclusive.
     * @param root
     * @param min
     * @param max
     * @return
     */
    public List<Integer> getRange(TreeNode root, int min, int max) {
        /*
            Method1:
                Use in order traversal in ascending order property.
                We can in order traverse the tree, as long as we find a key is in the range we add it in the result.
                As long as we find an element larger than max, we can stop the traversal.

            Method2:
                Still use in order traversal in ascending order property.
                When do we need to traverse left. root.key > min
                in current layer, as long as the root.key is within[min, max], we add it in the result
                when do we need to traverse right. root.key < max

         */
        List<Integer> res = new ArrayList<>();
        getKeys(root, min, max, res);
        return res;
    }

   private void getKeys(TreeNode root, int min, int max, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.key > min) {
            getKeys(root.left, min , max, res);
        }
        if (root.key >= min && root.key <= max) {
            res.add(root.key);
        }
        if (root.key < max) {
            getKeys(root.right, min, max, res);
        }
    }
}
