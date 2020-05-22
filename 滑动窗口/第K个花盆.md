## 第K个花盆
(https://leetcode-cn.com/problems/k-empty-slots)

描述:
```
花园里有 N 个花盆，每个花盆里都有一朵花。这 N 朵花会在 N 天内依次开放，每天有且仅有一朵花会开放并且会一直盛开下去。
给定一个数组 flowers 包含从 1 到 N 的数字，每个数字表示在那一天开放的花所在的花盆编号。
例如， flowers[i] = x 表示在第 i 天盛开的花在第 x 个花盆中，i 和 x 都在 1 到 N 的范围内。
给你一个整数 k，请你输出在哪一天恰好有两朵盛开的花，他们中间间隔了 k 朵花并且都没有开放。
如果不存在，输出 -1。

样例 1:
输入: 
flowers: [1,3,2]
k: 1
输出: 2
解释: 在第二天，第一朵和第三朵花都盛开了。
 
样例 2:
输入: 
flowers: [1,2,3]
k: 1
输出: -1

注释 :
给定的数组范围是 [1, 20000]。
```
解析:
```
flowers[i]=x ， 第i天开的是第x花盆。
反过来，flower[x] = i,表示第x花盆在第i天开花。
因此这道题就被转化为在flower[x]这个数组里，找两个坐标left 和 right, 满足两个条件.
1. right = left + K + 1; 即间隔k个花盆；
2. 在区间[left, right]中，flower[left] 和 flower[right]是两个最小的元素。中间的元素值都比他们大。
意思就是两边的元素先开花，等到了max(flower[left], flower[right])天，恰好只有left 和 right两个花盆开了花, 中间的花盆因为值都比他们大
因此开花时间比他们晚。这样我们就找到了一个满足条件的值。
我们将left = right, right = left + K + 1继续判断下一个窗口是否有满足条件的值出现。
如果在flower[j] > flower[left] || flower[j] > flower[right]:
我们让left = j; right = left + K + 1开启新的滑动窗口, 移动窗口的过程中始终保证
right < n 即可. 
```
Java 代码:
```
class Solution {
    public int kEmptySlots(int[] bulbs, int K) {
        int n = bulbs.length;
        int [] flower = new int[n];
        for(int i = 0; i < n; i++) {
            flower[bulbs[i] - 1] = i + 1;
        }

        int left = 0; 
        int right = K + 1;
        int result = Integer.MAX_VALUE;
        while(right < n) {
            boolean flag = true;
            for (int j = left + 1;j < right; j++) {
                if (flower[j] < flower[left] || flower[j] < flower[right]) {
                    left = j;
                    right = left + K + 1;
                    flag = false;
                    break;
                }
                
            }
            if (flag) {
                result = Math.min(result, Math.max(flower[left] ,flower[right]));
                left = right;
                right = left + K + 1;
            }
            
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
```
