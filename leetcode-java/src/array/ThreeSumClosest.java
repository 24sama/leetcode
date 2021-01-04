package array;

import java.util.Arrays;

/**
 * 最接近的三数之和
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * <p>
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * @author liyaozong
 * @date 2020/9/21 15:30
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{1, 1, 1, 0}, -100));
    }

    public static int threeSumClosest(int[] nums, int target) {
        int closestNum = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 跳过重复数字
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int min = nums[i] + nums[left] + nums[left + 1];
                // 若target已经小于当前循环最小值则跳出
                if (target < min) {
                    if (Math.abs(target - closestNum) > Math.abs(target - min)) {
                        closestNum = min;
                    }
                    break;
                }
                int max = nums[i] + nums[right] + nums[right - 1];
                // 若target已经大于当前循环最大值则跳出
                if (target > max) {
                    if (Math.abs(target - closestNum) > Math.abs(target - max)) {
                        closestNum = max;
                    }
                    break;
                }

                int cur = nums[i] + nums[left] + nums[right];
                if (target == cur) {
                    return target;
                } else if (target < cur) {
                    right--;
                    // 去重
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    left++;
                    // 去重
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
                // 当前值距离target更近
                if (Math.abs(target - closestNum) > Math.abs(target - cur)) {
                    closestNum = cur;
                }
            }
        }
        return closestNum;
    }
}
