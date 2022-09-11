package LinkedList;

import merge.ListNode;

public class MergeTwoSortedLinkedLists {

    /**
     * Merge two sorted lists into one large sorted list.
     * Examples:
     * L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
     * L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
     * L1 = null, L2 = null, merge L1 and L2 to null
     * @param one
     * @param two
     * @return
     */
    public ListNode merge(ListNode one, ListNode two) {
        /*
            We can use two pointers to merge the two linked list. We always move the smaller one

            1   4   6
                    p1
            2   5
               prev   p2

            In order to connect the linked list, we need to maintain a prev node
                if one.value <= two.value, prev.next = one, prev = one, one = one.next
                else, prev.next = two, prev = two, two = two.next

            We can use a dummy node to help us to simply the newHead issue, since we don't know the new head in the beginning

            What if one node reach to the end of the linked list, we connect prev.next = not Null
         */

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (one != null && two != null) {
            if (one.value <= two.value) {
                prev.next = one;
                one = one.next;
            } else {
                prev.next = two;
                two = two.next;
            }
            prev = prev.next;
        }
        prev.next = one == null ? two : one;
        return dummy.next;
    }
}
