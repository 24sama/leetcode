package array;

/**
 * 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * @author liyaozong
 * @date 2020/9/18 17:12
 */
public class MaxArea {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    /**
     * 双指针
     */
    public static int maxArea(int[] height) {
        int max = 0;
        int cur = 0;
        int len = height.length;
        int left = 0;
        int right = len-1;
        while (left<right) {
            cur = Math.min(height[left], height[right]) * (right-left);
            if (cur > max) {
                max = cur;
            }
            // 当左边高度小于右边时，移动右边对结果无影响无意义所以移动左边界尝试增加高度
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
