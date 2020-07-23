## 验证回文字符串III
[https://leetcode-cn.com/problems/valid-palindrome-iii](https://leetcode-cn.com/problems/valid-palindrome-iii)

### 问题描述
给出一个字符串 s 和一个整数 k，请你帮忙判断这个字符串是不是一个「K 回文」。

所谓「K 回文」：如果可以通过从字符串中删去最多 k 个字符将其转换为回文，那么这个字符串就是一个「K 回文」。

示例：
输入：s = "abcdeca", k = 2
输出：true
解释：删除字符 “b” 和 “e”。

### Java代码
```
class Solution {
    public boolean isValidPalindrome(String s, int k) {
        int longestPalindromeLen = getLongestPalindromeSubseq(s);
        return s.length() - longestPalindromeLen <= k;
    }

    public int getLongestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[][]dp = new int[len][len];
        for(int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for(int i = len - 1; i >= 0; --i) {
            for(int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][len-1];
    }
}
```
