package array;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @author liyaozong
 * @date 2020/9/30 14:25
 */
public class FindTheFirstAndLastPosition {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 5, 7, 7, 8, 8, 10}, 5)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10, 10}, 10)));
        System.out.println(Arrays.toString(searchRange(new int[]{10, 10}, 10)));
        System.out.println(Arrays.toString(searchRange(new int[]{1, 10}, 10)));
    }

    /**
     * 两次二分查找
     * 先找出现的第一个位置，再找出现的最后一个位置
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int first = findFirstPosition(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findLastPosition(nums, target);
        return new int[]{first, last};
    }

    public static int findFirstPosition(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            // 因为要找第一个 所以继续左移
            // low和high指针会不断逼近第一个target
            // 若数组中存在target，则最后一次循环low = high = mid 且值就是第一个target
            // 然后high - 1 < low 跳出循环
            if (target == nums[mid]) {
                high = mid - 1;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            }
        }

        // 最后while循环会在high = low+1即指针位置是[high，low] low指针在high指针后面的时候退出
        // 因为不管是否存在target都会跳出循环，所以这个时候再判断是否有target
        if (low != nums.length && nums[low] == target) {
            return low;
        }
        return -1;
    }

    public static int findLastPosition(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                low = mid + 1;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            }
        }
        // 根据主函数的逻辑，只有存在target时才会调用本函数，所以不用再判断
        return high;
    }
}
