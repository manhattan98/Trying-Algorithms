package com.EREMEI.Arrays;

public class LowArrayTest implements Runnable {
    @Override
    public void run() {
        System.out.println("Testing LowArray...");

        LowArray<String> array = new LowArray<>(1);

        //array.logger.enableLogging();

        array.add("hello");
        array.add("world");
        array.add("my");
        array.add("nickname");
        array.add("is");
        array.add("manhattan98");
        array.add("or");
        array.add("PunishedManhattan");

        array.remove(0);
        array.remove(1);
        array.remove(5);

        for (int i = 0; i < array.size(); i++)
            System.out.println(array.get(i));
    }
}
