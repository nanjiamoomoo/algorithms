package graph.dfs.nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeepDistanceForIdenticalElements {

    /**
     * Given an integer k, arrange the sequence of integers [1, 1, 2, 2, 3, 3, ...., k - 1, k - 1, k, k], such that the output integer array satisfy this condition:
     * Between each two i's, they are exactly i integers (for example: between the two 1s, there is one number, between the two 2's there are two numbers).
     * Assumption: k is guaranteed to be > 0
     * Examples: k = 3, The output = { 2, 3, 1, 2, 1, 3 }.
     * @param k
     * @return Return the arranged sequence of the integers if we can find such sequence; otherwise return null.
     */


    public int[] keepDistanceForIdenticalElements(int k) {
       /*
                                    k  = 3  1, 1, 2, 2, 3, 3,

                                    ""                 0
                           1        2        3         1
                          2  3    1   3     1  2       2
                        1 3  1   3     1    2   1      3
                        3    2  1  2   2    1          4
                        2              1    3          5
                                       3    2          6
             recursion tree level is 2k and for each level we have k options to put on

                 for each element, what is condition for us to choose
                    1. the count is not 0, it is available to choose
                    2. there is no limitation to use the current element if it is first tme we use it.
                    2. if this is the second time we choose current element ele, the element choose at (index - ele - 1) has to be the same element

                 what is the base case:
                 when index(level) == 2k, we find a valid result, so we should return.

        */
        List<Integer> res = new ArrayList<>();
        int[] count = new int[k];
        Arrays.fill(count, 2);
        if (helper(res, 0, k, count)) {
            int[] array = new int[2 * k];
            for (int i = 0; i < array.length; i++) {
                array[i] = res.get(i);
            }
            return array;
        }
        return null;
    }

    private boolean helper(List<Integer> list, int index, int k, int[] count) {
        //base case
        if (index == 2 * k) {
            return true;
        }
        //k options for each level
        for (int element = 1; element <= k; element++) {
            int remCount = count[element - 1];
            if (remCount == 2) {
                list.add(element);
                count[element - 1]--;
                if( helper(list, index + 1, k, count)) {
                    return true;
                }
                list.remove(list.size() - 1);
                count[element - 1]++;
            } else if (remCount == 1) {
                int prevIndex = index - element - 1;
                if (prevIndex >= 0 && list.get(prevIndex) == element) {
                    list.add(element);
                    count[element - 1]--;
                    if (helper(list, index + 1, k, count)) {
                        return true;
                    }
                    list.remove(list.size() - 1);
                    count[element - 1]++;
                }
            }
        }
        return false;
    }


    /**
     * Second solution: better solution
     */

    public int[] keepDistanceForIdenticalElementsII(int k) {
       /*
                                    k  = 3  1, 1, 2, 2, 3, 3,
                 recursion tree
                                x x x x x x x ..       0
                1:    1 x 1 x x x x    x 1 x 1 x x x..   x x 1 x 1 xx...   1
                2:      2     2                2     2       2
                3: ...                  3


                for each recursion level, we choose to put one element at i position and i + element + 1 position
                    in order to do this, we have to make sure array[i] and array[i + element + 1] is empty and i + element + 1 < array.length

                 base case:
                 at level K, we turn



        */
        int[] result = new int[2 * k];
        return helperII(result, 0) ? result : null;
    }

    private boolean helperII(int[] result, int index) {
        // note:
            // index represents the recursion level
            // index + 1 represents the element that we are putting in the result
        //base case
        if (index == result.length / 2) {
            return true;
        }
        int ele = index + 1;
        for (int i = 0; i < result.length - ele - 1; i++) {
            if (result[i] == 0 && result[i + ele + 1] == 0) {
                result[i] = ele;
                result[i + ele + 1]= ele;
                if (helperII(result, index + 1)) {
                    return true;
                }
                result[i] = 0;
                result[i + ele + 1]= 0;
            }
        }

        return false;
    }
}
