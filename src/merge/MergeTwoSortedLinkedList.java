package merge;

public class MergeTwoSortedLinkedList {

    /**
     * Merge two sorted lists into one large sorted list.
     * Examples
     * L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
     * L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
     * L1 = null, L2 = null, merge L1 and L2 to null
     * @param one
     * @param two
     * @return
     */
    public ListNode merge(ListNode one, ListNode two) {
        /*
                 1    3    5
                head       curr   one
                 2    4    6
                          two

                1  2  3 4  5
         */

        if (one == null || two == null) {
            return one == null ? two : one;
        }

        ListNode head = null;
        if (one.value < two.value) {
            head = one;
            one = one.next;
        } else {
            head = two;
            two = two.next;
        }
        ListNode curr = head;
        while (one != null && two != null) {
            if (one.value <= two.value) {
                curr.next = one;
                one = one.next;
            } else {
                curr.next = two;
                two = two.next;
            }
            curr = curr.next;
        }

        if (one == null) {
            curr.next = two;
        } else {
            curr.next = one;
        }

        return head;
    }
}
