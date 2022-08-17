package dynamic_programming;

/**
 * Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.
 *
 */
public class StringTransform {

    /**
     * Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.
     *
     * Examples:
     * "sigh" and "asith"
     * the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).
     * @param one
     * @param two
     * @return the minimum number of operations to edit one string to the other
     */
    public int transforms(String one, String two) {
        //let's define min[i][j] represents the minimum transforms needed to change first i characters from string one to the first j characters to string two
        int[][] min = new int[one.length() + 1][two.length() + 1];



        /*      0   1   2   3   4   5
                    a   s   i   t   h
                0   1
         1   s  1
         2   i
         3   g
         4   h

              if (two.charAt(i - 1)  == two.charAt(j - 1)
                min[i][j] = min[i-1][j-1];
              if (two.charAt(i - 1) != two.charAt(j - 1);
                 1. replace: min[i - 1][j - 1] + 1;

                 2. delete: min[i - 1][j] + 1 or min[i][j - 1] + 1

                 3. insert: min[i - 1][j] + 1 or min[i][j - 1] + 1

                 over all it is Math.min of the all the possible consitions

               final return need to return min[one.length()][two.length()]
         */

        min[0][0] = 0;
        for (int j = 1; j <= two.length(); j++) {
            min[0][j] = j;
        }
        for (int i = 0; i <= one.length(); i++) {
            min[i][0] = i;
        }
        min[1][0] = 1;
        for (int i = 1; i <= one.length(); i++) {
            for (int j = 1; j <= two.length(); j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    min[i][j] = min[i - 1][j - 1];
                } else {
                    min[i][j] = Math.min(Math.min(min[i - 1][j - 1], min[i][j - 1]), min[i - 1][j]) + 1;
                }
            }
        }
        return min[one.length()][two.length()];
    }
}
