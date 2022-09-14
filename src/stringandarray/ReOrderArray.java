package stringandarray;

public class ReOrderArray {

    /**
     * Given an array of elements, reorder it as follow:
     * { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
     * { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }
     * Try to do it in place.
     *
     * Assumption: The given array is not null
     *
     * Examples:
     * { 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }
     * { 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }
     * { 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }
     *
     * @param array
     * @return
     */
    public int[] reOrderArray(int[] array) {

        /*
            Naive method, two pointers: use another array to help

            Method2: if required in place. Use "I Love yahoo trick"
                   AB| CDE | 12 | 345

                   AB12  | CDE234

          on each level, we partition the array into four sections and use the trick to for second and third section

          in the range [left, right]
          size = right - left + 1;
          if size is even number.
          the four partition are
          [left, left + size / 4 - 1]
          [left + size / 4, left + size / 2 - 1]
          [left + size / 2, left + 3 * size / 4 - 1]
          [left + 3 * size / 4, right]

          after reverse operations,
          the left half becomes:
          [left, 2 * (size / 4) + left - 1]
          [

           if size is odd, we only do the partition from [0, array.length - 2]
         */

        if(array.length % 2 == 0) {
            convert(array, 0, array.length - 1);
        } else {
            convert(array, 0, array.length - 2);
        }

        return array;

    }

    private void convert(int[] array, int left, int right) {
        if (right - left <= 1) {
            return;
        }
        int size = right - left + 1;
        reverse(array,left + size / 4, left + size / 2 - 1);
        reverse(array,left + size / 2, left + size * 3 / 4 - 1);
        reverse(array,left + size / 4, left + size * 3 / 4 - 1);

        convert(array, left, left + 2 * (size / 4) - 1);
        convert(array, left + 2 * (size / 4), right);

    }

    private void reverse(int[] array, int left, int right) {
        while (left < right) {
            swap(array, left++, right--);
        }
    }

    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 5, 4};
        ReOrderArray reOrderArray = new ReOrderArray();
        reOrderArray.reOrderArray(array);
    }

}
