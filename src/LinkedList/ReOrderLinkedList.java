package LinkedList;

import merge.ListNode;

public class ReOrderLinkedList {

    /**
     * Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null
     * Examples
     * L = null, is reordered to null
     * L = 1 -> null, is reordered to 1 -> null
     * L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
     * L = 1 -> 2 -> 3 -> null, is reordered to 1 -> 3 -> 2 -> null
     *
     * @param head
     * @return
     */
    public ListNode reOrder(ListNode head) {
        /*
            Step1: find the middle of the linked list and partition the linked list into two
            Step2: reverse the second half
            Step3: merge
         */

        //corner case:
        if (head == null || head.next == null) {
            return head;
        }

        //step1: find the middle point and partition the linked list into two
        ListNode middle = middle(head);
        ListNode two = middle.next;
        middle.next = null;
        ListNode one = head;

        //step2: reverse the second linked list
        two = reverse(two);

        //step3: merge two linked list
        return merge(one, two);
    }

    private ListNode middle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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

    private ListNode merge(ListNode one, ListNode two) {
        /*
            1   2  3
                p1
            4   5
           prev p2

            prev.next = p1
            next1 = p1.next;
            p1.next = p2
            p2 = p2.next
            p1 = next1
            prev = prev.next.next;

            two scenarios at the end
              1   2
                     p1
              3   4
                     p2

              1   2   5
                      p1
              3   4
                 prev p2

         */
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (one != null && one != null) {
            prev.next = one;
            one = one.next;
            prev.next.next = two;
            two = two.next;
            prev = prev.next.next;
        }
        if (one != null) {
            prev.next = one;
        }
        return dummy.next;
    }
}
