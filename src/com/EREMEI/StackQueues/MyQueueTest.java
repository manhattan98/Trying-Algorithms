package com.EREMEI.StackQueues;

public class MyQueueTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing queue...");

        MyQueue<String> myQueue = new MyQueue<>(5);

        //myQueue.logger.enableLogging();

        myQueue.insert("hello");
        myQueue.insert("world");
        myQueue.insert("rwr");
        myQueue.insert("543rf");
        myQueue.insert("bvc");

        myQueue.remove();
        myQueue.remove();

        myQueue.insert("123");
        myQueue.insert("56");

        while (!myQueue.isEmpty())
            System.out.print(myQueue.remove() + " ");

        System.out.println("\n" + (myQueue.isEmpty() ? "Queue is empty" : "Queue is not empty"));
    }
}
