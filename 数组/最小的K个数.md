## 最小的K个数
[https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof)

输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

示例 1：
输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]

示例 2：
输入：arr = [0,1,2,1], k = 1
输出：[0]
 

限制：
0 <= k <= arr.length <= 10000
0 <= arr[i] <= 10000

```
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }

        if (k >= arr.length) {
            return arr;
        }

        return quickSearch(arr, 0, arr.length - 1, k);
    }

    private int[] quickSearch(int[]arr, int start, int end, int k) {
        int [] result = new int[k];

        while(true) {
            int pos = partion(arr, start, end);
            if (pos == k) {
                for(int i = 0; i < k; i++) {
                    result[i] = arr[i];
                }
                return result;
            } else if (pos < k) {
                start = pos + 1;
            } else {
                end = pos - 1;
            }
        }
    }
    private int partion(int[] arr, int start, int end) {
        int small = start - 1;
        int privot = arr[end];
        for (int i = start; i < end; i++) {
            if (arr[i] < privot) {
                small++;
                if (small != i) {
                    swap(arr, i, small);
                }
            }
        }
        small = small + 1;
        swap(arr, small, end);
        return small;
    }

    public void swap(int [] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```
