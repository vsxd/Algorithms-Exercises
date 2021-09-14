package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Design {
    public static void main(String[] args) {

    }
}

class LRUCacheSimple extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCacheSimple(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return this.size() > capacity;
    }
}

class MyLRUCache {
    private Map<Integer, Node> map;
    private DLinkedList list;
    private int cap;

    MyLRUCache(int capacity){
        map = new HashMap<>();
        list = new DLinkedList();
        cap = capacity;
    }

    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DLinkedList{
        Node head, tail;

        DLinkedList(){
            head = new Node(0,0);
            tail = new Node(0,0);

            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node){
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        public int delete(Node node){
            int k = node.key;
            node.next.prev = node.prev;
            node.prev.next = node.next;

            return k;
        }

        public int deleteLast(){
            if (head.next == tail)
                return -1;
            return delete(tail.prev);
        }
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node n = map.get(key);
        put(n.key, n.value);
        return n.value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)){
            list.delete(map.get(key));
            list.addFirst(node);
            map.put(key, node);
        } else {
            if (map.size() == cap){
                int delKey = list.deleteLast();
                map.remove(delKey);
            }
            map.put(key, node);
            list.addFirst(node);
        }
    }
}


class LRUCache_tmpl {
    class Node{
        public int key;
        public int val;
        public Node prev;
        public Node next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    class DoubleLinkedList{

        Node head;
        Node tail;

        public DoubleLinkedList(){
            head = new Node(0,0);
            tail = new Node(0,0);

            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node){

            node.next   = head.next;
            node.prev   = head;

            head.next.prev = node;
            head.next      = node;
        }

        public int delete(Node n){
            int key = n.key;
            n.next.prev = n.prev;
            n.prev.next = n.next;

            return key;
        }

        public int deleteLast(){
            if(head.next == tail)   return -1;

            return delete(tail.prev);
        }
    }

    HashMap<Integer, Node> map;
    DoubleLinkedList cache;
    int cap;
    public LRUCache_tmpl(int capacity){
        map   = new HashMap<>();
        cache = new DoubleLinkedList();
        cap   = capacity;
    }

    public void put(int key, int val){
        Node newNode = new Node(key, val);

        if(map.containsKey(key)){
            cache.delete(map.get(key));
            cache.addFirst(newNode);
            map.put(key, newNode);
        }else{
            if(map.size() == cap){
                int k = cache.deleteLast();
                map.remove(k);
            }
            cache.addFirst(newNode);
            map.put(key, newNode);

        }
    }

    public int get(int key){
        if(!map.containsKey(key))   return -1;

        int val = map.get(key).val;
        put(key, val);

        return val;
    }
}


