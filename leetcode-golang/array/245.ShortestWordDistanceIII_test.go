package array

import (
	"fmt"
	"math"
	"testing"
)

/**
245. Shortest Word Distance III

Given a list of words and two words  word1  and  word2 , return the shortest distance between these two words in the list.
word1  and  word2  may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: _word1_ = “makes”, _word2_ = “coding”
Output: 1

Input: _word1_ = "makes", _word2_ = "makes"
Output: 3

Note:
You may assume  word1  and  word2  are both in the list.
*/
func Test_shortestWordDistanceIII(t *testing.T) {
	words := []string{"practice", "makes", "perfect", "coding", "makes"}
	fmt.Println(shortestWordDistanceIII(words, "coding", "makes"))
	fmt.Println(shortestWordDistanceIII(words, "makes", "makes"))

	fmt.Println(shortestWordDistanceIII2(words, "coding", "makes"))
	fmt.Println(shortestWordDistanceIII2(words, "makes", "makes"))
}

func shortestWordDistanceIII(words []string, word1 string, word2 string) int {
	idx1, idx2 := len(words), -len(words)
	res := math.MaxFloat32
	for i, v := range words {
		if v == word1 {
			if word1 == word2 {
				idx1 = idx2
			} else {
				idx1 = i
			}
		}
		if v == word2 {
			idx2 = i
		}
		if idx1 > -1 && idx2 > -1 {
			res = math.Min(res, math.Abs(float64(idx1-idx2)))
		}
	}
	return int(res)
}

func shortestWordDistanceIII2(words []string, word1 string, word2 string) int {
	idx := -1
	res := math.MaxFloat32
	for i, v := range words {
		if v == word1 || v == word2 {
			if idx != -1 && (word1 == word2 || words[idx] != v) {
				res = math.Min(res, math.Abs(float64(idx-i)))
			}
			idx = i
		}
	}
	return int(res)
}
