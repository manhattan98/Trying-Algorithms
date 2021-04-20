package com.EREMEI.Trees;

import java.util.List;
import java.util.Random;

public class MyTree23Test implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing MyTree23...");

        MyTree23<Integer> tree23 = new MyTree23<>();

        Random R = new Random();
        int LENGTH = 5000;
        int BOUND = 500;

        System.out.println("Added elements: ");
        for (int i = 0; i < LENGTH; i++) {
            int e = R.nextInt(BOUND);
            tree23.add(e);
            System.out.print(e + " ");
        } System.out.println();

        System.out.println("Tree: ");

        displayNode(tree23.getRoot());

        //for (int i = 0; i < LENGTH; i++)
            //System.out.print(tree23.get(i) + " ");
        System.out.println();

        int traceMAX = Integer.MIN_VALUE;
        System.out.print("Traces: ");
        for (int i = 0; i < LENGTH; i++) {
            int trace = tree23.traceCountTo(i);
            if (trace > traceMAX)
                traceMAX = trace;
            System.out.print(trace + " ");
        } System.out.println();

        System.out.println("max trace: " + traceMAX);
    }

    private void displayNode(MyNode23 node) {
        if (node.getDescendantLeft() != null)
            displayNode(node.getDescendantLeft());

        if (node.getKeyLeft() != -1)
            System.out.print(node.getStoredLeft() + " ");
        //System.out.print("[" + node.getKeyLeft() +"]" + node.getStoredLeft() + "; ");

        if (node.getDescendantMiddle() != null)
            displayNode(node.getDescendantMiddle());

        if (node.getKeyRight() != -1)
            System.out.print(node.getStoredRight() + " ");
        //System.out.print("[" + node.getKeyRight() +"]" + node.getStoredRight() + "; ");

        if (node.getDescendantRight() != null)
            displayNode(node.getDescendantRight());

    }
}
