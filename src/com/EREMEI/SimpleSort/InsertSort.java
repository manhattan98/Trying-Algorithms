package com.EREMEI.SimpleSort;

import com.EREMEI.CommonInterfaces.Sorter;

import java.util.List;

public class InsertSort implements Sorter {
    public static <T extends Comparable<T>>void doInsertSort(List<T> list) {
        for (int out = 1; out < list.size(); out++) {
            T tmp = list.get(out);
            int in = out;
            while ((in > 0) && (list.get(in - 1).compareTo(tmp)) >= 0)
                list.set(in, list.get(--in));
            list.set(in, tmp);
        }
    }

    @Override
    public <T extends Comparable<T>> void doSort(List<T> list) {
        doInsertSort(list);
    }
}
