package math;

/**
 * 计数质数
 *
 * 统计所有小于非负整数 n 的质数的数量。
 * 示例:
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * @author liyaozong
 * @date 2020/9/8 10:54
 */
public class CalculatePrimeNumber {
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
        System.out.println(countPrimes2(10));
    }

    /**
     * 暴力法
     * leetcode 会超时
     */
    public static int countPrimes(int n) {
        int count = 0;
        for (int i=2; i<n; i++) {
            boolean flag = true;
            for (int j=2; j<i;j ++) {
                if (i%j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }

    /**
     * 厄拉多塞筛法
     * 本质是从小的数字开始遍历，并将该数的所有倍数标识为不是质数，当之后遍历到这些倍数时就直接忽略进行下次循环
     */
    public static int countPrimes2(int n) {
        // 初始化一个长度为n的数组 用于判断数组下标处的数字是否为质数
        // 布尔数组初始化默认值为false
        boolean[] arr = new boolean[n];
        int count = 0;
        for (int i=2;i<n;i++) {
            // 数组下标为i的元素值为false则说明为质数
            if (!arr[i]) {
                count++;
                // 从元素i的2倍开始，整个数组中i的倍数都不是质数，则将数组的这些位置置为true
                for (int j=i+i; j<n ;j+=i) {
                    arr[j] = true;
                }
            }
        }
        return count;
    }

}
