package learn.old;

import java.util.Arrays;

public class linkedList {
}

class ListNode {
    ListNode next;
    int val;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// https://leetcode-cn.com/problems/merge-two-sorted-lists/
class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode head = res;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                res.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                res.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            res = res.next;
        }
        res.next = l1 == null ? l2 : l1;
        return head.next;
    }
}

// https://leetcode-cn.com/problems/merge-k-sorted-lists/
class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0 || n == 1)
            return new ListNode();
        if (n == 2) return mergeList(lists[0], lists[1]);
        ListNode l1 = mergeKLists(Arrays.copyOfRange(lists,0, n/2));
        ListNode l2 = mergeKLists(Arrays.copyOfRange(lists, n/2, n));
        return mergeList(l1, l2);
    }

    public ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode head = res;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                res.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                res.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            res = res.next;
        }
        res.next = l1 == null ? l2 : l1;
        return head.next;
    }
}


// https://leetcode-cn.com/problems/merge-in-between-linked-lists/
class Solution1669 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode node = list1;
        for (int i = 0; i < a - 1; i++) {
            node = node.next;
        }
        ListNode node1 = node.next;
        for (int i = 0; i < (b - a + 1); i++) {
            node1 = node1.next;
        }
        node.next = list2;
        while (node.next != null) {
            node = node.next;
        }
        node.next = node1;
        return list1;
    }
}