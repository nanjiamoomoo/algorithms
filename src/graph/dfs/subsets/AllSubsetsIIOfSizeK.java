package graph.dfs.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubsetsIIOfSizeK {

    /**
     * Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K. Notice that each subset returned will be sorted for deduplication.
     * Assumption: There could be duplicate characters in the original set.
     * Examples:
     * Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].
     * Set = "abb", K = 2, all the subsets are [“ab”, “bb”].
     * Set = "abab", K = 2, all the subsets are [“aa”, “ab”, “bb”].
     * Set = "", K = 0, all the subsets are [""].
     * Set = "", K = 1, all the subsets are [].
     * @param set
     * @param k
     * @return
     */
    public List<String> allSubsetsIIOfSizeK(String set, int k) {
        /*
            e.g. abbb
                                        ""            0
                         a       a              ""       1
                         b    ab    a           b   ""      2
                         b  abb   ab  a       bb  b     ""    3
                         b abbb abb ab  a    bbb bb b    ""    4
                                return


             Step1: find all the subsets
             Step2: only chose the subsets of size k
         */
        List<String> res = new ArrayList<>();
        if (set == null || k > set.length()) {
            return res;
        }
        char[] array = set.toCharArray();
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        helper(res, array, sb, 0, k);
        return res;
    }

    private void helper(List<String> res, char[] array, StringBuilder sb, int index, int k) {
        if (sb.length() == k) {
            res.add(sb.toString());
            return;
        }
        if (index == array.length) {
            return;
        }
        char ch = array[index];
        //add
        sb.append(ch);
        helper(res, array, sb, index + 1, k);
        sb.deleteCharAt(sb.length() - 1);

        //not add, we will need to bypass all the duplicate characters
        while (index < array.length && array[index] == ch ) {
            index++;
        }
        helper(res, array, sb, index, k);
    }
}
