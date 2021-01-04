package others;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 缺失数字
 *
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 * 输入: [3,0,1]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 *
 * @author liyaozong
 * @date 2020/9/14 17:41
 */
public class MissingNumber {
    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
        System.out.println(missingNumber2(new int[]{9,6,4,2,3,5,7,0,1}));
        System.out.println(missingNumber3(new int[]{9,6,4,2,3,5,7,0,1}));
        System.out.println(missingNumber4(new int[]{9,6,4,2,3,5,7,0,1}));
    }

    /**
     * hash
     */
    public static int missingNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i=0;i<nums.length;i++) {
            map.put(nums[i], i);
        }
        for (int i=0;i<nums.length+1;i++) {
            if (!map.containsKey(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 排序+遍历
     */
    public static int missingNumber2(int[] nums) {
        Arrays.sort(nums);
        if (nums[nums.length-1] != nums.length) {
            return nums.length;
        }
        if (nums[0] != 0) {
            return 0;
        }
        for (int i=0;i<nums.length;i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 数学公式 高斯求和
     * 有整数溢出风险
     * c系语言可以使用无符号长整形来求和
     */
    public static int missingNumber3(int[] nums) {
        int total = nums.length*(nums.length+1)/2;
        int res = 0;
        for (int num : nums) {
            res += num;
        }
        return total - res;
    }

    /**
     * 异或运算
     * 我们可以先得到 [0..n][0..n] 的异或值，再将结果对数组中的每一个数进行一次异或运算。未缺失的数在 [0..n][0..n] 和数组中各出现一次，
     * 因此异或后得到 0。而缺失的数字只在 [0..n][0..n] 中出现了一次，在数组中没有出现，因此最终的异或结果即为这个缺失的数字。
     */
    public static int missingNumber4(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
