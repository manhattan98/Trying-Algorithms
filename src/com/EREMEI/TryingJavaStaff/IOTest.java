package com.EREMEI.TryingJavaStaff;

import java.io.*;

public class IOTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing java.io package...");

        //System.identityHashCode()

        String s1 = "hello file";

        File F = new File("test.txt");
        try {
            PrintStream PS = new PrintStream(F);
            PS.printf("%d", 5).close();
        } catch (FileNotFoundException E) { System.out.println("file error"); }

        try (FilterOutputStream OS = new BufferedOutputStream(new FileOutputStream(F))) {
            OS.write(s1.getBytes());

            System.out.println("file write success");
        } catch (IOException E) {
            System.out.println("file IO error");
        }

    }
}
