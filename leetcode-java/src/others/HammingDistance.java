package others;

/**
 * 汉明距离
 *
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * @author liyaozong
 * @date 2020/9/10 19:31
 */
public class HammingDistance {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1,4));
        System.out.println(hammingDistance2(1,4));
    }

    /**
     * X^y后得到的数就包含了x与y所有不同的二进制位 再遍历int类型长度检查最右位是否为1
     * 取模运算（i%2）和AND操作（i&1）都可以屏蔽最右位以外的位
     */
    public static int hammingDistance(int x, int y) {
        int xory = x^y;
        int count = 0;
        for (int i=0;i<32;i++) {
            if (((xory >>> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 布赖恩·克尼根算法
     * 当我们在 number 和 number-1 上做 AND 位运算时，原数字 number 的最右边等于 1 的比特会被移除。
     * 例如：
     * x       = 10001000
     * x-1     = 10000111
     * x&(x-1) = 10000000
     */
    public static int hammingDistance2(int x, int y) {
        int xory = x^y;
        int count = 0;
        while (xory != 0) {
            count++;
            xory &= (xory - 1);
        }
        return count;
    }
}
