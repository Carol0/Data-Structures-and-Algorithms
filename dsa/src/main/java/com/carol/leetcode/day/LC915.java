package com.carol.leetcode.day;

/**
 * @author Carol
 * @date 2022/10/24
 * @since 1.0.0
 */
public class LC915 {
    /**
     * 给定一个数组nums，将其划分为两个连续子数组left和right，使得：
     *
     * left中的每个元素都小于或等于right中的每个元素。
     * left 和right都是非空的。
     * left 的长度要尽可能小。
     * 在完成这样的分组后返回left的长度。
     *
     * 用例可以保证存在这样的划分方法。
     * 测试用例：
     * 输入：nums = [1,1,1,0,6,12]
     * 输出：4
     * 解释：left = [1,1,1,0]，right = [6,12]
     *
     * 分析：题意就是给定了一个nums，将其分成两个部分，其中左边部分小于等于右边的部分同时left要尽可能的小
     * 思路：维护一个下标leftPos（表示从该位置开始划分，也就是说该位置及左边部分小于等于右边部分），
     * 维护一个左边部分的最大值leftMax，遍历数组，当前下标的值大于leftMax,证明我的划分没有错误
     * 但是如果当前的值大于了leftMax，证明之前划分错误，需要更新下标。
     */
    public static void main(String[] args) {
        LC915 lc915 = new LC915();
        System.out.println(lc915.partitionDisjoint(new int[]{5,0,3,8,6}));;
    }
    public int partitionDisjoint(int[] nums) {
        int leftPos = 0, leftMax = nums[0];
        int curMax = leftMax;
        for (int i = 1; i < nums.length - 1; i ++) {
            //记录当前位置的最大值
            curMax = Math.max(curMax, nums[i]);
            //如果当前位置的值小于之前已经划分的左边的最大值，证明之前划分错误，需要纠正
            if (leftMax > nums[i]) {
                leftPos = i;
                leftMax = curMax;
            }
        }
        return leftPos + 1;
    }
}