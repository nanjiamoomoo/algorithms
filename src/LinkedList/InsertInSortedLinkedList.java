package LinkedList;

import merge.ListNode;

public class InsertInSortedLinkedList {

    /**
     * Insert a value in a sorted linked list.
     * Examples
     * L = null, insert 1, return 1 -> null
     * L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
     * L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
     * L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
     * @param head
     * @param value
     * @return
     */
    public ListNode insert(ListNode head, int value) {
        /*
            Step1: we need to find the first element larger than or equal to the inserted value
            Step2: insert a node before the first larger element.

            We can use a pointer curr to traverse the linked list
            if the curr.next == null or curr.next.value > value, we stop
            then we create a new node with the insert value
                newNode.next = curr.next;
                curr.next = newNode

            Since it is possible we will generate a node before the head
            we can use a dummy node to help us to simply the process
         */
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr.next != null && curr.next.value < value) {
            curr = curr.next;
        }
        ListNode newNode = new ListNode(value);
        newNode.next = curr.next;
        curr.next = newNode;
        return dummy.next;
    }
}
