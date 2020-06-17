package my.algs4.exercise;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chap3_1 {
}

class ST<Key extends Comparable<Key>, Value> {
    class Pair {
        Key key;
        Value val;

        Pair(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private List<Pair> table;

    ST() {
        table = new ArrayList<>();
    }

    void put(Key key, Value val) {
        table.add(new Pair(key, val));
    }

    Value get(Key key) {
        for (Pair item : table) {
            if (item.key.compareTo(key) == 0) {
                return item.val;
            }
        }
        return null;
    }

    void delete(Key key) {
        put(key, null);
    }

    boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return table.size();
    }

    private class STIter implements Iterator<Key> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return table.size() > cursor;
        }

        @Override
        public Key next() {
            Key result = table.get(cursor).key;
            cursor++;
            return result;
        }

        @Override
        public void remove() {
            delete(table.get(cursor - 1).key);
        }
    }

    Iterable<Key> keys() {
        return STIter::new;
    }

}

class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);   // key-length cutoff
        ST<String, Integer> st = new ST<String, Integer>();
        while (!StdIn.isEmpty()) {  // Build symbol table and count frequencies.
            String word = StdIn.readString();
            if (word.length() < minlen) continue;  // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }       // Find a key with the highest frequency count.

        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max)) max = word;
        StdOut.println(max + " " + st.get(max));
    }
}

class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {   // See Algorithm 1.1 for standard array-resizing code.
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && i >= 0 && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    private int rank_recursive(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = (lo + hi) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp > 0) return rank_recursive(key, mid + 1, hi);
        else if (cmp < 0) return rank_recursive(key, lo, mid - 1);
        else return mid;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) lo = mid + 1;
            else if (cmp < 0) hi = mid - 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {  // Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {   // See Exercise 3.1.16 for this method.
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            for (int j = i; j < N - 1; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            N--;
        }
    }
    boolean contains(Key key) {
        int i = rank(key);
        return keys[i].compareTo(key) == 0;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) q.enqueue(keys[i]);
        if (contains(hi)) q.enqueue(keys[rank(hi)]);
        return q;
    }
}