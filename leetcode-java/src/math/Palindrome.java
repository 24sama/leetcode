package math;

/**
 * 回文数
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * @author liyaozong
 * @date 2020/9/30 10:38
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(12213));
        System.out.println(isPalindrome2(1221));
        System.out.println(isPalindrome2(12213));
        System.out.println(isPalindrome2(12321));
    }

    /**
     * 遍历比较数字左边第一位和右边第一位是否相等
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        // 先求得数字长度
        int len = 1;
        while (x / len >= 10) {
            len *= 10;
        }

        while (x > 0) {
            // 左边第一位
            int left = x / len;
            // 右边第一位
            int right = x % 10;
            if (left != right) {
                return false;
            }
            // 去掉左边第一位和右边第一位
            x = (x % len) / 10;
            // 长度减少两位
            len /= 100;
        }
        return true;
    }

    /**
     *  反转数字后半部分再与前半部分比较
     */
    public static boolean isPalindrome2(int x) {
        if (x<0 || (x%10 == 0 && x != 0)) {
            return false;
        }

        int revertedNum = 0;
        while (x > revertedNum) {
            revertedNum = revertedNum*10 + x%10;
            x /= 10;
        }
        // 原数字为偶数则直接比较，原数字为奇数则后半部分除以10去掉远数字中间那个数
        return x == revertedNum || x == revertedNum / 10;
    }
}
