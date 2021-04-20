package com.EREMEI.BinaryTrees;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MyHASHTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Test MyHASH...");

        int k;
        byte[] bytes;
        bytes = new byte[] { 127, 95 };

        //System.out.println(( % 64));

        // step 1
        int bytesLen = bytes.length;

        //bytesLen = Integer.MAX_VALUE;

        int paddingLen = Math.abs((bytesLen % 64) - 56);

        byte[] bytesLenBytePresent = ByteBuffer
                .allocate(8)
                .putLong(bytesLen)
                .array();

        System.out.println("bytesLen = " + bytesLen);
        System.out.println("paddingLen = " + paddingLen);
        for (byte b : bytesLenBytePresent)
            System.out.print(b + " ");
        System.out.println("\n");
        // end step 1

    }
}
