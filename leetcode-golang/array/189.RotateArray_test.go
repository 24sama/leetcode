package array

import (
	"fmt"
	"testing"
)

/*
189. 旋转数组

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

进阶：
尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？

示例 1:
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]

示例 2:
输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
*/

func Test_rotate(t *testing.T) {
	nums := []int{1, 2, 3, 4, 5, 6, 7}
	rotate(nums, 10)
	fmt.Println(nums)

	nums2 := []int{1, 2, 3, 4, 5, 6, 7}
	rotate2(nums2, 3)
	fmt.Println(nums2)
}

// 反转三次数组
func rotate(nums []int, k int) {
	if nums == nil || len(nums) == 0 || k%len(nums) == 0 {
		return
	}

	if len(nums) < k {
		k = k - len(nums)
	}
	reverse(nums, 0, len(nums)-1)
	reverse(nums, 0, k-1)
	reverse(nums, k, len(nums)-1)
}

func reverse(nums []int, start int, end int) {
	tmp := end
	for i := start; i <= (start+end)/2; i++ {
		nums[i], nums[tmp] = nums[tmp], nums[i]
		tmp--
	}
}

// 使用额外数组
func rotate2(nums []int, k int) {
	newNums := make([]int, len(nums))
	for i, v := range nums {
		newNums[(i+k)%len(nums)] = v
	}
	copy(nums, newNums)
}
