package leetcode

import (
	"fmt"
	"sort"
	"testing"
)

/*
两数之和

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

示例:
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
*/

func Test_twoSum(t *testing.T) {
	nums := []int{2, 7, 11, 15}
	target := 9
	fmt.Println(twoSum(nums, target))
	fmt.Println(twoSum2(nums, target))
}

// hash 一次遍历
func twoSum(nums []int, target int) []int {
	m := make(map[int]int, 0)
	for i, _ := range nums {
		v := nums[i]
		if j, ok := m[target-v]; ok {
			return []int{i, j}
		} else {
			m[v] = i
		}
	}
	return nil
}

// 排序 + 双指针
func twoSum2(nums []int, target int) []int {
	if nums == nil {
		return nil
	}
	oldNums := make([]int, len(nums))
	copy(oldNums, nums)
	sort.Ints(nums)

	start := 0
	end := len(nums) - 1
	for start < end {
		if nums[start]+nums[end] == target {
			i := 0
			j := 0
			for k := 0; k < len(nums); k++ {
				if oldNums[k] == nums[start] {
					i = k
					break
				}
			}
			for k := 0; k < len(nums); k++ {
				if oldNums[k] == nums[end] && k != i {
					j = k
					break
				}
			}
			return []int{i, j}
		} else if nums[start]+nums[end] > target {
			end--
		} else {
			start++
		}
	}
	return nil
}
