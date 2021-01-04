package math;

/**
 * 3的幂
 *
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * 示例 1:
 * 输入: 27
 * 输出: true
 * 示例 2:
 *
 * 输入: 0
 * 输出: false
 * 示例 3:
 *
 * 输入: 9
 * 输出: true
 * 示例 4:
 *
 * 输入: 45
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 *
 * @author liyaozong
 * @date 2020/9/9 16:24
 */
public class PowerOfThree {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(45));
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree2(45));
        System.out.println(isPowerOfThree2(27));
    }

    /**
     * 递归
     */
    public static boolean isPowerOfThree(int n) {
        if (n == 1) {
            return true;
        } else if (n < 1 || n%3 != 0){
            return false;
        } else {
            return isPowerOfThree(n/3);
        }
    }

    /**
     * 换底公式
     * logAB = logCB / logCA
     * 换底完成的结果%1==0 可以判断是否是整数 不是则说明n不是3的幂次方
     */
    public static boolean isPowerOfThree2(int n) {
        return (Math.log10(n)/Math.log10(3)) % 1 == 0;
    }
}
