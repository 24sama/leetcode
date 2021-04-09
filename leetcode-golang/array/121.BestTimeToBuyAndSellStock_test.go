package array

import (
	"fmt"
	"math"
	"testing"
)

/*
121. 买卖股票的最佳时机

给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

示例 1：
输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

示例 2：
输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
*/

func Test_BestTimeToBuyAndSellStock(t *testing.T) {
	fmt.Println(maxProfit([]int{7, 1, 5, 3, 6, 4}))
	fmt.Println(maxProfit2([]int{7, 1, 5, 3, 6, 4}))
	fmt.Println(maxProfit3([]int{7, 1, 5, 3, 6, 4}))
}

// 贪心
func maxProfit(prices []int) int {
	if prices == nil || len(prices) == 0 {
		return 0
	}
	maxProfit := 0
	min := prices[0]
	for i := 1; i < len(prices); i++ {
		min = int(math.Min(float64(min), float64(prices[i])))
		maxProfit = int(math.Max(float64(maxProfit), float64(prices[i]-min)))
	}
	return maxProfit
}

// dp
func maxProfit2(prices []int) int {
	// 定义状态dp[i][j] 表示第i天的利润
	// i 表示天数
	// j 表示当天是否持有 0表示未持有 1表示持有
	dp := make([][]int, len(prices))
	for i := range dp {
		dp[i] = make([]int, 2)
	}

	// 1。第一天未买入股票状态，则利润为0
	// 2。第一天买入股票的状态，则利润为负当天的股价
	dp[0][0] = 0
	dp[0][1] = -prices[0]
	for i := 1; i < len(prices); i++ {
		// 第i天手中没有股票的状态
		// 1。i-1天手中就没有股票
		// 2.i-1天手中有股票，但是第i天卖掉
		dp[i][0] = int(math.Max(float64(dp[i-1][0]), float64(dp[i-1][1]+prices[i])))

		// 第i天手中有股票的状态
		// 1。i-1天手中有股票，选择第i天休息不操作
		// 2.i-1天手中没有股票，但是第i天买入股票
		dp[i][1] = int(math.Max(float64(dp[i-1][1]), float64(-prices[i])))
	}
	// 最终结果为最后一天且未持有股票的状态
	return dp[len(prices)-1][0]
}

// 暴力
func maxProfit3(prices []int) int {
	max := 0
	for i := 0; i < len(prices)-1; i++ {
		for j := 1; j < len(prices); j++ {
			profit := prices[j] - prices[i]
			if profit > max {
				max = profit
			}
		}
	}
	return max
}
