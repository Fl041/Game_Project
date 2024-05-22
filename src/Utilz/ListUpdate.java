package Utilz;

import java.util.LinkedList;

public class ListUpdate<E> extends LinkedList<E> {

    private int maxSize;

    public ListUpdate(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        while (size() > maxSize) {
            super.remove(); // Supprime le premier élément
        }
        return added;
    }
}
