package com.EREMEI.BinaryTrees;

import java.nio.ByteBuffer;
import java.util.*;

public class MyBinarySearchTree<T> implements List<T> {
    private HASHER hashFunc = new MD4HASH();

    private List<byte[]> HASHList = new ArrayList<>(Integer.MAX_VALUE);
    //private byte[][] HASHList = new byte[Integer.MAX_VALUE][16];

    private MyNode<T> root;

    private int startIndex = 0;
    private int endIndex = 0;

    // search parent for key-defined element
    private MyNode<T> searchParentOf(byte[] id) {
        MyNode<T> parent = this.root;
        while (true) {
            if ((parent == null) || (parent.equalsID(id)))
                break;
            parent = parent.getChildDirect(id);
        }
        return parent.getParent();
    }

    private int absIndex(int relIndex) {
        return relIndex + this.startIndex;
    }

    public MyBinarySearchTree() {

    }

    @Override
    public int size() {
        return endIndex - startIndex;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        add(size(), t);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int i) {
        if ((i < 0) || (i >= size()))
            throw new ArrayIndexOutOfBoundsException();

        i = absIndex(i);

        byte[] id = HASHList.get(i);

        return searchParentOf(id).getChildDirect(id).getStored();
    }

    @Override
    public T set(int i, T t) {
        if ((i < 0) || (i >= size()))
            throw new ArrayIndexOutOfBoundsException();

        i = absIndex(i);

        byte[] id = HASHList.get(i);

        MyNode<T> node = searchParentOf(id).getChildDirect(id);
        T oldValue = node.getStored();
        node.setStored(t);

        return oldValue;
    }

    @Override
    public void add(int i, T t) {
        if ((i < 0) || (i > size()))
            throw new ArrayIndexOutOfBoundsException();

        i = absIndex(i);

        byte[] id = hashFunc.getHASH(ByteBuffer.allocate(4).putInt(i).array());
        HASHList.add(i, id);

        if (root == null)
            root = new MyNode<>(id, t);
        else
            searchParentOf(id).connectChildDirect(new MyNode<>(id, t));

        endIndex++;
    }

    @Override
    public T remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
    public List<T> subList(int i, int i1) {
        return null;
    }
}
