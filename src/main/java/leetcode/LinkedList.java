package leetcode;

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



