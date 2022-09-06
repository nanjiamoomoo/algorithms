package recursion;

import java.util.ArrayList;
import java.util.List;

public class LargestAndSecondLargest {

    /**
     * Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. Return the largest number and 2nd largest number.
     * Assumption:
     * The given array is not null and has length of at least 2
     * Examples:
     * {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
     * @param array
     * @return
     */
    public int[] largestAndSecond(int[] array) {
        /*
            If we use binary comparison, to find the largest number, we need O(n)
            The 2nd largest number must have been compared with the largest number in the history and there are logn elements compared with the largest number in the history
            to find the largest amount the logn, we need logn comparisons.

            Total comparison is n + logn.

            The difficult part is how to store all the values compared with the largest
            We can create a helper class Element
            Element {
                int value;
                List<Integer> compared;
            }

            We can first convert the original array to Element[]
            and we use binary comparison to find the largest, every time we do comparison to find the larger element, we add the smaller element to the compared list of the larger
            element.

            Once we find the largest element, we use the compared list to find the second largest
         */
        //initialize elements array
        Element[] elements = new Element[array.length];
        for (int i = 0; i < array.length; i++) {
            elements[i] = new Element(array[i]);
        }

        //we use binary comparison to find the largest
        /*
            Define a method largest(Element[] elements, int left, int right) to find the largest elements between range [left, right]
            then use binary partition to find the largest int left and right partitions respectively. Then we find the largest by compare the left return and right return
         */

        Element largestEle = largest(elements, 0, array.length - 1);
        int largest = largestEle.value;
        List<Integer> compared = largestEle.compared;
        int secondLargest = compared.get(0);
        for (int i = 1; i < compared.size(); i++) {
            secondLargest = Math.max(secondLargest, compared.get(i));
        }
        return new int[]{largest, secondLargest};
    }

    private Element largest(Element[] elements, int left, int right) {
        if (left == right) {
            return elements[left];
        }
        int mid = left + (right - left) / 2;
        Element largeLeft = largest(elements, left, mid);
        Element largeRight = largest(elements, mid + 1, right);
        Element larger = null;
        if (largeLeft.value >= largeRight.value) {
            larger = largeLeft;
            larger.compared.add(largeRight.value);
        } else {
            larger = largeRight;
            larger.compared.add(largeLeft.value);
        }
        return larger;
    }
    class Element {
        int value;
        List<Integer> compared;
        Element(int value) {
            this.value = value;
            compared = new ArrayList<>();
        }
    }
    /*
    Another method: we can also do this iteratively by swapping the larger elements to the left half of the elements array.
        We do this iteratively, until we have only one element to compare, which is the largest elements.
     */
}

