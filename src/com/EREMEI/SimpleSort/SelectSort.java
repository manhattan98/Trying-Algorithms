package com.EREMEI.SimpleSort;

import com.EREMEI.CommonInterfaces.Sorter;

import java.util.List;

public class SelectSort implements Sorter {
    public static <T extends Comparable<T>>void doSelectSort(List<T> list) {
        for (int out = 0; out < list.size() - 1; out++) {
            int minIndex = out;
            for (int i = out + 1; i < list.size(); i++)
                if (list.get(i).compareTo(list.get(minIndex)) < 0)
                    minIndex = i;
            T tmp = list.get(out);
            list.set(out, list.get(minIndex));
            list.set(minIndex, tmp);
        }
    }

    @Override
    public <T extends Comparable<T>> void doSort(List<T> list) {
        doSelectSort(list);
    }
}
