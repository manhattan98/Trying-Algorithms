package com.EREMEI.Trees;

import java.util.*;
import java.util.function.Function;

public class MyTree23<T> implements Set<T> {
    private MyNode23<T> root;

    private int startIndex;
    private int endIndex;

    private int absIndex(int relIndex) {
        return relIndex + this.startIndex;
    }

    /**
     * Return node that contains given key or potential parent node
     * Returned node always is a leaf!
     * @param key given identifier
     * @return node if found one that contains associated value or parent node if doesn't
     */
    private MyNode23<T> searchNode(int key) {
        MyNode23<T> currentNode = root;
        while (true) {
            MyNode23<T> descendant = currentNode.getDescendant(key);
            if ((currentNode.contains(key)) || (descendant == null))
                break;
            currentNode = descendant;
        }
        return currentNode;
    }

    public MyNode23<T> getRoot() {
        return this.root;
    }

    public MyTree23() {
        init();
    }

    private void init() {
        root = new MyNode23<>();
        startIndex = 0;
        endIndex = 0;
    }

    //---------------------------------------List interface---------------------------------------
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            private T[] backArray;
            {
                backArray = (T[]) (new Object[size()]);
                toArray(backArray);
            }

            @Override
            public boolean hasNext() {
                return currentIndex == backArray.length;
            }

            @Override
            public T next() {
                return backArray[currentIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        if (t1s.length != size())
            t1s = Arrays.copyOf(t1s, size());

        Function<T1[], T1[]> function = new Function<>() {
            private int curPos = 0;

            private void trace(MyNode23 node, T1[] destArray) {
                if (node.getDescendantLeft() != null)
                    trace(node.getDescendantLeft(), destArray);

                if (node.getKeyLeft() != -1)
                    destArray[curPos++] = (T1) node.getStoredLeft();

                if (node.getDescendantMiddle() != null)
                    trace(node.getDescendantMiddle(), destArray);

                if (node.getKeyRight() != -1)
                    destArray[curPos++] = (T1) node.getStoredRight();

                if (node.getDescendantRight() != null)
                    trace(node.getDescendantRight(), destArray);
            }

            @Override
            public T1[] apply(T1[] objects) {
                trace(root, objects);
                return objects;
            }
        };

        return function.apply(t1s);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
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
        init();
    }

    public T get(int i) {
        if ((i < 0) || (i >= size()))
            throw new IndexOutOfBoundsException();

        int key = absIndex(i);
        MyNode23<T> node = searchNode(key);

        return node.getValue(key);
    }

    public T set(int i, T t) {
        if ((i < 0) || (i >= size()))
            throw new IndexOutOfBoundsException();

        int key = absIndex(i);
        MyNode23<T> node = searchNode(key);
        T prev = node.getValue(key);
        node.setValue(key, t);

        return prev;
    }

    public int traceCountTo(int key) {
        if ((key < startIndex) || (key >= endIndex))
            throw new IndexOutOfBoundsException();

        int count = 0;
        MyNode23<T> currentNode = root;
        while (true) {
            count++;
            if (currentNode.contains(key))
                break;
            currentNode = currentNode.getDescendant(key);
        }
        return count;
    }

    @Override
    public boolean add(T t) {
        int key = size();
        MyNode23<T> nodeCurrent = searchNode(key);
        MyNode23<T> nodePrev = null;

        while (true) {
            if (nodeCurrent.isFull()) {
                MyNode23<T> nodeLeft = new MyNode23<>();
                MyNode23<T> nodeRight = new MyNode23<>();

                // node fragmentation to two nodes
                if (key < nodeCurrent.getKeyLeft()) {
                    nodeLeft.putValueLeft(key, t);
                    nodeRight.putValueLeft(nodeCurrent.getKeyRight(), nodeCurrent.getStoredRight());
                    key = nodeCurrent.getKeyLeft();
                    t = nodeCurrent.getStoredLeft();
                }
                else if ((key > nodeCurrent.getKeyLeft()) && (key < nodeCurrent.getKeyRight())) {
                    nodeLeft.putValueLeft(nodeCurrent.getKeyLeft(), nodeCurrent.getStoredLeft());
                    nodeRight.putValueLeft(nodeCurrent.getKeyRight(), nodeCurrent.getStoredRight());
                }
                else {
                    nodeLeft.putValueLeft(nodeCurrent.getKeyLeft(), nodeCurrent.getStoredLeft());
                    nodeRight.putValueLeft(key, t);
                    key = nodeCurrent.getKeyRight();
                    t = nodeCurrent.getStoredRight();
                }

                // reconnect
                if (nodePrev != null) {
                    if (nodePrev == nodeCurrent.getDescendantLeft()) {
                        nodeLeft.connectNodeLeft(nodePrev.getDescendantLeft());
                        nodeLeft.connectNodeMiddle(nodePrev.getDescendantMiddle());
                        nodeRight.connectNodeLeft(nodeCurrent.getDescendantMiddle());
                        nodeRight.connectNodeMiddle(nodeCurrent.getDescendantRight());
                    }
                    if (nodePrev == nodeCurrent.getDescendantMiddle()) {
                        nodeLeft.connectNodeLeft(nodeCurrent.getDescendantLeft());
                        nodeLeft.connectNodeMiddle(nodePrev.getDescendantLeft());
                        nodeRight.connectNodeLeft(nodePrev.getDescendantMiddle());
                        nodeRight.connectNodeMiddle(nodeCurrent.getDescendantRight());
                    }
                    if (nodePrev == nodeCurrent.getDescendantRight()) {
                        nodeLeft.connectNodeLeft(nodeCurrent.getDescendantLeft());
                        nodeLeft.connectNodeMiddle(nodeCurrent.getDescendantMiddle());
                        nodeRight.connectNodeLeft(nodePrev.getDescendantLeft());
                        nodeRight.connectNodeMiddle(nodePrev.getDescendantMiddle());
                    }
                }

                // temporary connect to access at next step
                nodeCurrent.connectNodeLeft(nodeLeft);
                nodeCurrent.connectNodeMiddle(nodeRight);

                if (nodeCurrent.isRoot()) {
                    root = new MyNode23<>();
                    root.putValueLeft(key, t);
                    root.connectNodeLeft(nodeLeft);
                    root.connectNodeMiddle(nodeRight);

                    break;
                }
                else {
                    nodePrev = nodeCurrent;
                    nodeCurrent = nodeCurrent.getParent();
                }
            }
            // node is not full
            else {
                nodeCurrent.putValue(key, t);

                // reconnect
                if (nodePrev != null) {
                    if (nodePrev == nodeCurrent.getDescendantLeft()) {
                        nodeCurrent.connectNodeRight(nodeCurrent.getDescendantMiddle());
                        nodeCurrent.connectNodeLeft(nodePrev.getDescendantLeft());
                        nodeCurrent.connectNodeMiddle(nodePrev.getDescendantMiddle());
                    }
                    if (nodePrev == nodeCurrent.getDescendantMiddle()) {
                        nodeCurrent.connectNodeMiddle(nodePrev.getDescendantLeft());
                        nodeCurrent.connectNodeRight(nodePrev.getDescendantMiddle());
                    }
                }

                break;
            }
        }
        endIndex++;
        return true;
    }
}