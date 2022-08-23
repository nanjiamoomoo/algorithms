package graph.dfs.subsets;

public class TwoSubsetsWithMinDifference {

    /**
     * Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible.
     * Return the minimum difference(absolute value).
     * Examples:
     * {1, 3, 2} can be divided into {1, 2} and {3}, the minimum difference is 0
     *
     * @param array
     * @return Return the minimum difference(absolute value).
     */
    public int minDifference(int[] array) {
        /*
            Step1: we can calculate sum of elements in the array
            Step2: find a size n subset with the subSum = sum/2 or as close as possible, the result will be |sum - sumSum - sumSum|


            basically this is a subset problem, we can find all the subsets of size n / 2 of the array, and calculate the different between two subsets and find the global min

            techniques:
                dfs: the base case is current subset size = totalSize / 2. then we update globalMin

                [1, 4, 2, 3]
                                            0         0
                              1           1     0         1
                             4       5     1   4   0       2

         */
        int[] globalMin = {Integer.MAX_VALUE};
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        findMin(globalMin, array, sum, 0, 0, 0);
        return globalMin[0];
    }

    private void findMin(int[] globalMin, int[] array, int total, int subSum, int subSize, int index) {
        //base case
        if (subSize == array.length / 2) {
            globalMin[0] = Math.min(globalMin[0], Math.abs(total - 2 * subSum));
            return;
        }
        if (index == array.length) {
            return;
        }

        //add the element in the subSet
        findMin(globalMin, array, total, subSum + array[index], subSize + 1, index + 1);

        //not add the element in the subSet
        findMin(globalMin, array, total, subSum, subSize,index + 1);
    }
}
