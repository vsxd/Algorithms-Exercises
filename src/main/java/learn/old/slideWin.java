package learn.old;

import java.util.*;

public class slideWin {
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);  // true
        a = 128;
        b = 128;
        System.out.println(a == b);  // false
        // -128 and 127 Integer instances are cached. Use .equals()!
    }
}

class Solution76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>(); // 储存当前win里符合要求的字符，记录其数量
        for (char c : t.toCharArray()) { // 初始化need，确定需要的字符及其数量
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE; // 记录最小覆盖子串的起始索引及长度
        while (right < s.length()) {
            char c = s.charAt(right); // c 是将移入窗口的字符
            right++; // 右移窗口

            if (need.containsKey(c)) { // 符合要求 - 进行窗口内数据的一系列更新
                window.put(c, window.getOrDefault(c, 0) + 1); // 更新win
                if (need.get(c).equals(window.get(c))) { // Do Not Use == on Integer
                    valid++; // 当前win中字符的数量满足need要求
                }
            }

            while (valid == need.size()) { // 判断窗口左侧是否要收缩
                if (right - left < len) { // 在这里更新最小覆盖子串
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left); // d 是将移出窗口的字符
                left++; // 左移窗口

                if (need.containsKey(d)) { // 移出的字符是need需要的，需要进行窗口内数据的一系列更新
                    if (window.get(d).equals(need.get(d)))  // -128 and 127 Integer instances are cached. Cannot use ==
                        valid--; // 如果此字符的数量本来是满足了条件的，移除后就不满足了
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}

class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>(); // 滑动窗口中有效的字符；无效的不会记录。
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1); // 记录有效字符
                if (need.get(c).equals(window.getOrDefault(c, 0))) valid++; // win中，此有效字符的数量符合要求
            }
            while (right - left == s1.length()) { // 排列组合，所以right-left应为s1长度。
                // 并且， 大于这个长度是不可能为true的，应缩小。
                if (valid == need.size())  // 与while的条件结合后，是符合预期的结果。
                    return true;
                char d = s2.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.getOrDefault(d, 0))) valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }
}

class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right++);

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) valid++;
            }

            while (right - left == p.length()) {
                if (valid == need.size()) // 注意valid是与need的长度相等，而不是与target字符串。
                    result.add(left);
                char d = s.charAt(left++);

                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.getOrDefault(d, 0))) valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return result;
    }
}

class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int max = 0;
        while (right < s.length()) {
            char c = s.charAt(right++);
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char d = s.charAt(left++);
                window.put(d, window.get(d) - 1);
            }
            max = Integer.max(max, right - left);
        }
        return max;
    }
}


class Solution763 {
    public List<Integer> partitionLabels(String S) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < S.length(); i++){
            lastIndex[S.charAt(i)-'a'] = i;
        }
        List<Integer> res = new LinkedList<>();
        int left = 0, right = 0;
        while (left < S.length()){
            right = lastIndex[S.charAt(left) - 'a'];
            for (int i = left; i < right; i++) {
                if (right < lastIndex[S.charAt(i)-'a'])
                    right = lastIndex[S.charAt(i) - 'a'];
            }
            res.add(right-left);
            left = right+1;
        }
        return res;
    }
}