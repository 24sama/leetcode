package array

import (
	"fmt"
	"math"
	"testing"
)

/*
244. Shortest Word Distance II

Design a class which receives a list of words in the constructor,
and implements a method that takes two words  word1  and  word2  and return the shortest distance between these two words in the list.
Your method will be called  repeatedly  many times with different parameters.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: _word1_ = “coding”, _word2_ = “practice”
Output: 3

Input: _word1_ = "makes", _word2_ = "coding"
Output: 1

Note:
You may assume that  word1  does not equal to  word2 , and  word1  and  word2  are both in the list.
*/

func Test_shortestWordDistanceII(t *testing.T) {
	w := new(wordMap)
	w.initWordMap([]string{"practice", "makes", "perfect", "coding", "makes"})
	fmt.Println(w.shortestWordDistanceII("coding", "practice"))
	fmt.Println(w.shortestWordDistanceII("coding", "makes"))

	fmt.Println(w.shortestWordDistanceII2("coding", "practice"))
	fmt.Println(w.shortestWordDistanceII2("coding", "makes"))
}

type wordMap struct {
	m map[string][]int
}

// 初始化哈希表 key：单次 value：原数组中的下标组成的数组
func (w *wordMap) initWordMap(words []string) {
	w.m = make(map[string][]int)
	for i, v := range words {
		w.m[v] = append(w.m[v], i)
	}
}

// 双重循环遍历 O(mn)
func (w *wordMap) shortestWordDistanceII(word1 string, word2 string) int {
	res := math.MaxFloat64
	for _, v := range w.m[word1] {
		for _, j := range w.m[word2] {
			res = math.Min(res, math.Abs(float64(v-j)))
		}
	}
	return int(res)
}

// 一次循环遍历 O(mn)优化到O(m+n)
func (w *wordMap) shortestWordDistanceII2(word1 string, word2 string) int {
	res := math.MaxFloat64
	i, j := 0, 0
	for i < len(w.m[word1]) && j < len(w.m[word2]) {
		res = math.Min(res, math.Abs(float64(w.m[word1][i]-w.m[word2][j])))
		if w.m[word1][i] < w.m[word2][j] {
			i++
		} else {
			j++
		}
	}
	return int(res)
}
