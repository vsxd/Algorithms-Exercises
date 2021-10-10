package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DP {
}


class lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int ans = 0;
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

class TrappingRainWater {
    public int trap(int[] height) {
        int len = height.length;
        if (len == 0 || len == 1) return 0;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        int ans = 0;

        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        for (int i = 0; i < len; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
    public int trap_2(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        return ans;
    }
}


class climbStairs {
    public int climbStairs(int n) {
        if (n < 2) return 1;
        int pprev = 1;
        int prev = 1;
        int curr = 0;
        for(int i = 1; i < n; i++){
            curr = pprev + prev;
            pprev = prev;
            prev = curr;
        }
        return curr;
    }
}