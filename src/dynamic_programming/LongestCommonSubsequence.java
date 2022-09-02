package dynamic_programming;

public class LongestCommonSubsequence {

    /**
     * Find the length of longest common subsequence of two given strings.
     * Assumption: The two given strings are not null
     * Examples: S = “abcde”, T = “cbabdfe”, the longest common subsequence of s and t is {‘a’, ‘b’, ‘d’, ‘e’}, the length is 4.
     * @param source
     * @param target
     * @return
     */
    public int longest(String source, String target) {
        /*
            we can use dp to solve the problem
            Define lCS[i][j] represents the longest common subsequence of the first i letters in the source string and the first j letters int the target string
            if (charAt(i - 1) != charAt(j - 1), lCS[i][j] = max of (lCS[i - 1][j], lCS[i][j - 1], lCS[i - 1][j - 1]
            if equals, lCS[i][j] = lCS[i - 1][j - 1] + 1
         */
        int longest = 0;
        int[][] lCS = new int[source.length() + 1][target.length() + 1];
        for (int i = 1; i < lCS.length; i++) {
            for (int j = 1; j < lCS[0].length; j++) {
                if (source.charAt(i - 1) != target.charAt(j - 1)) {
                    lCS[i][j] = Math.max(Math.max(lCS[i - 1][j], lCS[i][j - 1]), lCS[i - 1][j - 1]);
                } else {
                    lCS[i][j] = lCS[i - 1][j - 1] + 1;
                }
                longest = Math.max(longest, lCS[i][j]);
            }
        }
        return longest;
    }
}
