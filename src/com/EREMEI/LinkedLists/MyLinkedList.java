package com.EREMEI.LinkedLists;

import java.util.*;

public class MyLinkedList<T> implements List<T> {

    // firstLinkExclusive.getNext() is the first inclusive link
    // so lastLinkInclusive.getNext() is the last exclusive link

    private MyLink<T> firstLinkExclusive;
    private MyLink<T> lastLinkExclusive;

    private MyLink<T> lastLinkInclusive;

    private int indexOffset;

    // constructor for empty list
    public MyLinkedList() {
        this.firstLinkExclusive = new MyLink<>(null);
        this.lastLinkExclusive = new MyLink<>(null);

        this.firstLinkExclusive.setKey(-1);
        this.lastLinkExclusive.setKey(0);

        this.firstLinkExclusive.setNext(this.lastLinkExclusive);

        this.indexOffset = 0;
    }

    // constructor for subList
    private MyLinkedList(MyLink<T> firstLinkExclusive, MyLink<T> lastLinkInclusive) {
        this.firstLinkExclusive = firstLinkExclusive;
        this.lastLinkExclusive = lastLinkInclusive.getNext();

        this.lastLinkInclusive = lastLinkInclusive;

        // absolute indexOffset
        // so .getKey() - indexOffset is relative index
        this.indexOffset = firstLinkExclusive.getNext().getKey();
    }

    // updatable size
    @Override
    public int size() {
        return lastLinkExclusive.getKey() - indexOffset;
    }

    @Override
    public boolean isEmpty() {
        return firstLinkExclusive.getNext() == lastLinkExclusive;
    }

    @Override
    public boolean contains(Object o) {
        for (MyLink<T> link : firstLinkExclusive.getNext())
            if (link == lastLinkExclusive)
                break;
            else if (link.getStored().equals(o))
                return true;

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Iterator<MyLink<T>> linkIterator = firstLinkExclusive.getNext().iterator();
            private MyLink<T> currentLink;

            @Override
            public boolean hasNext() {
                currentLink = linkIterator.next();
                return currentLink != MyLinkedList.this.lastLinkExclusive;
            }

            @Override
            public T next() {
                return currentLink.getStored();
            }
        };
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        if (t1s.length != size())
            t1s = Arrays.copyOf(t1s, size());

        int i = 0;
        for (T element : this)
            t1s[i++] = (T1)element;

        return t1s;
    }

    // add new element to last position, always true
    @Override
    public boolean add(T t) {
        add(size(), t);

        return true;
    }

    // remove the first occurrence, if success return true, else false
    @Override
    public boolean remove(Object o) {
        int removeIndex = indexOf(o);

        if (removeIndex == -1)
            return false;

        remove(removeIndex);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    // clear list
    @Override
    public void clear() {
        int size = size();

        firstLinkExclusive.setNext(lastLinkExclusive);

        for (MyLink<T> link : lastLinkExclusive)
            link.setKey(link.getKey() - size);
    }

    // the element at the specified position in this list
    @Override
    public T get(int i) {
        // only valid index passed
        if ((i < 0) || (i >= size()))
            throw new IndexOutOfBoundsException();

        T curElement = null;

        for (MyLink<T> link : firstLinkExclusive.getNext())
            if (link.getKey() - indexOffset == i) {
                curElement = link.getStored();
                break;
            }

        return curElement;
    }

    @Override
    public T set(int i, T t) {
        // only valid index passed
        if ((i < 0) || (i >= size()))
            throw new IndexOutOfBoundsException();

        T prevElement = null;

        for (MyLink<T> link : firstLinkExclusive.getNext())
            if (link.getKey() - indexOffset == i) {
                prevElement = link.getStored();
                link.setStored(t);
                break;
            }

        return prevElement;
    }

    // insert at index
    @Override
    public void add(int i, T t) {
        if ((i < 0) || (i > size()))
            throw new IndexOutOfBoundsException();

        MyLink<T> insertedLink = new MyLink<>(t);
        insertedLink.setKey(i + indexOffset);

        MyLink<T> pushedLink;

        if (isEmpty()) {
            lastLinkInclusive = insertedLink;
            lastLinkInclusive.setNext(lastLinkExclusive);
            firstLinkExclusive.setNext(lastLinkInclusive);
        }
        // means add to the end of list
        else if (i == size()) {
            lastLinkInclusive.setNext(insertedLink);
            insertedLink.setNext(lastLinkExclusive);
            lastLinkInclusive = insertedLink;
        }
        else {
            for (MyLink<T> link : firstLinkExclusive)
                // link has index i - 1 now
                if (link.getNext().getKey() - indexOffset == i) {
                    pushedLink = link.getNext();
                    link.setNext(insertedLink);
                    insertedLink.setNext(pushedLink);
                    break;
                }
        }

        for (MyLink<T> link : insertedLink.getNext())
            link.setKey(link.getKey() + 1);
    }

    // remove at index, return removed element
    @Override
    public T remove(int i) {
        // only valid index passed
        if ((i < 0) || (i >= size()))
            throw new IndexOutOfBoundsException();

        MyLink<T> removedLink = firstLinkExclusive.getNext();

        for (MyLink<T> link : firstLinkExclusive)
            // now link has index i - 1
            if (link.getNext().getKey() - indexOffset == i) {
                removedLink = link.getNext();
                link.setNext(removedLink.getNext());
                break;
            }

        for (MyLink<T> link : removedLink)
            link.setKey(link.getKey() - 1);

        return removedLink.getStored();
    }

    // return relative index of the first occurrence of the specified element, else return -1
    @Override
    public int indexOf(Object o) {
        int curIndex = -1 + indexOffset;

        for (MyLink<T> link : firstLinkExclusive.getNext())
            if (link == lastLinkExclusive)
                break;
            else if (link.getStored().equals(o)) {
                curIndex = link.getKey();
                break;
            }

        return curIndex - indexOffset;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    // view of the portion of this list between the specified i, inclusive, and i1, exclusive
    // if i == i1 the returned list is empty
    @Override
    public List<T> subList(int i, int i1) {
        if ((i > i1) || (i < 0) || (i1 > size()))
            throw new IndexOutOfBoundsException();

        MyLink<T> firstSubExclusive = null;
        MyLink<T> lastSubInclusive = null;

        for (MyLink<T> link : firstLinkExclusive)
            if (link.getKey() - indexOffset == i - 1)
                firstSubExclusive = link;
            else if (link.getKey() - indexOffset == i1 - 1) {
                lastSubInclusive = link;
                break;
            }

        return new MyLinkedList<>(firstSubExclusive, lastSubInclusive);
    }
}