## 和为s的连续正数序列
[https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof](https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof)

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

示例 1：
输入：target = 9
输出：[[2,3,4],[4,5]]

示例 2：
输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]

```
class Solution {
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        for(int l = 1, r = 1, sum = 0; r < target; r++) {
            sum += r;

            while(sum > target) {
                sum -= l++;
            }

            if (sum == target) {
                int[] temp = new int[r - l + 1];
                for(int i = 0; i < temp.length; i++) {
                    temp[i] = l + i;
                }
                list.add(temp);
            }
        }

        int[][]res = new int[list.size()][];
        for(int k = 0; k < res.length; k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}
```
