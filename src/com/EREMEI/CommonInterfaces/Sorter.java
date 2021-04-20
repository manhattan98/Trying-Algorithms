package com.EREMEI.CommonInterfaces;

import java.util.List;

public interface Sorter {
    public <T extends Comparable<T>>void doSort(List<T> list);
}
