package dynamic_programming;

public class DecodeWaysII {

    /**
     * Given an encrypted message that contains only digits and character '*' where character '*' could represent any digit from 1 to 9. We want to decode the given message to string of capital letters following the rule that A->1, B->2, C->3....Z->26.
     *
     * Count how many ways there are to decode the given message. You could assume that the length of given message is always > 0. The result may be very large, so return the result after mod 10^9 + 7.
     * Example 1:  *
     * Input = "0"
     * Output = 0
     * Explanation: There is no way do decode the given message.
     *
     * Example 2:
     * Input = "1"
     * Output = 1
     * Explanation: The given message could only be decoded to "A"
     *
     * Example 3:
     * Input = "*"
     * Output = 9
     * Explanation: The given message could be any letter in [A,B,C,D,E,F,G,H,I]
     *
     * Example 4:
     * Input = "2*"
     * Output = 15
     * Explanation: 9 + 6 = 15. 21~29 could be decoded as BA, BB, ..., BI, and 21~26 could be decoded as U, V, W, X, Y and Z.
     *
     * @param input
     * @return # of possible ways
     */
    public int decodeWaysII(String input) {
        /*
                we can use the dp to solve the problem
                define decodeWays[i] represents the decode ways of the substring from 0th index to ith index (inclusive)

                if input.charAt(0) is '0', return 0
                if it is a digit from 1 ~ 9,
                    decodeWays[0] = 1;
                if it is '*'
                    decodeWays[0] = 9;

                let's analyze decodeWays[1]
                    case 1:
                        we decode index 1 as single character
                            if (charAt(1) is between 1 ~ 9)
                                there are decodeWays[0] ways
                            else if (charAt(1) is *)
                                there are 9 * decodeWays[0] ways
                    case 2:
                        we decode index 0 and index 1 as a single character
                            if (charAt(0) and charAt(1) are both digits and are valid between 10 ~ 26)
                                there are 1 ways
                            else if (charAt(0) is *, and charAt(1) is digit)
                                then if (digit <= 6) it has 2 ways
                                then if (digit > 6) it has 1 ways
                            else if (charAt(0) is digit, and charAt(1) is *)
                                then if(digit = 1) it has 11 ~ 19, 9 ways
                                then if (digit = 2) it has21 ~ 26, 6 ways
                                else it has 0 ways
                            else if (charAt(0) is *, and charAt(1) is *)
                                the there are 11 ~ 19, and 21 ~ 26, total 15 possible ways


              Induction rules:
                decodeWays[i]
                    case 1:
                        we decode index as single character
                            if (charAt(i) is between 1 ~ 9)
                                there are decodeWays[i - 1] ways
                            else if (charAt(1) is *)
                                there are 9 * decodeWays[i - 1] ways
                    case 2:
                        we decode index i - 1 and index i as a single character
                            if (charAt(i - 1) and charAt(i) are both digits and are valid between 10 ~ 26)
                                there are decodeWays[i - 2] ways
                                there is corner case where i = 1, please see above
                            else if (charAt(i - 1) is *, and charAt(i) is digit)
                                then if (digit <= 6) it has 2 * decodeWays[i - 2] ways
                                then if (digit > 6) it has decodeWays[i - 2]  ways
                                note: i = 1 is the corner case
                            else if (charAt(i - 1) is digit, and charAt(i) is *)
                                then if(digit = 1) it has 11 ~ 19, 9 * decodeWays[i - 2]  ways
                                then if (digit = 2) it has21 ~ 26, 6 * decodeWays[i - 2]  ways
                                else it has 0 ways
                                note: i = 1 is the corner case
                            else if (charAt(i - 1) is *, and charAt(i) is *)
                                the there are 11 ~ 19, and 21 ~ 26, total 15 * decodeWays[i - 2] possible ways
                                note: i = 1 is the corner case


         */
        if (input == null || input.length() == 0) {
            return 0;
        }
        int digit = input.charAt(0) - '0';
        if (digit == 0) {
            return 0;
        }
        int asterisk = '*' - '0';
        int[] decodeWays = new int[input.length()];
        if (digit >= 1 && digit <= 9) {
            decodeWays[0] = 1;
        } else {
            //'*'
            decodeWays[0] = 9;
        }

        //induction rules:
        for (int i = 1; i < input.length(); i++) {
            //case 1: decode ith index as a single character
            digit = input.charAt(i) - '0';
            if (digit >= 1 && digit <= 9) {
                decodeWays[i] += decodeWays[i - 1];
            } else if (digit == asterisk) {
                decodeWays[i] += 9 * decodeWays[i - 1];
            }

            //case 2: decode i - 1 and ith index together
            int prev = input.charAt(i - 1) - '0';
            if (isDigit(prev) && isDigit(digit)) {
                int num = prev * 10 + digit;
                if (num >= 10 && num <= 26) {
                    if (i == 1) {
                        decodeWays[i] += 1;
                    } else {
                        decodeWays[i] += decodeWays[i - 2];
                    }
                }
            } else if (isAsterisk(prev) && isDigit(digit)) {
                if (digit >= 0 && digit <= 6) {
                    if (i == 1) {
                        decodeWays[i] += 2;
                    } else {
                        decodeWays[i] += 2 * decodeWays[i - 2];
                    }
                } else {
                    if (i == 1) {
                        decodeWays[i] += 1;
                    } else {
                        decodeWays[i] += decodeWays[i - 2];
                    }
                }
            } else if (isDigit(prev) && isAsterisk(digit)) {
                if (prev == 1) {
                    if (i == 1) {
                        decodeWays[i] += 9;
                    } else {
                        decodeWays[i] += 9 * decodeWays[i - 2];
                    }
                } else if (prev == 2) {
                    if (i == 1) {
                        decodeWays[i] += 6;
                    } else {
                        decodeWays[i] += 6 * decodeWays[i - 2];
                    }
                }
            } else {
                if (i == 1) {
                    decodeWays[i] += 15;
                } else {
                    decodeWays[i] += 15 * decodeWays[i - 2];
                }
            }
        }
        return decodeWays[input.length() - 1] % ((int)(Math.pow(10, 9)) + 7);
    }

    private boolean isDigit(int digit) {
        return digit >= 0 && digit <= 9;
    }

    private boolean isAsterisk(int digit) {
        return digit == '*' - '0';
    }
}
