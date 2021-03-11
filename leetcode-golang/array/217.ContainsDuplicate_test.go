package array

import (
	"fmt"
	"sort"
	"testing"
)

/*
217. 存在重复元素

给定一个整数数组，判断是否存在重复元素。
如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。

示例 1:
输入: [1,2,3,1]
输出: true

示例 2:
输入: [1,2,3,4]
输出: false

示例 3:
输入: [1,1,1,3,3,4,3,2,4,2]
输出: true
*/

func Test_containsDuplicate(t *testing.T) {
	fmt.Println(containsDuplicate([]int{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}))
	fmt.Println(containsDuplicate2([]int{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}))
}

// hash O(n)
func containsDuplicate(nums []int) bool {
	m := make(map[int]int, 0)
	for _, v := range nums {
		if _, ok := m[v]; ok {
			return true
		} else {
			m[v] = 1
		}
	}
	return false
}

// 排序 O(nlogn)
func containsDuplicate2(nums []int) bool {
	sort.Ints(nums)
	for i := 0; i < len(nums)-1; i++ {
		if nums[i] == nums[i+1] {
			return true
		}
	}
	return false
}
