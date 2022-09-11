package LinkedList;

import merge.ListNode;

public class CheckIfLinkedListHasACycle {

    /**
     * Check if a given linked list has a cycle. Return true if it does, otherwise return false.
     * Assumption: You can assume there is no duplicate value appear in the linked list.
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        /*
            we can use slow and fast pointers to solve the problem
            if the linked list has a cycle, the slow and faster pointer will meet eventually

            Above algorithm works even if there is duplicate values


         */
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
