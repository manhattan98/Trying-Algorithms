package com.EREMEI;

import com.EREMEI.BinaryTrees.MyHASHTest;
import com.EREMEI.BinaryTrees.MyHASHTest1;
import com.EREMEI.Graphs.KnightsProblemTest;
import com.EREMEI.Graphs.MyGraphTest;
import com.EREMEI.LinkedLists.LinkedListSpeedTest;
import com.EREMEI.LinkedLists.LinkedListTest;
import com.EREMEI.Trees.MyTree23Test;
import com.EREMEI.TryingJavaStaff.*;

public class Main {
    static Thread thread;
    static Runnable target;

    public static void main(String[] args) {
        //---------------------тестируемый класс тут---------------------
        target = new KnightsProblemTest();
        //---------------------------------------------------------------

        thread = new Thread(target);
        thread.setName("Testing thread");

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException E) {
            E.printStackTrace();
        }

        System.out.println(
                "Thread " + Thread.currentThread().getName() + " is over."
        );
    }
}
