package dynamicProgramming;

/**
 * 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *  
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobbery {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        System.out.println(rob(nums));
        System.out.println(rob2(nums));
        System.out.println(rob3(nums));
        System.out.println(rob4(nums));
        int[] nums2 = {2,1,1,2};
        System.out.println(rob(nums2));
        System.out.println(rob2(nums2));
        System.out.println(rob3(nums2));
        System.out.println(rob4(nums2));
    }

    /**
     * 二维dp解法 空间复杂度较高
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // dp[i][j] i表示第i天 j表示是否偷窃该家 0 偷窃该家 1 不偷窃该家
        int[][] dp = new int[nums.length][2];
        // 第一天的情况
        dp[0][0] = nums[0];
        dp[0][1] = 0;
        int max = nums[0];
        for (int i=1;i<nums.length;i++) {
            // 第i天偷窃该家的情况由两种状态转移过来，二者取大：
            // 1. i-1天未偷窃，第i天进行偷窃
            // 2. 以前偷窃最多的情况
            dp[i][0] = Math.max(dp[i-1][1]+nums[i], max);
            // 第i天不偷窃该家的情况由两种状态转移过来，二者取大：
            // 1. i-1天进行偷窃
            // 2. 以前偷窃最多的情况
            dp[i][1] = Math.max(dp[i-1][0], max);
            // 第i天偷窃最多的情况即为i天偷窃该家或不偷窃该家两种状态取大
            max = Math.max(dp[i][0], dp[i][1]);
        }
        return max;
    }

    /**
     * 一维dp解法
     */
    public static int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // dp[i] 代表前i个房子在满足条件下的能偷窃到的最高金额
        int[] dp = new int[nums.length];
        //设： 有n个房子，前n间能偷窃到的最高金额是dp[n]，前n−1间能偷窃到的最高金额是dp[n−1] ，此时向这些房子后加一间房，此房间价值为num ；
        //加一间房间后： 由于不能抢相邻的房子，意味着抢第n+1间就不能抢第n间；那么前n+1间房能偷取到的最高金额dp[n+1]一定是以下两种情况的较大值：
        //
        //不抢第n+1个房间，因此等于前n个房子的最高金额，即 dp[n+1] = dp[n]；
        //抢第n+1个房间，此时不能抢第n个房间；因此等于前n-1个房子的最高金额加上当前房间价值，即 dp[n+1] = dp[n-1] + num；
        //细心的我们发现： 难道在前n间的最高金额dp[n]情况下，第n间一定被偷了吗？
        //假设没有被偷，那n+1间的最大值应该也可能是 dp[n+1] = dp[n] + num吧？其实这种假设的情况可以被省略，这是因为：
        //假设第n间没有被偷，那么此时dp[n] = dp[n-1]，此时dp[n+1] = dp[n] + num = dp[n-1] + num，即两种情况可以 合并为一种情况考虑；
        //假设第n间被偷，那么此时dp[n+1] = dp[n] + num不可取 ，因为偷了第n间就不能偷第 n+1间。
        //最终的转移方程： dp[n+1] = max(dp[n],dp[n-1]+num)
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i=2;i<nums.length;i++) {
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[nums.length-1];
    }

    /**
     * dp简化版
     *
     * 简化空间复杂度：
     * 我们发现dp[n]与dp[n-1]和dp[n-2]有关系，因此我们可以设两个变量cur和pre交替记录，将空间复杂度降到 O(1)。
     */
    public static int rob3(int[] nums) {
        int cur=0,pre=0,tmp;
        for (int num : nums) {
            tmp = cur;
            cur = Math.max(cur, pre + num);
            pre = tmp;
        }
        return cur;
    }

    /**
     * dp 简化
     * dp_i = dp[n]
     * dp_i_1 = dp[n-1]
     * dp_i_2 = dp[n-2]
     */
    public static int rob4(int[] nums) {
        int dp_i=0,dp_i_1=0,dp_i_2=0;
        for (int num : nums) {
            dp_i = Math.max(dp_i_1, dp_i_2+num);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
