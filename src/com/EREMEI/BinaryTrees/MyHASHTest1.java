package com.EREMEI.BinaryTrees;

import java.nio.ByteBuffer;
import jcifs.util.*;

public class MyHASHTest1 implements Runnable {

    @Override
    public void run() {
        MD4 md4 = new MD4();
        System.out.println("Test MyHASH...");

        int k = 21;

        byte[] bytes = new byte[] {(byte)k};
        displayHexBytes("Byte: MD4(" + k + ") = ", MyHASH.MD4(bytes));
        md4.engineUpdate(bytes, 0, bytes.length);
        displayHexBytes(md4.engineDigest());

        bytes = String.valueOf(k).getBytes();
        displayHexBytes("String: MD4(" + k + ") = ", MyHASH.MD4(bytes));
        md4.engineUpdate(bytes, 0, bytes.length);
        displayHexBytes(md4.engineDigest());

        bytes = ByteBuffer.allocate(4).putInt(k).array();
        displayHexBytes("Int: MD4(" + k + ") = ", MyHASH.MD4(bytes));
        md4.engineUpdate(bytes, 0, bytes.length);
        displayHexBytes(md4.engineDigest());

    }

    private void displayHexBytes(String prefix, byte[] bytes) {
        System.out.print(prefix);
        for (int i = 0; i < bytes.length; i++)
            System.out.printf("%x", bytes[i]);
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
