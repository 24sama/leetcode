package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和 II
 * <p>
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * <p>
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * @author liyaozong
 * @date 2020/9/23 10:41
 */
public class CombinationSumII {
    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, target, res, combine);
        return res;
    }

    public static void dfs(int[] candidates, int begin, int target, List<List<Integer>> res, List<Integer> combine) {
        if (target == 0) {
            res.add(new ArrayList<>(combine));
            return;
        }

//        while (begin < candidates.length) {
//            if (target < candidates[begin]) {
//                break;
//            }
//            combine.add(candidates[begin]);
//            dfs(candidates, begin + 1, target - candidates[begin], res, combine);
//            combine.remove(combine.size() - 1);
//
//            begin++;
//            while (begin < candidates.length && candidates[begin] == candidates[begin - 1]) {
//                begin++;
//            }
//        }

        for (int i=begin;i<candidates.length;i++) {
            if (target < candidates[i]) {
                break;
            }
            // 当i大于begin说明处于同一层，本题中为外层循环。则进行去重
            if (i>begin&&candidates[i]==candidates[i-1]) {
                continue;
            }
            combine.add(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], res, combine);
            combine.remove(combine.size() - 1);
        }
    }
}
