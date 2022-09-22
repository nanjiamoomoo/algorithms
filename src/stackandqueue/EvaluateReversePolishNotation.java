package stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {

    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Assumption:
     * Valid operators are +, -, *, /.
     * Each operand may be an integer or another expression.
     *
     * Examples:
     * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     *
     * @param tokens
     * @return
     */
    public int evaluateReversePolishNotation(String[] tokens) {
        /*
            We can use a stack to solve the problem
            if the next letter is a digit, then we push it in the stack
            if the next letter is an operator, then we pop two elements from the stack top and make calculate and push it in the stack.

            We do so until the end of the token
         */
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (String s : tokens) {
            if (isOperator(s)) {
                int first = stack.pollFirst();
                int second = stack.pollFirst();
                res = operation(first, second, s);
                stack.offerFirst(res);
            } else {
                stack.offerFirst(Integer.parseInt(s));
            }
        }
        return res;
    }

    private int operation(int first, int second, String operator) {
        if (operator.equals("+")) {
            return first + second;
        }
        if (operator.equals("-")) {
            return second - first;
        }
        if (operator.equals("/")) {
            return second / first;
        }
        if (operator.equals("*")) {
            return first * second;
        }
        return -1;
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*");
    }
}
