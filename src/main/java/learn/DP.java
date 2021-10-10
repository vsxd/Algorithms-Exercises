package learn;

import algs4.In;

import java.util.ArrayList;
import java.util.List;

public class DP {
}




class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++){
            dp[i] = dp[i-1]*dp[i-2];
        }

        return dp[n];
    }
}

