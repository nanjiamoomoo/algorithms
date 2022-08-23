package graph.dfs.permutations;

import java.util.ArrayList;
import java.util.List;

public class AllPermutationOfSubsets {

    /**
     * Given a string with no duplicate characters, return a list with all permutations of the string and all its subsets.
     * Examples
     * Set = “abc”, all permutations are [“”, “a”, “ab”, “abc”, “ac”, “acb”, “b”, “ba”, “bac”, “bc”, “bca”, “c”, “cb”, “cba”, “ca”, “cab”].
     * Set = “”, all permutations are [“”].
     * Set = null, all permutations are [].
     *
     * @param set
     * @return Return a list with all permutations of the string and all its subsets.
     */
    public List<String> allPermutationOfSubsets(String set) {
        /*
                                            abc              level0      all permutation of size 0
                                 a|bc       b|ac       c|ba      level1     all permutation of size 1
                            ab|c  ac|b    ba|c  bc|a     cb|a  ca|b        level2     all permutation of size 2
                         abc        acb    bac    bca     cba    cab         level3    all permutation of size 3
                         return
                        all each permutation to the final results
         */
        List<String> res = new ArrayList<>();
        if(set == null) {
            return res;
        }
        char[] array = set.toCharArray();
        helper(res, array, 0);
        return res;
    }

    private void helper(List<String> res, char[] array, int index) {
        res.add(String.valueOf(array, 0, index));
//        if (index == array.length) {
//            return;
//        }
        for (int i = index; i < array.length; i++) {
            swap(array, i, index);
            helper(res, array, index + 1);
            swap(array, i, index);
        }
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
