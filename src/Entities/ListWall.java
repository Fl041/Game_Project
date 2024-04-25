package Entities;

import java.util.LinkedList;

public class ListWall<E> extends LinkedList<E> {

    private int maxSize;

    public ListWall(int maxSize) {
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
