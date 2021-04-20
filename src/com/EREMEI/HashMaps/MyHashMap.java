package com.EREMEI.HashMaps;

import java.util.*;
import java.util.function.Function;

public class MyHashMap<K, V> implements Map<K, V> {
    private static final int MIN_CAPACITY = 5;

    private K[] keys;
    private V[] values;

    private int capacity;
    private int size;

    private Function<Object, Integer> hashFunction =
            object -> Math.abs(object.hashCode()) % capacity;

    /**
     * double hash probe
     */
    private Function<Object, Integer> doubleHashProbe = key -> {
        final int c = 3;
        int hash = hashFunction.apply(key);
        int step = c - (key.hashCode() % c);

        while (!((isKeyEmpty(hash)) || (isKeyEquals(hash, key)))) {
            hash += step;
            hash %= capacity;
        }

        return hash;
    };

    private boolean isKeyEquals(int hashKey, Object key) {
        return keys[hashKey].equals(key);
    }
    private boolean isKeyEmpty(int hashKey) {
        return keys[hashKey] == null;
    }
    private void setKeyEmpty(int hashKey) {
        keys[hashKey] = null;
    }

    private void checkArg(Object arg) {
        if (arg == null)
            throw new NullPointerException("Null keys does not permitted");
    }

    private Function<Object, Integer> probeFunction = doubleHashProbe;

    private void init() {
        size = 0;

        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    public MyHashMap(int initialCapacity) {
        capacity =
                (initialCapacity < MIN_CAPACITY) ? MIN_CAPACITY : getSimpleCapacity(initialCapacity);

        init();
    }

    public MyHashMap(int initialCapacity, Function<Object, Integer> hashFunction) {
        this(initialCapacity);
        setHashFunction(hashFunction);
    }

    private int getSimpleCapacity(int initialCapacity) {
        while (!isSimple(initialCapacity))
            initialCapacity++;
        return initialCapacity;
    }

    private boolean isSimple(int number) {
        for (int i = 2; i < number / 2; i++)
            if (number % i == 0)
                return false;
        return true;
    }

    public void setHashFunction(Function<Object, Integer> function) {
        this.hashFunction = object -> function.apply(object) % capacity;
    }

    public float loadFactor() {
        return (float) size / (float) capacity;
    }

    //----------------------------------- Map<K,V> interface -----------------------------------

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        checkArg(key);

        for (int hash = 0; hash < capacity; hash++)
            if (!isKeyEmpty(hash) && keys[hash].equals(key))
                return true;

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int hash = 0; hash < capacity; hash++)
            if (!isKeyEmpty(hash) && values[hash].equals(value))
                return true;

