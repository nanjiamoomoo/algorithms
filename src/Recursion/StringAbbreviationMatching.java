package Recursion;

public class StringAbbreviationMatching {

    /**
     * Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.
     *
     * Assumption:
     * 1. Both input and pattern are not null.
     * 2. Pattern would not contain invalid information like "a0a" "0"
     *
     * Examples:
     * 1. pattern "s11d" matches input "sophisticated" since "11 matches eleven chars "ophisticate"
     *
     * @param input
     * @param pattern
     * @return
     */
    public boolean stringAbbreviationMatching(String input, String pattern) {

        //we use recursion/dfs to solve the problem

        /*
            for each letter in the pattern
                1. if the letter is a digit
                   we keep check if the next letter is digit as well...until a non-digit letter is met
                   we convert the digits into integer and pass checking for the same count of the characters in the input string and go to next level
                2. if the letter is a character
                    we check if the corresponding character in the input string is the same as the pattern character, if it is the same, we can go to next level
                    otherwise return false

         */

        return  dfs(input, pattern, 0, 0);
    }

    private boolean dfs(String input, String pattern, int indexInput, int indexPattern) {
        //base case
        if (indexInput == input.length() && indexPattern == pattern.length()) {
            return true;
        }
        if (indexInput >= input.length() || indexPattern >= pattern.length()) {
            return false;
        }

//        char ch = pattern.charAt(indexPattern);
//
//
//        //if the current character is not a digit
//        if (ch < '0' || ch > '9') {
//            if (input.charAt(indexInput) == pattern.charAt(indexPattern)) {
//               return dfs(input, pattern, indexInput + 1, indexPattern + 1);
//            } else {
//                return false;
//            }
//        } else {
//            StringBuilder sb = new StringBuilder();
//            while (ch >= '0' && ch <= '9') {
//                indexPattern++;
//                sb.append(ch);
//                if (indexPattern >= pattern.length()) {
//                    break;
//                }
//                ch = pattern.charAt(indexPattern);
//            }
//            int count = convertStringToNumber(sb);
//            return dfs(input, pattern, indexInput + count, indexPattern);
//        }

        if (input.charAt(indexInput) == pattern.charAt(indexPattern)) {
            return dfs(input, pattern, indexInput + 1, indexPattern + 1);
        } else if (!isNumber(pattern.charAt(indexPattern))) {
            return false;
        }
        int count = 0;
        while (indexPattern < pattern.length() && isNumber(pattern.charAt(indexPattern))) {
            count = 10 * count + pattern.charAt(indexPattern++) - '0';
        }
        return dfs(input, pattern, indexInput + count, indexPattern);
    }

    private int convertStringToNumber(StringBuilder sb) {
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            count = 10 * count + sb.charAt(i) - '0';
        }
        return count;
    }

    private boolean isNumber(char ch){
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }
}
