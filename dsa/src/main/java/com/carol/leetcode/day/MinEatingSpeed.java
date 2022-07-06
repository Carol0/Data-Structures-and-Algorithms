package com.carol.leetcode.day;

import java.util.Arrays;

/**
 * @author Carol
 * @date 2022/6/7
 * @since 1.0.0
 */
public class MinEatingSpeed {
    /**
     * 875. 爱吃香蕉的珂珂
     * https://leetcode.cn/problems/koko-eating-bananas/
     * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，
     * 将在 h 小时后回来。
     *
     * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，
     * 从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，
     * 然后这一小时内不会再吃更多的香蕉。
     *
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
     * 输入：piles = [3,6,7,11], h = 8
     * 输出：4
     * 1 <= piles.length <= 104
     * piles.length <= h <= 109
     * 1 <= piles[i] <= 109
     *
     * 思路：如果暴力枚举速度k， 需要O（n）
     * 但是根据题意如果当前堆<k，那么这小时只吃当前堆，直接最小从1开始
     * 最大值应该为堆中最大的数值
     * 求最小可以用二分去枚举
     */
    public static void main(String[] args) {
        MinEatingSpeed minEatingSpeed = new MinEatingSpeed();
        System.out.println(minEatingSpeed.minEatingSpeed(new int[]{30,11,23,4,20}, 6));;
    }
    public int minEatingSpeed(int[] piles, int h) {
        //最小速度
        int low = 1;
        //最大速度
        int high = Arrays.stream(piles)
                .max()
                .getAsInt();

        while (low <= high) {
            //可以避免溢出
            int mid = low + ((high - low) >> 1);
            if (judge(piles, h, mid)) {
                //如果能吃完，可能大了
                high = mid - 1;
                continue;
            }
            //吃补完，小了
            low = mid + 1;
        }
        return low;
    }

    /**
     * 判断以当前速度是否可以吃完
     * @param piles
     * @param h
     * @param k
     * @return
     */
    private boolean judge(int[] piles, int h, int k) {
        int needTime = 0;
        for (int pile : piles) {
            needTime += pile % k == 0 ?  pile / k : pile / k + 1;
        }
        if (needTime <= h) {
            return true;
        }
        return false;
    }
}