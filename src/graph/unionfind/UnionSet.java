package graph.unionfind;


import java.util.*;

class Node<V> {
    V value;
    public Node (V value) {
        this.value = value;
    }
}

public class UnionSet<V> {
    public Map<V, Node<V>> nodes;
    public Map<Node<V>, Node<V>> parents;
    public Map<Node<V>, Integer> sizeMap;

    public UnionSet(List<V> values) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
        for (V value : values) {
            Node<V> node = new Node<>(value);
            nodes.put(value, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node<V> findFather(Node<V> node) {
        if (node == parents.get(node)) {
            return node;
        }
        Node<V> head = findFather(parents.get(node));
        parents.put(node, head);
        return head;
//        Deque<Node<V>> stack = new ArrayDeque<>();
//        while (node != parents.get(node)) {
//            node = parents.get(node);
//            stack.offerFirst(node);
//        }
//        while(!stack.isEmpty()) {
//            parents.put(stack.pollFirst(), node);
//        }
//        return node;
    }

    public boolean isSameSet(V a, V b) {
        Node<V> aHead = findFather(nodes.get(a));
        Node<V> bHead = findFather(nodes.get(b));
        return aHead == bHead;
    }

    public void union(V a, V b) {
        Node<V> aHead = findFather(nodes.get(a));
        Node<V> bHead = findFather(nodes.get(b));
        if (aHead != bHead) {
            int aSetSize = sizeMap.get(aHead);
            int bSetSize = sizeMap.get(bHead);
            Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
            Node<V> small = big == aHead ? bHead : aHead;
            parents.put(small, big);
            sizeMap.put(big, aSetSize + bSetSize);
            sizeMap.remove(small);
        }
    }

    public int sets() {
        return sizeMap.size();
    }
}
