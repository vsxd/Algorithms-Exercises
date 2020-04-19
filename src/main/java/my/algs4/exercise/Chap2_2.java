package my.algs4.exercise;

public class Chap2_2 {
}


class MergeSort extends SortBase {
    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        aux = new Comparable[a.length];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) {
            // 4 conditions:
            // left half exhausted, take from the right
            // right half exhausted, take from thee left
            // right less than left
            // right greater than or equal to left
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}