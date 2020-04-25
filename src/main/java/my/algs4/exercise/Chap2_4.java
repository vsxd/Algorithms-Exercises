package my.algs4.exercise;

import java.util.LinkedList;

public class Chap2_4 {
}

class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    //    MaxPQ() {
//    }
    MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    MaxPQ(Key[] a) {
        pq = (Key[])new Comparable[a.length];
        for (Key item : a) {
            insert(item);
        }
    }

    public void insert(Key v) {
        if(pq.length == N+1)
            throw new ArrayStoreException();
        pq[N++] = v;
        swim(N);
    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        if(isEmpty())
            throw new ArrayStoreException();
        Key v = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return v;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void swim(int k) {
        while (k > 1 && less(parent(k), k)) {
            exch(parent(k), k);
            k = parent(k);
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void sink_recursion(int k) {
        if (2*k > N) return;
        int j = 2*k;
        if (j < N && less(j, j+1)) j++;
        if (!less(k, j)) return;
        exch(k, j);
        sink_recursion(j);
    }


    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private int lchild(int i) {
        return 2 * i;
    }

    private int rchild(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        return i / 2;  // java中int除法为towards-0，正数部分与向下取整一致
    }
}