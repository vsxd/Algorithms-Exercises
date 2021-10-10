package leetcode;

import java.util.*;

class ListNode {
    int val;
    ListNode next;

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

public class LinkedList {
    public static void main(String[] args) {

    }
}

class reverseList {
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 1; i <= 6; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }

        reverseList(head);
    }
}

class reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        while (head != null) {
            ListNode tail = head;
            for (int i = 0; i < k - 1; i++) {
                tail = tail.next;
                if (tail == null) return dummy.next;
            }
            ListNode nex = tail.next;
            ListNode[] ends = reverse(head, tail);

            pre.next = ends[0];
            ends[1].next = nex;
            pre = ends[1];
            head = ends[1].next;
        }
        return dummy.next;
    }

    private ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next, curr = head;
        while (prev != tail) {
            ListNode nex = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nex;
        }
        return new ListNode[]{tail, head};
    }
}

class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}

class mergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode curr = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                curr.next = l2;
                l2 = l2.next;
            } else {
                curr.next = l1;
                l1 = l1.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;

        return head.next;
    }
}

class getIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        if (headA == null) return null;
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        if (headB == null) return null;
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}

class reverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null) return head;
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode beginNode, leftNode, rightNode, endNode;
        beginNode = dummy;
        for (int i = 0; i < left - 1; i++) {
            beginNode = beginNode.next;
        }
        leftNode = beginNode.next;

        rightNode = leftNode;
        for (int i = 0; i < right - left; i++) {
            rightNode = rightNode.next;
        }
        endNode = rightNode.next;

        beginNode.next = null;
        rightNode.next = null;
        reverseLinkedList(leftNode);
        beginNode.next = rightNode;
        leftNode.next = endNode;

        return dummy.next;
    }

    private void reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
    }
}

class mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;
        int mid = l + (r - l) / 2;
        return merge2Lists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    private ListNode merge2Lists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = a != null ? a : b;
        return dummy.next;
    }

    public ListNode mergeKlists_2(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        for (ListNode node : lists) {
            if (node == null) continue;
            pq.offer(node);
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) {
                pq.offer(node.next);
                node.next = null;
            }
        }

        return dummy.next;
    }
}

class reorderList {
    //    public void reorderList(ListNode head) {
//        ListNode curr = head;
//        while (curr.next != null) {
//            ListNode nxt = curr.next;
//            ListNode last = getLastNode(curr);
//            curr.next = last;
//            last.next = nxt;
//            curr = curr.next.next;
//        }
//    }
//
//    private ListNode getLastNode(ListNode node) {
//        while (node.next != null) node = node.next;
//        return node;
//    }

    public void reorderList(ListNode head) {
        if (head.next == null) return;
        ListNode mid = findMiddle(head);
        ListNode list2 = mid.next;
        mid.next = null;
        list2 = reverseList(list2);
        ListNode list1 = head;
        mergeList(list1, list2);
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        return prev;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            head = head.next;
        }
        return head;
    }
}


class middleNode {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

class getKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < k; i++) p2 = p2.next;
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}

class removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode node = findNthFromEnd(dummy, n + 1);
        node.next = node.next != null ? node.next.next : null;
        return dummy.next;
    }

    private ListNode findNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

class addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1, l1);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null && l2 != null) {
            curr = curr.next;
            int add = l1.val + l2.val + carry;
            curr.val = add % 10;
            carry = add / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        curr.next = l1 != null ? l1 : l2;
        while (curr.next != null && carry != 0) {
            int add = curr.next.val + carry;
            curr.next.val = add % 10;
            carry = add / 10;
            curr = curr.next;
        }
        if (carry != 0)
            curr.next = new ListNode(carry);

        return dummy.next;
    }
}