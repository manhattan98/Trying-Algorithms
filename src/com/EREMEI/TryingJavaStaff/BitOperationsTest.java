package com.EREMEI.TryingJavaStaff;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Formatter;

public class BitOperationsTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing bit operations...");

        int k = -2147483648; // 32-bit 10000...0

        int s = 10;

        int p = k << s;

        int q = Integer.rotateLeft(k, s);

        System.out.printf("%d << %d = %d\n", k, s, p);
        System.out.printf("rotateLeft(%d, %d) = %d\n", k, s, q);

        displayHexBytes(new Formatter().format("%d in bytes: ", k).toString(), k);
        displayHexBytes(new Formatter().format("%d in bytes: ", p).toString(), p);
        displayHexBytes(new Formatter().format("%d in bytes: ", q).toString(), q);
        displayHexBytes(new Formatter().format("%d in bytes: ", k*2+5).toString(), k*2+5);

    }

    private void displayHexBytes(String prefix, byte[] bytes) {
        System.out.print(prefix);
        for (int i = 0; i < bytes.length; i++)
            System.out.printf("0x%X ", bytes[i]);
        System.out.println();
    }
    private void displayHexBytes(byte[] bytes) {
        displayHexBytes("", bytes);
    }
    private void displayHexBytes(String prefix, int value) {
        displayHexBytes(prefix, ByteBuffer.allocate(4).putInt(value).array());
    }
    private void displayHexBytes(int value) {
        displayHexBytes("", value);
    }
}
