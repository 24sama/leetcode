package array

import (
	"fmt"
	"math"
	"testing"
)

/*
45. 跳跃游戏 II

给定一个非负整数数组，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
你的目标是使用最少的跳跃次数到达数组的最后一个位置。

示例:
输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

说明:
假设你总是可以到达数组的最后一个位置。
*/

func Test_JumpGameII(t *testing.T) {
	fmt.Println(jump([]int{2, 3, 1, 1, 4}))
	fmt.Println(jump2([]int{2, 3, 1, 1, 4}))
	fmt.Println(jump3([]int{2, 3, 1, 1, 4}))
}

func jump(nums []int) int {
	ans := 0
	start := 0
	end := 1
	for end < len(nums) {
		maxPos := 0
		for i := start; i < end; i++ {
			// 能跳到的最远距离
			maxPos = int(math.Max(float64(maxPos), float64(i+nums[i])))
		}
		// 下一次起跳点范围开始的格子
		start = end
		// 下一次起跳点范围结束的格子
		end = maxPos + 1
		// 跳跃次数
		ans++
	}
	return ans
}

// 贪心
// 优化为一次循环
// 每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数
func jump2(nums []int) int {
	ans := 0
	end := 0
	maxPos := 0
	for i := 0; i < len(nums)-1; i++ {
		maxPos = int(math.Max(float64(maxPos), float64(i+nums[i])))
		if i == end {
			end = maxPos
			ans++
		}
	}
	return ans
}

// 贪心2
// 从后向前遍历
// 我们可以贪心的选择距离最后一个位置最远的那个位置，也就是对应下标最远的那个位置。遍历时可以从左往右遍历数组，找到第一个符合的位置
// 找到最后一步跳跃前所在的位置之后，可以继续贪心寻找倒数第二步跳跃的位置
// leetcode中c++和python会超时
func jump3(nums []int) int {
	pos := len(nums) - 1
	ans := 0
	for pos > 0 {
		for i := 0; i < pos; i++ {
			if i+nums[i] >= pos {
				pos = i
				ans++
				break
			}
		}
	}
	return ans
}
