package stringandarray;

import java.util.ArrayList;
import java.util.List;

public class ReverseOnlyVowels {

    /**
     * Only reverse the vowels('a', 'e', 'i', 'o', 'u') in a given string, the other characters should not be moved or changed.
     *
     * Assumptions:
     * The given string is not null, and only contains lower case letters.
     *
     * Examples:
     * "abbegi" --> "ibbega"
     *
     * @param input
     * @return
     */
    public String reverse(String input) {
        /*
            Solution:
            traverse the input string the find out the position of each vowel
            reverse the vowels based on their position index
            return the reversed solution
            assume there are k vowels in the input string
            TC: O(n) + O(k)
            SC: O(k)
         */
        char[] array = input.toCharArray();
        List<Integer> vowelIndex = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (isVowel(array[i])) {
                vowelIndex.add(i);
            }
        }
        reverseVowels(array, vowelIndex);
        return new String(array);
    }

    private void reverseVowels(char[] array, List<Integer> vowelIndex) {
        int left = 0;
        int right = vowelIndex.size() - 1;
        while (left < right) {
            swap(array, vowelIndex.get(left++), vowelIndex.get(right--));
        }
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
