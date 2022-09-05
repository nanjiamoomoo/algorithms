package binarysearch;

public class KthSmallestInTwoSortedArrays {

    /**
     * Given two sorted arrays of integers, find the Kth smallest number.
     *
     * Assumption:
     * The two given arrays are not null and at least one of them is not empty
     * K >= 1, K <= total lengths of the two sorted arrays
     *
     * Examples:
     * A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
     * A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
     *
     */
    public int kthSmallestInTwoSortedArrays(int[] one, int[] two, int k) {
        /*
            We two pointers, one pointer represents the index of the element in array one, the other pointer represents the index of element in array two
            We compare the element in array one with the element in array two and move pointer of the smaller element
         */
        int i = 0;
        int j = 0;
        int count = 0; //represents the count number of the smallest elements
        while (i < one.length && j < two.length) {
            count++;
            if (one[i] <= two[j]) {
                if (count == k) {
                    return one[i];
                }
                i++;
            } else {
                if (count == k) {
                    return two[j];
                }
                j++;
            }
        }
        if (i < one.length) {
            return one[i + k - count - 1];
        } else {
            return two[j + k - count - 1];
        }
        //TC: O(k)
    }

    /*
    Method 2:
        Q: can we improve the time complexity
     */
    public int kthSmallestInTwoSortedArraysII(int[] one, int[] two, int k) {
        /*
                          2/k
                   one : xxxxxxxxxx3 |   xxxxxxxxxx
                   two:  xxxxxxxxxx4 |   xxxxxxxxxx
                          2/k

                 if one[k/2] <= two[k/2]. the kth smallest cannot be in the range of [left, left + k / 2] in the array one, we can safely discard this range
                 if two[k/2] < one[k/2], the kth smallest cannot be int the range of [left, left + k/2] in the array two, we can safely discard this range

                 how to achieve this code?
                 we can define a method int helper(int[] one, int oneLeft, int[] two, int twoLeft, int k)
                 this method will return the kth smallest element in array one and array two starting from oneLeft index in array one and twoLeft index in array two

                 if (one[oneLeft + k / 2 - 1] <= two[twoLeft + k / 2 - 1[),
                    helper(one, oneLeft + k / 2, two, twoLeft, k - k / 2);
                 else
                    helper(one, oneLeft, two, twoLeft + k / 2, k - k / 2);

                 What is corner case?
                    what if there is no k/2 elements left in one array, we can safely remove the first k / 2 elements in the other array.
                    if (oneLeft + k / 2 > one.length)
                        helper(one, oneLeft, two, twoLeft + k / 2, k - k / 2);
                    else if (twoLeft + k / 2 - 1 >= two.length)
                         helper(one, oneLeft + k / 2, two, twoLeft, k - k / 2);

                What is base cases?
                    k = 1, since there will be dead loop. we return the smaller one

         */

        return findKthSmallest(one, 0, two, 0, k);
    }

    private int findKthSmallest(int[] one, int oneLeft, int[] two, int twoLeft, int k) {
        //base case
        if (oneLeft >= one.length) {
            return two[twoLeft + k - 1];
        }
        if (twoLeft >= two.length) {
            return one[oneLeft + k - 1];
        }
        if (k == 1) {
           return one[oneLeft] <= two[twoLeft] ? one[oneLeft] : two[twoLeft];
        }

        if (twoLeft + k / 2 > two.length || oneLeft + k / 2 <= one.length && one[oneLeft + k / 2 - 1] <= two[twoLeft + k / 2 - 1]) {
            return findKthSmallest(one, oneLeft + k / 2, two, twoLeft, k - k / 2);
        } else {
            return findKthSmallest(one, oneLeft, two, twoLeft + k / 2, k - k / 2);
        }
        //TC: klogk
        //SC: logk
    }
}
