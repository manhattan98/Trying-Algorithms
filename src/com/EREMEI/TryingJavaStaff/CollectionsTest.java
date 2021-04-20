package com.EREMEI.TryingJavaStaff;

import java.util.*;

public class CollectionsTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing collections...");

        List<Integer> list = new LinkedList<>();
        //list = new ArrayList<>();

        final int SIZE = 15;
        final int BOUND = 100;

        Random rand = new Random();

        for (int i = 0; i < SIZE; i++)
            list.add(rand.nextInt(BOUND));

        Iterator<Integer> integerIterator = list.iterator();
        while (integerIterator.hasNext()) {
            integerIterator.next();
            list.add(525);
        }

    }
}
