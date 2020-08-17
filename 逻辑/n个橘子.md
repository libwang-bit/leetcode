## n个橘子
[https://leetcode-cn.com/problems/minimum-number-of-days-to-eat-n-oranges](https://leetcode-cn.com/problems/minimum-number-of-days-to-eat-n-oranges)

### 问题描述
厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：

吃掉一个橘子。
如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
每天你只能从以上 3 种方案中选择一种方案。

请你返回吃掉所有 n 个橘子的最少天数。

示例 1：
输入：n = 10
输出：4
解释：你总共有 10 个橘子。
第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。
第 2 天：吃 6 个橘子，剩余橘子数 9 - 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除）
第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。
第 4 天：吃掉最后 1 个橘子，剩余橘子数 1 - 1 = 0。
你需要至少 4 天吃掉 10 个橘子。

### Java代码
```
class Solution {
    public int minDays(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        return getMin(n, map);
    }

    public int getMin(int n, Map<Integer, Integer> map) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 2;

        // 记忆化结果
        if (map.containsKey(n)) {
            return map.get(n);
        }

        // n > 3来说， 一定可以经过n % 2步以后，变成 2的倍数; 也可以经过n % 3 步以后, 变成3 的倍数.
        int m2 = getMin(n/2, map) + n % 2;
        int m3 = getMin(n/3, map) + n % 3;

        // 选择策略2或者策略三后，的最小值.
        int result = Math.min(m2, m3) + 1;
        map.put(n, result);
        return result;
    }
}
```
