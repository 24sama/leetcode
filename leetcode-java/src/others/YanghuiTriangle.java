package others;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 杨辉三角
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * @author liyaozong
 * @date 2020/9/11 16:04
 */
public class YanghuiTriangle {
    public static void main(String[] args) {
        System.out.println(generate(5));
        System.out.println(generate2(5));
    }

    /**
     * list加队列
     */
    public static List<List<Integer>> generate(int numRows) {
        // 构造整个三角形
        List<List<Integer>> total = new ArrayList<>();
        // 上一层的数字队列
        Queue<Integer> pre = new LinkedList<>();
        for (int i=1; i<=numRows; i++) {
            // 构造每一层的数组
            List<Integer> floor = new ArrayList<>();
            // 第一层特殊处理，主要因为该层数字不进入队列
            if (i == 1) {
                floor.add(1);
                total.add(floor);
                continue;
            }

            for (int j=1; j<=i; j++) {
                if (j == 1) {
                    floor.add(1);
                    pre.offer(1);
                } else if (j == i){
                    floor.add(1);
                    pre.offer(1);
                } else {
                    int num = pre.poll() + pre.poll();
                    floor.add(num);
                    // 除了每层两边两个数字1之外，每个数字都会被使用两次，所以入队两次
                    pre.offer(num);
                    pre.offer(num);
                }
            }
            total.add(floor);
        }
        return total;
    }

    /**
     * 不使用队列
     */
    public static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> total = new ArrayList<>();
        for (int i=0; i<numRows; i++) {
            List<Integer> floor = new ArrayList<>();
            for (int j=0; j<=i; j++) {
                if (j==0||j==i) {
                    floor.add(1);
                } else {
                    floor.add(total.get(i-1).get(j-1) + total.get(i-1).get(j));
                }
            }
            total.add(floor);
        }
        return total;
    }
}
