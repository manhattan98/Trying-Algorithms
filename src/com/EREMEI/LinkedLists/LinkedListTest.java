package com.EREMEI.LinkedLists;

import com.EREMEI.CommonInterfaces.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LinkedListTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing LinkedList class...");

        List<String> linkedList = new MyLinkedList<>();

        linkedList.add(0, "hello"); // 0
        linkedList.add(1, "world"); // 1
        linkedList.add("refvd"); // 2
        linkedList.add("gvfc"); // 3
        linkedList.add("785ruyhi"); // 4
        linkedList.add("p;.l"); // 5

        linkedList.add(2, "test");

        //linkedList.subList(5, 5).add("wow");

        System.out.print("linkedList display by iterator: ");
        for (String S : linkedList)
            System.out.print(S + " ");
        System.out.println();

        System.out.print("linkedList display by index access: ");
        for (int i = 0; i < linkedList.size(); i++)
            System.out.print(linkedList.get(i) + " ");
        System.out.println("\n");

    }

}
