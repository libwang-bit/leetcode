## 全排列II
[https://leetcode-cn.com/problems/permutations-ii](https://leetcode-cn.com/problems/permutations-ii)

### 问题描述
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

### 回溯
```
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int len = nums.length;
        Arrays.sort(nums);
        boolean [] f = new boolean[len];
        List<Integer> list = new ArrayList<>();
        backTrack(nums, 0, len, result, list, f);
        return result;
    }

    public void backTrack(int[] nums, int first, int len, List<List<Integer>> result, List<Integer> list, boolean [] f) {
        if (first == len) {
            result.add(new ArrayList<>(list));
            return;
        }

        

        for (int i = 0; i < len; i++) {
            if (f[i]) {
                continue;
            }

            // 去重处理
            if (i > 0 && nums[i] == nums[i - 1] && f[i-1]) {
                continue;
            }

            // 继续递归填下一个数
            if (!f[i]) {
                // 处理
                list.add(nums[i]);
                f[i] = true;

                // 递归
                backTrack(nums, first + 1, len, result, list, f);

                // 状态重置
                list.remove(list.size() - 1);
                f[i] = false;
            }
        }
    }
}
```
