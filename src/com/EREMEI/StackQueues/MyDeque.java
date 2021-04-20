package com.EREMEI.StackQueues;

import com.EREMEI.CommonInterfaces.Logger;

import java.util.*;

public class MyDeque<T> implements Deque<T> {
    private int size;

    private int firstIndex;
    private int lastIndex;

    private Object[] dequeArray;

    public MyDeque(int maxSize) {
        dequeArray = new Object[maxSize];
        clear();

        logger.showLog("new deque initialized with maxSize = " + maxSize);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == dequeArray.length;
    }

    public Logger logger = new Logger() {
        private boolean isLogging = false;

        @Override
        public void showLog(String msg) {
            if (isLogging) {
                System.out.println(msg);
                System.out.println(
                        "firstIndex = " + firstIndex + " lastIndex = " + lastIndex + " size = " + size + "\n"
                );
            }
        }

        @Override
        public void enableLogging() {
            isLogging = true;
        }

        @Override
        public void disableLogging() {
            isLogging = false;
        }
    };

    //------------------------------имплементация интерфейса Deque------------------------------

    @Override
    public void addFirst(T t) {
        if (!offerFirst(t))
            throw new IllegalStateException();
    }

    @Override
    public void addLast(T t) {
        if (!offerLast(t))
            throw new IllegalStateException();
    }

    @Override
    public boolean offerFirst(T t) {
        if (isFull()) return false;

        size++;
        if (firstIndex == 0)
            firstIndex = dequeArray.length;
        dequeArray[--firstIndex] = t;

        logger.showLog("new element " + t + " inserted to first position");

        return true;
    }

    @Override
    public boolean offerLast(T t) {
        if (isFull()) return false;

        size++;
        if (lastIndex == dequeArray.length - 1)
            lastIndex = -1;
        dequeArray[++lastIndex] = t;

        logger.showLog("new element " + t + " inserted to last position");

        return true;
    }

    @Override
    public T removeFirst() {
        T first = pollFirst();
        if (first == null)
            throw new NoSuchElementException();
        return first;
    }

    @Override
    public T removeLast() {
        T last = pollLast();
        if (last == null)
            throw new NoSuchElementException();
        return last;
    }

    @Override
    public T pollFirst() {
        if (isEmpty()) return null;

        size--;
        if (firstIndex == dequeArray.length)
            firstIndex = 0;

        logger.showLog("element " + dequeArray[firstIndex] + " removed from first position");

        return (T)dequeArray[firstIndex++];
    }

    @Override
    public T pollLast() {
        if (isEmpty()) return null;

        size--;
        if (lastIndex == -1)
            lastIndex = dequeArray.length - 1;

        logger.showLog("element " + dequeArray[lastIndex] + " removed from last position");

        return (T)dequeArray[lastIndex--];
    }

    @Override
    public T getFirst() {
        T first = peekFirst();
        if (first == null)
            throw new NoSuchElementException();
        return first;
    }

    @Override
    public T getLast() {
        T last = peekLast();
        if (last == null)
            throw new NoSuchElementException();
        return last;
    }

    @Override
    public T peekFirst() {
        if (isEmpty()) return null;
        if (firstIndex == dequeArray.length)
            firstIndex = 0;

        return (T)dequeArray[firstIndex];
    }

    @Override
    public T peekLast() {
        if (isEmpty()) return null;
        if (lastIndex == -1)
            lastIndex = dequeArray.length - 1;

        return (T)dequeArray[lastIndex];
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public boolean offer(T t) {
        return offerLast(t);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    // так же используется для инициализации
    @Override
    public void clear() {
        size = 0;

        firstIndex = 0;
        lastIndex = dequeArray.length - 1;
    }

    @Override
    public void push(T t) {
        addFirst(t);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int relIndex = firstIndex;
            private int absIndex = 0;

            @Override
            public boolean hasNext() {
                return absIndex < MyDeque.this.size;
            }

            @Override
            public T next() {
                if (absIndex == MyDeque.this.size)
                    throw new NoSuchElementException();
                absIndex++;
                if (relIndex == dequeArray.length) relIndex = 0;
                return (T)dequeArray[relIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        if (t1s.length != size)
            t1s = Arrays.copyOf(t1s, size);

        int i = 0;
        for (T element : this)
            t1s[i++] = (T1)element;

        return t1s;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            private int relIndex = lastIndex;
            private int absIndex = 0;

            @Override
            public boolean hasNext() {
                return absIndex < MyDeque.this.size;
            }

            @Override
            public T next() {
                if (absIndex == MyDeque.this.size)
                    throw new NoSuchElementException();
                absIndex++;
                if (relIndex == -1) relIndex = dequeArray.length - 1;
                return (T)dequeArray[relIndex--];
            }
        };
    }
}
