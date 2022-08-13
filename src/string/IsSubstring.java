package string;

/**
 * substring related problems
 */
public class IsSubstring {

    /**
     * Determine if a small string is a substring of another large string.
     * Return the index of the first occurrence of the small string in the large string.
     * Return -1 if the small string is not a substring of the large string.
     * <p>
     * Assumption:
     * 1. Both large and small string are not null.
     * 2. if the small string is empty string, return 0
     * <p>
     * Examples:
     * 1. "ab" is a substring of "bcabc", return 2
     * 2. "bcd" is not a substring of "bcabc", return -1
     * 3. "" is substring of "abc", return 0
     *
     * @param large the large string
     * @param small the small string
     * @return the first occurrence of the specified small string in the large string.
     */
    public int isSubstring(String large, String small) {
        /*

          if (small == "") {
            return 0;
        }
        //if we use the system API, we can use indexOf api
        // Returns the index within this string of the first occurrence of the specified substring, starting at the specified index.
        return large.indexOf(small, 0);

         */


        //for each index, we check if the substring starts at the current index in the large string can match the small string
        //if it can, we will return the index
        //if not, index move the next position to check
        // the time complexity will be the (large.length - small.length) * small.length

        //corner case:
        if (small == "") {
            return 0;
        }

        // index i represents the current index being processed
        int i = 0;
        while (i <= large.length() - small.length()) {
            //j represents the current index being processed in the small tring
            int j = 0;
            if (large.charAt(i) == small.charAt(j)) {
                //start represents the start position of the substring that will potentially match the small string
                int start = i;
                while (j < small.length() && large.charAt(i) == small.charAt(j)) {
                    i++;
                    j++;
                }
                //if j reaches to the end of the small index, we can tell that we found a match
                if (j == small.length()) {
                    return start;
                } else {
                    //reset i to start position
                    i = start;
                }
            }
            i++;
        }
        return -1;
    }
}
