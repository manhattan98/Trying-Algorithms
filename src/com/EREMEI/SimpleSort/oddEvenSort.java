package com.EREMEI.SimpleSort;

import com.EREMEI.CommonInterfaces.Sorter;

import java.util.List;

public class oddEvenSort implements Sorter {
    public static <T extends Comparable<T>>void doOddEvenSort(List<T> list) {

    }

    @Override
    public <T extends Comparable<T>> void doSort(List<T> list) {
        doOddEvenSort(list);
    }
}