package stringandarray;

/**
 * Character Deduplication problem
 */
public class RemoveAdjacentRepeatedCharacters {
    /**
     * Remove adjacent, repeated characters in a given string, leaving only one character for each group of such characters.
     * <p>
     * Assumption: Try to do it in place
     * <p>
     * Examples: "aaaabbbc" is transferred to "abc"
     * <p>
     * If the given characters is null, return null or an empty string are both valid
     *
     * @param input
     * @return the string after deduplication
     */
    public String removeAdjacentRepeatedCharactersI(String input) {

        //We can use two pointers that move in the same direction to solve this problem
        //[0, i) represents the characters to keep
        //[i, j) ... to ignore
        //j is the current visiting character
        //[j, n - 1) .... to be visited

        if (input == null || input.length() == 0) {
            return "";
        }

        //string is immutable, so we convert the input string to the char array
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;

        while (fast < array.length) {
            // when the current visited is the first character to be saved or not same to the previously saved character
            // we keep the current character by array[slow] = array[fast]
            if (slow == 0 || array[slow - 1] != array[fast]) {
                array[slow] = array[fast];
                slow++;
            }
            fast++;
        }
        return new String(array, 0, slow);

        /*
        //the Second solution to solve the problem: this solution can record the count of each character
        while (fast < array.length) {
            //the semantics of the position of the first occurrence of each consecutive duplicated letters
            int count = 0;
            int begin = fast;
            // the next step is to ignore all the duplicate letters
            while (fast < array.length && array[fast] == array[begin]) {
                fast++;
                count++;
            }
            //at this position, fast points to the next different character
            //in order to maintain the semantics of the slow pointer, we will need to assign the character at begin to slow position
            array[slow++] = array[begin];
        }
        ....
         */
    }

    /**
     * Remove adjacent, repeated characters in a given string, leaving only two characters for each group of such characters.
     * The characters in the string are sorted in ascending order.
     *
     * Example: "aaaabbbc" is transferred to "aabbc"
     *
     * @param input
     * @return the string after deduplication
     */
    public String removeAdjacentRepeatedCharactersII(String input) {
        //Do this problem in place
        // two pointers, one fast, one slow that move in the same direction
        //[0, slow) represents the characters to keep
        //[slow, fast) ... to ignore
        //we will traverse the character array using fast pointer, fast is the current visiting character
        //[fast, n - 1) .... to be visited

        //Since we are allowed to keep 2 characters and original character are sorted, we only needs to verify if the character at array[fast] the same as the character at array[slow - 2]
        //if they are the same, means we already have 2 duplicated characters
        //if they are not the same, we can keep current character no matter if it is same as array[slow - 1] or not

        if (input == null) {
            return null;
        }

        char[] array = input.toCharArray();

        int slow = 0;
        int fast = 0;
        while (fast < input.length()) {
            if (slow <= 1 || array[slow - 2] != array[fast]) {
                array[slow++] = array[fast];
            }
            fast++;
        }
        return new String(array, 0, slow);
    }


    /**
     * Remove adjacent, repeated characters in a given string, leaving no character for each group of such characters.
     * The characters in the string are sorted in ascending order.
     *
     * Example: "aaaabbbc" -> "c
     *
     * @param input string
     * @return string after deduplication
     */
    public String removeAdjacentRepeatedCharactersIII(String input) {

        //solution 1: this solution only applies when the original string is sorted.
        // Do this problem in place
        // two pointers, one fast, one slow that move in the same direction
        // [0, slow) represents the characters to keep
        // [slow, fast) ... to ignore
        // we will traverse the character array using fast pointer, fast is the current visiting character
        // [fast, n - 1) .... to be visited

        // a  a  b  b  c
        // s
        // f

        //    s
        //    f

        // the key point is once we find a duplicate character, we will need to skip all the duplicate characters
        // after the above process, we need to return the slow pointer to previous position


        if (input == null) {
            return null;
        }

        char[] array = input.toCharArray();

        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            // for first character and non duplicated characters, we will keep
            if (slow == 0 || array[fast] != array[slow - 1]) {
                array[slow++] = array[fast++];
            } else {
                //once we found duplicate, we will skip all the duplicate characters
                while (fast < array.length && array[fast] == array[slow - 1]) {
                    fast++;
                }
                //return to the previous position
                slow--;
            }
        }
        return new String(array, 0, slow);

        //Second solution:
        //we use 3 pointers: 2 pointers + 1 inner pointer
        //slow: all elements to the left side of the slow (excluding slow) pointer are the processed elements that shall be kept
        //fast:the current index being processed. and all elements to the right side of the fast pointer have not been processed
        //begin: the index of the first occurrence of the elements in current section

        /*
        *
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            int begin = fast;
            while (fast < array.length && array[fast] == array[begin]) {
                fast++;
            }
            //after the inner while loop, fast pointer points to the first different character after array[begin]
            if (fast - begin == 1) {
                array[slow++] = array[begin];
            }
        }
        *
        */
    }


    /**
     * Repeatedly remove all adjacent, repeated characters in a given string from left to right.
     * No adjacent characters should be identified in the final string.
     *
     *
     * @param input
     * @return
     */
    public String removeAdjacentRepeatedCharactersIV(String input) {

        //we use 2 pointers:
        //slow pointer: all the characters to the left side of slow pointer(exclude slow pointer) are the characters we are going to keep
        //fast pointer: the current index being processed. all the characters to the right side of the fast pointer are the characters have not processed

        // c   a   b  a  a   a  c  a  a
        //     s
        //                              f

        if (input == null) {
            return null;
        }

        char[] array = input.toCharArray();

        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            // for first character and non duplicated characters, we will keep
            if (slow == 0 || array[fast] != array[slow - 1]) {
                array[slow++] = array[fast++];
            } else {
                //once we found duplicate, we will skip all the duplicate characters
                while (fast < array.length && array[fast] == array[slow - 1]) {
                    fast++;
                }
                //return to the previous position
                slow--;
            }
        }
        return new String(array, 0, slow);
    }
}
