package iterator;

import java.util.*;

public class MergingIterator implements Iterator<Integer> {
    private final PriorityQueue<PeekingIterator> priorityQueue;
    public MergingIterator(List<Iterator<Integer>> iterators) {
        priorityQueue = new PriorityQueue<>(iterators.size(), (i1, i2) -> Integer.compare(i1.peek(), i2.peek()));
        for (Iterator<Integer> iterator : iterators) {
            if (iterator.hasNext()) {
                priorityQueue.add(new PeekingIterator(iterator));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !priorityQueue.isEmpty();
    }

    @Override
    public Integer next() {
        PeekingIterator nextIter = priorityQueue.poll();
        Integer next = nextIter.next();
        if (nextIter.hasNext()) {
            priorityQueue.offer(nextIter);
        }
        return next;
    }

    public static void main(String[] args) {
        Iterator<Integer> it1 = Arrays.asList(2, 5, 9).iterator();
        Iterator<Integer> it2 = Collections.emptyIterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
        Iterator<Integer> it3 = Arrays.asList(4, 10).iterator();
        MergingIterator mergingIterator = new MergingIterator(Arrays.asList(it1, it2, it3));
        while (mergingIterator.hasNext()) {
            System.out.println(mergingIterator.next());
        }

    }

}
