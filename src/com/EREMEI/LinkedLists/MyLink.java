package com.EREMEI.LinkedLists;

import java.util.Iterator;
import java.util.NoSuchElementException;

class MyLink<T> implements Iterable<MyLink<T>> {
    private T storedObject;
    private MyLink<T> nextLink;

    private int key;

    public MyLink(T storedObject) {
        this.storedObject = storedObject;
        this.nextLink = null;
    }

    public void setStored(T storedObject) {
        this.storedObject = storedObject;
    }

    public T getStored() {
        return this.storedObject;
    }

    public void setNext(MyLink nextLink) {
        this.nextLink = nextLink;
    }

    public MyLink<T> getNext() {
        return this.nextLink;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    @Override
    public Iterator<MyLink<T>> iterator() {
        return new Iterator<>() {
            private MyLink<T> currentLink;
            {
                currentLink = new MyLink<>(null);
                currentLink.nextLink = MyLink.this;
            }

            @Override
            public boolean hasNext() {
                return currentLink.nextLink != null;
            }

            @Override
            public MyLink<T> next() {
                if (currentLink.nextLink == null)
                    throw new NoSuchElementException();
                currentLink = currentLink.nextLink;
                return currentLink;
            }
        };
    }
}