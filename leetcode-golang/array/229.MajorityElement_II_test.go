package array

import (
	"fmt"
	"testing"
)

/*
229. 求众数 II

给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。

示例 1：
输入：[3,2,3]
输出：[3]

示例 2：
输入：nums = [1]
输出：[1]

示例 3：
输入：[1,1,1,3,3,2,2,2]
输出：[1,2]

提示：
1 <= nums.length <= 5 * 104
-109 <= nums[i] <= 109
*/

func Test_MajorityElementII(t *testing.T) {
	fmt.Println(majorityElementII([]int{3, 2, 3}))
	fmt.Println(majorityElementII([]int{1}))
	fmt.Println(majorityElementII([]int{1, 1, 1, 3, 3, 2, 2, 2}))
	fmt.Println(majorityElementII([]int{1, 2}))

	fmt.Println(majorityElementII2([]int{3, 2, 3}))
	fmt.Println(majorityElementII2([]int{1}))
	fmt.Println(majorityElementII2([]int{1, 1, 1, 3, 3, 2, 2, 2}))
	fmt.Println(majorityElementII2([]int{1, 2}))
}

// hash暴力
func majorityElementII(nums []int) []int {
	m := make(map[int]int, 0)
	res := make([]int, 0)
	for i := 0; i < len(nums); i++ {
		if v, ok := m[nums[i]]; ok {
			m[nums[i]] = v + 1
		} else {
			m[nums[i]] = 1
		}
	}
	for k, v := range m {
		if v > len(nums)/3 {
			res = append(res, k)
		}
	}
	return res
}

// 摩尔投票
func majorityElementII2(nums []int) []int {
	cand1 := nums[0]
	cand2 := nums[0]
	count1 := 0
	count2 := 0
	// 投票阶段
	for i := 0; i < len(nums); i++ {
		// 两个候选人，任何一个匹配上则对应候选人票数加1
		if cand1 == nums[i] {
			count1++
			continue
		}
		if cand2 == nums[i] {
			count2++
			continue
		}
		// 若票数为0则更换候选人
		if count1 == 0 {
			cand1 = nums[i]
			count1 = 1
			continue
		}
		if count2 == 0 {
			cand2 = nums[i]
			count2 = 1
			continue
		}
		// 若都没匹配上则票数均减1
		count1--
		count2--
	}

	count1 = 0
	count2 = 0
	// 检测阶段 可能出现数字均不同，候选人为最后选上的情况，所以需要检测
	for i := 0; i < len(nums); i++ {
		if cand1 == nums[i] {
			count1++
		} else if cand2 == nums[i] {
			count2++
		}
	}

	res := make([]int, 0)
	if count1 > len(nums)/3 {
		res = append(res, cand1)
	}
	if count2 > len(nums)/3 {
		res = append(res, cand2)
	}
	return res
}
