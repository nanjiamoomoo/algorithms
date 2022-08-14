package Recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /**
     * Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.
     *
     * Assumption: N > 0
     *
     * Example:
     *
     *N = 4, there are two ways of putting 4 queens:
     *  [1, 3, 0, 2] --> the Queen on the first row is at y index 1, the Queen on the second row is at y index 3, the Queen on the third row is at y index 0 and the Queen on the fourth row is at y index 2.
     *
     *  [2, 0, 3, 1] --> the Queen on the first row is at y index 2, the Queen on the second row is at y index 0, the Queen on the third row is at y index 3 and the Queen on the fourth row is at y index 1.
     * @param n, the dimension of the chessboard
     * @return all the possible ways of putting n queens
     */

    public List<List<Integer>> nQueens(int n) {

        //we can use dfs to solve this problem
        // on each recursion level, we choose one possible y index and add the list
        // there are total n levels

        /*

                                        ""
              lv0        0, 1, 2, ..                n - 1 ( we have n possible solutions)
              lv1        0, 1, 2, ..                n - 1 ( we have n possible solutions)
                       note: however once the zero level y index is determined, there will be constraints on the first level position
              lv2       0, 1, 2, ..                n - 1 ( we have n possible solutions)
                       note: however once the zero level and first level y indices are determined, there will be constraints on the second level position
                       ....
              lv8      return
              constrains: we will have to make sure that current level y index is not the same as previous level indices, plus it cannot be on the 45 and -45 degree lines

         */

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(res, list, n, 0);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> list, int n, int level) {
        // base case when we reach the last level of the recursion tree
        if (level == n) {
            res.add(new ArrayList(list));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (valid(col, list)) {
                list.add(col);
                dfs(res, list, n, level + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    // in order to know if validity of current y index, we need to know previous level selection
    public boolean valid(int col, List<Integer> list) {

        /*              list.get(row)
            row        x  o  x  x       (row, list.get(row))
                       x  x  x  0
                       x  x  x  x       (list.size(), col)
         */
        int currRow = list.size();
        for (int row = 0; row < list.size(); row++) {
            if (col == list.get(row) || Math.abs(row - currRow) == Math.abs(list.get(row) - col)) {
                return false;
            }
        }
        return true;
    }
}
