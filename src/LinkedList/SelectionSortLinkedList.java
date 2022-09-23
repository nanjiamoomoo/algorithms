package LinkedList;

import merge.ListNode;

public class SelectionSortLinkedList {

    /**
     * Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The selectoin sort algorithm should be used to solve this problem.
     *
     * Examples:
     * null, is sorted to null
     * 1 -> null, is sorted to 1 -> null
     * 1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
     * 4 -> 2 -> 6 -> 3 -> 5 -> null, is sorted to 2 -> 3 -> 4 -> 5 -> 6
     *
     * @param head
     * @return
     */
    public ListNode selectionSort(ListNode head) {
        /*
            Step1: Traverse the entire list and find the largest node
            Step2: create a new linked list
            Step2: Remove the largest node from the original linked list and append it to the new linked list.
            Do step 2 until there is only one node left.

            Method 2: Recursively
          public ListNode selectionSort(ListNode head) {
            return helper(head);
          }

          private ListNode helper(ListNode original) {
            if (original == null || original.next == null) {
              return original;
            }
            ListNode nodeBeforeS = nodeBeforeSmallest(original);
            ListNode smallest = nodeBeforeS.next;
            nodeBeforeS.next = smallest.next;
            if (smallest != original) {
              smallest.next = helper(original);
            } else {
              smallest.next = helper(original.next);
            }
            return smallest;
          }

          private ListNode nodeBeforeSmallest(ListNode head) {
            ListNode nodeBeforeS = new ListNode(-1);
            nodeBeforeS.next = head;
            while (head.next != null) {
              if (head.next.value < nodeBeforeS.next.value) {
                nodeBeforeS = head;
              }
              head = head.next;
            }
            return nodeBeforeS;
          }
         */

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head.next != null) {
            ListNode nodeBeforeL = nodeBeforeLargest(head);

            ListNode largest = nodeBeforeL.next;
            nodeBeforeL.next = nodeBeforeL.next.next;
            if (head == largest) {
                head = head.next;
            }
            largest.next = newHead;
            newHead = largest;
        }
        head.next = newHead;
        return head;
    }

    private ListNode nodeBeforeLargest(ListNode head) {
        ListNode nodeBeforeL = new ListNode(-1);
        nodeBeforeL.next = head;
        while (head.next != null) {
            if (head.next.value > nodeBeforeL.next.value) {
                nodeBeforeL = head;
            }
            head = head.next;
        }
        return nodeBeforeL;
    }
}
