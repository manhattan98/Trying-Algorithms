package com.EREMEI.SimpleSort;

import com.EREMEI.CommonInterfaces.Sorter;

import java.util.List;

public class BubbleSort implements Sorter {
    public static <T extends Comparable<T>>void doBubbleSort(List<T> list) {
        for (int out = list.size() - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (list.get(in).compareTo(list.get(in + 1)) > 0) {
                    T tmp = list.get(in + 1);
                    list.set(in + 1, list.get(in));
                    list.set(in, tmp);
                }
            }
        }
    }

    @Override
    public <T extends Comparable<T>> void doSort(List<T> list) {
        doBubbleSort(list);
    }
}