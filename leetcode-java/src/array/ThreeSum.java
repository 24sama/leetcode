package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * @author liyaozong
 * @date 2020/9/16 19:23
 */
public class ThreeSum {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{4,-2,2,4,-2,2}));
    }

    /**
     * 数组排序后 三层循环遍历
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++) {
            if (nums[i]>0) {
                break;
            }
            // 跳过重复数字
            if (i>0&&nums[i] == nums[i-1]) {
                continue;
            }
            int another = -nums[i];
            int start = i+1, end = nums.length-1;
            while (start<end) {
                if (another == nums[start] + nums[end]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[start]);
                    list.add(nums[end]);
                    res.add(list);
                    end--;
                    start++;
                    // 跳过重复数字
                    while (start<end&&nums[start]==nums[start-1]){start++;}
                    while (start<end&&nums[end]==nums[end+1]){end--;}
                } else if (nums[start] + nums[end] > another) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return res;
    }
}
