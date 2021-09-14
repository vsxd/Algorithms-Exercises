package leetcode;

import java.util.HashSet;

public class Strings {
}

class lengthOfLongestSubstring{
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int len = s.length();
        int ans = 0;
        int rIdx = 0;
        for (int i = 0; i < len; i++){
            if (i != 0) set.remove(s.charAt(i-1)); // 缩小左边界

            while (rIdx < len && !set.contains(s.charAt(rIdx))){
                set.add(s.charAt(rIdx));
                rIdx++; // 扩大右边界
            }
            ans = Math.max(ans, rIdx-i);
        }
        return ans;
    }
}