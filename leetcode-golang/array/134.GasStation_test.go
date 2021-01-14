package array

import (
	"fmt"
	"testing"
)

/*
34. 加油站

在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

说明:
如果题目有解，该答案即为唯一答案。
输入数组均为非空数组，且长度相同。
输入数组中的元素均为非负数。

示例 1:
输入:
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]
输出: 3
解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。

示例 2:
输入:
gas  = [2,3,4]
cost = [3,4,3]
输出: -1
解释:
你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。
*/

func Test_canCompleteCircuit(t *testing.T) {
	gas := []int{1, 2, 3, 4, 5}
	cost := []int{3, 4, 5, 1, 2}
	fmt.Println(canCompleteCircuit(gas, cost))

	fmt.Println(canCompleteCircuit2(gas, cost))
}

// 贪心算法
// 如果总加油量 sum(gas) >= sum(cost) 总耗油量，问题一定有解。
// 我们从起点 0 开始，累加每个站点的 gas[i]-cost[i]，即 left(i)
// 当站 i 累加完 left(i) 后，如果小于 0，则站 0 到 站 i 都不是起点，[0,i] 段的 ∑left<0
// 我们将 i+1 作为起点，重新累加每个站点的 left(i)
// 当站 j 累加完 left(j)，如果小于 0，则站 i+1 到站 j 都不是起点。[i+1,j] 段 ∑left<0
// 继续考察新起点......不可能一直 ∑left<0 下去
// 因为 sum(gas) >= sum(cost)，对于整个数组有 ∑left>=0
// 因此必然有一段是 ∑left>0，假设此时起点更新为 k，以 k 为起点的这一段能加到足够的油，足以填补其他段欠缺的量。
func canCompleteCircuit(gas []int, cost []int) int {
	// 余量，初始起点
	left, start := 0, 0
	totalGas, totalCost := 0, 0
	for i := 0; i < len(gas); i++ {
		totalGas += gas[i]
		totalCost += cost[i]
		// 累加每次的余量
		left += gas[i] - cost[i]
		// 若余量小于0，表示去不了下一站，则0到i都不能作为起点
		if left < 0 {
			// 把i+1作为起点
			start = i + 1
			// 余量归0
			left = 0
		}
	}
	// 若总油量不够，肯定无解
	if totalGas < totalCost {
		return -1
	}
	return start
}

// 暴力
func canCompleteCircuit2(gas []int, cost []int) int {
	length := len(gas)
	// 拼接一次数组，保证内层循环从数组前半段每一个下标开始都能走过以前数组的每一个元素
	gas = append(gas, gas...)
	cost = append(cost, cost...)
	for i := 0; i < length; i++ {
		left := 0
		isBreak := true
		// 模拟从当前站点i开始出发
		for j := i; j < i+length; j++ {
			left += gas[j] - cost[j]
			if left < 0 {
				isBreak = false
				break
			}
		}
		// 若内层循环left余量均没有小于0的情况，则isBreak=true函数直接返回开始站点i
		if isBreak {
			return i
		}
	}
	return -1
}
