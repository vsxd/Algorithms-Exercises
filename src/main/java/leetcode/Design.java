package leetcode;

import java.util.*;

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

    MyLRUCache(int capacity) {
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

    class DLinkedList {
        Node head, tail;

        DLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node) {
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        public int delete(Node node) {
            int k = node.key;
            node.next.prev = node.prev;
            node.prev.next = node.next;

            return k;
        }

        public int deleteLast() {
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
        if (map.containsKey(key)) {
            list.delete(map.get(key));
            list.addFirst(node);
            map.put(key, node);
        } else {
            if (map.size() == cap) {
                int delKey = list.deleteLast();
                map.remove(delKey);
            }
            map.put(key, node);
            list.addFirst(node);
        }
    }
}


class LRUCache_tmpl {
    class Node {
        public int key;
        public int val;
        public Node prev;
        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleLinkedList {

        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node) {

            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        public int delete(Node n) {
            int key = n.key;
            n.next.prev = n.prev;
            n.prev.next = n.next;

            return key;
        }

        public int deleteLast() {
            if (head.next == tail) return -1;

            return delete(tail.prev);
        }
    }

    HashMap<Integer, Node> map;
    DoubleLinkedList cache;
    int cap;

    public LRUCache_tmpl(int capacity) {
        map = new HashMap<>();
        cache = new DoubleLinkedList();
        cap = capacity;
    }

    public void put(int key, int val) {
        Node newNode = new Node(key, val);

        if (map.containsKey(key)) {
            cache.delete(map.get(key));
            cache.addFirst(newNode);
            map.put(key, newNode);
        } else {
            if (map.size() == cap) {
                int k = cache.deleteLast();
                map.remove(k);
            }
            cache.addFirst(newNode);
            map.put(key, newNode);

        }
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        int val = map.get(key).val;
        put(key, val);

        return val;
    }
}


class MyLinkedList {
    class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode prev, ListNode next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = new ListNode(Integer.MIN_VALUE);
        tail = new ListNode(Integer.MIN_VALUE);

        head.next = tail;
        tail.prev = head;

        size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        ListNode node = getNode(index);
        if (node == null) return -1;
        return node.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) return;
        if (index == size) {
            addAtTail(val);
            return;
        }
        ListNode next = getNode(index);
        ListNode prev = next.prev;
        ListNode node = new ListNode(val);

        prev.next = node;
        node.prev = prev;

        next.prev = node;
        node.next = next;

        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        ListNode node = getNode(index);
        if (node == null) return;

        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        size--;
    }

    private ListNode getNode(int index) {
        if (index >= size || index < 0) return null;
        ListNode curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i <= index; i++) {
                curr = curr.next;
            }
        }
        {
            curr = tail;
            for (int i = 0; i <= size - index; i++) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        System.out.println(list.get(1));
        list.deleteAtIndex(1);
        System.out.println(list.get(1));
    }


}

class MyCircularQueue {

    private int[] queue;
    private int headIndex;
    private int count;
    private int capacity;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.headIndex = 0;
        this.count = 0;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (this.count == this.capacity)
            return false;
        this.queue[(this.headIndex + this.count) % this.capacity] = value;
        this.count += 1;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (this.count == 0)
            return false;
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.count -= 1;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (this.count == 0)
            return -1;
        return this.queue[this.headIndex];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (this.count == 0)
            return -1;
        int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
        return this.queue[tailIndex];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return (this.count == this.capacity);
    }
}

class MyCircularDeque {
    int[] q;
    int capacity;
    int headIndex;
    int size;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        q = new int[k];
        capacity = k;
        headIndex = 0;
        size = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (size == capacity) return false;
        size++;
        headIndex--;
        if (headIndex < 0) headIndex = capacity - 1;
        q[headIndex] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (size == capacity) return false;
        q[(headIndex + size) % capacity] = value;
        size++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (size == 0) return false;
        headIndex = (headIndex + 1) % capacity;
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (size == 0) return false;
        size--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (size == 0) return -1;
        return q[headIndex];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (size == 0) return -1;
        return q[(headIndex + size - 1) % capacity];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == capacity;
    }
}


class Skiplist {

    private static int DEFAULT_MAX_LEVEL = 32;
    private static double SKIPLIST_P = 0.5;

    Node head = new Node(null, DEFAULT_MAX_LEVEL);

    int levelCount = 1;


    public Skiplist() {
    }

    /**
     * 找到level层 value 大于node 的节点
     */
    private Node findClosest(Node node, int levelIndex, int value) {
        while ((node.forwards[levelIndex]) != null && value > node.forwards[levelIndex].value) {
            node = node.forwards[levelIndex];
        }
        return node;
    }

    public boolean search(int target) {
        Node searchNode = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, target);
            if (searchNode.forwards[i] != null && searchNode.forwards[i].value == target) {
                return true;
            }
        }
        return false;
    }

    public void add(int num) {
        int level = randomLevel();
        Node updateNode = head;
        Node newNode = new Node(num, level);
        // 计算出当前num 索引的实际层数，从该层开始添加索引
        for (int i = levelCount - 1; i >= 0; i--) {
            //找到本层最近离num最近的list
            updateNode = findClosest(updateNode, i, num);
            if (i < level) {
                if (updateNode.forwards[i] == null) {
                    updateNode.forwards[i] = newNode;
                } else {
                    Node temp = updateNode.forwards[i];
                    updateNode.forwards[i] = newNode;
                    newNode.forwards[i] = temp;
                }
            }
        }
        if (level > levelCount) { //如果随机出来的层数比当前的层数还大，那么超过currentLevel的head 直接指向newNode
            for (int i = levelCount; i < level; i++) {
                head.forwards[i] = newNode;
            }
            levelCount = level;
        }
    }

    public boolean erase(int num) {
        boolean flag = false;
        Node searchNode = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, num);
            if (searchNode.forwards[i] != null && searchNode.forwards[i].value == num) {
                //找到该层中该节点
                searchNode.forwards[i] = searchNode.forwards[i].forwards[i];
                flag = true;
            }
        }
        return flag;
    }




    /**
     * 随机一个层数
     */
    private static int randomLevel() {
        int level = 1;
        while (Math.random() < SKIPLIST_P && level < DEFAULT_MAX_LEVEL) {
            level++;
        }
        return level;
    }


    class Node {
        Integer value;
        Node[] forwards;

        public Node(Integer value, int size) {
            this.value = value;
            this.forwards = new Node[size];
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

class MyQueue_using2stacks {
    private Deque<Integer> inStack = new ArrayDeque<>();
    private Deque<Integer> outStack = new ArrayDeque<>();
    public MyQueue_using2stacks() {
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        while(outStack.isEmpty()) in2out();
        return outStack.pop();
    }

    private void in2out(){
        while(!inStack.isEmpty())
            outStack.push(inStack.pop());
    }


    public int peek() {
        while(outStack.isEmpty()) in2out();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}


