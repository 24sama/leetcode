package designProblem;

import java.util.Arrays;

/**
 * 打乱数组
 *
 * 打乱一个没有重复元素的数组。
 *
 * 示例:
 * 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 *
 * 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 *
 * 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 */
public class ShuffleTheArray {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution solution = new Solution(nums);
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.reset()));
    }

    private static class Solution {
        public int[] nums;
        public int[] old;

        public Solution(int[] nums) {
            this.nums = nums;
            this.old = nums.clone();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return this.old;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            // 洗牌算法 随机取数组i元素之后的元素与i交换位置
            for (int i=0;i<this.nums.length;i++) {
                int p = (int)(i+Math.random()*(this.nums.length-1-i+1));
                int tmp = nums[i];
                nums[i] = nums[p];
                nums[p] = tmp;
            }
            return this.nums;
        }
    }
}
