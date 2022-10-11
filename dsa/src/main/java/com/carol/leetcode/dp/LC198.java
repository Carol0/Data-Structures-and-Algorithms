package com.carol.leetcode.dp;

/**
 * @author Carol
 * @date 2022/10/11
 * @since 1.0.0
 */
public class LC198 {
    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *     偷窃到的最高金额 = 1 + 3 = 4 。
     * 分析：从房间中偷到最多的现金（结果），并且相邻房间不能同时偷（制约条件），
     * 当前位置的处理方式，会影响后面位置的处理（后效性），采用动态规划
     * 思路：
     * 1。转移方程
     *  假设f（i）表示在i以及i之前的房间最多偷到的现金数，f（i）= Math.max(f(i - 1), f[i - 2] + num[i])
     * 2。初始条件
     * f(0) = nums[0]
     * f(1) = Math.max(num[1], num[0])
     *
     * 1 <= nums.length <= 100
     */
    public static void main(String[] args) {
        LC198 lc198 = new LC198();
        System.out.println(lc198.rob2(new int[]{1,2}));
    }
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //记录到当前房间偷到的最多的现金
        int[] f = new int[nums.length];
        //进行初始化
        f[0] = nums[0];
        f[1] = Math.max(nums[0], nums[1]);
        //开始进行转移f（i）= Math.max(f(i - 1), f[i - 2] + num[i])
        for (int i = 2 ; i < nums.length ; i++) {
            f[i] = Math.max(f[i - 1], f[i - 2] + nums[i]);
        }
        return f[nums.length - 1];
    }

    //优化空间，因为在进行转移的时候，只用到了当前位置，i-1以及i-2位置，因此可以不用申请一个数组，只需要两个临时变量保存即可
    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //第一间
        int a0 = nums[0];
        //第二间
        int a1 = Math.max(nums[0], nums[1]);
        for (int i = 2 ; i < nums.length ; i ++) {
            int temp = Math.max(a0 + nums[i], a1);
            a0 = a1;
            a1 = temp;
        }
        return Math.max(a0, a1);
    }
}