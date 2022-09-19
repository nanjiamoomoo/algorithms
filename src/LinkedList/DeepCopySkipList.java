package LinkedList;

import java.util.HashMap;
import java.util.Map;

class SkipListNode {
    public int value;
    public SkipListNode next;
    public SkipListNode forward;
    public SkipListNode(int value) {
        this.value = value;
    }
}
public class DeepCopySkipList {

    Map<SkipListNode, SkipListNode> map = new HashMap<>();
    /**
     * A Skip List is a special type of linked list, where each of the nodes has a forward pointer to another node in the front and forward pointers are guaranteed to be in non-descending order.
     *
     * Make a deep copy of the original skip list.
     * @param head
     * @return
     */
    public SkipListNode deepCopy(SkipListNode head) {
        /*
            We can use the recursion to solve the problem
            We can assume deepCopy(SkipListNode head) will return the deep of the linked list with the head

            Then the problem converts to make deep cope of the current head deepCopyHead
            then deepCopyHead.next = deepCopy(head.next);
                 deepCopyHead.forward = deepCopy(head.forward);

            We also need a one on one map between the original list and the new deepCopy list
         */

        if (head == null) {
            return null;
        }
        SkipListNode deepCopyHead = map.get(head);
        if (deepCopyHead != null) {
            return deepCopyHead;
        }
        deepCopyHead = new SkipListNode(head.value);
        map.put(head, deepCopyHead);
        deepCopyHead.next = deepCopy(head.next);
        deepCopyHead.forward = deepCopy(head.forward);
        return deepCopyHead;

        /*
            Another solution: iteration
            For each node, we make a deep copy of its next and forward node

            if (head == null) {
                return null;
            }
            SkipListNode deepCopyHead = new SkipListNode(head.value);
            map.put(head, deepCopyHead);
            SkipListNode copyCurr = deepCopyHead;

            while (head != null) {
                if (head.next != null) {
                    SkipListNode copyNext = map.get(head.next);
                    if (copyNext == null) {
                        copyNext = new SkipListNode(head.next.value);
                    }
                    copyCurr.next = copyNext;
                    map.put(head.next, copyNext);
                }

                if (head.forward != null) {
                    SkipListNode copyForward = map.get(head.forward);
                    if (copyForward == null) {
                        copyForward = new SkipListNode(head.forward.value);
                    }
                    copyCurr.forward = copyForward;
                    map.put(head.forward, copyForward);
                }

                head = head.next;
                copyCurr = copyCurr.next;
            }
            return deepCopyHead;

         */
    }
}
