package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * <p>
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author liyaozong
 * @date 2020/9/24 15:30
 */
public class Permute {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    /**
     * 回溯法
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        helper(nums, used, res, path);
        return res;
    }

    public static void helper(int[] nums, boolean[] used, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                // 进入递归前把该数字锁住
                used[i] = true;
                helper(nums, used, res, path);
                // 递归完毕回溯的时候把数字打开
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

}
