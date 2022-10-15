package com.carol.leetcode.day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Carol
 * @date 2022/10/15
 * @since 1.0.0
 */
public class LC1441 {
    /**
     * 给你一个数组 target 和一个整数 n。每次迭代，需要从 list = { 1 , 2 , 3 ..., n } 中依次读取一个数字。
     * 请使用下述操作来构建目标数组 target ：
     * "Push"：从 list 中读取一个新元素， 并将其推入数组中。
     * "Pop"：删除数组中的最后一个元素。
     * 如果目标数组构建完成，就停止读取更多元素。
     * 题目数据保证目标数组严格递增，并且只包含 1 到 n 之间的数字。
     * 请返回构建目标数组所用的操作序列。如果存在多个可行方案，返回任一即可。
     *
     * 输入：target = [1,3], n = 3
     * 输出：["Push","Push","Pop","Push"]
     * 解释：
     * 读取 1 并自动推入数组 -> [1]
     * 读取 2 并自动推入数组，然后删除它 -> [1]
     * 读取 3 并自动推入数组 -> [1,3]
     *
     * 分析：1。入参是target和n，target中的元素来自1-n
     * 2。1-n只能依次读取
     * 3。通过依次读取1-n中的元素构建出target数组
     * 4。返回操作过程
     * 思路：可以直接遍历1- target[target.length-1]
     * 时间复杂度 O（n） 空间复杂度O（n）
     */
    public static void main(String[] args) {
        LC1441 lc1441 = new LC1441();
        List<String> result = lc1441.buildArray2(new int[]{1,3}, 3);
        System.out.println("");
    }
    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        Set<Integer> targetSet = new HashSet<>();
        for (int i = 0 ; i < target.length ; i++) {
            targetSet.add(target[i]);
        }
        //用来记录栈中有多少个不属于target的数值没有出栈
        int count = 0;
        for (int i = 1 ; i <= target[target.length - 1] ; i ++) {
            //如果当前的元素在target中，需要将之前不属于target的元素出栈
            if (targetSet.contains(i)) {
                for (int j = 0 ; j < count ; j ++) {
                    result.add("Pop");
                }
                count = 0;
            } else {
                ++ count;
            }
            //每个元素都会入栈
            result.add("Push");
        }
        return result;
    }

    /**
     * 直接遍历target，根据target中的数计数该入栈多少，出栈多少
     * @param target
     * @param n
     * @return  leetcode
     */
    public List<String> buildArray2(int[] target, int n) {
        List<String> result = new ArrayList<>();
        int last = 0;
        for (int i = 0 ; i < target.length ; i ++) {
            //开始根据当前target的元素去决定应该有多少个Push，多少个Pop
            //target为1，3 n = 3为例
            //先把当前元素到上一个元素的元素push进来
            for (int j = last + 1 ; j < target[i] ; j ++) {
                result.add("Push");
                result.add("Pop");
            }
            //当前元素push进来
            result.add("Push");
            last = target[i];
        }
        return result;
    }
}