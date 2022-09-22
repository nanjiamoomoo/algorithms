package dynamic_programming;

public class LongestPalindromicSubstring {

    /**
     * Given a string S, find the longest palindromic substring in S.
     *
     * Assumptions
     * There exists one unique longest palindromic substring.
     * The input S is not null.
     *
     * Examples
     * Input:     "abbc"
     * Output:  "bb"
     *
     * Input:     "abcbcbd"
     * Output:  "bcbcb"
     * @param input
     * @return
     */
    public String longestPalindromic(String input) {
        /*
            define an array longestPalindrome[i][j] represents the longest palindrome between index i ~ index j in the original input, including index i and j position
            we can tell that longestPalindrome[i][i] = 1

            longestPalindrome[i][i + 1] = array[i] == array[i + 1] ? 2 : 0

            longestPalindrome[i][i + 2] = longestPalindrome[i + 1][i + 1] + (array[i] == array[i + 2] ? 2 : 0)

            longestPalindrome[i][j] = longestPalindrome[i + 1][j - 1] + (array[i] == array[i + 2] ? 2 : 0)

            TC: n^2
            SC: n^2
         */
        if (input.length() == 0) {
            return "";
        }
        int longest = 1;
        int[] indexPair = new int[2];
        int[][] longestPalindrome = new int[input.length()][input.length()];
        for (int j = 0; j < longestPalindrome.length; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    longestPalindrome[i][j] = 1;
                } else {
                    if (input.charAt(i) != input.charAt(j)) {
                        longestPalindrome[i][j] = 0;
                    } else {
                        longestPalindrome[i][j] = (i + 1 == j) ? 2 : longestPalindrome[i + 1][j - 1] == 0 ? 0 : 2 + longestPalindrome[i + 1][j - 1];
                    }
                    if (longestPalindrome[i][j] > longest) {
                        longest = longestPalindrome[i][j];
                        indexPair[0] = i;
                        indexPair[1] = j;
                    }
                }
            }
        }
        return input.substring(indexPair[0], indexPair[1] + 1);
    }
}
