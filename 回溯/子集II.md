## 子集II
[https://leetcode-cn.com/problems/subsets-ii](https://leetcode-cn.com/problems/subsets-ii)

### 问题描述
给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

### 回溯
```
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int len = 0; len <= nums.length; len++) {
            backTrack(result, len, new ArrayList<>(), nums, 0);
        }

        return result;
    }

    public void backTrack(List<List<Integer>> result, int len, List<Integer> temp, int[] nums, int start) {
        if (temp.size() == len) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int j = start; j < nums.length; j++) {
            if (j > start && nums[j] == nums[j-1]) {
                continue;
            }
            temp.add(nums[j]);
            backTrack(result, len, temp, nums, j + 1);
            temp.remove(temp.size() - 1);
        }
    }

}
```
