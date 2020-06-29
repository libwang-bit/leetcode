## 数组中第K大数字
[https://leetcode-cn.com/problems/kth-largest-element-in-an-array](https://leetcode-cn.com/problems/kth-largest-element-in-an-array)
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5

示例 2:
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:
你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

Java代码:
```
class Solution {
    public int findKthLargest(int[] nums, int k) {
        buildMinHeap(nums, k);

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                swap(nums, 0, i);
                heapAdjust(nums, 0, k);
            }
        }

        return nums[0];
    }

    private void buildMinHeap(int[] nums, int k) {
        for(int i = k / 2; i >= 0; i--) {
            heapAdjust(nums, i, k);
        }
    }

    private void heapAdjust(int[] nums, int start, int end) {
        while((start << 1) + 1 < end) {
            int left = (start << 1) + 1;
            int right = (start << 1) + 2;
            int min = start;
            if (left < end && nums[left] < nums[min]) {
                min = left;
            }

            if (right < end && nums[right] < nums[min]) {
                min = right;
            }

            if (min == start) {
                break;
            }

            swap(nums, start, min);
            start = min;
        }
    }

    private void swap(int[] nums, int start, int min) {
        int temp = nums[start];
        nums[start] = nums[min];
        nums[min] = temp;
    }
}
```
