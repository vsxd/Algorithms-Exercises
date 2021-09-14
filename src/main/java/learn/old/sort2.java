package learn.old;

@SuppressWarnings("rawtypes")
public class sort2 {

    private static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void insertionSort(Comparable[] a) {  // Sort a[] into increasing order.
        int N = a.length;
        for (int i = 1; i < N; i++) {  // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }


}

class heapsort {
    public int findKthLargest(int[] nums, int k) {
        return 0;
    }

    private void swim(int[] nums, int k){
        while (k > 0 && less(nums, k/2, k)) {
            swap(nums, k/2, k);
            k = k/2;
        }
    }

    private void sink(int[] nums, int k){
        while (k*2 < nums.length) {
            int j = k*2;
            if (j < nums.length-1 && less(nums, j, j+1)) j++;
            if (!less(nums, k,j)) break;
            swap(nums, k,j);
            k = j;
        }
    }

    private boolean less(int[] nums, int a , int b){
        return nums[a] < nums[b];
    }
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}