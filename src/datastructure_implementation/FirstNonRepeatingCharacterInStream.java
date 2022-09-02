package datastructure_implementation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FirstNonRepeatingCharacterInStream {

    /**
     * Given a stream of characters, find the first non-repeating character from stream. You need to tell the first non-repeating character in O(1) time at any moment.
     * Implement two methods of the class:
     * read() - read one character from the stream
     * firstNonRepeating() - return the first non-repoeating character from the stream at any time when calling the method, return null if there does not exist such characters
     * <p>
     * Examples:
     * read: a   b   c   a   c   c   b
     * firstNonRepeating: a   a   a   b   b   b   null
     */

    /*
           When we read an element, if it is the first time we read a character, we append it to the linked list
                                    if it is not the first time, we will need to remove it from the linked list

           Q: how do we know if it is the first time or not, we can use a Map<Character, Node<Character, Integer>>  to indicate if it is the first time we read this character
           if the map does not contain the node, then it is the first time,
           else if the node value is 1, then it is the second time, we have to remove it from the linked list. we also assign the value to null.
           if the value is null, then we don't need to anything.

           Q: whey do we need to use Node<Character, Integer>?
           it will help to find the node we need to remove in O(1) time.


     */

    class Node<Character, Integer> {
        private Character key;
        private Integer value;
        private Node<Character, Integer> next;
        private Node<Character, Integer> prev; //we need a prev pointer to remove the node in O(1)

        public Node(Character key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Character getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public void update(Integer value) {
            this.value = value;
        }
    }

    private Node<Character, Integer> head;
    private Node<Character, Integer> tail;
    private Map<Character, Node<Character, Integer>> map;
    private int size;

    public FirstNonRepeatingCharacterInStream() {
        map = new HashMap<Character, Node<Character, Integer>>();
    }


    public void read(char ch) {
        if (!map.containsKey(ch)) {
            //if map does not contain the character
            append(ch);
        } else {
            //if the character is a repeat character
            //case1 : the second time show up
            Node<Character, Integer> node = map.get(ch);
            if (node.getValue() != null) {
                remove(node);
                node.update(null);
            }
            //case2 : more than the second time : do nothing
        }
    }

    //append a new character to the linked list
    private void append(char ch) {
        Node<Character, Integer> newNode = new Node<>(ch, 1);
        map.put(ch, newNode);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        }
        size++;
    }

    private void remove(Node<Character, Integer> node) {
        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.prev;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        node.prev = null;
        node.next = null;
        size--;
    }

    //remove a node from the linked list
    public char firstNonRepeating() {
        return isEmpty() ? null : head.getKey();
    }

    public boolean isEmpty() {
        return size == 0;
    }


    /*
        simplified version:
    static class Node {
        private char ch;
        Node prev;
        Node next;
        public Node(char ch) {
            this.ch = ch;
        }
    }

    private Map<Character, Node> map;
    private Node head;
    private Node tail;

    public Solution() {
        // Initialize the class.
        map = new HashMap<Character, Node>();
    }

    public void read(char ch) {
        // Implement this method here.
        //case 1: contains, the 2nd time apear, then we need to remove from DLL, set map node to null
        //case 2: contains, but more than 2nd time, do nothing
        //case 3: not contains, add to the map, and append to the head

        Node node = map.get(ch);
        if (map.containsKey(ch) && node != null) {
            remove(node);
        } else if (!map.containsKey(ch)) {
            node = new Node(ch);
            append(node);
        }
    }

    private void append(Node node) {
        map.put(node.ch, node);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
    private void remove(Node node) {
        map.put(node.ch, null);
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == head) {
            head = node.next;
        }
        if (tail == node) {
            tail = node.prev;
        }
        node.next = node.prev = null;
    }


    public Character firstNonRepeating() {
        // Implement this method here.
        if (head == null) {
            return null;
        }
        return tail.ch;
    }
     */

}
