package learn;


import java.util.Arrays;
import java.util.Random;

public class Sort {
}

class SortBase {
    boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    void exchange(Comparable[] ar, int i, int j) {
        Comparable temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }
}

class BasicSort extends SortBase {
    void selectionSort(Comparable[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exchange(a, min, i);
        }
    }

    void insertionSort(Comparable[] a) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            Comparable temp = a[i];
            int j = i;
            for (; j > lo && less(temp, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp; // not a[j-1], because of the "j--" in for()
        }
    }

    void shellSort(Comparable[] a) {
        int len = a.length;
        int h = 1;
        while (h < len / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = 1; i < len; i++) {
                for (int j = i; j > 0 && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}

class MergeSort extends SortBase {
    Comparable[] aux;

    private void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public void sortBottomUp(Comparable[] a) {
        int len = a.length;

        for (int sz = 1; sz < len; sz = sz + sz) {
            for (int lo = 0; lo < len - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(len - 1, lo + sz + sz - 1));
            }
        }
    }
}

class QuickSort extends SortBase {
    private void shuffle(Comparable[] a) {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n - i);     // between i and n-1
            Comparable temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        if (lo < hi + 10) new BasicSort().insertionSort(a, lo, hi); // improvement: cutoff to insertion sort

        // improvement: median of three
        int median = hi + (lo - hi) / 2;
        if (less(a[median], a[lo])) exchange(a, median, lo); // sort 3 elements
        if (less(a[hi], a[lo])) exchange(a, hi, lo);
        if (less(a[hi], a[median])) exchange(a, hi, median);
        exchange(a, lo, median); // move median to lo, partition() will use a[lo] to partition

        int mid = partition(a, lo, hi);
        sort(a, lo, mid - 1);
        sort(a, mid + 1, hi);
    }

    private void sort3way(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exchange(a, lt++, i++);
            } else if (cmp > 0) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }
        sort3way(a, lo, lt - 1);
        sort3way(a, gt + 1, hi);
    }

    int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] ar = new Integer[]{6,5,4,3,2,1};
        QuickSort qs = new QuickSort();
        qs.partition(ar, 0, 5);
    }
}

class MaxPQ<Key extends Comparable<Key>> extends SortBase {
    private Key[] pq;
    private int N = 0; //pq[1..N] are used, pq[0] is not

    //    MaxPQ(){
//
//    }
    MaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
    }
//    MaxPQ(Key[] a) {
//
//    }

    private void swim(int k) {
        while (k > 1 && less(pq[k / 2], pq[k])) {
            exchange(pq, k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (k * 2 <= N) {
            int j = 2 * k;
//            if(j >= N) break;
//            if (less(pq[k], pq[j])) {
//                exchange(pq, k, j);
//                k = j;
//            } else if( less(pq[k], pq[j+1])){
//                exchange(pq, k, j+1);
//                k = j+1;
//            } else {
//                break;
//            }
            if (j < N && less(pq[j + 1], pq[j])) j++;
            if (!less(pq[k], pq[j])) break;
            exchange(pq, j, k);
            k = j;
        }
    }

    void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    Key max() {
        if (N == 0) return null;
        return pq[1];
    }

    Key delMax() {
        Key v = pq[1];
        exchange(pq, 1, N--);
        pq[N + 1] = null; // release ref
        sink(1);
        return v;
    }

    boolean isEmpty() {
        return N == 0;
    }

    int size() {
        return N;
    }

}

class HeapSort {
    private boolean less(Comparable[] a, int i, int j){
        // original: a[1..N], now: a[0..N-1]
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    private void exchange(Comparable[]a, int i, int j){
        // original: a[1..N], now: a[0..N-1]
        Comparable tmp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = tmp;
    }

    private void sink(Comparable[] a, int k, int N) {
        while (k * 2 <= N) {
            int j = k * 2;
            if (j < N && less(a, j, j+1)) j++;
            if (!less(a, k, j)) break;
            exchange(a, k, j);
            k = j;
        }
    }

    public void sort(Comparable[] a) {
        int len = a.length;
        for (int k = len/2; k >= 1; k--)
            sink(a, k, len);
        while (len > 1){
            exchange(a, 1, len--);
            sink(a, 1, len);
        }
    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        Integer[] ar = new Integer[]{6,1,2,4,2,0,3,4};
        hs.sort(ar);
        for(int n : ar){
            System.out.println(n);
        }
    }
}
