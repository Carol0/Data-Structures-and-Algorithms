package com.carol.interview.unkonw;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/12/24
 * @since 1.0.0
 */
public class Lis {
    /**
     * 给定一个序列，找到严格递增的最长子序列
     * 最长上升子序列
     * 1 4 2 6 4 9
     * 两种方案
     * 1.动态规划
     * 2.贪心+二分
     */
    public static void main(String[] args) {
        Lis lis = new Lis();
        int[] nums = new int[]{1,4,2,5,6,4,9};
        System.out.println(lis.dp(nums));
        System.out.println(lis.greedy(nums));
    }

    /**
     * 动态规划-》转移方程
     * 首先我们用一个数组dp
     * dp[i]表示以第i个位置元素为结尾的严格递增子序列的最长的数量
     * 转移方程应该是：从第一个位置到当前位，满足条件 nums[k] < nums[i] -> dp[k] + 1
     * @return
     */
    public int dp(int[] nums) {
        //用来记录以当前位置为结尾的最长子序列的数量
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int answer = 0;
        for (int i = 1 ; i < nums.length ; i ++) {
            dp[i] = 1;
            for (int j = 0 ; j < i ; j ++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i],  dp[j] + 1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }

    /**
     * 初始化一个min数组
     * min[i]表示的是对于长度为i的最长子序列，记录最小结尾
     * min数组应该是严格递增的，最后一个是最大的
     * 当遍历数组的时候，如果当前元素大于最后一个元素，证明最长子序列可以增长，直接将当前元素add到后面
     * 如果当前元素小于最后一个元素，找到直接已经存在的第一个大于等于当前元素的元素进行替换
     * 遍历完成之后，min的长度应该是最长子序列的长度，但是min中的序列不一定是最长子序列
     * @return
     */
    public int greedy(int[] nums) {
        List<Integer> min = new ArrayList<>();
        min.add(nums[0]);
        for (int i = 1 ; i < nums.length ; i++) {
            if (nums[i] > min.get(min.size() - 1)) {
                min.add(nums[i]);
                continue;
            }
            int index = binarySearch(nums[i], min, 0, min.size() - 1);
            min.set(index, nums[i]);
        }
        return min.size();
    }

    private int binarySearch(int num, List<Integer> nums, int start, int end) {
        if (start > end) {
            return start;
        }
       int mid = (start + end) >> 1;
        mid = start + ((end - start ) >> 1);
       if (num == nums.get(mid)) {
           return mid;
       }
       if (num > nums.get(mid)) {
           return binarySearch(num, nums, mid + 1, end);
       }
       return binarySearch(num, nums, start, mid - 1);
    }
}