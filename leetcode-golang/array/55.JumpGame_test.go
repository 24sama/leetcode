package array

import (
	"fmt"
	"math"
	"testing"
)

/*
55. 跳跃游戏

给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个下标。

示例 1：
输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

示例 2：
输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。

提示：
1 <= nums.length <= 3 * 104
0 <= nums[i] <= 105
*/

func Test_canJump(t *testing.T) {
	fmt.Println(canJump([]int{2, 3, 1, 1, 4}))
	fmt.Println(canJump([]int{3, 2, 1, 0, 4}))
}

// 贪心算法
// 只要存在一个位置 x，它本身可以到达，并且它跳跃的最大长度为 x + nums[x]，这个值大于等于 y，即 x + nums[x]≥ y，那么位置 y 也可以到达。
// 换句话说，对于每一个可以到达的位置 x，它使得 x+1, x+2, x+nums[x] 这些连续的位置都可以到达。
// 这样以来，我们依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。对于当前遍历到的位置 x，
// 如果它在 最远可以到达的位置 的范围内，那么我们就可以从起点通过若干次跳跃到达该位置，因此我们可以用 x + nums[x] 更新 最远可以到达的位置。
// 在遍历的过程中，如果 最远可以到达的位置 大于等于数组中的最后一个位置，那就说明最后一个位置可达，我们就可以直接返回 True 作为答案。
// 反之，如果在遍历结束后，最后一个位置仍然不可达，我们就返回 False 作为答案。
func canJump(nums []int) bool {
	distance := 0
	for i := 0; i < len(nums); i++ {
		if i <= distance {
			distance = int(math.Max(float64(distance), float64(i+nums[i])))
			if distance >= len(nums)-1 {
				return true
			}
		}
	}
	return false
}