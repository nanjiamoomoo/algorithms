package graph;

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



        /*
                               "sigh"  "asith"
             level0:            sig      asit
             level1:          /replace
             level2:       si asi


         */
       return minSteps(one, two, one.length(), two.length());
    }

    /**
     *
     * @param one
     * @param two
     * @param i the first i characters in the first string
     * @param j the first j characters in the second string
     * @return  returns the minimum steps needed to transform the first i characters in string one to match the first j characters in the string two
     */
    private int minSteps(String one, String two, int i, int j) {
        //base case
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }

        //case 1: one.charAt(i - 1) == two.charAt(j - 1)
        if (one.charAt(i - 1) == two.charAt(j - 1)) {
            return minSteps(one, two, i - 1, j - 1);
        }

        //case 2: one.charAt(i - 1) != two.charAt(j - 1)

        //Replace:
        int minReplace = minSteps(one, two, i - 1, j - 1) + 1;

        //Delete from one (same as the insert into two):
        int minDelete = minSteps(one, two, i - 1, j) + 1;

        //Insert into one (same as delete form two):
        int minInsert = minSteps(one, two, i, j - 1) + 1;
        return Math.min(minInsert, Math.min(minDelete, minReplace));
    }
}
