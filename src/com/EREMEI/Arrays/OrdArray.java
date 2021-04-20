package com.EREMEI.Arrays;

import com.EREMEI.CommonInterfaces.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class OrdArray<T extends Comparable<T>> implements Iterable<T> {

    private List<T> array;

    public OrdArray(int length) {
        array = new LowArray<>(length);
    }
    public OrdArray() {
        array = new LowArray<>();
    }

    public int find(T key) {
        return binarySearch(key).currentIndex;
    }

    public void insert(T element) {
        Bounds bounds = binarySearch(element);
        LowArray<T> tmp = new LowArray<>();

        // если массив пустой, или новый элемент больше последнего в массиве, просто добавить в конец
        if ((array.size() == 0) || (array.get(array.size() - 1).compareTo(element) < 0)) {
            array.add(element);
            return;
        }

        boolean key = false;
        for (int i = 0; i < array.size();) {
            if ((i != bounds.lowerBound) || key)
                tmp.add(array.get(i++));
            else {
                tmp.add(element);
                key = true;
            }
        }
        array = tmp;

        logger.showLog("lower = " + bounds.lowerBound + "\nupper = " + bounds.upperBound);
    }

    public boolean remove(T element) {
        int index = find(element);
        if (index == -1) return false;
        array.remove(index);
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < array.size();
            }

            @Override
            public T next() throws NoSuchElementException {
                if (currentIndex == array.size())
                    throw new NoSuchElementException();
                return array.get(currentIndex++);
            }
        };
    }

    // двоичный поиск
    // вернуть индекс искомого элемента key, иначе вернуть -1
    private Bounds binarySearch(T key) {
        Bounds result = (new Bounds()).setLower(0).setUpper(array.size() - 1);

        // если массив пустой - значит элемент key не может быть найден
        if (array.size() == 0) return result.setCurrent(-1);

        while (true) {
            result.setCurrent ((result.lowerBound + result.upperBound) / 2);
            // если lower и upper равны, то current = lower = upper
            if (array.get(result.currentIndex).compareTo(key) == 0)
                return result;
            else if (result.lowerBound > result.upperBound)
                return result.setCurrent(-1);
            else {
                if (array.get(result.currentIndex).compareTo(key) < 0)
                    result.setLower(result.currentIndex + 1);
                if (array.get(result.currentIndex).compareTo(key) > 0)
                    result.setUpper(result.currentIndex - 1);
            }
        }
    }

    // имплементация логгера класса
    public final Logger logger = new Logger() {
        private boolean isLogging;

        @Override
        public void showLog(String msg) {
            if (isLogging) {
                System.out.println(msg);
                System.out.print("array: ");
                for (int i = 0; i < array.size(); i++) System.out.print(array.get(i) + " ");
                System.out.println("\n");
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

    private class Bounds {
        public int currentIndex, lowerBound, upperBound;

        public Bounds setCurrent(int index) {
            this.currentIndex = index;
            return this;
        }
        public Bounds setLower(int index) {
            this.lowerBound = index;
            return this;
        }
        public Bounds setUpper(int index) {
            this.upperBound = index;
            return this;
        }
    }
}

