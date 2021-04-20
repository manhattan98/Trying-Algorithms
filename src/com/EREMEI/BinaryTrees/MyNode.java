package com.EREMEI.BinaryTrees;

import java.math.BigInteger;

public class MyNode<T> {
    private MyNode<T> leftChild;
    private MyNode<T> rightChild;

    private MyNode<T> parent;

    private T storedObject;
    private byte[] id;

    public MyNode(byte[] id, T obj) {
        setIDStored(id, obj);
    }
    public MyNode() {}

    public MyNode<T> getLeftChild() {
        return this.leftChild;
    }
    public MyNode<T> getRightChild() {
        return this.rightChild;
    }

    public MyNode<T> getChildDirect(byte[] id) {
        return this.compareToID(id) > 0 ? this.leftChild : this.rightChild;
    }
    public MyNode<T> getChildReverse(byte[] id) {
        return this.compareToID(id) < 0 ? this.leftChild : this.rightChild;
    }

    public MyNode<T> getParent() {
        return this.parent;
    }

    public MyNode<T> getRoot() {
        MyNode<T> root = this;
        while(root.parent != null)
            root = root.parent;

        return root;
    }

    public MyNode<T> getLeftmostNode() {
        MyNode<T> leftmost = this;
        while (leftmost.leftChild != null)
            leftmost = leftmost.leftChild;

        return leftmost;
    }
    public MyNode<T> getRightmostNode() {
        MyNode<T> rightmost = this;
        while (rightmost.rightChild != null)
            rightmost = rightmost.rightChild;

        return rightmost;
    }

    public void connectLeftChild(MyNode<T> node) {
        this.leftChild = node;
        node.parent = this;
    }
    public void connectRightChild(MyNode<T> node) {
        this.rightChild = node;
        node.parent = this;
    }

    // auto connect depend on key, left if connected node key lower than this, and so on
    public void connectChildDirect(MyNode<T> node) {
        if (this.compareToID(node.id) > 0) connectLeftChild(node);
        else connectRightChild(node);
    }
    public void connectChildReverse(MyNode<T> node) {
        if (this.compareToID(node.id) < 0) connectLeftChild(node);
        else connectRightChild(node);
    }

    public byte[] getID() {
        return this.id;
    }
    public T getStored() {
        return this.storedObject;
    }

    public void setStored(T obj) {
        this.storedObject = obj;
    }
    public void setIDStored(byte[] id, T obj) {
        this.id = id;
        this.storedObject = obj;
    }

    public boolean isLeaf() {
        return (leftChild == null) && (rightChild == null);
    }
    public boolean isRoot() {
        return parent == null;
    }

    public boolean equalsID(byte[] id) {
        return this.compareToID(id) == 0;
    }
    public int compareToID(byte[] id) {
        BigInteger thisKey = new BigInteger(this.id);
        BigInteger addKey = new BigInteger(id);

        return thisKey.compareTo(addKey);
    }
}
