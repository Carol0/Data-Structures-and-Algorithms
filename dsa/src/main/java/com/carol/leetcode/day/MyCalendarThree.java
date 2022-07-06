package com.carol.leetcode.day;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Carol
 * @date 2022/6/6
 * @since 1.0.0
 */
public class MyCalendarThree {
    /**
     * https://leetcode.cn/problems/my-calendar-iii/
     * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
     * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
     * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
     * MyCalendarThree() 初始化对象。
     * int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
     * 暴力解法：直接存下每次出现的时间点
     */

    public static void main(String[] args) {
        MyCalendarThree x = new MyCalendarThree();
        System.out.println(x.book2(10,20));
        System.out.println(x.book2(50,60));
        System.out.println(x.book2(10,40));
        System.out.println(x.book2(5,15));
        System.out.println(x.book2(5,10));
        System.out.println(x.book2(25,55));
    }

    Map<Integer, Integer> times;
    TreeMap<Integer, Integer> treeMap;
    public MyCalendarThree() {
        times = new HashMap<>();
        treeMap = new TreeMap<>();
    }

    public int book(int start, int end) {
        for (int i = start ; i <end ; i++) {
            if (!times.containsKey(i)) {
                times.put(i, 0);
            }
            times.put(i, times.get(i) + 1);
        }
        return this.findMax();
    }

    private int findMax() {
        int max = -1;
        for (int key : times.keySet()) {
            max = Math.max(max, times.get(key));
        }
        return max;
    }


    public int book2(int start, int end) {
        if (!treeMap.containsKey(start)) {
            treeMap.put(start, 0);
        }
        if (!treeMap.containsKey(end)) {
            treeMap.put(end, 0);
        }
        treeMap.put(start, treeMap.get(start) + 1);
        treeMap.put(end, treeMap.get(end) - 1);
        //x1 - 0 = value1  x2 - x1 = value2
        int answer = 0;
        int max = 0;
        for (Integer value : treeMap.values()) {
            max += value;
            answer = Math.max(max, answer);
        }
        return answer;
    }
}