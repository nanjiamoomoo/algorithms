package datastructure_implementation;

/**
 * Design a Singly Linked List
 */
public class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {

    }

    public static class Node {
        int value;
        Node next;
        public Node() {
            this(0);
        }
        public Node(int value) {
            this.value = value;
        }
    }

    /**
     *
     * @param index
     * @return Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node curr = head;
        while (index-- != 0) {
            curr = curr.next;
        }
        return curr.value;
    }

    /**
     *
     * @return size of the linked list
     */
    public int size() {
        return size;
    }

    /**
     * if the linked list is empty or not
     * @return return true if empty; else return false;
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the head of the linked list.
     * @param value the value of the new node
     */
    public void addAtHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     * @param value the value of the new node
     */
    public void addAtTail(int value) {
        Node newNode = new Node(value);
        size++;
        if (size == 0) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {
        Node newNode = new Node(val);
        if (index < 0 || index > size) {
            return;
        }
        Node prev = null;
        Node curr = head;
        while (index-- != 0) {
            prev = curr;
            curr = curr.next;
        }
        if (prev != null) {
            prev.next = newNode;
            newNode.next = curr;
        } else {
            newNode.next = curr;
            head = newNode;
        }
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     * @param index
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        Node prev = null;
        Node curr = head;
        while (index-- != 0) {
            prev = curr;
            curr = curr.next;
        }
        if (prev != null) {
            prev.next = curr.next;
        } else {
            head = curr.next;
        }
        size--;
    }

}
