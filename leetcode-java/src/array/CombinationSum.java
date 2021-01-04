package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 * <p>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 *
 * @author liyaozong
 * @date 2020/9/22 10:57
 */
public class CombinationSum {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,5}, 8));
    }

    /**
     * 回溯法 + 剪枝
     * 寻找所有可行解都可以使用回溯法
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, target, combine, res);
        return res;
    }

    public static void dfs(int[] candidates, int begin, int target, List<Integer> combine, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(combine));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            //剪枝 剩余元素均大于target直接退出循环
            if (target < candidates[i]) {
                break;
            }
            combine.add(candidates[i]);
            // 由于每个元素可以重复使用，所以下一轮起点仍然是i
            dfs(candidates, i, target - candidates[i], combine, res);
            // 删除最后一个 即状态重置 深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            combine.remove(combine.size() - 1);
        }
    }
}
