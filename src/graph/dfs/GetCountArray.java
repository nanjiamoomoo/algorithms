package graph.dfs;

public class GetCountArray {

    /**
     * Given an array A of length N containing all positive integers from [1...N]. How to get an array B such that B[i] represents how many elements A[j] (j > i) in array A that are smaller than A[i].
     * Assumption:
     * The given array A is not null.
     * Examples:
     * A = { 4, 1, 3, 2 }, we should get B = { 3, 0, 1, 0 }.
     *
     * Requirement: Time complexity O(nlogn)
     * @param array
     * @return
     */
    public int[] countArray(int[] array) {

        /*
            We can use merge sort to solve this problem
            When we merge the left array and right array
                for each element in the left array, we can calculate how many element in the right array is moved in front of it (means, how many elements A[j] (j > i) in the current
                section that are smaller than A[i]. We update the count until the entire array is sorted.

            Since the sort will change the sequence of the original array, so we need to keep the original index for each element
                we can create a class Element {
                                    int value;
                                    int index;

                                }

         */
        if (array.length == 0) {
            return new int[0];
        }

        Element[] elements = new Element[array.length];
        for (int i = 0; i < array.length; i++) {
            elements[i] = new Element(array[i], i);
        }
        //store the final result
        int[] result = new int[array.length];

        //helper array for merge sort
        Element[] helper = new Element[array.length];
        mergeSort(elements, helper, 0, array.length - 1, result);
        return result;
    }

    private void mergeSort(Element[] elements, Element[] helper, int left, int right, int[] result) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        //sort left
        mergeSort(elements, helper, left, mid, result);
        //sort right
        mergeSort(elements, helper, mid + 1, right, result);

        merge(elements, helper, left, mid, right, result);
    }

    private void merge(Element[] elements, Element[] helper, int left, int mid, int right, int[] result) {
        //copy the original array into helper array
        for (int i = left; i <= right; i++) {
            helper[i] = elements[i];
        }
        /*
             1  4    |   2   3
             i           j
         */
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (helper[i].value <= helper[j].value) {
                result[helper[i].index] += j - (mid + 1);
                elements[k++] = helper[i++];
            } else {
                elements[k++] = helper[j++];
            }
        }
        while (i <= mid) {
            result[helper[i].index] += right - mid;
            elements[k++] = helper[i++];
        }
    }

    class Element {
        int value;
        int index;
        Element(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
