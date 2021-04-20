package com.EREMEI.BinaryTrees;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Stream;

public class MyHASH implements HASHER {
    public static byte[] MD4(byte[] bytes) {
        // step 1
        int bytesLen = bytes.length;
        int paddingLen = Math.abs((bytesLen % 64) - 56);

        byte[] M = Arrays.copyOf(bytes, bytesLen + paddingLen + 8);
        M[bytesLen] = (byte)0x80; // start padding byte
        // end step 1

        // step 2
        byte[] bitsLen = ByteBuffer.allocate(8).putLong(bytesLen * 8).array();

        for (int i = 4; i < 8; i++)
            M[i - 4 + bytesLen + paddingLen] = bitsLen[i];
        for (int i = 0; i < 4; i++)
            M[i + 4 + bytesLen + paddingLen] = bitsLen[i];
        // end step 2

        // step 3
        byte[] A = new byte[] {(byte)0x01, (byte)0x23, (byte)0x45, (byte)0x67};
        byte[] B = new byte[] {(byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef};
        byte[] C = new byte[] {(byte)0xfe, (byte)0xdc, (byte)0xba, (byte)0x98};
        byte[] D = new byte[] {(byte)0x76, (byte)0x54, (byte)0x32, (byte)0x10};

        /*
        A = new byte[] {(byte)0x67, (byte)0x45, (byte)0x23, (byte)0x01};
        B = new byte[] {(byte)0xef, (byte)0xcd, (byte)0xab, (byte)0x89};
        C = new byte[] {(byte)0x98, (byte)0xba, (byte)0xdc, (byte)0xfe};
        D = new byte[] {(byte)0x10, (byte)0x32, (byte)0x54, (byte)0x76};
        */
        // end step 3

        // step 4
        for (int i = 0; i < M.length; i += 64) {
            // X is 16-word block
            byte[][] X = new byte[16][4];

            for (int j = 0; j < 16; j++)
                for (int k = 0; k < 4; k++)
                    X[j][k] = M[i + j * 4 + k];

            int AA = ByteBuffer.wrap(A).getInt();
            int BB = ByteBuffer.wrap(B).getInt();
            int CC = ByteBuffer.wrap(C).getInt();
            int DD = ByteBuffer.wrap(D).getInt();


            // round 1
            A = round1(A, B, C, D, X[0], 3);
            D = round1(D, A, B, C, X[1], 7);
            C = round1(C, D, A, B, X[2], 11);
            B = round1(B, C, D, A, X[3], 19);

            A = round1(A, B, C, D, X[4], 3);
            D = round1(D, A, B, C, X[5], 7);
            C = round1(C, D, A, B, X[6], 11);
            B = round1(B, C, D, A, X[7], 19);

            A = round1(A, B, C, D, X[8], 3);
            D = round1(D, A, B, C, X[9], 7);
            C = round1(C, D, A, B, X[10], 11);
            B = round1(B, C, D, A, X[11], 19);

            A = round1(A, B, C, D, X[12], 3);
            D = round1(D, A, B, C, X[13], 7);
            C = round1(C, D, A, B, X[14], 11);
            B = round1(B, C, D, A, X[15], 19);

            // round 2
            A = round2(A, B, C, D, X[0], 3);
            D = round2(D, A, B, C, X[4], 5);
            C = round2(C, D, A, B, X[8], 9);
            B = round2(B, C, D, A, X[12], 13);

            A = round2(A, B, C, D, X[1], 3);
            D = round2(D, A, B, C, X[5], 5);
            C = round2(C, D, A, B, X[9], 9);
            B = round2(B, C, D, A, X[13], 13);

            A = round2(A, B, C, D, X[2], 3);
            D = round2(D, A, B, C, X[6], 5);
            C = round2(C, D, A, B, X[10], 9);
            B = round2(B, C, D, A, X[14], 13);

            A = round2(A, B, C, D, X[3], 3);
            D = round2(D, A, B, C, X[7], 5);
            C = round2(C, D, A, B, X[11], 9);
            B = round2(B, C, D, A, X[15], 13);

            // round 3
            A = round3(A, B, C, D, X[0], 3);
            D = round3(D, A, B, C, X[8], 9);
            C = round3(C, D, A, B, X[4], 11);
            B = round3(B, C, D, A, X[12], 15);

            A = round3(A, B, C, D, X[2], 3);
            D = round3(D, A, B, C, X[10], 9);
            C = round3(C, D, A, B, X[6], 11);
            B = round3(B, C, D, A, X[14], 15);

            A = round3(A, B, C, D, X[1], 3);
            D = round3(D, A, B, C, X[9], 9);
            C = round3(C, D, A, B, X[5], 11);
            B = round3(B, C, D, A, X[13], 15);

            A = round3(A, B, C, D, X[3], 3);
            D = round3(D, A, B, C, X[11], 9);
            C = round3(C, D, A, B, X[7], 11);
            B = round3(B, C, D, A, X[15], 15);


            A = ByteBuffer.allocate(4).putInt(ByteBuffer.wrap(A).getInt() + AA).array();
            B = ByteBuffer.allocate(4).putInt(ByteBuffer.wrap(B).getInt() + BB).array();
            C = ByteBuffer.allocate(4).putInt(ByteBuffer.wrap(C).getInt() + CC).array();
            D = ByteBuffer.allocate(4).putInt(ByteBuffer.wrap(D).getInt() + DD).array();
        }
        // end step 4

        return ByteBuffer.allocate(16).put(A).put(B).put(C).put(D).array();
    }

    private static byte[] round1(byte[] A, byte[] B, byte[] C, byte[] D, byte[] X, int S) {
        int iF = ByteBuffer.wrap(F(B, C, D)).getInt();
        int iA = ByteBuffer.wrap(A).getInt();
        int iX = ByteBuffer.wrap(X).getInt();

        iA = Integer.rotateLeft(iA + iF + iX, S);

        return ByteBuffer.allocate(4).putInt(iA).array();
    }
    private static byte[] round2(byte[] A, byte[] B, byte[] C, byte[] D, byte[] X, int S) {
        int iG = ByteBuffer.wrap(G(B, C, D)).getInt();
        int iA = ByteBuffer.wrap(A).getInt();
        int iX = ByteBuffer.wrap(X).getInt();

        iA = Integer.rotateLeft(iA + iG + iX + 0x5a827999, S);

        return ByteBuffer.allocate(4).putInt(iA).array();
    }
    private static byte[] round3(byte[] A, byte[] B, byte[] C, byte[] D, byte[] X, int S) {
        int iH = ByteBuffer.wrap(H(B, C, D)).getInt();
        int iA = ByteBuffer.wrap(A).getInt();
        int iX = ByteBuffer.wrap(X).getInt();

        iA = Integer.rotateLeft(iA + iH + iX + 0x6ed9eba1, S);

        return ByteBuffer.allocate(4).putInt(iA).array();
    }

    private static byte[] F(byte[] X, byte[] Y, byte[] Z) {
        int iX = ByteBuffer.wrap(X).getInt();
        int iY = ByteBuffer.wrap(Y).getInt();
        int iZ = ByteBuffer.wrap(Z).getInt();

        int iF = ((iX&iY)|((~iX)&iZ));

        return ByteBuffer.allocate(4).putInt(iF).array();
    }
    private static byte[] G(byte[] X, byte[] Y, byte[] Z) {
        int iX = ByteBuffer.wrap(X).getInt();
        int iY = ByteBuffer.wrap(Y).getInt();
        int iZ = ByteBuffer.wrap(Z).getInt();

        int iG = ((iX&iY)|(iX&iZ)|(iY&iZ));

        return ByteBuffer.allocate(4).putInt(iG).array();
    }
    private static byte[] H(byte[] X, byte[] Y, byte[] Z) {
        int iX = ByteBuffer.wrap(X).getInt();
        int iY = ByteBuffer.wrap(Y).getInt();
        int iZ = ByteBuffer.wrap(Z).getInt();

        int iH = (iX^iY^iZ);

        return ByteBuffer.allocate(4).putInt(iH).array();
    }

    @Override
    public byte[] getHASH(byte[] bytes) {
        return MD4(bytes);
    }
}
