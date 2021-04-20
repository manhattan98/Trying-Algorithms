package com.EREMEI.Trees;

public class MyNode23<T> {
    private MyNode23<T> parent;

    private MyNode23<T> descLeft;
    private MyNode23<T> descMiddle;
    private MyNode23<T> descRight;

    private int keyLeft = -1;
    private int keyRight = -1;

    private T storedLeft;
    private T storedRight;

    public MyNode23<T> getParent() {
        return this.parent;
    }
    public MyNode23<T> getDescendantLeft() {
        return this.descLeft;
    }
    public MyNode23<T> getDescendantMiddle() {
        return this.descMiddle;
    }
    public MyNode23<T> getDescendantRight() {
        return this.descRight;
    }

    /**
     * Return a descendant depending on given key.
     * @param key
     * @return node or null, if no descendants associated with key
     */
    public MyNode23<T> getDescendant(int key) {
        if (key < keyLeft)
            return descLeft;
        else if ((key > keyLeft) && ((key < keyRight) || (keyRight == -1)))
            return descMiddle;
        else
            return descRight;
    }

    public MyNode23<T> getRoot() {
        MyNode23 root = this;
        while (root.parent != null)
            root = root.parent;
        return root;
    }

    public T getStoredLeft() {
        return storedLeft;
    }
    public T getStoredRight() {
        return storedRight;
    }

    public int getKeyLeft() {
        return this.keyLeft;
    }
    public int getKeyRight() {
        return this.keyRight;
    }

    /**
     * Get access to value associated with given identifier.
     * Throws runtime exception if isn't contains given key
     * @param key given identifier
     * @return associated object
     */
    public T getValue(int key) {
        if (!contains(key)) throw new RuntimeException("Node isn't contains key " + key);
        return (key == keyLeft) ? storedLeft : storedRight;
    }

    public boolean setValue(int key, T obj) {
        if (!contains(key)) return false;

        if (key == keyLeft)
            storedLeft = obj;
        else
            storedRight = obj;

        return true;
    }

    public void putValueLeft(int key, T obj) {
        this.keyLeft = key;
        this.storedLeft = obj;
    }
    public void putValueRight(int key, T obj) {
        this.keyRight = key;
        this.storedRight = obj;
    }

    public void connectNodeLeft(MyNode23<T> desc) {
        this.descLeft = desc;
        desc.parent = this;
    }
    public void connectNodeMiddle(MyNode23<T> desc) {
        this.descMiddle = desc;
        desc.parent = this;
    }
    public void connectNodeRight(MyNode23<T> desc) {
        this.descRight = desc;
        desc.parent = this;
    }

    /**
     * Puts value into node if it's not full
     * @param key unique identifier
     * @param obj object
     * @return false if node is full, else return true
     */
    public boolean putValue(int key, T obj) {
        if (isFull()) return false;

        // if empty
        if (keyLeft == -1)
            putValueLeft(key, obj);
        // and other invariants
        else if (key < keyLeft) {
            putValueRight(keyLeft, storedLeft);
            putValueLeft(key, obj);
        } else
            putValueRight(key, obj);

        return true;
    }

    public boolean isFull() {
        return ((keyLeft != -1) && (keyRight != -1));
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean contains(int key) {
        return ((key == keyLeft) || (key == keyRight));
    }

}