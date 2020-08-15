## 第k个数
[https://leetcode-cn.com/problems/get-kth-magic-number-lcci](https://leetcode-cn.com/problems/get-kth-magic-number-lcci)

### 问题描述
有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。

示例 1:

输入: k = 5

输出: 9

### java求解
```
class Solution {
    public int getKthMagicNumber(int k) {
        int i3 = 0, i5 = 0, i7 = 0;
        int[]dp = new int[k];
        dp[0] = 1;
        for(int i = 1; i < k; i++) {
            dp[i] = Math.min(Math.min(3 * dp[i3], 5 * dp[i5]), 7 * dp[i7]);
            if (dp[i] == 3 * dp[i3]) {
                i3++;
            }
            if (dp[i] == 5 * dp[i5]) {
                i5++;
            }
            if (dp[i] == 7 * dp[i7]) {
                i7++;
            }
        }

        return dp[k-1];
    }
}
```
