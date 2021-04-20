package com.EREMEI.HashMaps;

import com.EREMEI.RandomStringGenerator;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyHashMapTest {
    static int CAPACITY;
    static int SIZE;

    MyHashMap<String, String> myHashMap;
    String[][] pairs;

    @BeforeAll
    public static void initAll(TestReporter testReporter) {
        CAPACITY = 100;
        SIZE = 70;

        testReporter.publishEntry("capacity", String.valueOf(CAPACITY));
        testReporter.publishEntry("size", String.valueOf(SIZE));
    }

    @BeforeEach
    public void init() {
        myHashMap = new MyHashMap<>(CAPACITY);
        pairs = new String[SIZE][2];

        for (int i = 0; i < SIZE; i++) {
            pairs[i][0] = RandomStringGenerator.nextString(5, 9);
            pairs[i][1] = RandomStringGenerator.nextString(11, 15);
        }
        for (int i = 0; i < SIZE; i++) {
            myHashMap.put(pairs[i][0], pairs[i][1]);
        }

        //System.out.println("init complete\n" + "load factor: " + myHashMap.loadFactor());
    }

    @Test
    public void size() {
        assertEquals(SIZE, myHashMap.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(!myHashMap.isEmpty());
    }

    @Test
    public void containsKey() {
        for (int i = 0; i < SIZE; i++)
            assertTrue(myHashMap.containsKey(pairs[i][0]));
    }

    @Test
    public void containsValue() {
        for (int i = 0; i < SIZE; i++)
            assertTrue(myHashMap.containsValue(pairs[i][1]));
    }

    @Test
    public void get() {
        for (int i = 0; i < SIZE; i++) {
            assertTrue(pairs[i][1].equals(myHashMap.get(pairs[i][0])));
        }
    }

    @Test
    public void put() {

    }

    @Test
    public void remove() {
        final int rmv1 = 5;
        final int rmv2 = 11;

        assertTrue((rmv1 < SIZE) && (rmv2 < SIZE),
                "removed indexes must not be greater than size");

        assertTrue(pairs[rmv1][1].equals(myHashMap.remove(pairs[rmv1][0])));
        assertTrue(pairs[rmv2][1].equals(myHashMap.remove(pairs[rmv2][0])));

        assertTrue(!myHashMap.containsKey(pairs[rmv1][0]));
        assertTrue(!myHashMap.containsKey(pairs[rmv2][0]));

        assertTrue(!myHashMap.containsValue(pairs[rmv1][1]));
        assertTrue(!myHashMap.containsValue(pairs[rmv2][1]));

        assertEquals(SIZE - 2, myHashMap.size());
    }

    @Test
    public void clear() {
        myHashMap.clear();

        assertTrue(myHashMap.isEmpty());

        for (int i = 0; i < SIZE; i++) {
            assertTrue(!myHashMap.containsKey(pairs[i][0]));
            assertTrue(!myHashMap.containsValue(pairs[i][1]));
        }
    }

    @Test
    public void keySet_1() {
        Collection<String> keys = myHashMap.keySet();

        assertTrue(keys.size() == SIZE);

        for (int i = 0; i < SIZE; i++)
            assertTrue(keys.contains(pairs[i][0]));
    }

    @Test
    public void keySet_2() {
        final int rmv1 = 5;
        final int rmv2 = 11;

        assertTrue((rmv1 < SIZE) && (rmv2 < SIZE),
                "removed indexes must not be greater than size");

        Set<String> keySet = myHashMap.keySet();

        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (key.equals(pairs[rmv1][0])
                    ||key.equals(pairs[rmv2][0]))
                iterator.remove();
        }

        assertTrue(!myHashMap.containsValue(pairs[rmv1][1]));
        assertTrue(!myHashMap.containsKey(pairs[rmv2][0]));

        assertEquals(SIZE - 2, myHashMap.size());
    }

    @Test
    public void values_1() {
        Collection<String> values = myHashMap.values();

        assertTrue(values.size() == SIZE);

        for (int i = 0; i < SIZE; i++)
            assertTrue(values.contains(pairs[i][1]));
    }

    @Test
    public void values_2() {
        final int rmv1 = 5;
        final int rmv2 = 11;

        assertTrue((rmv1 < SIZE) && (rmv2 < SIZE),
                "removed indexes must not be greater than size");

        Collection<String> values = myHashMap.values();

        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if (value.equals(pairs[rmv1][1])
                    ||value.equals(pairs[rmv2][1]))
                iterator.remove();
        }

        assertTrue(!myHashMap.containsValue(pairs[rmv1][1]));
        assertTrue(!myHashMap.containsKey(pairs[rmv2][0]));

        assertEquals(SIZE - 2, myHashMap.size());
    }

    @Test
    public void entrySet_1() {
        Set<Map.Entry<String, String>> entrySet = myHashMap.entrySet();

        assertTrue(entrySet.size() == SIZE);

        for (int i = 0; i < SIZE; i++) {
            Map.Entry<String, String> pair = getEntry(pairs[i][0], pairs[i][1]);

            assertTrue(entrySet.contains(pair));
        }
    }

    @Test
    public void entrySet_2() {
        Set<Map.Entry<String, String>> entrySet = myHashMap.entrySet();

        assertTrue(entrySet.size() == SIZE);

        final int rmv1 = 5;
        final int rmv2 = 11;

        assertTrue((rmv1 < SIZE) && (rmv2 < SIZE),
                "removed indexes must not be greater than size");

        Iterator<Map.Entry<String, String>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> entry = entryIterator.next();
            if (entry.getKey().equals(pairs[rmv1][0])
            || entry.getValue().equals(pairs[rmv2][1]))
                entryIterator.remove();
        }

        assertTrue(!myHashMap.containsValue(pairs[rmv1][1]));
        assertTrue(!myHashMap.containsKey(pairs[rmv2][0]));

        assertEquals(SIZE - 2, myHashMap.size());
    }

    public void displayEntries(Set<Map.Entry<?, ?>> entrySet) {
        for (Map.Entry<?, ?> entry : entrySet)
            System.out.print(entry.getKey() + ":" + entry.getValue() + "; ");
    }

    public static <K, V>Map.Entry getEntry(K key, V value) {
        return new Map.Entry<K, V>() {
            private final K finalKey = key;
            private final V finalValue = value;

            @Override
            public K getKey() {
                return finalKey;
            }
            @Override
            public V getValue() {
                return finalValue;
            }
            @Override
            public V setValue(V value) {
                return null;
            }
            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Map.Entry<?, ?>))
                    return false;

                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) obj;
                return getKey().equals(entry.getKey()) && getValue().equals(entry.getValue());
            }
        };
    }
}
