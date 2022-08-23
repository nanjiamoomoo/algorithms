package graph.dfs.permutations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AllValidPermutationsOfParentheses {

    /**
     * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.
     * Assumption: l, m, n >= 0
     * l + m + n > 0
     *
     * Examples: l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]     *
     * @param l the pairs of ()
     * @param m the pairs of <>
     * @param n the pairs of {}
     * @return all valid permutations of parentheses.
     */
    public List<String> validParenthesesII(int l, int m, int n) {
        /*
                                            ""                0
                                      (        <        {       1
                              ((  () (<  ({                       2
                             (((  ()( ()< (<)
                              ....

                for each type of parentheses
                    the condition to add left is left < # of pairs
                    the condition to add the right is that it must match the closest left parenthesis
                        because of this we can use a stack to keep track the closest available left parenthesis

               when to return?
                when the count of right parentheses of each type matches the l, m n.

                for this special problem we need to count the parentheses used for each type
                for easier option, we can use an array to keep track of the count of each parenthesis used
                e.g. [
         */

        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
//        dfs(0,0, 0, 0, 0, 0, l, m, n, sb, res, stack);
        final char[] parentheses = {'(', ')', '<', '>', '{', '}'};
        int[] count = {l, l, m, m, n, n};
        dfsII(parentheses, count, res, sb, stack);
        return res;

    }

//    private void dfs (int ll, int lr, int ml, int mr, int nl, int nr, int l, int m, int n, StringBuilder sb, List<String> res, Deque<Character> stack) {
//          /*
//            ll: count of (
//            lr : count of )
//            ml : count of <
//            mr : count of >
//            nl : count of {
//            nr : count lf }
//         */
//        if (lr == l && nr == n && mr == m) {
//            res.add(sb.toString());
//            return;
//        }
//
//        //add left parenthesis
//        if (ll < l) {
//            sb.append('(');
//            stack.offerFirst('(');
//            dfs(ll+1, lr, ml, mr, nl, nr, l, m, n, sb, res, stack);
//            sb.deleteCharAt(sb.length() - 1);
//            stack.pollFirst();
//        }
//        if (ml < m) {
//            sb.append('<');
//            stack.offerFirst('<');
//            dfs(ll, lr, ml+1, mr, nl, nr, l, m, n, sb, res, stack);
//            sb.deleteCharAt(sb.length() - 1);
//            stack.pollFirst();
//        }
//        if (nl < n) {
//            sb.append('{');
//            stack.offerFirst('{');
//            dfs(ll, lr, ml, mr, nl+1, nr, l, m, n, sb, res, stack);
//            sb.deleteCharAt(sb.length() - 1);
//            stack.pollFirst();
//        }
//
//        //add right parenthesis
//        if (!stack.isEmpty() && stack.peek().equals('(')) {
//            sb.append(')');
//            stack.pollFirst();
//            dfs(ll, lr+1, ml, mr, nl, nr, l, m, n, sb, res, stack);
//            sb.deleteCharAt(sb.length() - 1);
//            stack.offerFirst('(');
//        }
//        if (!stack.isEmpty() && stack.peek().equals('<')) {
//            sb.append('>');
//            stack.pollFirst();
//            dfs(ll, lr, ml, mr+1, nl, nr, l, m, n, sb, res, stack);
//            sb.deleteCharAt(sb.length() - 1);
//            stack.offerFirst('<');
//        }
//        if (!stack.isEmpty() && stack.peek().equals('{')) {
//            sb.append('}');
//            stack.pollFirst();
//            dfs(ll, lr, ml, mr, nl, nr+1, l, m, n, sb, res, stack);
//            sb.deleteCharAt(sb.length() - 1);
//            stack.offerFirst('{');
//        }
//    }

    public static void main(String[] args) {
        AllValidPermutationsOfParentheses allValidPermutationsOfParenthesesII = new AllValidPermutationsOfParentheses();
        System.out.println(allValidPermutationsOfParenthesesII.validParenthesesII(1, 0, 0));
    }

    /**
     * Above code is so bulky and a lot of parameters, can we optimize it
     * Yes
     * we can use an array to keep the remaining count of each parenthesis
     * use another array to store each parenthesis
     * e.g.['(', ')', '<', '>', '{', '}']
     * e.g.[l, l, m, m, n, n]
     */

    private void dfsII(char[] parentheses, int[] count, List<String> res, StringBuilder sb, Deque<Character> stack) {
        if (count[1] == 0 && count[3] == 0 && count[5] == 0) {
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < parentheses.length; i++) {
            //left parentheses
            if (i % 2 == 0) {
                if (count[i] > 0) {
                    sb.append(parentheses[i]);
                    count[i]--;
                    stack.offerFirst(parentheses[i]);
                    dfsII(parentheses, count, res, sb, stack);
                    count[i]++;
                    stack.pollFirst();
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                //add right parentheses
                if (!stack.isEmpty() && stack.peek().equals(parentheses[i - 1])) {
                    sb.append(parentheses[i]);
                    count[i]--;
                    stack.pollFirst();
                    dfsII(parentheses, count, res, sb, stack);
                    stack.offerFirst(parentheses[i - 1]);
                    count[i]++;
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    /**
     * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}, subject to the priority restriction: {} higher than <> higher than ().
     * Assumption:
     * l, m, n >= 0
     * l + m + n >= 0
     * Examples
     * l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "<()>", "<>()"].
     * l = 2, m = 0, n = 1, all the valid permutations are [“()(){}”, “(){()}”, “(){}()”, “{()()}”, “{()}()”, “{}()()”].
     * @param l the pairs of ()
     * @param m the pairs of <>
     * @param n the pairs of {}
     * @return all valid permutations of parentheses.
     */
    public List<String> validParenthesesIII(int l, int m, int n) {

        /*
                                  ""   0
                                 (    <     1
                                ()   <>  <(   2
                              ()<   <>(   <()  3
                            ()<>   <>()    <()>    4
                              return

                rules to add left parentheses
                    there is left parentheses available
                    you can only add a new left with the lesser priority
                    the corner case is when adding the first not paired left parenthesis, we can add any as long as there is remaining count
                rules to add right parentheses
                    you can only add a right parentheses to match the first available

         */
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        final char[] parentheses = {'(', ')', '<', '>', '{', '}'};
        //count represents the remaining quantities of the corresponding parenthesis.
        int[] count = {l, l, m, m, n, n};
        //stack is used for to keep track of the first unpaired left parenthesis we added in order to match the next available right parenthesis
        //the integer represents the index in the parentheses array to uniquely identify the type of the parenthesis
        Deque<Integer> stack = new ArrayDeque<>();
        helper(parentheses, count, stack, res, sb);
        return res;

    }

    private void helper(char[] parentheses, int[] count, Deque<Integer> stack, List<String> res, StringBuilder sb) {
        //base case
        if (count[1] == 0 && count[3] == 0 && count[5] == 0) {
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < parentheses.length; i++) {
            //add left
            if (i % 2 == 0) {
                if (count[i] > 0 && (stack.isEmpty() || stack.peek() > i)) {
                    sb.append(parentheses[i]);
                    count[i]--;
                    stack.offerFirst(i);
                    helper(parentheses, count, stack, res, sb);
                    sb.deleteCharAt(sb.length() - 1);
                    count[i]++;
                    stack.pollFirst();
                }
            } else {
                //add right
                if (!stack.isEmpty() && stack.peek() == i - 1){
                    sb.append(parentheses[i]);
                    count[i]--;
                    stack.poll();
                    helper(parentheses, count, stack, res, sb);
                    stack.offerFirst(i - 1);
                    count[i]++;
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}
