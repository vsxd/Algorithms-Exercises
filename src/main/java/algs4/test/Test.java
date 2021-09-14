package algs4.test;

public class Test {
    public static void main(String[] args) {
        System.out.println("abc");
    }
}

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PQ pq = new PQ(nums.length);
        for (int num : nums){
            pq.add(num);
        }

        for (int i = 0; i < k-1; i++){
            pq.popMax();
        }
        return pq.popMax();
    }
}

class PQ{
    private int[] ar;
    private int len;

    PQ(int len) {
        ar = new int[len+1];
        len = 0;
    }

    public void add(int x){
        ar[++len] = x;
        swim(len);
    }

    public int popMax(){
        int max = ar[1];
        swap(1, len--);
        sink(1);
        return max;
    }

    private void swim(int k){
        while (k > 1 && less(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= len){
            int j = 2*k;
            if (j < len && less(j, j+1)) j++;
            if (!less(k , j)) break;
            swap(k, j);
            k = j;

        }
    }

    private boolean less(int a , int b){
        return ar[a] < ar[b];
    }

    private void swap(int a, int b){
        int temp = ar[a];
        ar[a] = ar[b];
        ar[b] = temp;
    }
}