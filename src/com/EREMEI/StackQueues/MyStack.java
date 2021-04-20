package com.EREMEI.StackQueues;

import com.EREMEI.Arrays.LowArray;
import com.EREMEI.CommonInterfaces.Logger;

import java.util.EmptyStackException;
import java.util.List;

public class MyStack<T> {
    private Object[] stackArray;
    private int topIndex;

    public MyStack(int stackSize) {
        this.stackArray = new Object[stackSize];
        this.topIndex = -1;

        logger.showLog("New stack initialized with size " + stackSize);
    }

    public void push(T element) throws StackOverflowError {
        if (topIndex + 1 == stackArray.length)
            throw new StackOverflowError("Stack is overflow!");
        stackArray[++topIndex] = element;

        logger.showLog("New element pushed, top index: " + topIndex);
    }

    public T pop() throws EmptyStackException {
        if (topIndex == -1)
            throw new EmptyStackException();
        return (T) stackArray[topIndex--];
    }

    public T peek() throws EmptyStackException {
        if (topIndex == -1)
            throw new EmptyStackException();
        return (T) stackArray[topIndex];
    }

    public boolean isEmpty() {
        return topIndex == -1;
    }

    public boolean isFull() {
        return topIndex == stackArray.length - 1;
    }

    public Logger logger = new Logger() {
        private boolean isLogging = false;

        @Override
        public void showLog(String msg) {
            if (isLogging) {
                System.out.println(msg);
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
