package dynamic_programming;

public class InterleaveStrings {

    /**
     * Given three strings A, B and C. Determine if C can be created by merging A and B in a way that maintains the relative order of the characters in A and B.
     *
     * Assumptions
     *
     * none of A, B, C is null
     * Examples
     *
     * C = "abcde", A = "acd", B = "be", return true
     * C = "abcde", A = "adc", B = "be", return false
     * @param a
     * @param b
     * @param c
     * @return
     */
    public boolean canMerge(String a, String b, String c) {

    /*
      define canMerge[i][j] as if the first i characters in a can merge with the first j characters in b.

      canMerge[i][j] = canMerge[i - 1][j] && a.charAt(i - 1) == c.charAt(i + j - 1) ||
                         canMerge[i][j - 1] && b.charAt(j - 1) == c.charAt(i + j - 1)

      TC: O(n^2)
      SC: O(n^2)
  */

        if (a.length() + b.length() != c.length()) {
            return false;
        }
        /*
             if (a.length() == 0) {
              return c.equals(b);
            }
            if (b.length() == 0) {
              return c.equals(a);
            }
         */
        if (a.length() == 0 || b.length() == 0) {
            return c.equals(a) || c.equals(b);
        }
        boolean[][] canMerge = new boolean[a.length() + 1][b.length() + 1];
        canMerge[0][0] = true;
        for (int j = 1; j <= b.length(); j++) {
            canMerge[0][j] = canMerge[0][j - 1] && b.charAt(j - 1) == c.charAt(j - 1);
        }

        for (int i = 1; i <= a.length(); i++) {
            canMerge[i][0] = canMerge[i - 1][0] && a.charAt(i - 1) == c.charAt(i - 1);
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                canMerge[i][j] = canMerge[i - 1][j] && a.charAt(i - 1) == c.charAt(i + j - 1) ||
                        canMerge[i][j - 1] && b.charAt(j - 1) == c.charAt(i + j - 1);
            }
        }

        return canMerge[a.length()][b.length()];
    }
}
