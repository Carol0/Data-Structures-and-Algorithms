package com.carol.leetcode.day;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Carol
 * @date 2022/10/26
 * @since 1.0.0
 */
public class LC862 {
    /**
     * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，
     * 并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
     * 子数组 是数组中 连续 的一部分。
     * 1 <= nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * 1 <= k <= 10^9
     *  分析：从数组中找到一段子数组，子数组之和大于等于k，数组中有正负号，从头到尾相加时候可能有大有小
     *  思路：求的子数组只之和，很容易想到前缀和，假设前缀和数组为prefixSum[],则[i,j]的和=prefixSum[j]-prefixSum[i]
     *  一段数组，采用双指针的方式，start指向子数组开始，end指向子数组结尾，如果区间和大于等于k，证明为备选项，只要找到所有备选项
     *  找到其中最小值即可。
     *  遍历nums，以每一个数组值为start，往后找以这个为起始点，最短的符合要求的区间
     *  nums = [2,-1,2], k = 3
     * 输出：3
     */
    public static void main(String[] args) {
        LC862 lc862 = new LC862();
        System.out.println(lc862.shortestSubarray(new int[]{1,2}, 2));
    }

    /**
     * 暴力解法，通过以每一个元素为起点进行计算
     * @param nums
     * @param k
     * @return
     */
    public int shortestSubarray(int[] nums, int k) {
        //计算前缀和
        long[] prefixSum = new long[nums.length + 1];
        for (int i = 0 ; i < nums.length ; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int start = 0;
        int result = nums.length + 1;
        //以start为起点，计算每一个符合题意的区间
        while (start < prefixSum.length) {
            //决定end
            boolean flag = true;
            for (int i = start ; i < prefixSum.length ; i ++) {
                while (i <= start && prefixSum[i] - prefixSum[start] >= k) {
                    result = Math.min(result, i - start);
                    ++ start;
                    flag = false;
                }
            }
            if (flag) {
                ++ start;
            }
        }
        return result < nums.length + 1 ? result : -1;
    }

    /**
     * 优化：利用双端队列，存储之前已经遍历的前缀点
     * 如果当前端点与之前前缀点构成区间符合条件，则更新result，之前端点值已经找到以他为起点的最短路径
     * 如果其中还存在端点<=当前和，则如果以该端点为起点的区间往后的区间一定比当前端点为起点的区间大
     * @param nums
     * @param k
     * @return
     */
    public int shortestSubarray2(int[] nums, int k) {
        //计算前缀和
        long[] prefixSum = new long[nums.length + 1];
        for (int i = 0 ; i < nums.length ; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int result = nums.length + 1;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0 ; i <= prefixSum.length ; i ++) {
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                result = Math.min(i - deque.pollFirst(), result);
            }
            while (!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return result < nums.length + 1 ? result : -1;
    }
}