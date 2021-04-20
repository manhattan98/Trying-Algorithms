package com.EREMEI;

import java.util.Random;

public class RandomStringGenerator {
    public static String nextString(int minLength, int maxLength) {
        if (minLength > maxLength)
            throw new RuntimeException("minLength can not be greater than maxLength\n" +
                    "minLength = " + minLength + ", maxLength = " + maxLength);

        Random rand = new Random();
        final int length = rand.nextInt(maxLength - minLength + 1) + minLength;
        char[] chars = new char[length];
        for (int i = 0; i < length; i++)
            chars[i] = (char) ('a' + rand.nextInt(26));

        return String.valueOf(chars);
    }
}