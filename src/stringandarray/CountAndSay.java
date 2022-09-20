package stringandarray;

public class CountAndSay {

    /**
     * Given a sequence of number: 1, 11, 21, 1211, 111221, …
     *
     * The rule of generating the number in the sequence is as follow:
     * 1 is "one 1" so 11.
     * 11 is "two 1s" so 21.
     * 21 is "one 2 followed by one 1" so 1211.
     * Find the nth number in this sequence.
     *
     * Assumptions:
     * n starts from 1, the first number is "1", the second number is "11"
     * n is smaller than 30
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        /*
            if we know the result for n - 1,
            then we can interpret the string to find the result for n

            how to interpret the string
            e.g. 111221 -> 312211

            basically the problem is to encode string by counting duplicate characters


         */
        if (n == 1) {
            return "1";
        }
        String curr = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            //decode previous spring to find the current string
            int j= 0;
            while (j < curr.length()) {
                int begin = j;
                while (j < curr.length() && curr.charAt(j) == curr.charAt(begin)) {
                    j++;
                }
                sb.append((char)(j - begin + '0'));
                sb.append(curr.charAt(begin));
            }
            curr = sb.toString();
        }
        return curr;
    }
}
