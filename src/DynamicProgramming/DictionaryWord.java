package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class DictionaryWord {

    /**
     * Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.
     *
     * Examples:
     * Dictionary: {“bob”, “cat”, “rob”}
     * Word: “robob” return false
     * Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
     *
     *
     * @param input, the input string
     * @param dictionary, list of words in the dictionary
     * @return
     */
    public boolean ifWordsFromDictionary(String input, String[] dictionary) {
        /*
              input: robcatbob
               canBreak[i] represents if the first i characters can be composed by concatenating words from the given dictionary.

               zero character
                    "" : true
               one character
                    r
                    ""|r canBreak[0] && r?
                two characters:
                    ro
                    case1: ""|ro   canBreak[0] && ro?
                    case2: r|o   canBreak[1] && o?
                    ....

               induction rule:
               canBreak[i] =
                    |robcatbob    canBreak[0] && if robcatbob is in the dictionary
                    r| obcatbob   canBreak[1] && if obcatbob is in the dictionary
                    ro|bcatbob    canBreak[2] && if bcatbob is in the dictionary
                     ....
                                  canBreak[i - 1] && if b is in the dictionary



         */
        //set up a set of dictionary
        Set<String> set = new HashSet<>();
        for (String s : dictionary) {
            set.add(s);
        }
        //canBreak[i] represents the first i characters that can be composed by words in the dictionary
        boolean[] canBreak = new boolean[input.length() + 1];
        //we assume "" is true
        canBreak[0] = true;
        //i represent count of characters we are evaluating
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (canBreak[j] && set.contains(input.substring(j, i))) {
                    canBreak[i] = true;
                }
            }
        }

        //return if the first input.length() characters can be composed by the words in the dictionary
        return canBreak[input.length()];
    }
}
