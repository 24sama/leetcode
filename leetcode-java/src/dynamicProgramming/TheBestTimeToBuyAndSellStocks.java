package dynamicProgramming;

/**
 * 买卖股票的最佳时机
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0
 */
public class TheBestTimeToBuyAndSellStocks {
    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(prices));

        System.out.println(maxProfit2(prices));
    }

    /**
     * dp解法
     */
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) {
            return 0;
        }

        // 定义状态dp[i][j]
        // i表示天数
        // j表示当天股票的持有状态，0 表示没有持有， 1 表示持有
        int[][] dp = new int[len][2];
        for (int i=0; i<len; i++) {
            // 定义base case
            if ((i-1) == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            // 第i天手中没有持有股票的状态由两种情况转移过来：
            // 1. i-1天手中就没有持有股票
            // 2。i-1天持有股票，但是在i天卖出了手中股票
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            // 第i天手中持有股票的状态由两种情况转移过来：
            // 1. i-1天手中就持有股票选择了休息
            // 2. i-1天手中未持有股票，但是在i天买入了股票
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        // 最终结果即为最后一天且没有持有股票的状态
        return dp[len-1][0];
    }

    /**
     * 一次遍历
     */
    public static int maxProfit2(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        int minNum = prices[0];
        for (int i=0;i<prices.length;i++){
            minNum = Math.min(prices[i], minNum);
            maxProfit = Math.max(prices[i]-minNum, maxProfit);
        }
        return maxProfit;
    }
}
