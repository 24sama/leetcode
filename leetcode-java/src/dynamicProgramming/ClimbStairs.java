package dynamicProgramming;

/**
 * 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(5));
        System.out.println(climbStairs2(45));
        System.out.println(climbStairs3(45));
    }

    public static int climbStairs(int n) {
        // 暴力递归 leetcode解题会超时
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }

    static int[] memo;
    public static int climbStairs2(int n) {
        // 暴力递归 优化版
        memo = new int[n + 1];
        return helper(n);
    }
    public static int helper(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (memo[n] > 0) {
            return memo[n];
        }
        memo[n] = helper(n-1) + helper(n-2);
        return memo[n];
    }

    public static int climbStairs3(int n) {
        // dp解法
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;

        for (int i=2; i<=n; i++) {
            memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[n];
    }
}
