## 和为k 的子数组
[https://leetcode-cn.com/problems/subarray-sum-equals-k](https://leetcode-cn.com/problems/subarray-sum-equals-k)

描述:
```
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
```
分析:
采用累计求和的方法 求数组 [a<sub>0</sub>, a<sub>1</sub>, a<sub>2</sub>……, a<sub>n-1</sub>, a<sub>n</sub> ] 中 和为 S 的子数组。

我们定义 S<sub>i</sub> = $\sum_{0}^n {a_i}$, 

如果 S<sub>i</sub> - S = S<sub>k</sub> (0<=k<=i),
我们可以得到一个包含 a<sub>i</sub> 的子数组 [a<sub>k+1</sub>, a<sub>k+2</sub>, ……, a<sub>i</sub>] , 其和等于S。
因为遍历一次，所以时间复杂度为 o(n)， 空间复杂度为 o(n).

Java 代码:
```
class Solution {
        public int subarraySum(int[] nums, int k) {
            int sum = 0; 
            int count = 0;

            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);

            for (int num : nums) {
                sum = sum + num;
                if (map.containsKey(sum - k)) {
                    count += map.get(sum - k);
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
}
```
