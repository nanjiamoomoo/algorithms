package dynamic_programming;

public class CuttingWood {
    /**
     * There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut. Determine the minimum total cost to cut the stick into the defined pieces.
     * Examples:
     * L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
     * @param cuts
     * @param length
     * @return
     */
    public int minCost(int[] cuts, int length) {

        /*
            0 1   2 3   4 5 6   7 8 9
            |_ _ | _ _ | _ _ _ | _ _ _|

            We are splitting the original length L wood into several partitions
            How to define each partitions? we can use two pointers i and j, i represents the left border, j represents the right border
            we can create a helper array with length of cuts.length + 2, helper[i] represents the length of the wood from 0th index to cutting position cuts[i]

            Then we can use DP to solve the problem
            we can define minCost[i][j] represents the min cost to cut the partition[i, j]

            minCost[i][i+1] always equal to 0

            minCost[i][j] =
                one additional partition means one additional cut
                find the max among:
                 for each cutting position k from i + 1 ~ j - 1
                    minCost[i][k] + minCost[k][j] + helper[j] - helper[i]



         */
        if (cuts == null || cuts.length == 0) {
            return 0;
        }
        int[] helper = new int[cuts.length + 2];
        helper[0] = 0;
        helper[helper.length - 1] = length;
        for (int i = 1; i < helper.length - 1 ; i++) {
            helper[i] = cuts[i];
        }

        int[][] minCost = new int[helper.length][helper.length];

        // since we are using the values of smaller partitions first, so i should be as bigger as possible, j should be as smaller as possible
        for (int i = 0; i < helper.length - 1; i++) {
            for (int j = i + 1; j < helper.length - 1; j++) {
                if (j == i + 1) {
                    minCost[i][j] = 0;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int currCost = helper[j] - helper[i];
                for (int k = i + 1; k < j; k++) {
                    min = Math.min(min, minCost[i][k] + minCost[k][j] + currCost);
                }
                minCost[i][j] = min;
            }
        }
        return minCost[0][helper.length - 1];
    }
}
