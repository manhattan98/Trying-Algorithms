package com.EREMEI.LinkedLists;

import com.EREMEI.CommonInterfaces.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LinkedListSpeedTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing LinkedList class...");

        //this.logger.enableLogging();

        final List<Integer>[] linkedLists = new List[]{
                new LinkedList<>(),
                new MyLinkedList<>(),
        };

        final int testCount = 5;

        final Random rand = new Random();

        final long[][] timerStat = new long[testCount][linkedLists.length];

        Thread testThread = new Thread(() -> {
            // test passes for testCount times
            for (int testN = 0; testN < testCount; testN++) {
                //logger.showLog("Test " + (testN + 1) + " started...");
                for (int l = 0; l < linkedLists.length; l++) {
                    List<Integer> linkedList = linkedLists[l];

                    long start = System.nanoTime();

                    int size = 10000;

                    for (int i = 0; i < size; i++)
                        linkedList.add(i, Integer.valueOf(rand.nextInt(size)));

                    List<Integer> subList = linkedList.subList(size / 5, size / 2);

                    subList.clear();

                    for (int i = 0; i < size / 10; i++)
                        subList.add(rand.nextInt(size));

                    Integer testElement = rand.nextInt(size);

                    //logger.showLog("index of " + testElement + " in subList: " + subList.indexOf(testElement));

                    //logger.showLog("linkedList after modifying subList: ");

                    String listViewStr = "";
                    for (Integer I : linkedList)
                        listViewStr += (I + " ");
                    //logger.showLog(listViewStr);

                    //logger.showLog("display list by index: ");
                    listViewStr = "";
                    for (int i = 0; i < linkedList.size(); i++)
                        listViewStr += (linkedList.get(i) + " ");
                    //logger.showLog(listViewStr);

                    long end = System.nanoTime();

                    timerStat[testN][l] = end - start;

                    //logger.showLog("Time passed in ns: " + timerStat[testN][l] + "\n");
                }
                //logger.showLog("Test " + (testN + 1) + " is over.\n");
            }
        });

        testThread.start();

        // print beautiful dots while tests passing
        while (testThread.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException E) {
                E.printStackTrace();
            }
            System.out.print(".");
        }
        System.out.println();

        System.out.println(testCount + " tests passed.");

        long[] avgListTime = new long[linkedLists.length];
        // for every list
        for (int l = 0; l < linkedLists.length; l++) {
            // for every test for list l
            long sumAvg = 0;
            for (int t = 0; t < testCount; t++) {
                sumAvg += (timerStat[t][l] / testCount);
            }
            avgListTime[l] = sumAvg;
            System.out.println(
                    "Avg time for " + linkedLists[l].getClass().getSimpleName() + " class in ns: " + sumAvg + "\n" +
                    "Last time for " + linkedLists[l].getClass().getSimpleName() + " class in ns: " + timerStat[testCount - 1][l] + "\n"
            );
        }

    }

    private Logger logger = new Logger() {
        private boolean isLogging = false;

        @Override
        public void showLog(String msg) {
            if (isLogging)
                System.out.println(msg);
        }

        @Override
        public void enableLogging() {
            isLogging = true;
        }

        @Override
        public void disableLogging() {
            isLogging = false;
        }
    };

}