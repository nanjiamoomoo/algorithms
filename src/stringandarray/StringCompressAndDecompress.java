package stringandarray;

/**
 * String compress and decompress related problems
 * most of the time string problems required to solved in place
 * however, if we solve compress and decompress string problems in place, the problem can become too complicated
 * Here only shows how to solve them using StringBuilder
 */
public class StringCompressAndDecompress {

    //we need to count the duplicate characters
    //use one pointer to traverse the string

    /**
     * Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences.
     *
     * Assumption:
     * 1. the string is not null
     * 2. the characters used in the original string are guaranteed to be 'a' - 'z'
     *
     * Examples:
     * "abbcccdeee" -> "a1b2c3d1e3"
     *
     * @param input string
     * @return compressed string
     */
    public String stringCompress(String input) {
        if (input.length() == 0) {
            return "";
        }

        int pointer = 0;
        StringBuilder sb = new StringBuilder();
        while (pointer < input.length()) {
            //firstOccur represents the first occurrence of the character in one section
            int firstOccur = pointer;
            int count = 0;
            while (pointer < input.length() && input.charAt(pointer) == input.charAt(firstOccur)) {
                count++;
                pointer++;
            }
            //now pointer points to the first different character
            sb.append(input.charAt(firstOccur)).append(count);
        }
        return sb.toString();
    }
    //time complexity is O(n)
    //space complexity is O(n)

    /**
     * Given a string in compressed form, decompress it to the original string.
     * The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.
     *
     * Examples:
     * "a1c0b2c4" -> "abbcccc"
     *
     * Assumption:
     * 1. the string is not null
     * 2. the characters used in the original string are guaranteed to be 'a' - 'z'
     * 3. There are no adjacent repeated characters with length > 9
     *
     * @param input
     * @return
     */
    public String stringDecompress(String input) {

        //we traverse the input string using one pointer
        //if the pointer position is a character between 'a' - ‘z', we count the next digit and append # of characters accordingly

        if (input.length() == 0) {
            return "";
        }

        int pointer = 0;
        StringBuilder sb = new StringBuilder();
        while (pointer < input.length()) {
            char ch = input.charAt(pointer);
            if (isCharacter(ch)) {
                int count = input.charAt(pointer + 1) - '0';
                while (count-- > 0) {
                    sb.append(ch);
                }
            }
            pointer++;
        }
        return sb.toString();
    }

    private boolean isCharacter(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return true;
        }
        return false;
    }

}
