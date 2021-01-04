package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author liyaozong
 * @date 2020/9/16 18:51
 */
public class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum2(new int[]{2, 7, 11, 15}, 9)));
    }

    /**
     * hash 一次遍历
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int another = 0;
        for (int i=0;i<nums.length;i++) {
            another = target - nums[i];
            if (map.containsKey(another)) {
                return new int[]{map.get(another), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 数组排序+双指针
     */
    public static int[] twoSum2(int[] nums, int target) {
        if (nums == null) {
            return null;
        }
        int[] oldNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);

        int start = 0, end =nums.length-1;
        while (start<end) {
            if (nums[start] + nums[end] == target) {
                int i=0,j=0;
                for (int k=0;k<nums.length;k++) {
                    if (oldNums[k] == nums[start]) {
                        i=k;
                        break;
                    }
                }
                for (int k=0;k<nums.length;k++) {
                    if (oldNums[k] == nums[end] && k != i) {
                        j=k;
                        break;
                    }
                }
                return new int[]{i, j};
            } else if (nums[start] + nums[end] > target){
                end--;
            } else {
                start++;
            }
        }
        return null;
    }
}
