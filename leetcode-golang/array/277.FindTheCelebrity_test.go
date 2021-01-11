package array

import (
	"fmt"
	"testing"
)

/*
277. Find the Celebrity 寻找名人

Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one.
The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b)which tells you whether A knows B.
Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party.
Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

Example 1:
Input: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2.
graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j.
The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.

Example 2:
Input: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
Output: -1
Explanation: There is no celebrity.
*/

func Test_FindCelebrity(t *testing.T) {
	fmt.Println(findCelebrity(3))
	fmt.Println(findCelebrity2(3))
}

// 由题可知
// 1.如果a认识b，那么a肯定不是名人，b则有可能是名人
// 2.如果a不认识b，那么b肯定不是名人，a则可能是名人
// 利用排除法，将肯定不是名人的剔除
func findCelebrity(n int) int {
	// res代表名人候选人
	// 假设下标0为候选人
	res := 0
	for i := 0; i < n; i++ {
		// 如果res认识i，则res必不是名人，i可能是名人，则候选人变为下标i
		// 继续遍历判断当前候选人是否认识下一个i
		if knows(res, i) {
			res = i
		}
	}

	for i := 0; i < n; i++ {
		// 再次检查候选人是否认识其他人或被认识
		if res != i && (knows(res, i) || !knows(i, res)) {
			return -1
		}
	}
	return res
}

// 改进
// 通过二维数组保存认识关系
func findCelebrity2(n int) int {
	memo := make([][]int, n)
	for i := 0; i < n; i++ {
		memo[i] = make([]int, n)
	}

	res := 0
	for i := 0; i < n; i++ {
		isKnow := knows(res, i)
		if isKnow {
			res = i
			memo[res][i] = 1
		} else {
			memo[res][i] = 2
		}
	}

	for i := 0; i < n; i++ {
		if res == i {
			continue
		}

		var resKnowI bool
		if memo[res][i] != 0 {
			if memo[res][i] == 1 {
				resKnowI = true
			} else if memo[res][i] == 2 {
				resKnowI = false
			}
		} else {
			resKnowI = knows(res, i)
		}

		var iKnowRes bool
		if memo[i][res] != 0 {
			if memo[i][res] == 1 {
				iKnowRes = true
			} else if memo[i][res] == 2 {
				iKnowRes = false
			}
		} else {
			iKnowRes = knows(i, res)
		}

		if resKnowI || !iKnowRes {
			return -1
		}
	}
	return res
}

func knows(a int, b int) bool {
	amap := map[int][]int{
		0: {1, 1, 0},
		1: {0, 1, 0},
		2: {1, 1, 1},
	}
	if arr, ok := amap[a]; ok {
		if arr[b] == 1 {
			return true
		}
	}
	return false
}

func Test_knows(t *testing.T) {
	fmt.Println(knows(0, 1))
	fmt.Println(knows(2, 1))
	fmt.Println(knows(1, 0))
}
