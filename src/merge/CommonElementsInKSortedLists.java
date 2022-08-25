package merge;

import java.util.ArrayList;
import java.util.List;

public class CommonElementsInKSortedLists {

    /**
     * Find all common elements in K sorted lists.
     * Assumption:
     * The input and its elements are not null, and support fast random access.
     * There could be duplicate elements in each of the lists.
     * Examples:
     * Input = {{1, 2, 2, 3}, {2, 2, 3, 5}, {2, 2, 4}}, the common elements are {2, 2}.
     * @param input
     * @return
     */
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        /*
            we can use iteratively reduction
                find common elements in list 1 and 2
                    then find comments with list 3
                        ... list 4
         */
        List<Integer> res = new ArrayList<>();
        if (input.size() == 0) {
            return res;
        }
        res = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            res = commonElementsInTwoSortedLists(res, input.get(i));
        }
        return res;

    }

    private List<Integer> commonElementsInTwoSortedLists(List<Integer> one, List<Integer> two) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < one.size() && j < two.size()) {
            if (one.get(i) == two.get(j)) {
                res.add(one.get(i));
                i++;
                j++;
            } else if (one.get(i) < two.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }
    //TC: O(kn)
}
