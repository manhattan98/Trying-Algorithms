package com.EREMEI.Graphs;

import com.EREMEI.LinkedLists.MyLinkedList;

import java.util.Iterator;
import java.util.List;

public class MyGraphTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing MyGraph class...");

        MyGraph<String> myGraph = new MyGraph<>();

        myGraph.addEntry("A");
        myGraph.addEntry("B");
        myGraph.addEntry("C");
        myGraph.addEntry("D");
        myGraph.addEntry("E");

        myGraph.linkEntries("A", "B");
        myGraph.linkEntries("A", "C");
        myGraph.linkEntries("A", "D");
        myGraph.linkEntries("A", "E");

        //myGraph.linkEntries("B", "C");
        myGraph.linkEntries("B", "D");
        myGraph.linkEntries("B", "E");

        myGraph.linkEntries("C", "B");
        myGraph.linkEntries("C", "D");
        myGraph.linkEntries("C", "E");

        myGraph.linkEntries("D", "E");

        System.out.println(myGraph.mst());

        Iterator<String> graphIterator = myGraph.DFSIterator();
        graphIterator = myGraph.BFSIterator();

        //while (graphIterator.hasNext())
            //System.out.println(graphIterator.next());

    }
}
