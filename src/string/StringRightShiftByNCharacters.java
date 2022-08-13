package string;

/**
 * String shift problems
 */
public class StringRightShiftByNCharacters {

    /**
     * Right shift a given string by n characters
     *
     * Assumptions:
     * 1. The given string is not null
     * 2. n >= 0
     *
     * Examples:
     * "abc" 4 -> "cab"
     *
     * @param input
     * @return
     */
    public String rightShiftByNCharacters(String input, int n) {
        // this problem is similar to the reverse string problem

        if (input == null) {
            return null;
        }

        char[] array = input.toCharArray();

        // k is the characters we are going to shift
        int k = n % array.length;


        //the first words is from [0, array.length - k - 1]
        //the second is from [array.length - k, array.length - 1]
        reverse(array, 0, array.length - k - 1);
        reverse(array, array.length - k, array.length - 1);
        reverse(array, 0, array.length - 1);
        return new String(array);
    }

    //define a private reverse method to reverse the characters between index i and index j
    private void reverse(char[] array, int i, int j) {
        while (i < j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }
}
