## 跳跃游戏III
[https://leetcode-cn.com/problems/jump-game-iii](https://leetcode-cn.com/problems/jump-game-iii)

### 问题描述
这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。

请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。

注意，不管是什么情况下，你都无法跳到数组之外。

 

示例 1：

输入：arr = [4,2,3,0,3,1,2], start = 5
输出：true
解释：
到达值为 0 的下标 3 有以下可能方案：
下标 5 -> 下标 4 -> 下标 1 -> 下标 3
下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3

### 广度遍历 + 记忆化搜索
```
class Solution {
    public boolean canReach(int[] arr, int s) {
        int len = arr.length;
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(s);
        Set<Integer> visit = new HashSet<>();
        while(!queue.isEmpty()) {
            int q = queue.poll();
            if (arr[q] == 0) {
                return true;
            }

            if ((q + arr[q]) < len && !visit.contains(q + arr[q])) {
                visit.add(q + arr[q]);
                queue.offer(q + arr[q]);
            }
            if ((q - arr[q] >= 0) && !visit.contains(q - arr[q])) {
                visit.add(q - arr[q]);
                queue.offer(q - arr[q]);
            }
        }

        return false;
    }
}
```
