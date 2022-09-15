package binarysearch;

public class SearchInShiftedSortedArray {

    /**
     * Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.
     * <p>
     * For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i such that A[i] == T or return -1 if there is no such index.
     * <p>
     * Assumption:
     * There are no duplicate elements in the array.
     * <p>
     * Examples:
     * A = {3, 4, 5, 1, 2}, T = 4, return 1
     * A = {1, 2, 3, 4, 5}, T = 4, return 3
     * A = {3, 5, 6, 1, 2}, T = 4, return -1
     *
     * @param array
     * @return
     */
    public int searchInShiftedSortedArrayI(int[] array, int target) {
        /*
            5 6  1  2  3  4
            target = 5

            There are potential two sections, the first section value is larger than the section value


            if we can make sure the target is in the first section target >= array[left]
                if array[mid] = target, return
                if array[mid] < target,
                    if array[mid] >= array[left], left = mid + 1
                    else right = mid - 1;
                if array[mid] > target, right = mid - 1

            if we can make sure the target is in the second section target , array[left]
                if array[mid] = target, return
                if array[mid] < target, left = mid + 1
                if array[mid] > target, right = mid - 1
                    if array[mid] >= array[left] , left = mid + 1
                    else right = mid - 1



         */

        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (target >= array[left]) {
                if (array[mid] < target && array[mid] >= array[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (array[mid] > target && array[mid] < array[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.
     * For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the smallest index i such that A[i] == T or return -1 if there is no such index.
     * <p>
     * Examples
     * A = {3, 4, 5, 1, 2}, T = 4, return 1
     * A = {3, 3, 3, 1, 3}, T = 1, return 3
     * A = {3, 1, 3, 3, 3}, T = 1, return 1
     *
     * @param array
     * @return Return the smallest index if target has multiple occurrence.
     */
    public int searchInShiftedSortedArrayII(int[] array, int target) {
        /*
            we can use BS to solve the problem

            There could be two potential sections
            if target == array[left], we return left
            why not right? since we need to return the smallest index. right maybe be the smallest index even if they are equal
            if (target > array[left] it is in the left ascending section
                if array[mid] == target, right = mid; //target is in the range of [left, mid]
                if array[mid] < target
                    if array[mid] > array[left], left = mid + 1

                    if array[mid] == array[left]
                        if array[mid] == array[right], big problem here
                        //example: 3 4 3 3 3  or 3 3 3 4 3
                            we should move left and right simultaneously until they are not equal

                        if array[mid] > array[right], left = mid + 1
                        if array[mid] < array[right], left = mid + 1;
                    if array[mid] < array[left], right = mid (or mid - 1)
                if array[mid] > target, right = mid (or mid - 1)
            else it is in the right ascending section
                if array[mid] == target, right = mid
                if array[mid] < target, left = mid + 1
                if array[mid] > target
                    if array[mid] > array[left], left = mid + 1
                    if array[mid] == array[left]
                        if array[mid] == array[right], big problem here
                        //example: 3 2 3 3 3  or 3 3 3 2 3
                            we should move left and right simultaneously until they are not equal

                        if array[mid] > array[right], left = mid + 1
                        if array[mid] < array[right], right = mid
                    if array[mid] < array[left], right = mid (or mid - 1)


                How to simply the above solution
                for each cycle:
                    1.check if left is equal to the target, if yes, return left
                    2. make sure array[left] != array[right]
                    3. Then use BS to narrow down the searching space

         */
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left] == target) {
                return left;
            }
            while (left < right && array[left] == array[right]) {
                left++;
                right--;
            }
            //now array[left] > array[right]

            int mid = left + (right - left) / 2;
            if (target > array[left]) {
                if (array[mid] == target) {
                    right = mid;
                } else if (array[mid] < target && array[mid] >= array[left]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                if (array[mid] == target) {
                    right = mid;
                } else if (array[mid] < target || array[mid] >= array[left]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return left == right && array[left] == target ? left : -1;
//
//        int left = 0;
//        int right = array.length - 1;
//
//        while (left < right) {
//            if (array[left] == target) {
//                return left;
//            }
//
//            int mid = left + (right - left) / 2;
//            if (target > array[left]) {
//                if (array[mid] == target) {
//                    right = mid;
//                } else if (array[mid] < target) {
//                    if (array[mid] > array[left]) {
//                        left = mid + 1;
//                    } else if (array[mid] < array[left]) {
//                        right = mid;
//                    } else if (array[mid] != array[right]) {
//                        left = mid + 1;
//                    } else {
//                        while (left < right && array[left] == array[right]) {
//                            left++;
//                            right--;
//                        }
//                    }
//                } else {
//                    right = mid;
//                }
//            } else {
//                if (array[mid] == target) {
//                    right = mid;
//                } else if (array[mid] < target) {
//                    left = mid + 1;
//                } else if (array[mid] > target) {
//                    if (array[mid] > array[left]) {
//                        left = mid + 1;
//                    } else if (array[mid] < array[left]) {
//                        right = mid;
//                    } else {
//                        if (array[mid] > array[right]) {
//                            left = mid + 1;
//                        } else if (array[mid] < array[right]) {
//                            right = mid;
//                        } else {
//                            while (left < right && array[left] == array[right]) {
//                                left++;
//                                right--;
//                            }
//                        }
//                    }
//                }
//
//            }
//        }
//        return left == right && array[left] == target ? left : -1;

        //TC: worst: O(n), average(logn)
    }

    public static void main(String[] args) {
        int[] array = {2, 2, 2, 3, 3, 3, 4, 4, 2, 2, 2, 2, 2};
        SearchInShiftedSortedArray searchInShiftedSortedArray = new SearchInShiftedSortedArray();
        searchInShiftedSortedArray.searchInShiftedSortedArrayII(array, 4);
    }
}
