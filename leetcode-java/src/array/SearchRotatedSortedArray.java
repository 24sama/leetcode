package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 搜索旋转排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * @author liyaozong
 * @date 2020/9/29 16:24
 */
public class SearchRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(search(new int[]{1,3}, 3));

        System.out.println(search2(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(search2(new int[]{1,3}, 3));

        System.out.println(search3(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(search3(new int[]{1,3}, 3));
    }

    /**
     * hash
     * leetcode 2ms
     */
    public static int search(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            map.put(nums[i], i);
        }
        return map.getOrDefault(target, -1);
    }

    /**
     * 遍历
     * 时间复杂度O(n)
     */
    public static int search2(int[] nums, int target) {
        for (int i=0; i<nums.length;i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分 + 分段讨论
     * 普通二分只能针对有序数组
     * 时间复杂度O(logn)
     */
    public static int search3(int[] nums, int target) {
        int low = 0, high = nums.length-1, mid = 0;
        while (low<=high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 先判断mid是在左段还是右段
            if (nums[mid] >= nums[low]) {
                // 在左段的情况，如：[3,4,5,1,2]
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // 在右段的情况，如：[5,6,7,1,2,3,4]
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
