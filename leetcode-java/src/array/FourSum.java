package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author liyaozong
 * @date 2020/9/17 10:25
 */
public class FourSum {
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(fourSum(new int[]{-3, -1, 0, 2, 4, 5}, 0));
        System.out.println(fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0));
        System.out.println(fourSum(new int[]{0, 0, 0, 0}, 0));
        System.out.println(fourSum(new int[]{0, 4, -5, 2, -2, 4, 2, -1, 4}, 12));
    }

    /**
     * 三层循环遍历 + 剪枝
     * 不剪枝速度很慢
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int a = 0; a < nums.length - 3; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            // 剪枝
            // 若当前循环中的最小值大于target则直接退出循环
            if (nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
                break;
            }
            // 若当前循环中的最大值小于target则跳过该次循环
            if (nums[a] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
                continue;
            }

            for (int b = a + 1; b < nums.length - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }

                // 剪枝
                // 若当前循环中的最小值大于target则直接退出循环
                if (nums[a] + nums[b] + nums[b + 1] + nums[b + 2] > target) {
                    break;
                }
                // 若当前循环中的最大值小于target则跳过该次循环
                if (nums[a] + nums[b] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
                    continue;
                }

                int left = b + 1, right = nums.length - 1;
                while (left < right) {
                    int cur = nums[a] + nums[b] + nums[left] + nums[right];
                    if (cur == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[a]);
                        list.add(nums[b]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        left++;
                        right--;
                        // 跳过重复数字
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (target < cur) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return res;
    }
}
