package com.EREMEI.TryingJavaStaff;

public class MyStringTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing strings...");

        String s1 = "587o4hello875ru";
        String s2 = "bgvHELLO6489g";

        int off1 = 5;
        int off2 = 3;
        int len = 5;

        System.out.println(
                s1.substring(off1, off1 + len) +
                " is equals to " +
                s2.substring(off2, off2 + len) + " ignoring case: " +
                s1.regionMatches(true, off1, s2, off2, len)
        );
    }
}
