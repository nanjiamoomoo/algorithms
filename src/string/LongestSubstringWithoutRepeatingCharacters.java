package string;


import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Given a string, find the longest substring without any repeating characters and return the length of it. The input string is guaranteed to be not null.
     * Example:
     * "bcdfbd" -> "bcdf"
     *
     * @param input
     * @return
     */
    public int longestSubstringWithoutRepeatingCharacters(String input) {


        //data structure: use a queue + a hashset
        //queue: we will make sure all the characters in the queue are unique
        //hashset is used for to check duplication
        if (input == null || input.length() == 0) {
            return 0;
        }

        Queue<Character> queue = new ArrayDeque<>();
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int index = 0;
        while (index < input.length()) {
            char ch = input.charAt(index);
            //if current character is repeating
            if (!set.add(ch)) {
                maxLength = Math.max(maxLength, queue.size());
                while (queue.peek() != ch) {
                    set.remove(queue.poll());
                }
                queue.poll();
            }
            queue.offer(ch);
            index++;
        }
        //after we reach the end of the string, we need to update the maxLength one more time
        maxLength = Math.max(maxLength, queue.size());
        return maxLength;
    }
}
