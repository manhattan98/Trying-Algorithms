package com.EREMEI.SimpleSort;

import com.EREMEI.CommonInterfaces.Sorter;

import java.util.List;

public class BubbleSortMod implements Sorter {
    public static <T extends Comparable<T>>void doBubbleSortMod(List<T> list) {
        int outLeft = 0;
        int outRight = list.size() - 1;

        while (outLeft < outRight) {
            for (int in = outLeft; in < outRight; in++) {
                if (list.get(in).compareTo(list.get(in + 1)) > 0) {
                    T tmp = list.get(in + 1);
                    list.set(in + 1, list.get(in));
                    list.set(in, tmp);
                }
            } outRight--;
            for (int in = outRight; in > outLeft; in--) {
                if (list.get(in).compareTo(list.get(in - 1)) < 0) {
                    T tmp = list.get(in - 1);
                    list.set(in - 1, list.get(in));
                    list.set(in, tmp);
                }
            } outLeft++;
        }
    }

    @Override
    public <T extends Comparable<T>> void doSort(List<T> list) {
        doBubbleSortMod(list);
    }
}