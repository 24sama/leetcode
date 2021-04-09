package array

import (
	"fmt"
	"math"
	"testing"
)

/*
123. 买卖股票的最佳时机 III

给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
输入：prices = [3,3,5,0,0,3,1,4]
输出：6
解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

示例 2：
输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

示例 3：
输入：prices = [7,6,4,3,1]
输出：0
解释：在这个情况下, 没有交易完成, 所以最大利润为 0。

示例 4：
输入：prices = [1]
输出：0
*/

func Test_BestTimeToBuyAndSellStockIII(t *testing.T) {
	fmt.Println(maxProfitIII([]int{3, 3, 5, 0, 0, 3, 1, 4}))
	fmt.Println(maxProfitIII([]int{1, 2, 3, 4, 5}))

	fmt.Println(maxProfitIII2([]int{3, 3, 5, 0, 0, 3, 1, 4}))
	fmt.Println(maxProfitIII2([]int{1, 2, 3, 4, 5}))
}

// 二维数组dp
func maxProfitIII(prices []int) int {
	// 第一次交易的情况
	dp1 := make([][2]int, len(prices))
	// 第二次交易的情况
	dp2 := make([][2]int, len(prices))

	dp1[0][0] = 0
	dp1[0][1] = -prices[0]
	dp2[0][0] = 0
	dp2[0][1] = -prices[0]
	for i := 1; i < len(prices); i++ {
		// 第一次手中没有股票的情况
		dp1[i][0] = max(dp1[i-1][0], dp1[i-1][1]+prices[i])
		// 第一次手中有股票的情况
		dp1[i][1] = max(dp1[i-1][1], -prices[i])
		// 第二次手中没有股票的情况
		dp2[i][0] = max(dp2[i-1][0], dp2[i-1][1]+prices[i])
		// 第二次手中有股票的情况
		dp2[i][1] = max(dp2[i-1][1], dp1[i][0]-prices[i])
	}
	return dp2[len(prices)-1][0]
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

// 三维数组dp
func maxProfitIII2(prices []int) int {
	// dp[天数][当天是否持股][卖出的次数]
	dp := make([][2][3]int, len(prices))
	dp[0][0][0] = 0
	dp[0][1][0] = -prices[0]
	dp[0][0][1] = -math.MaxInt32
	dp[0][0][2] = -math.MaxInt32
	dp[0][1][1] = -math.MaxInt32
	dp[0][1][2] = -math.MaxInt32
	for i := 1; i < len(prices); i++ {
		dp[i][0][0] = 0
		// 当天持股且未卖出过 可能是之前买的，可能是今天买的
		dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][0][0]-prices[i])
		// 当天未持股且卖出过一次，可能是之前卖的，可能是今天卖的
		dp[i][0][1] = max(dp[i-1][0][1], dp[i-1][1][0]+prices[i])
		// 当天持股且卖出过一次，可能是之前买的，也可能是今天买的
		dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][1]-prices[i])
		// 当天未持股且卖出过两次，可能是之前卖的，也可能是今天卖的
		dp[i][0][2] = max(dp[i-1][0][2], dp[i-1][1][1]+prices[i])
		// 当天持股且卖出过两次的情况（不可能出现）
		dp[i][1][2] = -math.MaxInt32
	}
	return max(max(dp[len(prices)-1][0][1], dp[len(prices)-1][0][2]), 0)
}
