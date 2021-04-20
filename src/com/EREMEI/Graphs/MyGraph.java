package com.EREMEI.Graphs;

import java.util.*;

public class MyGraph<T> {
    private class MyGraphFactory {
        public <E>List<E> getList() {
            return new LinkedList<>();
        }
        public <E>Queue<E> getQueue() {
            return new ArrayDeque<>();
        }
        public <E>Stack<E> getStack() {
            return new Stack<>();
        }
    }

    private MyGraphFactory FACTORY = new MyGraphFactory();

    private List<Entry<T>> entryList = FACTORY.getList();

    private List<T> FS(int start, Staque<Entry<T>> staque) {
        List<T> bckList = FACTORY.getList();

        Entry<T> currentEntry = entryList.get(start);
        staque.insert(currentEntry);
        currentEntry.visited = true;
        bckList.add(currentEntry.stored);

        while (!staque.isEmpty()) {
            currentEntry = staque.examine();

            Entry<T> nextEntry = currentEntry.nextUnvisitedLink();
            if (nextEntry != null) {
                staque.insert(nextEntry);
                nextEntry.visited = true;
                bckList.add(nextEntry.stored);
            }
            else
                staque.remove();
        }

        for (Entry<T> entry : entryList)
            entry.visited = false;

        return bckList;
    }

    private void DFS(List<T> bckList) {
        Stack<Entry<T>> stack = FACTORY.getStack();
        stack.push(entryList.get(0));

        while (!stack.isEmpty()) {
            Entry<T> currentEntry = stack.peek();
            currentEntry.visited = true;

            bckList.add(currentEntry.stored);

            Entry<T> nextEntry = currentEntry.nextUnvisitedLink();
            if (nextEntry != null)
                stack.push(nextEntry);
            else
                stack.pop();
        }

        for (Entry<T> entry : entryList)
            entry.visited = false;
    }

    private void BFS(List<T> bckList) {
        Queue<Entry<T>> queue = FACTORY.getQueue();
        queue.add(entryList.get(0));

        while (!queue.isEmpty()) {
            Entry<T> currentEntry = queue.peek();
            currentEntry.visited = true;

            bckList.add(currentEntry.stored);

            Entry<T> nextEntry = currentEntry.nextUnvisitedLink();
            if (nextEntry != null)
                queue.add(nextEntry);
            else
                queue.remove();
        }

        for (Entry<T> entry : entryList)
            entry.visited = false;
    }

    public void addEntry(T obj) {
        Entry<T> newEntry = new Entry<>(obj);
        entryList.add(newEntry);
    }

    public void linkEntries(int first, int second) {
        Entry<T> firstEntry = entryList.get(first);
        Entry<T> secondEntry = entryList.get(second);

        firstEntry.links.add(secondEntry);
        secondEntry.links.add(firstEntry);
    }

    public void linkEntries(T first, T second) {
        int firstEntry = entryList.indexOf(new Entry<>(first));
        int secondEntry = entryList.indexOf(new Entry<>(second));

        linkEntries(firstEntry, secondEntry);
    }

    public void linkEntriesDirect(int first, int second) {
        Entry<T> firstEntry = entryList.get(first);
        Entry<T> secondEntry = entryList.get(second);

        firstEntry.links.add(secondEntry);
    }

    public void linkEntriesDirect(T first, T second) {
        int firstEntry = entryList.indexOf(new Entry<>(first));
        int secondEntry = entryList.indexOf(new Entry<>(second));

        linkEntriesDirect(firstEntry, secondEntry);
    }


        public Iterator<T> DFSIterator() {
        List<T> bckList = FS(0, new Staque<Entry<T>>() {
            private Stack<Entry<T>> stack = FACTORY.getStack();

            @Override
            public Entry<T> examine() {
                return stack.peek();
            }
            @Override
            public Entry<T> remove() {
                return stack.pop();
            }
            @Override
            public void insert(Entry<T> tEntry) {
                stack.push(tEntry);
            }
            @Override
            public boolean isEmpty() {
                return stack.isEmpty();
            }
        });

        return bckList.iterator();
    }

    public Iterator<T> BFSIterator() {
        List<T> bckList = FS(0, new Staque<Entry<T>>() {
            private Queue<Entry<T>> queue = FACTORY.getQueue();

            @Override
            public Entry<T> examine() {
                return queue.element();
            }
            @Override
            public Entry<T> remove() {
                return queue.remove();
            }
            @Override
            public void insert(Entry<T> tEntry) {
                queue.add(tEntry);
            }
            @Override
            public boolean isEmpty() {
                return queue.isEmpty();
            }
        });

        return bckList.iterator();
    }

    public MyGraph<T> mst() {
        MyGraph<T> mst = new MyGraph<>();
        Iterator<T> dfs = this.DFSIterator();

        int i = 0;
        mst.addEntry(dfs.next());
        while (dfs.hasNext()) {
            mst.addEntry(dfs.next());
            mst.linkEntriesDirect(i, ++i);
        }

        return mst;
    }

    @Override
    public String toString() {
        String graph = "";

        for (Entry<T> entry : entryList)
            for (Entry<T> neighbor : entry.links)
                graph += (entry.stored.toString() + " -> " + neighbor.stored.toString() + "\n");

        return graph.substring(0, graph.length() - 1);
    }

    public class Entry<T> {
        private List<Entry<T>> links;

        private T stored;
        private boolean visited;

        public Entry(T obj) {
            this.links = FACTORY.getList();

            this.stored = obj;
            this.visited = false;
        }

        private Entry<T> nextUnvisitedLink() {
            for (Entry<T> entry : links)
                if (!entry.visited)
                    return entry;
            return null;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Entry))
                return false;

            Entry<?> entry = (Entry) obj;
            return this.stored.equals(entry.stored);
        }
    }

    private interface Staque<T> {
        public T examine();
        public T remove();
        public void insert(T t);
        public boolean isEmpty();
    }
}
