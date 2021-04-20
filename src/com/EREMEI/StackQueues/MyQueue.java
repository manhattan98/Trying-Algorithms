package com.EREMEI.StackQueues;

import com.EREMEI.CommonInterfaces.Logger;

import java.util.EmptyStackException;

public class MyQueue<T> {
    private Object[] queueArray;

    private int rearIndex; // конец очереди, индекс последнего вошедшего элемента
    private int frontIndex; // начало очереди, индекс первого вошедшего элемента
    private int size;

    public MyQueue(int stackSize) {
        this.queueArray = new Object[stackSize];

        this.rearIndex = -1;
        this.frontIndex = 0;
        this.size = 0;

        logger.showLog("New queue initialized with size " + stackSize);
    }

    public void insert(T element) {
        if (rearIndex + 1 == queueArray.length)
            rearIndex = -1;
        queueArray[++rearIndex] = element;
        size++;

        logger.showLog("New element pushed, is full: " + isFull());
    }

    public T remove() {
        if (frontIndex == queueArray.length)
            frontIndex = 0;
        size--;

        logger.showLog("Element out from queue, is empty: " + isEmpty());

        return (T) queueArray[frontIndex++];
    }

    public T peek() {
        if (frontIndex == queueArray.length)
            frontIndex = 0;
        return (T) queueArray[frontIndex];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queueArray.length;
    }

    public Logger logger = new Logger() {
        private boolean isLogging = false;

        @Override
        public void showLog(String msg) {
            if (isLogging) {
                System.out.println(msg);
                System.out.print("queue array: ");
                for (Object element : queueArray)
                    System.out.print(element + " ");
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

}
