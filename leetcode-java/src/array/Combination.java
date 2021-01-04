package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * <p>
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * @author liyaozong
 * @date 2020/9/23 11:50
 */
public class Combination {
    public static void main(String[] args) {
        System.out.println(combine(4, 2));
        System.out.println(combine2(4, 2));
    }

    /**
     * 回溯 + 剪枝
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cbn = new ArrayList<>();
        dfs(n, k, 1, res, cbn);
        return res;
    }

    public static void dfs(int n, int k, int begin, List<List<Integer>> res, List<Integer> cbn) {
        if (cbn.size() == k) {
            res.add(new ArrayList<>(cbn));
            return;
        }

        // 如果 n = 7, k = 4，从5开始搜索就已经没有意义了，这是因为：即使把5选上，后面的数只有6和7，一共就3个候选数，凑不出4个数的组合。
        for (int i = begin; i <= n - (k - cbn.size()) + 1; i++) {
            cbn.add(i);
            dfs(n, k, i + 1, res, cbn);
            cbn.remove(cbn.size() - 1);
        }
    }

    /**
     * 按照每个数选与不选进行二叉树的遍历
     * 可以按照每一个数选与不选画出二叉树，二叉树最多 n 层。
     */
    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        // 为了防止动态数组扩容 初始化时传入大小
        List<Integer> cbn = new ArrayList<>(k);
        dfs2(n, k, 1, res, cbn);
        return res;
    }

    public static void dfs2(int n, int k, int begin, List<List<Integer>> res, List<Integer> cbn) {
        if (k == 0) {
            res.add(new ArrayList<>(cbn));
            return;
        }

        // 带剪枝的终止条件
        if (begin > n - k + 1) {
            return;
        }
        // 不选当前数begin，则直接递归下一层
        dfs2(n, k, begin+1, res, cbn);

        // 选当前数begin，所需要选的数的数量即k 需要减一
        cbn.add(begin);
        dfs2(n, k-1, begin+1, res, cbn);
        cbn.remove(cbn.size()-1);
    }

}
