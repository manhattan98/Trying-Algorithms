package com.EREMEI.BinaryTrees;

public class MyNodeTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing MyNode class...");

        MyNode<Integer> nodeInt1 = new MyNode<>();

        MyNode<Integer> nodeInt2 = new MyNode<>();

        MyNode<Object> nodeObj = new MyNode<>();

        MyNode<String> nodeStr = new MyNode<>();

        Comparable<Object> objectComparable = new Comparable<Object>() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        MyNode<Comparable<Object>> nodeWTF = new MyNode<>();

        nodeWTF.setStored(objectComparable);


    }

}
