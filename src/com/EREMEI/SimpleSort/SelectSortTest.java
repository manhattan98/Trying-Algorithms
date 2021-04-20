package com.EREMEI.SimpleSort;

import com.EREMEI.Arrays.LowArray;

import java.util.List;
import java.util.Random;

public class SelectSortTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing SelectSort...");

        Random rand = new Random();

        int size = 100;

        List<Integer> list = new LowArray<>();
        for (int i = 0; i < size; i++)
            list.add(rand.nextInt(999));

        System.out.println("Array: ");
        for (Integer element : list)
            System.out.print(element + " ");

        long start, end;
        start = System.nanoTime();

        SelectSort.doSelectSort(list);

        end = System.nanoTime();

        System.out.println("\nSorted array: ");
        for (Integer element : list)
            System.out.print(element + " ");

        System.out.println("\nSort time(ns): " + (end - start));
    }
}
