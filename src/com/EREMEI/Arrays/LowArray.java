package com.EREMEI.Arrays;

import com.EREMEI.CommonInterfaces.Logger;

import java.util.*;

public class LowArray<T> implements List<T> {
    static final private int DEFAULT_ARRAY_LENGTH = 10000;

    private Object[] array;
    private int size;

    public LowArray(int length) {
        size = 0;
        array = new Object[length];

        logger.showLog("array initialized");
    }
    public LowArray() {
        this(DEFAULT_ARRAY_LENGTH);
    }

    public T remove(int index) throws ArrayIndexOutOfBoundsException {
        if ((index >= size) || (index < 0))
            throw new ArrayIndexOutOfBoundsException(
                    "What are you doing??? Index out of bounds!\nArray size = " + size + " index = " + index
            );
        T previousElement = (T)array[index];
        size--;
        for (int i = index; i < size; i++)
            array[i] = array[i + 1];

        logger.showLog("element at index " + index + " removed");

        return previousElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (array[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Oops... Operation lastIndexOf() is unsupported for now");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Oops... Operation listIterator() is unsupported for now");
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        throw new UnsupportedOperationException("Oops... Operation ListIterator() is unsupported for now");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> subList = new LowArray<>();
        for (int i = fromIndex; i < toIndex; i++)
            subList.add(this.get(i));
        return subList;
    }

    public boolean add(T element) {
        // если текущий массив заполнен, создать новый массив большего размера,
        // скопировать все из текущего и присвоить текущему массиву новый
        if (size == array.length) {
            Object[] tmp = new Object[array.length * 2];
            for (int i = 0; i < array.length; i++)
                tmp[i] = array[i];
            array = tmp;
        }
        array[size++] = element;

        logger.showLog("element " + element.toString() + " added");

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int removeIndex = this.indexOf(o);

        if (removeIndex == -1)
            return false;

        this.remove(removeIndex);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Oops... Operation containsAll() is unsupported for now");
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.size() == 0)
            return false;

        for (T element : collection)
            this.add(element);
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Oops... Operation addAll() is unsupported for now");
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Oops... Operation removeAll() is unsupported for now");
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Oops... Operation retainAll() is unsupported for now");
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if ((index >= size) || (index < 0))
            throw new ArrayIndexOutOfBoundsException(
                    "What are you doing??? Index out of bounds!\nArray size = " + size + " index = " + index
            );
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) throws ArrayIndexOutOfBoundsException {
        if ((index >= size) || (index < 0))
            throw new ArrayIndexOutOfBoundsException(
                    "What are you doing??? Index out of bounds!\nArray size = " + size + " index = " + index
            );

        T previousElement = (T)array[index];
        array[index] = element;
        return previousElement;
    }

    @Override
    public void add(int i, T t) {
        throw new UnsupportedOperationException("Oops... Operation add() is unsupported for now");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (array.equals(o)) return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (currentIndex == size)
                    throw new NoSuchElementException();
                return (T)array[currentIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return array.clone();
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return (T1[]) array.clone();
    }

    // имплементация логгера класса
    public final Logger logger = new Logger() {
        private boolean isLogging = false;

        @Override
        public void showLog(String msg) {
            if (isLogging) {
                System.out.println(msg);
                System.out.print("array: ");
                for (int i = 0; i < size; i++) System.out.print(array[i] + " ");
                System.out.println("\nsize = " + size);
                System.out.println("length = " + array.length + "\n");
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
}
