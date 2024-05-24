package Utilz;

import java.util.LinkedList;

/**
 * class which creates a list and which extends the LinkedLIST class
 * @param <E>
 */
public class ListUpdate<E> extends LinkedList<E> {

    private int maxSize;

    public ListUpdate(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * If the list is full then the first elements and delete to make room for the new one
     */
    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        while (size() > maxSize) {
            super.remove();
        }
        return added;
    }
}
