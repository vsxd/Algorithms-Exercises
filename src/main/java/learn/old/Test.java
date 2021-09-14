package learn.old;

import learn.old.ListNode;

import java.util.*;

public class Test {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = 6;
        List<Integer> list = new LinkedList<>();
//        int x;
//        for (int i = 0; i < n; i++) {
//            x = sc.nextInt();
//            list.add(x);
//        }

    }

    public ListNode solve(ListNode S) {
        // write code here
        ListNode curr = S;
        int len = 0;
        while (curr != null) {
            if (curr.next == null) {
                curr.next = S;
                curr = null;
            } else {
                curr = curr.next;
            }
            len++;
        }
        curr = S;
        ListNode[] headList = new ListNode[len];
        for (int i = 0; i < len; i++) {
            headList[i] = curr;
            curr = curr.next;
        }

        int minVal = Integer.MAX_VALUE;
        Set<Integer> minIdxSet = new HashSet<>();
        for (int i = 0; i < len; i++){
            minIdxSet.add(i);
        }
        Set<Integer> filter = new HashSet<>(minIdxSet);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!filter.contains(i)){
                    continue;
                }
                if (headList[j].val < minVal) {
                    minVal = headList[j].val;
                    minIdxSet.clear();
                    minIdxSet.add(j);
                } else if (headList[j].val == minVal) {
                    minIdxSet.add(j);
                }
                headList[j] = headList[j].next;
            }
            if (minIdxSet.size() == 1) {
                break;
            } else {
                filter.clear();
                filter.addAll(minIdxSet);
            }
        }
        int minIdx = minIdxSet.iterator().next();

        ListNode head = rotate(S, len-minIdx);
        curr = head;
        for (int i = 0; i < len-1; i++){
            curr = curr.next;
        }
        curr.next = null;
        return head;
    }

    public ListNode rotate(ListNode head, int k) {
        if (head == null)
            return null;
        int length = 1;
        ListNode end = head;
        while (end.next != null) {
            end = end.next;
            ++length;
        }

        end.next = head;
        ListNode new_end = head;

        int times = length - k % length;
        for (int i = 1; i < times; i++) {
            new_end = new_end.next;
        }

        ListNode new_head = new_end.next;
        new_end.next = null;
        return new_head;
    }

}