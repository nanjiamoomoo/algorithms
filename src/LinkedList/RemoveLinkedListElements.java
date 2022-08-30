package LinkedList;

import merge.ListNode;

public class RemoveLinkedListElements {

    /**
     * Remove all elements from a linked list of integers that have value val.
     * Example
     * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
     * Return: 1 --> 2 --> 3 --> 4 --> 5
     * @param head
     * @param target
     * @return
     */
    public ListNode removeElements(ListNode head, int target) {
        /*
            What if head is null? return null
            What if the head has the target value? Means we need a new head for the linked list

            let's look at a more general case
                1     2    6    6   4   5   6 , remove 6
                                       curr

               if curr.value == target, we can do prev.next = curr.next to remove the current element
               do we need to move prev? no
               if (curr.value != target, we can do prev = curr. curr = curr.next.

               Question: can we not use curr, yes
               we can compare curr.next.value with target as long curr.next != null;
               if curr.next = target? curr.next = curr.next.next;
               else, curr = curr.next


            Then we look at corner case if head has the target value, we can use a dummy node to help



         */
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr.next != null) {
            if (curr.next.value == target) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
