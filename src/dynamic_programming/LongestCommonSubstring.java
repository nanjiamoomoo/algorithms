package dynamic_programming;

public class LongestCommonSubstring {

    /**
     * Find the longest common substring of two given strings.
     * Assumption: The two given strings are not null
     * Examples: S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”
     * @param source
     * @param target
     * @return
     */
    public String longestCommonSubstring(String source, String target) {
        /*
            Naive method:
            for each position (as the start of the common substring) in source string and target string, we find the longest substring and update globalMax
            TC: m * n * (m + n)

         */
//        if (source.length() == 0 || target.length() == 0) {
//            return "";
//        }
//        int start = 0;
//        int longest = 0;
//        for (int i = 0; i < source.length(); i++) {
//            for (int j = 0; j < target.length(); j++) {
//                if (source.charAt(i) == target.charAt(j)) {
//                    int length = longestSubstring(source, target, i, j);
//                    if (length > longest) {
//                        longest = length;
//                        start = i;
//                    }
//                }
//            }
//        }
//        return source.substring(start, start + longest);

        /*
            Can we find a better method?
            We can use dp to solve the problem
            define lCS[i][j] represents the length of the longest common substring of the first i letters in source string and the first j letters in the target string. The common string should
            include the letter at (i - 1) index position in source string and (j p- 1) index position in the target string
            if source.charAt(i - 1) != target.charAt(j - 1) , the equals to 0, means there is no comment substring
            if source.charAt(i - 1) == target.charAt(j - 1), then lCS[i][j] = lCS[i - 1][j - 1] + 1
         */
        if (source.length() == 0 || target.length() == 0) {
            return "";
        }
        int[][] lCS = new int[source.length() + 1][target.length() + 1];
        int longest = 0;
        //Why should we initialize it as 0.
        // (end - 1) represents the index position of the common substring in the source string. in the beginning we haven't found any common substring, so the end should initialize as 0
        //if we initialize it as 1, then it means we have found a common string ending at index 0 in the source string.
        int end = 0;
        for (int i = 1; i < lCS.length; i++) {
            for (int j = 1; j < lCS[0].length; j++) {
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    lCS[i][j] = lCS[i - 1][j - 1] + 1;
                    if (lCS[i][j] > longest) {
                        longest = lCS[i][j];
                        end = i;
                    }
                }
            }
        }
        return source.substring(end - longest, end);
        //TC: m * n
        //SC: m * n
    }

//    private int longestSubstring(String source, String target, int i , int j) {
//        int len = 0;
//        while (i < source.length() && j < target.length()) {
//            if (source.charAt(i) != target.charAt(j)) {
//                break;
//            }
//            len++;
//            i++;
//            j++;
//        }
//        return len;
//    }

}
