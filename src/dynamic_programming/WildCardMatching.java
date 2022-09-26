package dynamic_programming;

public class WildCardMatching {

    public boolean match(String input, String pattern) {
    /*
       we can use Dp to solve the problem
       assume canMatch[i][j] represents if the first i characters of input match the first j characters of pattern

       canMatch[0][0] = true;
       if pattern.charAt(j - 1) != * and != '?'
          canMatch[i][j] = canMatch[i - 1][j - 1]&& if the input.charAt(i - 1) can match pattern.charAt(j - 1）
       if pattern.charAt(j - 1) == '?‘
          canMatch[i][j] = canMatch[i - 1][j - 1]
       if pattern.charAt(j - 1) == *
        for (int k = 0; k <= i; k++) if canMatch[k][j] = true, then canMatch[i][j] = true;

        TC: O(n ^ 3)
        SC: O(n ^ 2)

    */
        if (input.length() == 0 && (pattern.length() == 0 || pattern.length() == 1 && pattern.charAt(0) == '*')) {
            return true;
        }
        if (input.length() != 0 && pattern.length() == 0) {
            return false;
        }


        boolean[][] canMatch = new boolean[input.length() + 1][pattern.length() + 1];
        canMatch[0][0] = true;

        for (int j = 1; j <= pattern.length(); j++) {
            for (int i = 1; i <= input.length(); i++) {
                char ch = pattern.charAt(j - 1);
                if (ch != '?' && ch != '*') {
                    canMatch[i][j] = canMatch[i - 1][j - 1] && input.charAt(i - 1) == pattern.charAt(j - 1);
                } else if (ch == '?') {
                    canMatch[i][j] = canMatch[i - 1][j - 1];
                } else {
                    for (int k = 0; k <= i; k++) {
                        canMatch[i][j] = canMatch[i][j] || canMatch[k][j - 1];
                    }
                }
            }
        }
        return canMatch[input.length()][pattern.length()];
    }
}
