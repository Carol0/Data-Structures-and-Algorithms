package com.carol.interview.ali;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carol
 * @date 2022/12/21
 * @since 1.0.0
 */
public class PerfectPair {
    /**
     * 有n个物品，每个物品有k个属性，第i件物品的第j个属性用一个正整数表示记为aij，两个不同的物品i,j被称为是完美对的当且仅当，
     * ai1+aj1=ai2+aj2=.....=aik+ajk
     * 求完美对的个数。
     * 输入描述：
     * 第一行两个数字n,k。
     * 接下来n行，第行k个数字表示ai1,ai2....aik
     * 输入例子：
     * 5 3
     * 2 11 21
     * 19 10 1
     * 20 11 1
     * 6 15 24
     * 18 27 36
     * 输出例子：
     * 3
     * 思路：
     * 题意：给定你n个物品，每个物品有属性，根据完美对的定义，求有多少个物品对符合完美对
     * ai1+aj1 = ai2 + aj2
     * ai2 - ai1 = aj1 - aj2 = -(aj2 - aj1)
     * 步骤：直接循环每一个物品，计算每一个物品前一个属性与后一个属性的差值，将差值连接作为一个key，记录这个key多少次
     * 当每一次访问一个物品的时候，除了计算差值的key，并计算差值相反数的key，通过查看之前已经出现过差值key的次数，
     * 我们就知道当前物品可以与之前多少个物品组成完美对
     */
    public static void main(String[] args) {
        PerfectPair perfectPair = new PerfectPair();
        int[][] nums = new int[][]{{2 ,11 ,21},{19 ,10 ,1},{20 ,11 ,1},{6 ,15 ,24},{18 ,27 ,36}};
        System.out.println(perfectPair.perfectPair(nums));
    }
    public int perfectPair(int[][] nums) {
        Map<String, Integer> countMap = new HashMap<>();
        int answer = 0;
        for (int i = 0 ; i < nums.length ; i ++) {
            //记录当前物品的差串
            StringBuilder diffStr = new StringBuilder();
            //相反数的差串
            StringBuilder convertStr = new StringBuilder();
            for (int j = 1 ; j < nums[0].length ; j++) {
                int temp = nums[i][j] - nums[i][j - 1];
                diffStr.append(temp);
                convertStr.append(temp * -1);
            }
            answer += countMap.getOrDefault(convertStr.toString(), 0);
            countMap.put(diffStr.toString(), countMap.getOrDefault(diffStr, 0) + 1);
        }
        return answer;
    }
}