package com.EREMEI.StackQueues;

public class MyStackTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing stack...");

        MyStack<String> myStack = new MyStack<>(5);

        myStack.logger.enableLogging();

        myStack.push("hello");
        myStack.push("world");

        while (!myStack.isEmpty())
            System.out.println(myStack.pop());

        System.out.println(myStack.isEmpty() ? "Stack is empty" : "Stack is not empty");
    }
}
