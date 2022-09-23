package dynamic_programming;

public class LargestSubArrayProduct {

    /**
     * Given an unsorted array of doubles, find the subarray that has the greatest product. Return the product.
     *
     * Assumptions
     *
     * The given array is not null and has length of at least 1.
     * Examples
     *
     * {2.0, -0.1, 4, -2, -1.5}, the largest subarray product is 4 * (-2) * (-1.5) = 12
     *
     * @param array
     * @return
     */
    public double largestProduct(double[] array) {
        /*
            We can use dp to solve the problem
            product[i][j] represents the product of numbers between sub array[i ~ j] product
            product[i][j] = array[i]

            product[i][j] = product[i + 1][j] * array[i]

            TC: O(n ^ 2)
            SC: O(n ^ 2)
         */

//        double[][] product = new double[array.length][array.length];
//        double largest = array[0];
//        for (int j = 0; j < array.length; j++) {
//            for (int i = j; i >= 0; i--) {
//                if (i == j) {
//                    product[i][i] = array[i];
//                } else {
//                    product[i][j] = product[i + 1][j] * array[i];
//                }
//                largest = Math.max(largest, product[i][j]);
//            }
//        }
//        return largest;
//    }

    /*
        Further optimize to save space
        for each position i  we calculate max product of subarray ending at index i
            for each i
                for each j from i - 1 ~ 0
                we calculate the max product

         TC: O(n ^ 2)
         SC: O(1)
     */
        double largest = array[0];
        for (int i = 1; i < array.length; i++) {
            double currLargest = array[i];
            double prev = array[i];
            for (int j = i - 1; j >= 0; j--) {
                prev = prev * array[j];
                currLargest = Math.max(currLargest, prev);
            }
            largest = Math.max(largest, currLargest);
        }
        return largest;
    }
}
