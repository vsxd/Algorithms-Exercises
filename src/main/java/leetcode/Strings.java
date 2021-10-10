package leetcode;

import java.util.*;

public class Strings {
}

class lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int len = s.length();
        int ans = 0;
        int rIdx = 0;
        for (int i = 0; i < len; i++) {
            if (i != 0) set.remove(s.charAt(i - 1)); // 缩小左边界

            while (rIdx < len && !set.contains(s.charAt(rIdx))) {
                set.add(s.charAt(rIdx));
                rIdx++; // 扩大右边界
            }
            ans = Math.max(ans, rIdx - i);
        }
        return ans;
    }
}

class isValid {
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack();
        for (Character c : s.toCharArray())
            if (c == '(' || c == '{' || c == '[') {
                stk.push(c);
            } else if (c == ')' && !stk.empty() && stk.peek() == '(') {
                stk.pop();
            } else if (c == '}' && !stk.empty() && stk.peek() == '{') {
                stk.pop();
            } else if (c == ']' && !stk.empty() && stk.peek() == '[') {
                stk.pop();
            } else {
                return false;
            }
        return stk.empty();
    }
}

class addStrings {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        ArrayList<Character> res = new ArrayList<>();
        boolean carry = false;
        while (i >= 0 || j >= 0) {
            int a = 0;
            int b = 0;
            if (i >= 0) a = num1.charAt(i--) - 48;
            if (j >= 0) b = num2.charAt(j--) - 48;

            int n = a + b;
            if (carry) n++;
            carry = n / 10 == 1;

            res.add((char) (n % 10 + 48));
        }
        if (carry) {
            res.add((char) (48 + 1));
        }
        StringBuilder sb = new StringBuilder();
        for (int k = res.size() - 1; k >= 0; k++) {
            sb.append(res.get(k));
        }
        return sb.toString();
    }
}


class longestPalindrome {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;

        int maxLen = 1;
        int maxBegin = 0;

        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] str = s.toCharArray();
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = i + L - 1;
                if (j >= len) break;

                if (str[i] != str[j]) {
                    dp[i][j] = false;
                } else {
                    if (L == 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && L > maxLen) {
                    maxLen = L;
                    maxBegin = i;
                }
            }
        }
        return s.substring(maxBegin, maxBegin + maxLen);
    }
}

class myAtoi {
    public int myAtoi(String s) {
        int ans = 0;
        int i = 0;
        boolean negFlag = false;
        char[] str = s.toCharArray();
        while (i < str.length && str[i] == ' ') {
            i++;
        }
        if (i >= str.length) return 0;
        if (str[i] == '-') {
            negFlag = true;
            i++;
        } else if (str[i] == '+') {
            i++;
        }
        for (; i < str.length; i++) {
            int num = str[i] - 48;
            if (num > 9 || num < 0) break;

            if (ans > Integer.MAX_VALUE / 10 ||
                    (ans == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (ans < Integer.MIN_VALUE / 10 ||
                    (ans == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }
            ans *= 10;
            ans += negFlag ? -num : num;
        }
        return ans;
    }

    public static void main(String[] args) {
        new myAtoi().myAtoi("42");
    }

    public int myAtoi2(String s) {
        Automaton m = new Automaton();
        for (int i = 0; i < s.length(); i++) {
            if (m.get(s.charAt(i))) break;
        }
        return m.negSign ? -(int) m.ans : (int) m.ans;
    }

    class Automaton {
        private boolean negSign = false;
        private long ans = 0;
        private String status = "start";
        private Map<String, String[]> table;

        Automaton() {
            table = new HashMap<>();
            table.put("start", new String[]{"start", "signed", "in_number", "end"});
            table.put("signed", new String[]{"end", "end", "in_number", "end"});
            table.put("in_number", new String[]{"end", "end", "in_number", "end"});
            table.put("end", new String[]{"end", "end", "end", "end"});
        }

        public boolean get(char ch) {
            status = table.get(status)[get_col(ch)];
            if ("in_number".equals(status)) {
                ans = ans * 10 + ch - '0';
                ans = negSign ? Math.min(ans, -(long) Integer.MIN_VALUE) :
                        Math.min(ans, (long) Integer.MAX_VALUE);
            } else if ("signed".equals(status)) {
                negSign = ch == '-';
            } else if ("end".equals(status)) {
                return true;
            }
            return false;
        }

        private int get_col(char ch) {
            if (ch == ' ') return 0;
            if (ch == '+' || ch == '-') return 1;
            if (Character.isDigit(ch)) return 2;
            return 3;
        }
    }
}
