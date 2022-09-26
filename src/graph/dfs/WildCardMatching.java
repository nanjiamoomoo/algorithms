package graph.dfs;

public class WildCardMatching {

    /**
     * Given two strings where first string is a normal string and second string may contain wild card characters. Write a function that returns true if the two strings match. The following are allowed wildcard characters in first string.
     * * --> Matches with 0 or more instances of any character or set of characters.
     * ? --> Matches with any one character.
     *
     * Assumptions:
     *
     * The two strings are both not null.
     * Assume there is no more than one '*' adjacent to each other.
     * Examples:
     *
     * input = "abc", pattern = "?*", return true.
     * @param input
     * @param pattern
     * @return
     */
    public boolean match(String input, String pattern) {
        // Write your solution here

    /*
      We can use DFS to solve the problem
      define a function boolean canMatch(String input, int inputFromIndex, String pattern, int patternFromIndex)
      this function returns if the input's substring from inputFromIndex can match the pattern's substring from patternFromIndex

      base cases:
        if both index reaches to the end, return true;
        if both inputFromIndex reaches to the end and patternFromIndex == pattern.length - 1, and the last character is *, then turn true
        if only one index reaches to the end return false

      if pattern.charAt(patternFromIndex) is not wildCard, then check if it matches the character in the input and move to next recursion level
      if pattern.charAt(patternFromIndex) is ?, then we move to next recursion level
      if pattern.charAt(patternFromIndex) is *, then we need to pair it with 0 ~ input.length() - patternFromIndex characters respectively.
         there are input.length() - patternFromIndex possible branches.


       TC: Worst scenario happens where all the characters in the pattern is *, even though it cannot happen based on assumption
            assume there are m characters in the input, then are maximum m branches on each recursion level. actually this is decreasing for each level
            assume there are n characters in the pattern, then there are n recursion levels
            the worst scenario is m*(m-1)*(m-2)...(m-n + 1)
           O(m!) this is a conservative estimation, the actually is much less than this.
       SC: call stack O(n)

    */
        return canMatch(input, 0, pattern, 0);
    }

    private boolean canMatch(String input, int inputFromIndex, String pattern, int patternFromIndex) {
        if (inputFromIndex == input.length() && (patternFromIndex == pattern.length() ||
                patternFromIndex == pattern.length() - 1 && pattern.charAt(patternFromIndex) == '*')) {
            return true;
        }
        if (inputFromIndex == input.length() || patternFromIndex == pattern.length()) {
            return false;
        }

        char ch = pattern.charAt(patternFromIndex);
        if (ch != '?' && ch != '*') {
            if (input.charAt(inputFromIndex) == pattern.charAt(patternFromIndex)) {
                return canMatch(input, inputFromIndex + 1, pattern, patternFromIndex + 1);
            }
            return false;
        }
        if (ch == '?') {
            return canMatch(input, inputFromIndex + 1, pattern, patternFromIndex + 1);
        }

        if (ch == '*') {
            for (int offset = 0; offset <= input.length() - inputFromIndex; offset++) {
                if (canMatch(input, inputFromIndex + offset, pattern, patternFromIndex + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
