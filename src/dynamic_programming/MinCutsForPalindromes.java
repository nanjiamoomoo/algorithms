package dynamic_programming;

public class MinCutsForPalindromes {

    /**
     * Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. Determine the fewest cuts needed for a palindrome partitioning of a given string.
     * Assumption: The Given string is not null
     * @param input
     * @return
     */
    public int minCutsForPalindromes(String input) {

        /*
            We can use DP to solve the problem
            if we define minCuts[], minCuts[i] represents the minimum palindrome cuts for the first i letters
            the return of the problem will be minCuts[input.length()]

            minCuts[0] 0
            minCuts[1] 0

            minCuts[2]:
                if (the first 2 letters is palindrome), the minCuts is 0
                if (the substring from the first letter to the second letter is palindrome), the minCuts = minCuts[1] + 1

            minCuts[3]
                if (the substring(0, 3) is palindrome), minCuts is 0
                if (the substring(1, 3) is palindrome), minCuts is minCuts[1] + 1
                if (the subString(2, 3) is palindrome), minCuts is minCuts[2] + 1
                minCuts[3] = smallest of the above

            minCuts[i]
                if (substring(0, i), 0
                for j from 1, to i - 1
                    find min of :   if (substring(j, i),  (minCuts[j] + 1)


            Q: Can we assume minCuts[i] represents the min cut of letters from 0th index to ith index?
            minCuts[0] = 0
            minCuts[1]
                    if the substring(0, 2) is palindrome, then it is 0
                    otherwise
                        if (substring(1, 2) is palindrome, it is minCuts[0] + 1
            minCuts[i]
                    if the substring(0, i + 1) is palindrome it is 0
                    otherwise
                        for j 0 ~ i - 1, find min among
                        if substring(j+1, i + 1) is palindrome, it is minCuts[j] + 1
         */

        if (input == null || input.length() == 0) {
            return 0;
        }
        //minCuts[i] represents the min palindrome cut of the substring from 0th position to ith position(inclusive)
        int[] minCuts = new int[input.length()];
        minCuts[0] = 0;
        for (int i = 1; i < minCuts.length; i++) {
            if (isPalindrome(input.substring(0, i + 1))) {
                minCuts[i] = 0;
                continue;
            }
            int min = i;
            for (int j = 0; j < i; j++) {
                if (isPalindrome(input.substring(j + 1, i+ 1))) {
                    min = Math.min(min, minCuts[j] + 1);
                }
            }
            minCuts[i] = min;
        }
        return minCuts[input.length() - 1];
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //TC: n ^ 3
}
