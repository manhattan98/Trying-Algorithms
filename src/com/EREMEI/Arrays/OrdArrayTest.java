package com.EREMEI.Arrays;

import java.util.Iterator;

public class OrdArrayTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing OrdArray...");

        OrdArray<Integer> array = new OrdArray<>();
        //array.logger.enableLogging();

        array.insert(5);
        array.insert(3);
        array.insert(11);
        array.insert(1);
        array.insert(4);

        array.insert(-6);

        for (Integer element : array)
            System.out.println(element);

        System.out.println("(not) found index: " + array.find(11));

    }
}
