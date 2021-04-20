package com.EREMEI.TryingJavaStaff;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class IOTest3 implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing java.io package...");

        int k = 255;

        byte[] b1 = String.valueOf(k).getBytes();
        byte[] b2 = new byte[0];
        try (ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(bs)) {
            ds.writeInt(k);
            b2 = bs.toByteArray();
        } catch (IOException E) { System.out.println("io error"); }

        //b2 = ByteBuffer.allocate(4).putInt(k).array();
        //b2 = new byte[] {0x00, 0x00, 0x00, (byte)0xff};

        System.out.print("bytes from String: ");
        displayHexBytes(b1);

        System.out.print("bytes from Stream: ");
        displayHexBytes(b2);

        System.out.println(ByteBuffer.allocate(4).put(b2).rewind().getInt());

        byte[] A = new byte[]{(byte)0x1a};
        byte[] B = new byte[]{(byte)0xaa, (byte)0xbb};
        byte[] C = new byte[]{(byte)0xef};
        byte[] D = new byte[]{(byte)0x95, (byte)0x76, (byte)0x17};

        byte[] ABCD = ByteBuffer.allocate(7).put(A).put(B).put(C).put(D).array();

        for (int i = 0; i < ABCD.length; i++)
            System.out.printf("%x", ABCD[i]);


        //System.out.println(ByteBuffer.wrap(b2).getInt());
    }

    private void displayHexBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++)
            System.out.printf("0x%x ", bytes[i]);
        System.out.println();
    }
}
