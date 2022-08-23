package graph.dfs.subsets;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class AllSubsetsOfSizeK {

    /**
     * Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K.
     * Assumption: There are no duplicate characters in the orignal set
     * @param set
     * @param k
     * @return Return a list containing all subsets of the characters whose size is K.
     */
    public List<String> allSubsetsOfK(String set, int k) {
        /*

                   if k = 2
                                                ""                  0
                         a:           a                    ""       1
                         b:        ab   a              b            2
                         c:     return  ac             bc          3
                                       return        return

                     1. when the length of the string = k, return
                     2. when the set.length - level == k - string.length, we can only add, otherwise we will not get k elements

                     corner cases
                     1. set == null return []
                     2. if k >= set.length(), k = set.length()

         */
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        if (k > set.length()) {
            k = set.length();
        }

        StringBuilder sb = new StringBuilder();
        dfs(sb, set, 0, res, k);
        return res;
    }

    private void dfs(StringBuilder sb, String set, int index, List<String> res, int k) {
//        if (sb.length() == k) {
//            res.add(sb.toString());
//            return;
//        }
//
//        //add
//        sb.append(set.charAt(index));
//        dfs(sb, set, index + 1, res, k);
//        sb.deleteCharAt(sb.length() - 1);
//
//        //not add
//        if (set.length() - index != k - sb.length()) {
//            dfs(sb, set, index + 1, res, k);
//        }

//        Better way is that we just find the all the subSets
//        The base case is
        if (sb.length() == k) {
            res.add(sb.toString());
            return;
        }
        if (index == set.length()) {
            return;
        }
        //add
        sb.append(set.charAt(index));
        dfs(sb, set, index + 1, res, k);
        sb.deleteCharAt(sb.length() - 1);

        //not add
        dfs(sb, set, index + 1, res, k);
    }

    /*
        Better way is that we just find the all the subSets
        The base case is
        if (sb.length() == k) {
          res.add(sb.toString());
          return;
        }
        if (index == set.length()) {
          return;
        }
        //add
         sb.append(set.charAt(index));
        dfs(sb, set, index + 1, res, k);
        sb.deleteCharAt(sb.length() - 1);

        //not add
        dfs(sb, set, index + 1, res, k);
     */
}
