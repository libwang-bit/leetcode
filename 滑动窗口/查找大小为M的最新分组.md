## 查找大小为 M 的最新分组
[https://leetcode-cn.com/problems/find-latest-group-of-size-m](https://leetcode-cn.com/problems/find-latest-group-of-size-m)
### 问题描述
给你一个数组 arr ，该数组表示一个从 1 到 n 的数字排列。有一个长度为 n 的二进制字符串，该字符串上的所有位最初都设置为 0 。

在从 1 到 n 的每个步骤 i 中（假设二进制字符串和 arr 都是从 1 开始索引的情况下），二进制字符串上位于位置 arr[i] 的位将会设为 1 。

给你一个整数 m ，请你找出二进制字符串上存在长度为 m 的一组 1 的最后步骤。一组 1 是一个连续的、由 1 组成的子串，且左右两边不再有可以延伸的 1 。

返回存在长度 恰好 为 m 的 一组 1  的最后步骤。如果不存在这样的步骤，请返回 -1 。

 
```
示例 1：

输入：arr = [3,5,1,2,4], m = 1
输出：4
解释：
步骤 1："00100"，由 1 构成的组：["1"]
步骤 2："00101"，由 1 构成的组：["1", "1"]
步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
步骤 4："11101"，由 1 构成的组：["111", "1"]
步骤 5："11111"，由 1 构成的组：["11111"]
存在长度为 1 的一组 1 的最后步骤是步骤 4 。
```

### 滑动窗口求最值
```
class Solution {
    Deque<Integer> queue;

    // 获取滑动窗口大小为k的最大值数组
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k > nums.length) {
            return new int[]{};
        }
        queue = new LinkedList<>();
        int len = nums.length;
        int[] ans = new int[len - k + 1];

        initQueue(nums, k);
        int index = 0;
        ans[index++] = nums[queue.getFirst()];

        for(int i = 1; i <= len - k; i++) {
            updateQueue(nums, i + k - 1, k);
            ans[index++] = nums[queue.getFirst()];
        }
        return ans;
    }

    // 初始化前k个元素的双端队列
    private void initQueue(int[] nums, int k) {
        for(int i = 0; i < k; i++) {
            while(!queue.isEmpty() && nums[i] > nums[queue.getLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
    }

    // 新的滑动窗口更新队列
    private void updateQueue(int[] nums, int index, int k) {
        if (!queue.isEmpty() && queue.getFirst() <= index - k) {
            queue.pollFirst();
        }

        while(!queue.isEmpty() && nums[index] > nums[queue.getLast()]) {
            queue.pollLast();
        }
        queue.offer(index);
    }

    public int findLatestStep(int[] arr, int m) {
        int len = arr.length;
        if (m == len) {
            return m;
        }
        // num[i]表示第i+1 步， 位置为i的位置设为1 . => times[i] 表示第i个位置的0， 在 times[i]时刻设为1
        // 时间位置数组 => 位置时间数组
        int[] times = new int[len];
        int index = 0;
        for(int t : arr) {
            times[t-1] = index++;
        }

        // 问题就转化为，在times数组中找一个窗口, 满足窗口内的最大值 < Math.min(窗口左邻居的值, 窗口右邻居的值)
        // 遍历所有的窗口，记录所有答案中的最大值
        int[] maxSlidingNum = maxSlidingWindow(times, m);
        int ans = -1;
        for(int i = 0; i <= len - m; i++) {
            if (i == 0) {
                if (maxSlidingNum[0] < times[m]) {
                    ans = Math.max(ans, times[m]);
                }
            } else if (i == len - m) {
                if (maxSlidingNum[i] < times[len-m-1]) {
                    ans = Math.max(ans, times[len-m-1]);
                }
            } else {
                if (maxSlidingNum[i] < times[i-1] && maxSlidingNum[i] < times[i+m]) {
                    ans = Math.max(ans, Math.min(times[i-1], times[i+m]));
                }
            }
        }
        return ans;
    }
}
```
