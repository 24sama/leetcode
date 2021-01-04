package array;

import java.util.Stack;

/**
 * 接雨水
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author liyaozong
 * @date 2020/9/18 17:51
 */
public class CatchRain {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap3(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    /**
     * 单调栈
     * 时间复杂度 o(n) leetcode 4ms
     */
    public static int trap(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int cur = stack.peek();
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                // 求出凹槽的高度
                int h = Math.min(height[left], height[i]) - height[cur];
                res += (i - left - 1) * h;
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 暴力按列计算当前列的水量
     * 时间复杂度o（n^2）巨慢 leetcode 77ms
     */
    public static int trap2(int[] height) {
        int res = 0;
        // 最两端的列不用考虑，一定不会有水。所以下标从1到length-2
        for (int i = 1; i < height.length - 1; i++) {
            //找出左边最高
            int maxleft = 0;
            for (int j = i - 1; j >= 0; j--) {
                maxleft = Math.max(height[j], maxleft);
            }

            //找出右边最高
            int maxRight = 0;
            for (int j = i + 1; j < height.length; j++) {
                maxRight = Math.max(height[j], maxRight);
            }

            //找出两者小的一个
            int minColumn = Math.min(maxleft, maxRight);
            //只有minColumn的高度大于当前列才会储存到水
            if (minColumn > height[i]) {
                res += minColumn - height[i];
            }
        }
        return res;
    }

    /**
     * 暴力改进的dp
     * 时间复杂度o(n) leetcode 1ms
     * 空间复杂度o(n)
     */
    public static int trap3(int[] height) {
        int res = 0;
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++) {
            int minColumn = Math.min(maxLeft[i], maxRight[i]);
            if (minColumn > height[i]) {
                res += minColumn - height[i];
            }
        }
        return res;
    }
}
