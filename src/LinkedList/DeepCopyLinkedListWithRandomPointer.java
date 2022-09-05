package LinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomListNode {
    public int value;
    public RandomListNode next;
    public RandomListNode random;

    public RandomListNode(int value) {
        this.value = value;
    }
}

public class DeepCopyLinkedListWithRandomPointer {

    /**
     * Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. Make a deep copy of the original list.
     *
     * @param head
     * @return
     */
    public RandomListNode copy(RandomListNode head) {
        /*

            If there is no random pointer, we can just traverse the linked list and make the deep copy of each node one by one
            Since there is a random pointer, it is possible that random node was copied before.
                if it was already copied, we don't need to make another copy, instead we only need to connect the current node to the random node
                How do we know if a node has been copied or not, we can use a one to one map between the original linked list and the deep copied one.
                When we traverse the original linked list, if the map contains the node, then we know that the node has been copied, otherwise we make a copy of it

            Step1 make the deep copy of the head
            Step2 traverse the original linked list using curr reference from head position
                if (curr.next != null) make a deep copy of it(note we need to check if we have copied it before)
                if (curr.random != null) make a deep copy of it(note we need to check if we have copied it before)

         */
        if (head == null) {
            return null;
        }
        RandomListNode copyHead = new RandomListNode(head.value);
        RandomListNode curr = head;
        RandomListNode copyCurr = copyHead;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        map.put(head, copyHead);
        while (curr != null) {
            //copy next node
            if (curr.next != null) {
                RandomListNode next = map.get(curr.next);
                //if the next node has been deep copied
                if (next == null) {
                    next = new RandomListNode(curr.next.value);
                    map.put(curr.next, next);
                }
                copyCurr.next = next;
            }
            //copy random node
            if (curr.random != null) {
                RandomListNode random = map.get(curr.random);
                if (random == null) {
                    random = new RandomListNode(curr.random.value);
                    map.put(curr.random, random);
                }
                copyCurr.random = random;
            }
            curr = curr.next;
            copyCurr = copyCurr.next;
        }
        return copyHead;

        /*
            We can use recursion DFS to solve the problem as well.

            We define a method copy(RandomListNode head), this method makes the copy of the linked list with head

            on each recursion level
            We make a copy of the head
            Then we use recursion to make the copy of the sub linked list represented by the head.next
            and the sub linked list represented by the head.random

            What if the copy of the head has been made?
            Then we don't need to make the copy again. It means all the sub linked list represented by head has been copied. we can just return the copy

            Map<RandomListNode, RandomListNode> map = new HashMap<>();
            public RandomListNode copy(RandomListNode node) {
                if (node == null) {
                    return;
                }
                if (map.containsKey(node)) {
                    return map.get(node);
                }

                RandomListNode newNode = new RandomListNode(node.value);
                map.put(node, newNode);
                newNode.next = copy(node.next);
                newNode.random = copy(node.random);
                return newNode;

            }
         */
    }
}
