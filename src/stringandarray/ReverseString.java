package stringandarray;


/**
 * String Reversal related problems
 */
public class ReverseString {

    /**
     * Reverse a given string
     *
     * The given string is not null
     *
     * @param input string
     * @return return the reversed string
     */
    public String reverseString(String input) {
        char[] array = input.toCharArray();
        //use two pointers
        //left: the index being processed on the left side
        //right: the index being processed on the right side
        //we swap the characters at index left and index right

        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            char tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
        return new String(array);
    }

    /**
     * Reverse the words in a sentence
     *
     * Assumption:
     * 1. words are separated by single space
     * 2. There are no heading or trailing white spaces
     *
     * Examples:
     * "I love Google" -> "Google love I"
     *
     * Corner cases:
     * if the string is null, we return null
     *
     * @param input
     * @return
     */
    public String reverseWordsInSentenceI(String input) {

        // step1: reverse each words in the string
        // step2: reverse entire string

        // we use an index i to traverse the string
        // if i is 0 or array[i - 1] is a space, then we can tell i is the starting index of a word
        // if i is array.length -1 or array[i + 1] is a space, then we can tell i is the end index of a word

        if (input == null) {
            return null;
        }
        char[] array = input.toCharArray();
        int i = 0;
        int start = 0; //represents the start index of a word
        int end = 0; //represents the end index of a word
        while (i < array.length) {
            if (i == 0 || array[i - 1] == ' ') {
                start = i;
            }
            if (i == array.length - 1 || array[i + 1] == ' ') {
                end = i;
                //once we find a word end, we will need to reverse the word
                reverse(array, start, end);
            }
            i++;
        }

        //step2: reverse the entire string
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
