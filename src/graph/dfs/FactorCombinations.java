package graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    /**
     * Given an integer number, return all possible combinations of the factors that can multiply to the target number.
     * Example:
     * Give A = 24
     * since 24 = 2 x 2 x 2 x 3
     *               = 2 x 2 x 6
     *               = 2 x 3 x 4
     *               = 2 x 12
     *               = 3 x 8
     *               = 4 x 6
     * your solution should return
     * { { 2, 2, 2, 3 }, { 2, 2, 6 }, { 2, 3, 4 }, { 2, 12 }, { 3, 8 }, { 4, 6 } }
     * note: duplicate combination is not allowed.
     *
     * @param target
     * @return
     */
    public List<List<Integer>> combinations(int target) {

        /*
                [2, 3, 4, 6, 8, 12]
                                              24                  0
            2               0(24)      2(12)      2*2(6)     2*2*2(3)             1
            3        0(24)   3(8)       3(4)        3(2)
            ......

                list.add(2)
                dfs(...)
                list.remove(list.size()-1);
                list.add(2)
                list.add(2)
                list.remove(list.size() -1)
                list.remove(list.size() - 1)

                ...

                //base case

                //don't add
                    dfs(...)
                //add
                while (target % num == 0) {
                    target = target / num; 3
                    list.add(num);
                    dfs(... target, index + 1)
                }

         */
        List<Integer> factors = findFactors(target);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(list, res, 0, target, factors);
        return res;
    }

    private void dfs(List<Integer> list, List<List<Integer>> res, int index, int target, List<Integer> factors) {
        //base case
        if (target == 1) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (index == factors.size()) {
            return;
        }
        //do not add current factor
        dfs(list, res, index + 1, target, factors);

        //add current factor
        int size = list.size();
        int num = factors.get(index);
        while (target % num == 0) {
            list.add(num);
            target /= num;
            dfs(list, res, index + 1, target, factors);
        }
        //remove all the added factors
        list.subList(size, list.size()).clear();
    }

    /**
     * get all the factors of num
     * @param num
     * @return
     */
    private List<Integer> findFactors(int num) {
        List<Integer> factors = new ArrayList<>();
        for (int i = num / 2; i >= 2; i--) {
            if (num % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }
}
