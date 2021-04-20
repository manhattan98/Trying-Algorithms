package com.EREMEI.TryingJavaStaff;

public class HashTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing hash...");

        int[] iData = new int[100];

        for (int i = 0; i < iData.length; i++) {
            String s = String.valueOf(i);
            iData[i] = s.hashCode();//Integer.hashCode(i);
        }

        //for (int data : iData)
            //System.out.print(data + " ");
        //System.out.println("\n");

        int k = 256;
        System.out.println(Integer.toHexString(k).length());

        //System.out.println((int)(Math.log10(k) + 1));

    }

    private int myHash(int ref) {
        int base = ref % 2 == 0 ? Integer.MAX_VALUE : 0;
        return -1;
    }
}
