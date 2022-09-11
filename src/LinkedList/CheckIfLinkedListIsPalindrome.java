package LinkedList;

import merge.ListNode;

public class CheckIfLinkedListIsPalindrome {

    /**
     * Given a linked list, check whether it is a palindrome.
     * Examples:
     * Input:   1 -> 2 -> 3 -> 2 -> 1 -> null
     * output: true.
     * Input:   1 -> 2 -> 3 -> null
     * output: false.
     *
     * Requirements:
     * Space complexity must be O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        /*
            Step1: Find the middle point
            Step2: Reverse the second half
            Step3: compare the two linked list and check if they match.

         */
        if (head == null || head.next == null) {
            return true;
        }
        //step1:
        ListNode middle = middle(head);
        ListNode two = middle.next;
        middle.next = null;

        //step2:
        two = reverse(two);

        //step3:
        return isMatch(head, two);
    }

    private boolean isMatch(ListNode one, ListNode two) {
        while (one != null && two != null) {
            if (one.value != two.value) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null || one.next == null;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    private ListNode middle (ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
