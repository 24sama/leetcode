package leetcode

import (
	"fmt"
	"sort"
	"testing"
)

/*
15.三数之和

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得a+b+c=0 ? 请你找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。

示例：
给定数组 nums = [-1, 0, 1, 2, -1, -4]，
满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

func Test_threeSum(t *testing.T) {
	nums := []int{-1, 0, 1, 2, -1, -4}
	fmt.Println(threeSum(nums))

	nums2 := []int{1, -1, -1, 0}
	fmt.Println(threeSum(nums2))
}

// 排序 + 双指针
func threeSum(nums []int) [][]int {
	res := make([][]int, 0)
	sort.Ints(nums)

	for i := 0; i < len(nums)-1; i++ {
		if nums[i] > 0 {
			break
		}

		//跳过重复数字
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}

		first := nums[i]
		other := -first
		start, end := i+1, len(nums)-1
		for start < end {
			if nums[start]+nums[end] == other {
				res = append(res, []int{first, nums[start], nums[end]})
				end--
				start++

				//跳过重复数字
				for start < end && nums[start] == nums[start-1] {
					start++
				}
				for start < end && nums[end] == nums[end+1] {
					end--
				}
			} else if nums[start]+nums[end] > other {
				end--
			} else {
				start++
			}
		}
	}
	return res
}
