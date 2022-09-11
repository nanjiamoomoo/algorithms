package LinkedList;

import merge.ListNode;

public class MergeSortLinkedList {

    /**
     * Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The merge sort algorithm should be used to solve this problem.
     * Examples:
     * null, is sorted to null
     * 1 -> null, is sorted to 1 -> null
     * 1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
     * 4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
     * @param head
     * @return
     */
    public ListNode mergeSort(ListNode head) {
        /*
            Step1: find the middle point of the linked List and partition the linked list into two
            Step2: sort left and sort right
            Step3: merge left and right
         */

        if (head == null || head.next == null) {
            return head;
        }
        //step 1 find middle point
        ListNode middle = middle(head);
        ListNode two = middle.next;
        middle.next = null;

        //step2: sort left and sort right
        ListNode one = mergeSort(head);
        two = mergeSort(two);

        //step3: merge left and right
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

    private ListNode merge(ListNode one, ListNode two) {
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
