package AmazonQuestions;

public class MyAtoi {

    /**
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
     * <p>
     * The algorithm for myAtoi(string s) is as follows:
     * <p>
     * Read in and ignore any leading whitespace.
     * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * Return the integer as the final result.
     *
     * @param s
     * @return
     */
    public int StringToInteger(String s) {
               /*
            Step1 : remove leading spaces
            Step2 : " check the sign, if there is no sign, then we regard it as '+'
            Step3 : pass all the zeros, if zeros are all passed
            Step4 : pass all the digits until a non-digit character or the end of the string
            if the step4 has zero letters, then return 0

            how to check overflow using integer
            Initially, store the sign for the final result and consider only the absolute values to build the integer and return the final result with a correct sign at the end.
            If the current number is less than 214748364 = (INT_MAX / 10), we can append the next digit.
            If the current number is greater than 214748364:
            And, the sign for the result is '+', then the result will overflow, so return INT_MAX;
            Or, the sign for the result is '-', then the result will underflow, so return INT_MIN.
            If the current number is equal to 214748364:
            If the next digit is between 0-7, the result will always be in range.
            If, next digit is 8:
            and the sign is '+' the result will overflow, so return INT_MAX;
            if the sign is '-', the result will not underflow but will still be equal to INT_MIN, so that we can return INT_MIN.
            But if, the next digit is greater than 8:
            and the sign is '+' the result will overflow, so return INT_MAX;
            if the sign is '-', the result will underflow, so return INT_MIN.
              // Traverse next digits of input and stop if it is not a digit
            while (index < n && Character.isDigit(input.charAt(index))) {
                int digit = input.charAt(index) - '0';

                // Check overflow and underflow conditions.
                if ((result > Integer.MAX_VALUE / 10) ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                    // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                // Append current digit to the result.
                result = 10 * result + digit;
                index++;
            }
        */

        if (s == null || s.length() == 0) {
            return 0;
        }
        //step 1
        int i = 0;

        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        //step 2
        int sign = 1;
//        char signBit = '+';
        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }
        int res = 0;
        // Traverse next digits of input and stop if it is not a digit
        while (i < s.length()  && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // Check overflow and underflow conditions.
            if ((res > Integer.MAX_VALUE / 10) ||
                    (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Append current digit to the result.
            res = 10 * res + digit;
            i++;
        }
        return sign * res;
//        while (i < s.length() && s.charAt(i) == '0') {
//            i++;
//        }

//        StringBuilder sb = new StringBuilder();
//        while (i < s.length() && isDigit(s.charAt(i))) {
//            sb.append(s.charAt(i));
//            i++;
//        }
//        if (sb.length() == 0) {
//            return 0;
//        }
//
//        long res = convert(sb);
//        if (signBit == '-') {
//            res = -res;
//        }
//        if (res < Integer.MIN_VALUE) {
//            return Integer.MIN_VALUE;
//        }
//        if (res > Integer.MAX_VALUE) {
//            return Integer.MAX_VALUE;
//        }
//        return (int) res;
    }

//    private long convert(StringBuilder sb) {
//        long res = 0;
//        for (int i = 0; i < sb.length(); i++) {
//            res = 10 * res + sb.charAt(i) - '0';
//            if (res > Integer.MAX_VALUE) {
//                return res;
//            }
//        }
//        return res;
//    }
//
//    private boolean isDigit(char ch) {
//        return ch >= '0' && ch <= '9';
//    }
}
