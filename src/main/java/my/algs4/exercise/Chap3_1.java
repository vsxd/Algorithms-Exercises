package my.algs4.exercise;

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
    private class STIter implements Iterator<Key>{
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
            delete(table.get(cursor-1).key);
        }
    }

    Iterable<Key> keys(){
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