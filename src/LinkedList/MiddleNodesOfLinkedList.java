package LinkedList;

import merge.ListNode;

public class MiddleNodesOfLinkedList {

    /**
     * Find the middle node of a given linked list.
     * Examples
     * L = null, return null
     * L = 1 -> null, return 1
     * L = 1 -> 2 -> null, return 1
     * L = 1 -> 2 -> 3 -> null, return 2
     * L = 1 -> 2 -> 3 -> 4 -> null, return 2
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        /*
            We can use slow and fast pointers to solve the problem
            slow pointer moves one step a time
            fast pointer moves two steps while slow moves one step.

            The questions return the left mid
            x  x   x
               s   f
            x  x   x   x
               s   f
         */
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
