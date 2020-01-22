import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * <p>https://leetcode-cn.com/problems/first-missing-positive/
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
public class LostNumber {
    public static int firstMissingPositive(int[] nums) {
        for (int i = 1; i < nums.length + 1; i++) {
            Integer[] boxedArray = IntStream.of(nums).boxed().toArray(Integer[]::new);
            Set<Integer> targetSet = new HashSet<Integer>(Arrays.asList(boxedArray));
            if (!targetSet.contains(i)) {
                return i;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 8, 9, 11, 12};
        System.out.println(firstMissingPositive(nums));
    }
}
