package com.EREMEI.StackQueues;

public class MyDequeTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing deque...");

        MyDeque<String> theDeque = new MyDeque<>(5);

        theDeque.logger.enableLogging();

        theDeque.offerFirst("fdc");
        theDeque.offerFirst("iokl");
        theDeque.offerFirst("2134");
        theDeque.offerFirst("bvc");
        theDeque.offerLast("999");

        System.out.println("The deque " + (theDeque.isFull() ? "is full" : "is not full yet"));

        String[] dequeArray = theDeque.toArray(new String[] {});

        for (String element : dequeArray)
            System.out.print(element + " ");
        System.out.println();


        System.out.println("" +
                "//----------------------------------------------------------------------------------"
        );


        theDeque = new MyDeque<>(5);

        theDeque.logger.enableLogging();

        theDeque.offerLast("fdc");
        theDeque.offerLast("iokl");
        theDeque.offerLast("2134");
        theDeque.offerLast("bvc");
        theDeque.offerLast("999");

        System.out.println("The deque " + (theDeque.isFull() ? "is full" : "is not full yet"));

        dequeArray = theDeque.toArray(new String[] {});

        for (String element : dequeArray)
            System.out.print(element + " ");
        System.out.println();

    }
}