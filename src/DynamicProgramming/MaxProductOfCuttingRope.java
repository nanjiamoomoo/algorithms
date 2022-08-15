package DynamicProgramming;

public class MaxProductOfCuttingRope {

    /**
     * Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]*p[1]* ... *p[m-1]?
     * m is determined by you and must be greater than 0 (at least one cut must be made). Return the max product you can have.
     *
     * Examples:
     * n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).
     *
     * @param length
     * @return
     */
    public int maxProductOfRopes(int length) {

        //assume length >= 2

        /*

            length = 2
                _ | _  product = 1
            length = 3
               _ | _  _
               can be considered as the Math.max(maxProduct(1), without any cut = 1)* Math.max(maxProduct(2), without any cut = 2)
            length = 4
               _ _ _ _
                case1: _ | _ _ _  can be considered as the  Math.max(maxProduct(1), without any cut = 1) * Math.max(maxProduct(3), without any cut = 3)
                case2: _ _ | _ _ can be considered as the  Math.max(maxProduct(2, without any cut = 2) * Math.max(maxProduct(2), without any cut = 2)
                next will be duplicate
         */

        //maxProduct[i] represents the maxProduct we can have for a rope length of i with at least one cut
        int[] maxProduct = new int[length + 1];
        maxProduct[1] = 1;
        maxProduct[2] = 1;

        for (int i = 3; i <= length; i++) {
            int currMax = 1;
            for (int left = 1; left <= i / 2; left++) {
                currMax = Math.max(currMax, Math.max(maxProduct[left], left) * Math.max(maxProduct[i - left], i - left));
            }
            maxProduct[i] = currMax;
        }
        return maxProduct[length];
    }
}
