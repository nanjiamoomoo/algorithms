package LinkedList;

import merge.ListNode;

public class ReverseLinkedList {

    /**
     * Reverse a singly-linked list iteratively.
     * Examples:
     * L = null, return null
     * L = 1 -> null, return 1 -> null
     * L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
     * @param head
     * @return
     */
    public ListNode reverseIteratively(ListNode head) {
        /*
             1 ->  2 ->  3
           p c     n

            <- 1<- 2 <- 3   null
                        p  c
             step1: keep record the next node
             step2: point curr.next = prev
             step3: move prev and curr pointers
         */
        if (head == null || head.next == null) {
            return head;
        }
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

    /**
     * Reverse a singly-linked list iteratively.
     * Examples:
     * L = null, return null
     * L = 1 -> null, return 1 -> null
     * L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
     * @param head
     * @return
     */
    public ListNode reverseRecursively(ListNode head) {
        /*
             sub problem:
             1 -> 2 <- 3
           head   |
                  null
         */
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseRecursively(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
