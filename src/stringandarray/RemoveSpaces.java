package stringandarray;

/**
 * Given a string, remove all leading/trailing/duplicated empty spaces.
 *
 * Assumptions:
 * 1. the give string is not null.
 *
 * examples:
 *  " a" -> "a"
 *  " I  love MTV " -> "I love MTV"
 */
public class RemoveSpaces {

    public String removeSpaces(String input) {
        //The high level idea is to traverse the input string, when we meet a character
        //if it is not a space, we keep it
        //if it is space
        //1. if the previous character is not a space, we need to keep it
        //2. if the previous character is a space, we need to ignore it
        //corner cases: if the string starts with space, then the space needs to be ignored

        //we can use two pointers to solve the problem
        //[0, i) represents the characters we keep
        //[i, j) represents the characters we don't care
        //j is the character we are visiting
        //(j, n- 1] represents the characters to be visited.

        char[] array = input.toCharArray();
        int i = 0;
        int j = 0;
        while (j < array.length) {
//            if (array[j] == ' ') {
//                //i == 0 means we haven't kept any characters, so cannot copy the current space to i position, otherwise the result string will start with space
//                //array[i - 1] == ' ' means the previous characters we kept is a space, we should ignore this space
//                if (i == 0 || array[i - 1] == ' ') {
//                    j++;
//                } else {
//                    array[i] = array[j];
//                    i++;
//                    j++;
//                }
//            } else {
//                array[i] = array[j];
//                i++;
//                j++;
//            }

            if (array[j] == ' ' && (i == 0 || array[i - 1] == ' ')) {
                j++;
                continue;
            }
            array[i] = array[j];
            i++;
            j++;
        }

        //after all the operations, we cannot guarantee that the last characters is not a space
        if (i != 0 && array[i - 1] == ' ') {
            i--;
        }
        return new String(array, 0, i);
    }
    //time complexity is O(n)

    /**
     * Another method using StringBuilder
     */
    public String removeSpacesUsingStringBuilder(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        StringBuilder string = new StringBuilder(input);
        int i = 0;
        for (int j = 0; j < input.length(); j++){
            if (string.charAt(j) == ' ' && (i == 0 || string.charAt(i - 1) == ' ')) {
                continue;
            }
            string.setCharAt(i, string.charAt(j));
            i++;
        }
        if  (i != 0 && string.charAt(i - 1) == ' ') {
            i--;
        }
        //setLength() is good method for StringBuilder to set length.
        string.setLength(i);
        return string.toString();
    }
}
