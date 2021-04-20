package com.EREMEI.SimpleSort;

import com.EREMEI.Arrays.LowArray;
import com.EREMEI.CommonInterfaces.Sorter;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SortTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing sort algorithms ...");

        Random rand = new Random();

        // инициализация сортировщиков
        Sorter[] sorters = new Sorter[]{
                new BubbleSort(),
                new SelectSort(),
                new InsertSort(),
                new BubbleSortMod(),
        };

        int size = 10; // размер массива

        List<Integer>[] lists = new List[sorters.length];
        // инициализация первого листа
        lists[0] = new LowArray<>();
        for (int i = 0; i < size; i++)
            lists[0].add(rand.nextInt(999));

        // инициализация остальных листов на основе первого
        for(int i = 1; i < lists.length; i++){
            lists[i] = new LowArray<>();
            lists[i].addAll(lists[0]);
        }

        System.out.println("Median: " + findMedian(lists[0]));

        System.out.println("List0: ");
        displayCollection(lists[0]);
        System.out.println();

        long minTime = Long.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < lists.length; i++) {
            System.out.println("Sorting with " + sorters[i].getClass().getSimpleName() + " algorithm");
            long start, end;
            start = System.nanoTime();

            sorters[i].doSort(lists[i]);

            end = System.nanoTime();

            if (end - start < minTime) {
                minTime = end - start;
                minIndex = i;
            }

            System.out.println("Sorted array: ");
            displayCollection(lists[i]);

            System.out.println("Sort time(ns): " + (end - start) + "\n");
        }

        System.out.println(
                sorters[minIndex].getClass().getSimpleName() + " is the fastest (" + minTime + " ns)\n"
        );

    }

    private void displayCollection(Iterable<?> collection) {
        for (Object element : collection)
            System.out.print(element + " ");
        System.out.println();
    }

    // поиск среднего элемента
    // основан на сортировке половины массива выбором
    private <T extends Comparable<T>>T findMedian(List<T> list) {
        for (int out = 0; out < list.size() / 2; out++) {
            int minIndex = out;
            for (int i = out + 1; i < list.size(); i++)
                if (list.get(i).compareTo(list.get(minIndex)) < 0)
                    minIndex = i;
            T tmp = list.get(out);
            list.set(out, list.get(minIndex));
            list.set(minIndex, tmp);
        }
        return list.get(list.size() / 2 - 1);
    }

}
