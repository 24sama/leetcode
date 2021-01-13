package array

import (
	"fmt"
	"sort"
	"testing"
)

/*
41. 缺失的第一个正数

给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？

示例 1：
输入：nums = [1,2,0]
输出：3

示例 2：
输入：nums = [3,4,-1,1]
输出：2

示例 3：
输入：nums = [7,8,9,11,12]
输出：1

提示：
0 <= nums.length <= 300
-2的31次方 <= nums[i] <= 2的31次方 - 1
*/

func Test_firstMissingPositive(t *testing.T) {
	fmt.Println(firstMissingPositive([]int{7, 8, 9, 11, 12}))
	fmt.Println(firstMissingPositive([]int{3, 4, -1, 1}))
	fmt.Println(firstMissingPositive([]int{1, 2, 0}))
	fmt.Println(firstMissingPositive([]int{1}))

	fmt.Println("-----------")
	fmt.Println(firstMissingPositive2([]int{7, 8, 9, 11, 12}))
	fmt.Println(firstMissingPositive2([]int{3, 4, -1, 1}))
	fmt.Println(firstMissingPositive2([]int{1, 2, 0}))
	fmt.Println(firstMissingPositive2([]int{1}))

	fmt.Println("-----------")
	fmt.Println(firstMissingPositive3([]int{7, 8, 9, 11, 12}))
	fmt.Println(firstMissingPositive3([]int{3, 4, -1, 1}))
	fmt.Println(firstMissingPositive3([]int{1, 2, 0}))
	fmt.Println(firstMissingPositive3([]int{1}))
}

// 哈希表
// 最简单的办法，但是空间复杂度较高
func firstMissingPositive(nums []int) int {
	m := make(map[int]int)
	for _, v := range nums {
		m[v] = v
	}

	for i := 1; i <= len(nums)+1; i++ {
		if _, ok := m[i]; ok {
			continue
		} else {
			return i
		}
	}
	return 1
}

// 原地哈希
// 要找的数一定在[1, N+1]左闭右闭这个区间，因此把原数组当作哈希表使用
// 最好N+1这个数不用找，N个元素找不到就返回N+1
// 那么就把1这个数放到下标为0的位置，2这个数放到下标为1的位置，然后再遍历一次数组，第一个遇到值不等于下标减1的数就是要找的缺少的第一个正数
func firstMissingPositive2(nums []int) int {
	len := len(nums)
	for i := 0; i < len; i++ {
		// for循环不会每一次都把数组里面的所有元素都看一遍。
		// 如果有一些元素在这一次的循环中被交换到了它们应该在的位置，那么在后续的遍历中，由于它们已经在正确的位置上了，代码再执行到它们的时候，就会被跳过。
		for nums[i] > 0 && nums[i] <= len && nums[nums[i]-1] != nums[i] {
			// 数放到下标减1的位置
			nums[i], nums[nums[i]-1] = nums[nums[i]-1], nums[i]
		}
	}

	for i := 0; i < len; i++ {
		if nums[i] != i+1 {
			return i + 1
		}
	}
	return len + 1
}

// 排序再寻找突变值
// 时间复杂度 排序：O(NlogN) 查找：O(N)
func firstMissingPositive3(nums []int) int {
	sort.Ints(nums)

	pre := 0
	for i := 0; i < len(nums); i++ {
		if nums[i] <= 0 || nums[i] == pre {
			continue
		} else if nums[i] > pre+1 {
			break
		}
		pre++
	}
	return pre + 1
}
