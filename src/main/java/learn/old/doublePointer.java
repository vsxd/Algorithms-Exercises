package learn.old;

public class doublePointer {
}


// https://leetcode-cn.com/problems/longest-palindromic-substring/
// 5. Longest Palindromic Substring
// Given a string s, return the longest palindromic substring in s.
class Solution5 {
    private String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // 注意条件里判断越界
            l--;
            r++;
        }
        return s.substring(l + 1, r); // [l+1, r)
    }

    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i); // 回文串长度为奇数
            String s2 = palindrome(s, i, i + 1); // 回文串长度为偶数
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }
}