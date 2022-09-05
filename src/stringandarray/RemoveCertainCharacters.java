package stringandarray;

import java.util.HashSet;
import java.util.Set;

/**
 * Remove given characters in input string, the relative order of other characters should be remained. Return the new string after deletion.
 *
 * Assumption:
 * The given input is not null
 * The characters to be removed is given by another string, it is guaranteed to be not null
 *
 * Examples:
 * input: "abcd", t "ab", delete all instances of 'a' and 'b' results in "cd"
 */
public class RemoveCertainCharacters {

    public String removeCertainCharacters(String input, String t) {
        //String is immutable in Java, so we have to convert the input String into a char array
        char[] array = input.toCharArray(); //time complexity is O(n), space complexity is O(n)

        //we need to maintain a set to includes all the characters to be removed
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < t. length(); i++) {
            set.add(t.charAt(i));
        }

        //to remove characters and keep the same original sequence, we can use two pointers that move in the same direction
        //the characters between [0, i) are the characters we are going to keep
        //the characters between [i, j) are the characters we don't care
        //the character at j is the character we are current checking
        //the characters between (j, array.length - 1] are the characters to be traversed
        //for example
        // a  b  c  d
        // i
        // j
        // we use the j to traverse the character array
        // if array[j] belongs to the set we are going to remove, -> j++
        // if array[j] doesn't belong to the set, means we need to keep the character at array[j], so we copy array[j] to array[i], then do i++, j++
        // eventually we just return the characters between[0, i) as the new string

        int i = 0;
        int j = 0;
        while (j < array.length) {  //time complexity is O(n)
            if (!set.contains(array[j])) {
                array[i] = array[j];
                i++;
            }
            j++;
        }

        return new String(array, 0, i); // //time complexity is O(n)
    }

    //total time complexity is O(n), space complexity is O(n)

}
