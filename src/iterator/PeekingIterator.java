package iterator;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {
    private boolean hasPeeked;
    private Integer peekedElement;
    private Iterator<Integer> iterator;
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Integer next() {
        if (hasPeeked) {
            hasPeeked = false;
            return peekedElement;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || iterator.hasNext();
    }

    public Integer peek() {
        if (!hasPeeked) {
            peekedElement = iterator.next();
            hasPeeked = true;
        }
        return peekedElement;
    }
}
