package graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class CombinationsForTelephonePad {

    /**
     * Given a telephone keypad, and an int number, print all words which are possible by pressing these numbers, the output strings should be sorted.
     * {0 : "", 1 : "", 2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 : "pqrs", 8 : "tuv", 9 : "wxyz"}
     * Assumption: The given number >= 0
     * Examples:
     * if input number is 231, possible words which can be formed are:
     * [ad, ae, af, bd, be, bf, cd, ce, cf]
     * @param number
     * @return
     */
    public String[] combinations(int number) {
        /*
                                         ""
                      2  a             b               c
                      3 ad ae af     bd be bf    cd ce cf

               step1: convert the int number to string
               step2: we can use a String[] to map each digit to the string value
               step3: then use dfs to find all the possible words



         */
        //stringMap[i] represents the string relevant to the digit i
        final String[] stringMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        String input = String.valueOf(number);
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, stringMap, sb, input, 0);
        String[] words = new String[res.size()];
        for (int i = 0; i < words.length; i++) {
            words[i] = res.get(i);
        }
        return words;

    }

    private void helper(List<String> res, String[] stringMap, StringBuilder sb, String input, int index) {
        //base case
        if (index == input.length()) {
            res.add(sb.toString());
            return;
        }

        char ch = input.charAt(index);
        int digit = ch - '0';
        String charSet = stringMap[digit];
        // if current char set is 0, such as digit is 0 or 1, we can bypass this layer and go the next lay
        if (charSet.length() == 0) {
            helper(res, stringMap, sb, input, index + 1);
        }
        //otherwise
        for (int i = 0; i < charSet.length(); i++) {
            sb.append(charSet.charAt(i));
            helper(res, stringMap, sb, input, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
