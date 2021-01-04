package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * @author liyaozong
 * @date 2020/10/16 16:44
 */
public class Subset {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
    }

    /**
     * 回溯法
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        helper(nums, 0, res, path);
        return res;
    }

    public static void helper(int[] nums, int i, List<List<Integer>> res, List<Integer> path) {
        res.add(new ArrayList<>(path));

        for (int j=i;j<nums.length;j++) {
            path.add(nums[j]);
            helper(nums, j+1, res, path);
            path.remove(path.size() - 1);
        }
    }
}
