package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和 III
 * <p>
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * <p>
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author liyaozong
 * @date 2020/9/24 10:31
 */
public class CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 9));
    }

    /**
     * 回溯 + 剪枝
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(k, n, 1, res, path);
        return res;
    }

    public static void dfs(int k, int n, int begin, List<List<Integer>> res, List<Integer> path) {
        if (0 == k && n == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 找3个数，起点最多到 7 即起点上界为7
        // 找2个数，起点最多到 8 即起点上界为8
        // 规律是 起点上界 + k = 10， 所以起点上界 = 10 - k
        for (int i = begin; i <= 10-k; i++) {
            // 剪枝
            // n即要寻找的数 比当前 i 还要小则直接退出循环
            if (n<i) {
                break;
            }
            path.add(i);
            dfs(k-1, n - i, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }
}
