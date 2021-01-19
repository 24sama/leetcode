package array

import (
	"fmt"
	"testing"
)

/*
119. 杨辉三角 II

给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。

在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:
输入: 3
输出: [1,3,3,1]
*/

func Test_getRow(t *testing.T) {
	fmt.Println(getRow(3))
}

func getRow(rowIndex int) []int {
	arr := make([]int, 1)
	arr[0] = 1
	for i := 1; i < rowIndex+1; i++ {
		arr = append(arr, 1)
		for j := i - 1; j >= 1; j-- {
			arr[j] = arr[j] + arr[j-1]
		}
	}
	return arr
}
