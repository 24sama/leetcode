package array;

import java.util.Arrays;

/**
 * 下一个排列
 *
 * 找出这个数组排序出的所有数中，刚好比当前数大的那个数
 * 比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数 也就是132
 * 如果当前 nums = [3,2,1]。这就是1，2，3所有排序中最大的那个数，那么就返回1，2，3排序后所有数中最小的那个，也就是1，2，3 -> [1,2,3]
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @author liyaozong
 * @date 2020/9/28 16:00
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = new int[]{3,2,1};
        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = new int[]{1,3,2};
        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3));

        int[] nums4 = new int[]{1,3,2,5,4};
        nextPermutation(nums4);
        System.out.println(Arrays.toString(nums4));

        int[] nums5 = new int[]{2,3,1};
        nextPermutation(nums5);
        System.out.println(Arrays.toString(nums5));
    }

    public static void nextPermutation(int[] nums) {
        int right = nums.length-2;
        // 从右遍历 找到第一组紧挨的递增的两个数中较小的那个数的下标
        // 两种情况
        // 1. 1 2 3 4 5 第一个递增组合就是 4 5
        // 2. 1 2 5 4 3 第一个递增组合就是 2 5
        while (right >= 0) {
            if (nums[right+1] > nums[right]) {
                break;
            } else {
                right--;
            }
        }
        if (right >= 0) {
            int j = nums.length-1;
            // 从右遍历 找到第一个大于递增组合中较小数的数的下标
            // 两种情况
            // 1.这个数就是递增组合的另一个较大数 1 2 3 4 5 nums[j] = 5
            // 2.这个数不在递增组合中 1 2 5 4 3 nums[j] = 3
            // 提示：1 2 5 3 4 递增组合直接就是 3 4 nums[j] = 4 所以递增组合后的数只可能是降序或者就没有
            while (j >= 0) {
                if (nums[j] > nums[right]) {
                    break;
                } else {
                    j--;
                }
            }
            // 交换两个数
            int tmp = nums[j];
            nums[j] = nums[right];
            nums[right] = tmp;
            // 换位置的那个数之后的数按大小升序排序
            Arrays.sort(nums, right+1,nums.length);
        } else {
            Arrays.sort(nums);
        }
    }
}
