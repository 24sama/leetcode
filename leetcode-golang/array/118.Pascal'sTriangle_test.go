package array

import (
	"container/list"
	"fmt"
	"testing"
)

/*
118. 杨辉三角

给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

func Test_PascalTriangle(t *testing.T) {
	fmt.Println(generate(5))
	fmt.Println(generate(2))
}

func generate(numRows int) [][]int {
	depth := make([][]int, 0)

	queue := list.New()
	for i := 0; i < numRows; i++ {

		layer := make([]int, i+1)
		layer[0] = 1
		if len(layer) > 1 {
			queue.PushBack(1)
		}
		for j := 1; j < i; j++ {
			num1 := queue.Front()
			x := queue.Remove(num1)
			num2 := queue.Front()
			y := queue.Remove(num2)

			res := x.(int) + y.(int)
			layer[j] = res
			queue.PushBack(res)
			queue.PushBack(res)
		}
		layer[len(layer)-1] = 1
		if len(layer) > 1 {
			queue.PushBack(1)
		}
		depth = append(depth, layer)
	}
	return depth
}
