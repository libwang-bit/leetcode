## 和可被 K 整除的子数组
(https://leetcode-cn.com/problems/subarray-sums-divisible-by-k)

描述:

```
给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。

示例：
输入：A = [4,5,0,-2,-3,1], K = 5
输出：7
解释：
有 7 个子数组满足其元素之和可被 K = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 

提示：
1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
```

解答:
```
题目是要在数组中找到这样一组数(i, j) i <= j <= n, 满足 (s[j] - s[i]) % k == 0. s[i] 表示 
根据同余定理，也就是要满足 s[j] % k == s[j] % k;
我们利用hash表 + 前缀和的方式，可以将时间复杂度降低到o(n).

hash key存储的是 sum[i] % k, sum[i] 表示以 nums[i] 为结尾的元素和。 value 存储的是出现的次数.
当我们遍历到下标 j 时， 我们检查hash是否有 sum[j] % k 的key存在。如果有，那么count = count + map.get(sum[j] % k)。
反之，map.put(sum[j] % k, 1);

有两点需要注意, 
a) 初始化: map.put(0, 1); 比如 nums = [4], k = 4, count = 1;
b) sum[j] 可能为负， 要纠正 (sum[j] % k + k) % k. 比如 [-1, 4], k = 4, count = 1:
-1 % 4 = -1; 
3 % 4 = 3;
但其实 3 - (-1) = 4， 可以被4整除.
```

Java代码:

```
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int sum = 0;
        for(int i = 0; i < A.length; i++) {
            sum = sum + A[i];
            int res = (sum % K + K) % K; // 纠正sum为负的情况 [-1, 3] k = 4 
            count += map.getOrDefault(res,0);
            map.put(res, map.getOrDefault(res, 0) + 1);
        }
        return count;
    }
}
```
