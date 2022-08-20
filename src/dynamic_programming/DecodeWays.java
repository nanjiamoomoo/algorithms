package dynamic_programming;

public class DecodeWays {

    /**
     *
     /**
     * Find #number of decode ways providing an input string
     *
     * The decoding scheme is:
     *
     * 1 -> A
     * 2 -> B
     * …
     * 10 -> J
     * 11 -> K
     * 12 -> L
     * …
     * 21 -> U
     * 22 -> V
     * ...
     * 26 -> Z
     * e.g. Given input String  "1121"
     *
     * It can be decoded as
     * [ "AABA",
     *   "AAU",
     *   "ALA",
     *   "KBA",
     *   "KU"
     * ]
     *
     * number of decode ways is 5
     * Assumptions:
     *  input is not null and empty
     *  the input string only contains digit letters
     *  if there is no valid decode ways, return empty list, e.g. 3404
     *
     * @param input
     * @return # of decode ways
     */
    public int decodeWays(String input) {

        /*

               we can use DP to solve this problem
               decodeWays[i] represents the # of decode ways the substring from 0th to ith index (inclusive)
               if (input.charAt(0) == ' 0' ) return 0; (this is a corner case)

               if (input.charAt(0) is a valid digit) then
               decodeWays[0] = 1

               what is the induction rules;
               decodeWays[i] =

                       case 1
                             we decode ith character as a single digit.
                             if input.charAt(i) is a valid digit 1 ~ 9,  there are decodeWays[i - 1] possible decode ways
                       case 2
                             we decode i-1th and ith character together
                             if (these) two numbers is valid number from 10 ~ 26, there are decodeWays[i - 2] possible ways
                             here is corner case when i - 2 < 0, we use 1 instead
         */
        if (input == null || input.length() == 0) {
            return 0;
        }
        int[] decodeWays = new int[input.length()];
        int digit = input.charAt(0) - '0';
        if (digit == 0) {
            return 0;
        }
        if (digit >= 1 && digit <= 9) {
            decodeWays[0] = 1;
        }

        for (int i = 1; i < input.length(); i++) {
            digit = input.charAt(i) - '0';
            if (digit >= 1 && digit <= 9) {
                decodeWays[i] += decodeWays[i - 1];
            }
            int num = (input.charAt(i - 1) - '0') * 10 + digit;
            if (num >= 10 && num <= 26) {
                if (i == 1) {
                    //corner case
                    decodeWays[i] += 1;
                } else {
                    decodeWays[i] += decodeWays[i - 2];
                }
            }
        }
        return decodeWays[input.length() - 1];
    }
}
