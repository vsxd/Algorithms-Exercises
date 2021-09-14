package learn.old;

import java.util.Arrays;
import java.util.PriorityQueue;

public class intervals {
}

class Solution1288 {
    public int removeCoveredIntervals(int[][] intervals) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)->{return b-a;});
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; //终点降序
            }
            return a[0] - b[0]; //起点升序
        });

        int left = intervals[0][0];
        int right = intervals[0][1];
        int res = 0; // 记录可以合并的
        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            if (left <= intv[0] && right >= intv[1]) { // 当前区间被上一个完全覆盖
                res++;
            } else if (left <= intv[0] && right < intv[1]) { // 当前区间和上一个部分重叠
                right = intv[1];
            } else if (right < intv[0]) { // 区间完全不重叠
                left = intv[0];
                right = intv[1];
            }
        }
        return intervals.length - res; //计算剩下的
    }
}

class Solution56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });
        int[][] res = new int[intervals.length][2];
        int count = 0;
        res[count++] = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (curr[0] <= res[count - 1][1]) {
                res[count - 1][1] = Integer.max(res[count - 1][1], curr[1]);
            } else {
                res[count++] = curr;
            }
        }
        return Arrays.copyOf(res, count);
    }
}

class Solution986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int[][] res = new int[Integer.max(firstList.length, secondList.length)][2];
        int count = 0;
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int aLeft = firstList[i][0];
            int aRight = firstList[i][0];
            int bLeft = secondList[j][0];
            int bRight = secondList[j][0];

            if (bRight >= aLeft && aRight >= bLeft) {
                res[count++] = new int[] {Integer.max(aLeft,bLeft),Integer.min(aRight,bRight)};
            }
            if (bRight < aRight) j++;
            else i++;
        }
        return Arrays.copyOf(res, count);
    }
}