        return false;
    }

    @Override
    public V get(Object o) {
        checkArg(o);

        int hash = probeFunction.apply(o);
        return (isKeyEmpty(hash)) ? null : values[hash];
    }

    @Override
    public V put(K k, V v) {
        checkArg(k);

        int hash = probeFunction.apply(k);
        V prev = (isKeyEmpty(hash)) ? null : values[hash];

        keys[hash] = k;
        values[hash] = v;

        size++;
        return prev;
    }

    @Override
    public V remove(Object o) {
        checkArg(o);

        int hash = probeFunction.apply(o);

        if (isKeyEmpty(hash))
            return null;

        V prev = values[hash];
        setKeyEmpty(hash);

        size--;
        return prev;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry<? extends K, ? extends V> entry : map.entrySet())
            this.put(entry.getKey(), entry.getValue());
    }

    @Override
    public void clear() {
        init();
    }

    @Override
    public Set<K> keySet() {
        return new MyAbstractSubSet<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    private boolean resentRemoved = true;

                    private int curIndex = 0;
                    private int[] bckArray = new int[size()];
                    {
                        int i = 0;
                        for (int hash = 0; hash < capacity; hash++)
                            if (!isKeyEmpty(hash))
                                bckArray[i++] = hash;
                    }

                    @Override
                    public boolean hasNext() {
                        return curIndex != bckArray.length;
                    }

                    @Override
                    public K next() {
                        if (curIndex == bckArray.length)
                            throw new NoSuchElementException();

                        resentRemoved = false;

                        return keys[bckArray[curIndex++]];
                    }

                    @Override
                    public void remove() {
                        if (resentRemoved)
                            throw new IllegalStateException();

                        MyHashMap.this.remove(keys[bckArray[curIndex - 1]]);
                        resentRemoved = true;
                    }
                };
            }
        };
    }

    @Override
    public Collection<V> values() {
        return new MyAbstractSubSet<V>() {
            @Override
            public Iterator<V> iterator() {
                return new Iterator<>() {
                    private boolean resentRemoved = true;

                    private int curIndex = 0;
                    private int[] bckArray = new int[size()];
                    {
                        int i = 0;
                        for (int hash = 0; hash < capacity; hash++)
                            if (!isKeyEmpty(hash))
                                bckArray[i++] = hash;
                    }

                    @Override
                    public boolean hasNext() {
                        return curIndex != bckArray.length;
                    }

                    @Override
                    public V next() {
                        if (curIndex == bckArray.length)
                            throw new NoSuchElementException();

                        resentRemoved = false;

                        return values[bckArray[curIndex++]];
                    }

                    @Override
                    public void remove() {
                        if (resentRemoved)
                            throw new IllegalStateException();

                        MyHashMap.this.remove(keys[bckArray[curIndex - 1]]);
                        resentRemoved = true;
                    }
                };
            }
        };
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new MyAbstractSubSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<>() {
                    private boolean resentRemoved = true;

                    private int curIndex = 0;
                    private int[] bckArray = new int[size()];
                    {
                        int i = 0;
                        for (int hash = 0; hash < capacity; hash++)
                            if (!isKeyEmpty(hash))
                                bckArray[i++] = hash;
                    }

                    @Override
                    public boolean hasNext() {
                        return curIndex != bckArray.length;
                    }

                    @Override
                    public Entry<K, V> next() {
                        if (curIndex == bckArray.length)
                            throw new NoSuchElementException();

                        resentRemoved = false;
                        return new Entry<>() {
                            private final int hash = bckArray[curIndex++];

                            @Override
                            public K getKey() {
                                return keys[hash];
                            }
                            @Override
                            public V getValue() {
                                return values[hash];
                            }
                            @Override
                            public V setValue(V v) {
                                return MyHashMap.this.put(keys[bckArray[hash]], v);
                            }
                            @Override
                            public boolean equals(Object obj) {
                                if (!(obj instanceof Entry<?, ?>))
                                    return false;

                                Entry<?, ?> entry = (Entry<?, ?>) obj;
                                return getKey().equals(entry.getKey()) && getValue().equals(entry.getValue());
                            }
                        };
                    }

                    @Override
                    public void remove() {
                        if (resentRemoved)
                            throw new IllegalStateException();

                        MyHashMap.this.remove(keys[bckArray[curIndex - 1]]);
                        resentRemoved = true;
                    }
                };
            }
        };
    }

    private abstract class MyAbstractSubSet<T> implements Set<T> {
        @Override
        public int size() {
            return MyHashMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return MyHashMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            for (T element : this)
                if (element.equals(o))
                    return true;
            return false;
        }

        @Override
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        @Override
        public <T1> T1[] toArray(T1[] t1s) {
            if (t1s.length != size())
                t1s = Arrays.copyOf(t1s, size());

            int i = 0;
            for (T element : this)
                t1s[i++] = (T1)element;

            return t1s;
        }

        @Override
        public boolean add(T t) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            Iterator<T> iterator = iterator();
            while (iterator.hasNext()) {
                T e = iterator.next();
                if ((o == null) ? e == null : e.equals(o)) {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object obj : collection)
                if (!this.contains(obj))
                    return false;
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends T> collection) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            boolean isChanged = false;
            for (Object obj : collection)
                isChanged = remove(obj) || isChanged;
            return isChanged;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            boolean isChanged = false;
            Iterator<T> iterator = iterator();
            while (iterator.hasNext())
                if (!contains(iterator.next())) {
                    iterator.remove();
                    isChanged = true;
                }
            return isChanged;
        }

        @Override
        public void clear() {
            MyHashMap.this.clear();
        }
    }
}
