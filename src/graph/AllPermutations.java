package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPermutations {


    /**
     * Give a string with no duplicate characters, return a list with all permutations of characters
     * <p>
     * Example:
     * Set="abc", all permutations are ["abc", “acb", "bac", "bca", "cab", "cba"]
     * <p>
     * corner cases:
     * 1. if Set = "", return [""]
     * 2. if Set = null, return []
     *
     * @param input
     * @return a list of strings to include all permutations
     */
    public List<String> allPermutationsI(String input) {
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }
        char[] array = input.toCharArray();
        dfsI(0, array, res);
        return res;
    }

    private void dfsI(int index, char[] array, List<String> res) {
        if (index == array.length) {
            res.add(new String(array));
            return;
        }
        for (int i = index; i < array.length; i++) {
            swap(array, index, i);
            dfsI(index + 1, array, res);
            swap(array, index, i);
        }
    }



    /**
     * Give a string with possible duplicate characters, return a list with all permutations of characters
     * <p>
     * Example:
     * Set="abc", all permutations are ["abc", “acb", "bac", "bca", "cab", "cba"]
     * Set="aba", all permutations are ["aab", "aba", "baa"]
     * <p>
     * corner cases:
     * 1. if Set = "", return [""]
     * 2. if Set = null, return []
     *
     * @param input
     * @return a list of strings to include all permutations
     */
    public List<String> allPermutationsII(String input) {
        //all permutations can be solved with dfs

        /*
                                        aba
           level 0:         a|ba             b|aa     for 0 position, we need a set to control the duplication
           level 1:   ab|a      aa|b         ba|a     for 1 position, we also need a set to control the duplication
           level 2:   aba|      aab|         baa      for 2 position, only one option left
           level 3: return

         */
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }
        char[] array = input.toCharArray();
        dfsII(0, array, res);
        return res;
    }

    private void dfsII(int index, char[] array, List<String> res) {
        if (index == array.length) {
            res.add(new String(array));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (set.add(array[i])) {
                swap(array, index, i);
                dfsII(index + 1, array, res);
                swap(array, index, i);
            }
        }
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
