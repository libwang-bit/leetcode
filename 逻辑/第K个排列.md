## 第K个排列
[https://leetcode-cn.com/problems/permutation-sequence](https://leetcode-cn.com/problems/permutation-sequence)
描述:
```
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：
给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。

示例 1:
输入: n = 3, k = 3
输出: "213"

示例 2:
输入: n = 4, k = 9
输出: "2314"
```
==假设我们现在求 n = 4, k = 9这个答案。
我们先求第一位数字，任何一个数字，都有3! = 6个。
9 / 6 = 1; 此时数字列表是[1，2，3，4]， 所以我们知道第一个数字是 下标为1 的数字，也就是2;

此时问题变为 n = 3, k = 3; 2!= 2
3 / 2 == 1; 注意这里的数字列表变为[1,3,4] 所以我们知道第二个数字是 3， 即下标是1。

以此类推.

Java 代码:
```
class Solution {
    public String getPermutation(int n, int k) {
        // 用一个 map 保存每一个数的阶乘值
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        List<Integer> list = new ArrayList<>();

        int mul = 1;
        for (int i = 1; i <= n; i++) {
            mul = mul * i;
            map.put(i, mul);
            list.add(i);
        }
        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i > -1; i--) {
            int value = k / map.get(i);

            sb.append(list.get(value));

            k = k - map.get(i) * value;
            list.remove(value);
        }

        return sb.toString();
    }
}
```
