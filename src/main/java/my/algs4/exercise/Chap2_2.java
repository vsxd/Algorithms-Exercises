package my.algs4.exercise;

import edu.princeton.cs.algs4.Insertion;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Chap2_2 {
    public static void main(String[] args) {
        reverse(123);
    }
    public static int reverse(int x) {
        if(x==Integer.MIN_VALUE)
            return 0;
        boolean neg = false;
        if(x<0){
            neg = true;
            x = -x;
        }
        String str = Integer.toString(x);
        StringBuilder sb = new StringBuilder();
        for (int i = str.length()-1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        int result = 0;
        try {
            result = Integer.parseInt(sb.toString());
        } catch (NumberFormatException e){
            return 0;
        }

        if(neg) return -result;
        return result;
    }


}


class MergeSort extends SortBase {
    private static Comparable[] aux;

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        // if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            // 4 conditions:
            // left half exhausted, take from the right
            // right half exhausted, take from thee left
            // right less than left
            // right greater than or equal to left
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {

        // improve: if array is too small, use insertion sort
        if (hi - lo <= 15) {
            Insertion.sort(a, lo, hi);
            return;
        }

        // merge sort
        if (hi <= lo) // must use <= or >=
            return;
        int mid = (lo + hi) / 2;

        sort(a, lo, mid); // recursive
        sort(a, mid + 1, hi);

        // improve: if two subarrays is already in order
        if (less(a[mid], a[mid + 1]))
            return; // skip merge

        merge(a, lo, mid, hi);
    }

    public static void sortBottomUp(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {  // sz: subarray size
            for (int lo = 0; lo < N - sz; lo += sz + sz) {  // lo: subarray index
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }

    }
}