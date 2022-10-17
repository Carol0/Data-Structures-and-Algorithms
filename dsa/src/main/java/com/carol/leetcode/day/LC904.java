package com.carol.leetcode.day;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carol
 * @date 2022/10/17
 * @since 1.0.0
 */
public class LC904 {
    /**
     * 你正在探访一家农场，农场从左到右种植了一排果树。
     * 这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
     * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
     * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
     * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。
     * 采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
     * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
     * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
     *
     * 分析：总共只有两个篮子，每个篮子只能装一种水果，每棵树通过fruits数组标志了当前树的水果种类
     * 也就是只能左到右遍历一次，并且是连续的，从第i棵树开始，后面的每棵树都需要被采摘，
     * 当不符合篮子类型的时候需要停止。
     * 思路：暴力解法，遍历fruits数组，以每棵树为起点，进行计算
     * 输入：fruits = [1,2,1]
     * 输出：3
     * 解释：可以采摘全部 3 棵树。
     */
    public static void main(String[] args) {
        LC904 lc904 = new LC904();
        System.out.println(lc904.totalFruit2(new int[]{1,1,2,3}));
    }
    public int totalFruit(int[] fruits) {
        //以i位置为起点，进行装篮
        int kind1;
        int kind2;
        int max = 0;
        int start = 0;
        int temp;
        while (start < fruits.length) {
            temp = 0;
            kind1 = -1;
            kind2 = -1;
            for (int i = start ; i < fruits.length ; i++) {
                if (kind1 == -1) {
                    kind1 = fruits[i];
                    ++ temp;
                    continue;
                }
                if (kind1 != fruits[i] && kind2 == -1) {
                    ++ temp;
                    kind2 = fruits[i];
                    continue;
                }
                if (fruits[i] != kind1 && fruits[i] != kind2) {
                    max = Math.max(max, temp);
                    break;
                }
                ++temp;
            }
            ++ start;
            max = Math.max(max, temp);
            if (max >= fruits.length - start) {
                return max;
            }
        }
        return max;
    }

    /**
     * 利用滑动窗口（left指向第一棵树，right指向最后一棵树），当窗口内树的种类不大于2的时候，符合要求
     * 可以直接算入答案，当大于2的时候，将窗口左边指针（left）往右滑动，直到重新回到不大于2即可计算答案
     * @param fruits
     * @return
     */
    public int totalFruit2(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        int left = 0, ans = 0;
        for (int right = 0; right < n; ++right) {
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while (cnt.size() > 2) {
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}