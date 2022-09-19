package stringandarray;

public class DecompressString {

    /**
     * Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences. If the character does not have any adjacent repeated occurrences, it is not compressed.
     * Assumptions:
     * The string is not null
     * The characters used in the original string are guaranteed to be ‘a’ - ‘z’
     * There are no adjacent repeated characters with length > 9
     * <p>
     * Examples
     * “acb2c4” → “acbbcccc”
     *
     * @param input
     * @return
     */
    public String decompressI(String input) {
        /*
            Method 1: Use StringBuilder
             1. if the current character is an alphabetical character, copy direct
             2. if it is a digit number i, duplicate the previous character, i - 1 times
         */
//        if (input == null || input.length() == 0) {
//            return input;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < input.length(); i++) {
//            char ch = input.charAt(i);
//            if (ch >= '0' && ch <= '9') {
//                int digit = ch - '0';
//                for (int j = 0; j < digit - 1; j++) {
//                    sb.append(input.charAt(i - 1));
//                }
//            } else {
//                sb.append(ch);
//            }
//        }
//        return sb.toString();

        /*
            Method 2: do it in place
            [ a 2 c 3
                    i
            [ a 2 c 3 x
                    s f

            step1: scan the original array and find the new length
            step2: two pointers scan from the right. traverse the slow pointer from right to left
                   (f, array.length - 1] the decompressed letters
                   (s, f] we don't care
                   [0, s] elements we are exploring
                if it is a digit, copy array[s - 1] to array[f] i times and move f one step left each time
                    then s--
                if it is not a digit, copy array[s] to array[f] f--, s--

         */
        int newLength = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int digit = ch - '0';
                newLength += digit - 1;
            } else {
                newLength++;
            }
        }

        char[] array = new char[newLength];
        int fast = array.length - 1;
        for (int slow = input.length() - 1; slow >= 0; slow--) {
            char ch = input.charAt(slow);
            if (ch >= '0' && ch <= '9') {
                int digit = ch - '0';
                for (int i = 0; i < digit; i++) {
                    array[fast--] = input.charAt(slow - 1);
                }
                slow--;
            } else {
                array[fast--] = ch;
            }
        }
        return new String(array);
    }

    /**
     * Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.
     * Assumption:
     * The string is not null
     * The characters used in the original string are guaranteed to be ‘a’ - ‘z’
     * There are no adjacent repeated characters with length > 9
     *
     * Examples: “a1c0b2c4” → “abbcccc”
     * @param input
     * @return
     */
    public String decompressII(String input) {
        /*
            method 1: StringBuilder

             if (input == null || input.length() == 0) {
            return input;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '0') {
                sb.deleteCharAt(sb.length() - 1);
            }
            else if (ch == '1') {
                continue;
            } else if (ch >= '2' && ch <= '9') {
                int digit = ch - '0';
                for (int j = 0; j < digit - 1; j++) {
                    sb.append(input.charAt(i - 1));
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();

         */
       /*
            method2: in place
        */
        if (input == null || input.length() == 0) {
            return input;
        }
        int newLength = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '0') {
                newLength -= 1;
            } else if (ch >= '1' && ch <= '9') {
                int digit = ch - '0';
                newLength += digit - 1;
            } else {
                newLength++;
            }
        }

        char[] array = new char[newLength];
        int j = array.length - 1;
        for (int i = input.length() - 1; i >=0 ;i -= 2) {
            char ch = input.charAt(i);
            if (ch == '0') {
                continue;
            } else {
                int digit = ch - '0';
                for (int k = 0; k < digit; k++) {
                    array[j--] = input.charAt(i - 1);
                }
            }
        }
        return new String(array);
    }

}
