package com.carol.leetcode.day;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author gaoxiaolin
 * @date 2022/7/28
 * @since 1.0.0
 */
public class ArrayRankTransform {
    public static void main(String[] args) {
        ArrayRankTransform cla = new ArrayRankTransform();
        int[] result = cla.arrayRankTransform(new int[] {10,45,-1,100,100});
        System.out.println("sdfdsfdsf");
    }

    /**
     * https://leetcode.cn/problems/rank-transform-of-an-array/
     * 1331. 数组序号转换
     * 给你一个整数数组arr ，请你将数组中的每个元素替换为它们排序后的序号。
     *
     * 序号代表了一个元素有多大。序号编号的规则如下：
     *
     * 序号从 1 开始编号。
     * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
     * 每个数字的序号都应该尽可能地小。
     * 思路：需要找到数组每个元素在排序后应该处于的编号，相同的元素处于的排序编号应该是一致的，
     * java中TreeSet具有排序功能，我们可以先利用TreeSet将元素进行排序，然后将排序后的index存入
     * 一个hashmap，再通过遍历数组，从map中取出每个元素对应的排序后的编号值
     */
    public int[] arrayRankTransform(int[] arr) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int a : arr) {
            treeSet.add(a);
        }
        int index = 1;
        Map<Integer, Integer> map = new HashMap<>(treeSet.size());
        for (Integer key : treeSet) {
            map.put(key, index ++);
        }
        int[] result = new int[arr.length];
        for (int i = 0 ; i < arr.length ; i++) {
            result[i] = map.get(arr[i]);
        }
        return result;
    }
}