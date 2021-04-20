package com.EREMEI.TryingJavaStaff;

import java.io.*;

public class IOTest2 implements Runnable {

    @Override
    public void run() {
        //System.out.println("Testing java.io package...");

        File thisFile = new File("src/com/EREMEI/TryingJavaStaff/IOTest2.java");

        boolean key = false;
        int slashCount = 0;

        try (FileReader fr = new FileReader(thisFile)) {
            int c;
            while ((c = fr.read()) != -1) {
                switch ((char)c) {
                    case '/':
                        if ((slashCount == 0) || key)
                            slashCount++;
                        else
                            slashCount = 1;
                        key = true;
                        break;
                    case '\n':
                        if (slashCount >= 2)
                            System.out.println();
                        slashCount = 0;
                        key = false;
                        break;
                    default:
                        if (slashCount >= 2)
                            System.out.print((char) c);
                        key = false;
                        break;
                }
                //System.out.print((char) c);
            }
            System.out.println();
        } catch (IOException E) {
            System.out.println("io error");
        }

    }
}
//это все грибы!