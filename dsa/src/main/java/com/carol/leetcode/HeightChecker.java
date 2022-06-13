package com.carol.leetcode;

import java.util.Arrays;

/**
 * @author Carol
 * @date 2022/6/13
 * @since 1.0.0
 */
public class HeightChecker {
    /**
     * https://leetcode.cn/problems/height-checker/
     * 1051. 高度检查器
     * 学校打算为全体学生拍一张年度纪念照。根据要求，
     * 学生需要按照 非递减 的高度顺序排成一行。
     * 排序后的高度情况用整数数组 expected 表示，
     * 其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
     * 给你一个整数数组 heights ，
     * 表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。
     * 返回满足 heights[i] != expected[i] 的 下标数量 。
     */

    public static void main(String[] args) {
        HeightChecker h = new HeightChecker();
        System.out.println(h.heightChecker(new int[]{5,1,2,3,4}));
    }

    public int heightChecker(int[] heights) {
        int[] expected = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expected);
        int result = 0;
        for (int i = 0 ; i < heights.length ; i++) {
            if (expected[i] != heights[i]) {
                ++ result;
            }
        }
        return result;
    }
}