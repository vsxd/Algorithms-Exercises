package leetcode;

import java.util.*;

public class Backtrace {
}

class permute {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, depth+1, path, used, res);
            used[i] = false;
            path.removeLast();
        }
    }

}


class generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrace(n, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backtrace(int max, int open, int close, StringBuilder curr, List<String> ans) {
        if (curr.length() == max*2){
            ans.add(curr.toString());
            return;
        }
        if (open < max) {
            curr.append('(');
            backtrace(max, open+1, close, curr, ans);
            curr.deleteCharAt(curr.length()-1);
        }
        if (close < open) {
            curr.append(')');
            backtrace(max, open, close+1, curr, ans);
            curr.deleteCharAt(curr.length()-1);
        }
    }
}
