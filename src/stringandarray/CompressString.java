package stringandarray;

public class CompressString {

    /**
     * Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences. If the character does not has any adjacent, repeated occurrences, it is not changed.
     *
     * Assumption:
     * The string is not null
     * The characters used in the original string are guaranteed to be ‘a’ - ‘z’
     *
     * Example: “abbcccdeee” → “ab2c3de3”
     * @param input
     * @return
     */
    public String compressI(String input) {
        /*
            method 1: use StringBuilder
            for each character we jump through all the duplicate letters and calculate the count a duplicate characters
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < input.length()) {
                char ch = input.charAt(i);
                int start = i;
                while (i < input.length() && input.charAt(i) == input.charAt(start)) {
                    i++;
                }
                int count = i - start;
                sb.append(ch);
                if (count > 1) {
                    sb.append(count);
                }
            }
            return sb.toString();
         */

        /*
            method 2: String in place operation
            two pointers: slow and fast
            traverse using fast pointer
            [0, slow) contains compressed letter
            [slow, fast), we don't care
            [fast, array.length -1] to be explored
         */

        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            int start = fast;
            while (fast < array.length && array[fast] == array[start]) {
                fast++;
            }
            int count = fast - start;
            array[slow++] = array[start];
            if (count != 1) {
                slow += copyDigitI(array, slow, count);
            }
        }
        return new String(array, 0, slow);
    }

    private int copyDigitI(char[] array, int start, int count) {
        int num = count;
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        start += len - 1;
        while (count != 0) {
            array[start--] = (char)(count % 10 + '0');
            count /= 10;
        }
        return len;
    }


    /**
     * Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences.
     * Assumption:
     * The string is not null
     * The characters used in the original string are guaranteed to be ‘a’ - ‘z’
     *
     * Examples
     * “abbcccdeee” → “a1b2c3d1e3”
     * @param input
     * @return
     */
    public String compressII(String input) {
        /*
            method1: use StringBuilder: see above. only difference is that we don't need to consider if count == 1
         */

        /*
            method 2: String in place operation
            step1: we need to calculate the new length, since the string may become longer when a character has no duplicate
            step2: use two pointers to traverse from right to left and copy value from input string into the new char array based on the following rules
                1. we need to calculate the count of duplicate characters and append the count into the new string. each digit we move the pointer in the new string one step left
                2. append the duplicate characters
            step2: return new String(pointer + 1, array.length)

         */
        int newLength = input.length();
        int i = 0;
        while (i < input.length()){
            int begin = i;
            while (i < input.length() && input.charAt(i) == input.charAt(begin)) {
                i++;
            }
            if (i - begin == 1) {
                newLength++;
            }
        }

        char[] array = new char[newLength];
        int slow = input.length() - 1;
        int fast = array.length - 1;
        while (slow >= 0) {
            int begin = slow;
            while (slow >= 0 && input.charAt(slow) == input.charAt(begin)) {
                slow--;
            }
            int count = begin - slow;
            //count digits
            fast = copyDigitII(array, fast, count);
            array[fast--] = input.charAt(begin);
        }
        return new String(array, fast + 1, array.length - fast - 1);
    }

    private int copyDigitII(char[] array, int begin, int count) {
        while (count != 0) {
            array[begin--] = (char)(count % 10 + '0');
            count /= 10;
        }
        return begin;
    }
}
