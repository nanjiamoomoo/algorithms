package graph.dfs.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllSubsets {

    /**
     * Given a set of characters represented by a String, return a list containing all subsets of the characters.
     * Assumption: There are no duplicate characters in the original set.
     * Examples:
     * Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
     * Set = "", all the subsets are [""]
     * Set = null, all the subsets are []
     * @param set
     * @return
     */
    public List<String> subSetI(String set) {
        /*
                           ""
              a          a     ""
              b        ab a   b ""

         */
        List<String> res =new ArrayList<>();
        if (set == null) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        helper(res, sb, 0, set);
        return res;
    }

    private void helper(List<String> res, StringBuilder sb, int index, String set) {
        if (index == set.length()) {
            res.add(sb.toString());
            return;
        }

        //add
        helper(res, sb.append(set.charAt(index)), index + 1, set);
        sb.deleteCharAt(sb.length() - 1);

        //not add
        helper(res, sb, index + 1, set);
    }



    /**
     * Given a set of characters represented by a String, return a list containing all subsets of the characters. Notice that each subset returned will be sorted to remove the sequence.
     * Assumption: There could be duplicate characters in the original set.
     * Examples:
     * Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
     * Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"]
     * Set = "abab", all the subsets are ["", "a", "aa","aab", "aabb", "ab","abb","b", "bb"]
     * @param input
     * @return
     */
    public List<String> subSetsII(String input) {
        /*
                                            ""                                                    abb
                      a                               level 0
                                     "a"            ""
                      b                                         level 1
                            "ab"         "a"        "b"    ""
                      b                                                 level 2
                       "abb"    "ab    "ab"   "a"   "bb"  "b"    "b"    ""
                 "abbb"  "abb" "abb""ab"                                                                level 3



                   conclusion
                   if the original string is ordered in lexicographically
                   on each recursion level, the branch that does not add a letter should not add any duplicate letters in the future layers.
                   which means we should bypass all the duplicate letters
         */
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }
        char[] array = input.toCharArray();
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        dfs(array, sb, 0, res);
        return res;
    }

    public void dfs(char[] array, StringBuilder sb, int index, List<String> res) {
        if (index == array.length) {
            res.add(sb.toString());
            return;
        }

        //add current letter
        sb.append(array[index]);
        dfs(array, sb, index = 1, res);
        sb.deleteCharAt(sb.length() - 1);

        //don't add, we need to bypass all the duplicate letter
        char ch = array[index];
        while (index < array.length && ch == array[index]) {
            index++;
        }
        dfs(array, sb, index, res);
    }
}
