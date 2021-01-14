package array

import (
	"fmt"
	"testing"
)

/*
299. 猜数字游戏

你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：

你写出一个秘密数字，并请朋友猜这个数字是多少。
朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
朋友根据提示继续猜，直到猜出秘密数字。

请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛。
xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。

示例 1:
输入: secret = "1807", guess = "7810"
输出: "1A3B"
解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。

示例 2:
输入: secret = "1123", guess = "0111"
输出: "1A1B"
解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。

说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
*/

func Test_getHint(t *testing.T) {
	fmt.Println(getHint("1807", "7810"))
	fmt.Println(getHint("1123", "0111"))
	fmt.Println(getHint("1122", "1222"))

	fmt.Println("------------")
	fmt.Println(getHint2("1807", "7810"))
	fmt.Println(getHint2("1123", "0111"))
	fmt.Println(getHint2("1122", "1222"))
}

// 利用map进行两次遍历
func getHint(secret string, guess string) string {
	secretMap := make(map[byte]int, 0)
	guessMap := make(map[byte]int, 0)
	secretArr := []byte(secret)
	guessArr := []byte(guess)
	bulls := 0
	cows := 0

	// 判断如果同位置且值相同则bull增加
	// 否则将值和值出现的次数存入map
	for i := 0; i < len(secretArr); i++ {
		if secretArr[i] == guessArr[i] {
			bulls++
		} else {
			if num, ok := secretMap[secretArr[i]]; ok {
				secretMap[secretArr[i]] = num + 1
			} else {
				secretMap[secretArr[i]] = 1
			}
			if num, ok := guessMap[guessArr[i]]; ok {
				guessMap[guessArr[i]] = num + 1
			} else {
				guessMap[guessArr[i]] = 1
			}
		}
	}

	// 遍历秘密数字
	for i, v := range secretMap {
		// 如果猜测数字里面包含秘密数字，则从两个map里面取最小值
		if num, ok := guessMap[i]; ok {
			if num < v {
				cows += num
			} else {
				cows += v
			}
		}
	}
	return fmt.Sprintf("%vA%vB", bulls, cows)
}

// 一次遍历
// 申请一个长度为10的数组，作为记录数字出现次数，数组下标即表示数字，元素值表示出现次数
func getHint2(secret string, guess string) string {
	bulls := 0
	cows := 0
	cache := make([]int, 10)
	secretArr := []byte(secret)
	guessArr := []byte(guess)
	for i := 0; i < len(secretArr); i++ {
		s := secretArr[i] - '0'
		g := guessArr[i] - '0'
		if s == g {
			bulls++
		} else {
			// 通过secret数组当前值进行匹配查找，若在共享数组中当前值的次数小于0，则说明之前在guess中出现过
			if cache[s] < 0 {
				cows++
			}
			// 通过guess数组当前值进行匹配查找，若在共享数组中当前值的次数大于0，则说明之前在secret中出现过
			if cache[g] > 0 {
				cows++
			}
			// 更新共享数组中元素的次数
			// 例：值=1表示该数字在secret数组中出现过1次，值=-1表示该数字在secret数组中出现过1次
			cache[s]++
			cache[g]--
		}
	}
	return fmt.Sprintf("%vA%vB", bulls, cows)
}
