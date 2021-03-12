package array

import (
	"fmt"
	"math"
	"testing"
)

/*
220. 存在重复元素 III

在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
如果存在则返回 true，不存在返回 false。

示例 1:
输入: nums = [1,2,3,1], k = 3, t = 0
输出: true

示例 2:
输入: nums = [1,0,1,1], k = 1, t = 2
输出: true

示例 3:
输入: nums = [1,5,9,1,5,9], k = 2, t = 3
输出: false
*/

func Test_containsNearbyAlmostDuplicate(t *testing.T) {
	fmt.Println(containsNearbyAlmostDuplicate([]int{1, 2, 3, 1}, 3, 0))
	fmt.Println(containsNearbyAlmostDuplicate([]int{1, 0, 1, 1}, 1, 2))
	fmt.Println(containsNearbyAlmostDuplicate([]int{1, 5, 9, 1, 5, 9}, 2, 3))
	fmt.Println(containsNearbyAlmostDuplicate([]int{1, 2, 1, 1}, 1, 0))
	fmt.Println(containsNearbyAlmostDuplicate([]int{2, 0, -2, 2}, 2, 1))

	fmt.Println("-----------")
	fmt.Println(containsNearbyAlmostDuplicate2([]int{1, 2, 3, 1}, 3, 0))
	fmt.Println(containsNearbyAlmostDuplicate2([]int{1, 0, 1, 1}, 1, 2))
	fmt.Println(containsNearbyAlmostDuplicate2([]int{1, 5, 9, 1, 5, 9}, 2, 3))
	fmt.Println(containsNearbyAlmostDuplicate2([]int{1, 2, 1, 1}, 1, 0))
	fmt.Println(containsNearbyAlmostDuplicate2([]int{2, 0, -2, 2}, 2, 1))
}

// 滑动窗口+桶排序
// 窗口中的数即为桶的id
func containsNearbyAlmostDuplicate(nums []int, k int, t int) bool {
	m := make(map[int]int)
	n := len(nums)
	// 桶中数字的个数是t+1
	// 这样桶内任意两个数之差一定<=t
	w := t + 1
	for i := 0; i < n; i++ {
		// 当i指针移动到大于k的数组索引位置时，开始删除窗口中第一个数
		// 始终保持窗口的大小<=k
		if i > k {
			delete(m, getId(nums[i-k-1], w))
		}
		// 获取当前数所在桶的编号
		id := getId(nums[i], w)
		// 表示该数字x计算出的所在的桶id已经存在，既然桶存在就一定说明在距离k范围内存在数字y计算出的桶id相同（即|数字y - 数字x| <= t）
		if _, ok := m[id]; ok {
			return true
		}
		// 数字x计算出的桶id不存在，即所在桶没有数字
		// 因为所在桶不相邻的桶中的数字与x的差的绝对值一定大于t，所以只需要考虑与x相邻的两个桶中的数字与x的差的绝对值是否小于等于t
		if v, ok := m[id-1]; ok && nums[i]-v < w {
			return true
		}
		if v, ok := m[id+1]; ok && v-nums[i] < w {
			return true
		}
		m[id] = nums[i]
	}
	return false
}

// 获取桶id
// w表示桶中的存储数字范围的个数
func getId(num int, w int) int {
	if num >= 0 {
		return num / w
	} else {
		return ((num + 1) / w) - 1
	}
}

// 暴力线性搜索
func containsNearbyAlmostDuplicate2(nums []int, k int, t int) bool {
	for i := 0; i < len(nums)-1; i++ {
		for j := i + 1; j <= int(math.Min(float64(i+k), float64(len(nums)-1))); j++ {
			if int(math.Abs(float64(nums[i]-nums[j]))) <= t {
				return true
			}
		}
	}
	return false
}
