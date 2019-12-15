/**
 * <p>https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * </p>
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int res = 0;

        int min = 0;
        while (l < r) {
            int lower = height[height[l] < height[r] ? l++ : r--];
            min = Math.max(min, lower);
            res += min - lower;
        }
        return res;
    }
}
