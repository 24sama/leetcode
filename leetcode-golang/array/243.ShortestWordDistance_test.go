package array

import (
	"fmt"
	"math"
	"testing"
)

/*
243. Shortest Word Distance

Given a list of words and two words  word1  and  word2 , return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: _word1_ = “coding”, _word2_ = “practice”
Output: 3

Input: _word1_ = "makes", _word2_ = "coding"
Output: 1

Note:
You may assume that  word1  does not equal to  word2 , and  word1  and  word2  are both in the list.
*/

func Test_shortestWordDistance(t *testing.T) {
	words := []string{"practice", "makes", "perfect", "coding", "makes"}
	fmt.Println(shortestWordDistance(words, "coding", "practice"))
	fmt.Println(shortestWordDistance(words, "makes", "coding"))

	fmt.Println("---------")
	fmt.Println(shortestWordDistance2(words, "coding", "practice"))
	fmt.Println(shortestWordDistance2(words, "makes", "coding"))

	fmt.Println("---------")
	fmt.Println(shortestWordDistance3(words, "coding", "practice"))
	fmt.Println(shortestWordDistance3(words, "makes", "coding"))
}

// 暴力法
func shortestWordDistance(words []string, word1 string, word2 string) int {
	idx1 := make([]int, 0)
	idx2 := make([]int, 0)
	// 遍历一次数组 将字符串的下标存入两个数组中
	for i, v := range words {
		if v == word1 {
			idx1 = append(idx1, i)
		} else if v == word2 {
			idx2 = append(idx2, i)
		}
	}

	// 遍历两个数组，取距离最小的结果
	res := math.MaxInt32
	for _, v := range idx1 {
		for _, j := range idx2 {
			res = int(math.Abs(float64(v - j)))
		}
	}
	return res
}

// 一次遍历
func shortestWordDistance2(words []string, word1 string, word2 string) int {
	idx1, idx2 := -1, -1
	res := math.MaxInt32
	for i, v := range words {
		if v == word1 {
			idx1 = i
		} else if v == word2 {
			idx2 = i
		}
		if idx1 != -1 && idx2 != -1 {
			res = int(math.Abs(float64(idx1 - idx2)))
		}
	}
	return res
}

// 一次遍历+一个变量存储
func shortestWordDistance3(words []string, word1 string, word2 string) int {
	idx := -1
	res := math.MaxInt32
	for i, v := range words {
		if word1 == v || word2 == v {
			if idx != -1 && words[idx] != v {
				res = int(math.Abs(float64(idx - i)))
			}
			idx = i
		}
	}
	return res
}
