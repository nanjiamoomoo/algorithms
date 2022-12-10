package LinkedList;

import merge.ListNode;

import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbers {

    /**
     * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * Examples:
     * Input: (2 -> 4 -> 2) + (5 -> 6 -> 2)
     * Output: 7 -> 0 -> 5
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
            We traverse both linked lists and add the corresponding letters together.
            2  4  3  9
                        l1
            5  6  7
                     l2


            the value of the sum = (l1.value + l2.value + carry) % 10
            the new carry = sum / 10
            dummy  7   0   1    0
                               prev

            There is a trick, we can regard null node as value 0 node.

         */
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int num1 = l1 == null ? 0 : l1.value;
            int num2 = l2 == null ? 0 : l2.value;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            prev.next = node;
            prev = prev.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        List<String> lists = new ArrayList();
    }

}
