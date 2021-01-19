package array

import (
	"fmt"
	"sort"
	"testing"
)

/*
169. 多数元素

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1：
输入：[3,2,3]
输出：3

示例 2：
输入：[2,2,1,1,1,2,2]
输出：2

进阶：
尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
*/

func Test_majorityElement(t *testing.T) {
	fmt.Println(majorityElement([]int{2, 2, 1, 1, 1, 2, 2}))
	fmt.Println(majorityElement2([]int{2, 2, 1, 1, 1, 2, 2}))
	fmt.Println(majorityElement3([]int{2, 2, 1, 1, 1, 2, 2}))
}

// hash暴力法
func majorityElement(nums []int) int {
	m := make(map[int]int, 0)
	for i := 0; i < len(nums); i++ {
		if v, ok := m[nums[i]]; ok {
			m[nums[i]] = v + 1
			if v+1 > len(nums)/2 {
				return nums[i]
			}
		} else {
			m[nums[i]] = 1
			if v+1 > len(nums)/2 {
				return nums[i]
			}
		}
	}
	return 0
}

// 排序取中位数
func majorityElement2(nums []int) int {
	sort.Ints(nums)
	return nums[len(nums)/2]
}

// 摩尔投票
// 遇到相同的数则票数+1，不同的数则票数-1
// 因为多数元素个数大于n/2，因此多数元素获得的票数结果肯定大于1
func majorityElement3(nums []int) int {
	// 候选人初始化为元素0
	candidate := nums[0]
	// 票数初始化为1
	count := 1
	for i := 1; i < len(nums); i++ {
		// 若当前元素和候选人相同则票数加1
		if candidate == nums[i] {
			count++
			// 否则票数减1
			// 若票数等于0则重置票数为1，候选人为当前元素
		} else if count--; count == 0 {
			count = 1
			candidate = nums[i]
		}
	}
	return candidate
}
