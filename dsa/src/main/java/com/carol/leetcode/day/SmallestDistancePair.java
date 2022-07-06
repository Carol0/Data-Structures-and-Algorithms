package com.carol.leetcode.day;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Carol
 * @date 2022/6/15
 * @since 1.0.0
 */
public class SmallestDistancePair {
    /**
     *https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     * 719. 找出第 K 小的数对距离
     * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
     * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成
     * 且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
     * 输入：nums = [1,3,1], k = 1
     * 输出：0
     * 解释：数对和对应的距离如下：
     * (1,3) -> 2
     * (1,1) -> 0
     * (3,1) -> 2
     * 距离第 1 小的数对是 (1,1) ，距离为 0 。
     */
    public static void main(String[] args) {
        SmallestDistancePair pain = new SmallestDistancePair();
        System.out.println(pain.smallestDistancePair2(new int[]{1,6,1}, 3));;
    }

    /**
     * 思路：返回第k小的数堆距离，明显的优先队列提示，可以维护一个k大的优先队列，
     * 当队列不满k时，将当前数对距离入队，如果当前已经满k，则直接与队首元素比较，小于
     * 则入队，大于则丢弃
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int i = 0 ; i < nums.length ; i ++) {
            for (int j = i  + 1 ; j < nums.length ; j ++) {
                int distance = Math.abs(nums[i] - nums[j]);
                if (queue.size() == k) {
                    if (queue.peek() < distance) {
                        continue;
                    }
                    queue.poll();
                    queue.offer(distance);
                } else {
                    queue.offer(distance);
                }
            }
        }
        return queue.peek();
    }

    /**
     * 1.先将nums进行排序
     * 2.需要查找的第k小的距离一定存在于0～（numsMax-numsMin）之间，二分枚举这个距离
     * 3.对于每一个枚举的距离，计算小于等于该距离的距离出现的次数，如果为k，则找到，如果大于k
     * 距离大了，走左边，否则走右边
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 0, end = nums[nums.length - 1] - nums[0];
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            int ant = 0;
            for (int j = 0 ; j < nums.length ; j ++) {
                int i = this.binarySearch(nums, 0 , j , nums[j] - mid);
                ant  += j - i;

            }
            if (ant >= k) {
                end = mid - 1;
                continue;
            }
            start = mid + 1;
        }
        return start;
    }

    /**
     *
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int binarySearch(int[] nums, int start, int end, int target) {
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] < target) {
                start  = mid + 1;
                continue;
            }
            end = mid - 1;
        }
        return start;
    }
 }