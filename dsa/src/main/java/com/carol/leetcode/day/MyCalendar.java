package com.carol.leetcode.day;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Carol
 * @date 2022/7/5
 * @since 1.0.0
 */
public class MyCalendar {

    /**
     * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
     *
     * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
     *
     * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数x 的范围为， start <= x < end 。
     *
     * 实现 MyCalendar 类：
     *
     * MyCalendar() 初始化日历对象。
     * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。
     * 否则，返回 false并且不要将该日程安排添加到日历中。
     * 1。暴力解法，直接遍历
     * 2. 排序后遍历
     * 3。线段树
     */

    /**
     * 思路：按start从小到大将日程表进行排序，然后进行遍历，
     *
     */

    public static void main(String[] args) {
        //[[], [10, 20], [15, 25], [20, 30]]
        //["MyCalendar","book","book","book","book","book","book","book","book","book","book"]
        //[[],[47,50],[33,41],[39,45],[33,42],[25,32],[26,35],[19,25],[3,8],[8,13],[18,27]]
        //[null,true,true,false,false,true,false,true,true,true,false]
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(47,50));
        System.out.println(myCalendar.book(33,41));
        System.out.println(myCalendar.book(39,45));
        System.out.println(myCalendar.book(33,42));
        System.out.println(myCalendar.book(25,32));
        System.out.println(myCalendar.book(26,35));
        System.out.println(myCalendar.book(19,25));
        System.out.println(myCalendar.book(3,8));
        System.out.println(myCalendar.book(8,13));
        System.out.println(myCalendar.book(18,27));



    }
    TreeMap<Integer, Integer> treeMap;
    public MyCalendar() {
        treeMap = new TreeMap<>();
        head = new Node(-1, -1);
    }

    public boolean book(int start, int end) {
        for(Integer key : treeMap.keySet()) {
            //2.当前end - 1 小于start ,前面没有找到，并且后续的都会大于当前段，直接返回true；
            if (end - 1 < key) {
                treeMap.put(start, end);
                return true;
            }
            //1.[start，end）与当前日程表段有交叉，直接返回false
            //分为三段
            int value = treeMap.get(key);
            if (start <= key && end > key && end < value) {
                //落在前面，有交叉
                return false;
            }
            if (start >= key && end <= value) {
                //落在中间，有交叉
                return false;
            }
            if (start > key && start < value) {
                return false;
            }
            if (start <= key && end >= value) {
                //start,end 大于整个区间
                return false;
            }
        }
        treeMap.put(start, end);
        return true;
    }


    /**
     * 判断index所在节点与[start，end)是否有重合
     * @param start
     * @param end
     * @param index
     * @return true有重合
     */
    private boolean isContain(int start, int end,int index) {
        int key = arrays.get(index)[0];
        int value = arrays.get(index)[1];
        if (end - 1 < key) {
            return false;
        }
        //1.[start，end）与当前日程表段有交叉，直接返回false
        //分为三段
        if (start <= key && end > key && end < value) {
            //落在前面，有交叉
            return true;
        }
        if (start >= key && end <= value) {
            //落在中间，有交叉
            return true;
        }
        if (start > key && start < value) {
            return true;
        }
        if (start <= key && end >= value) {
            //start,end 大于整个区间
            return true;
        }
        return false;
    }

    List<int[]> arrays = new ArrayList<>();
    Node head ;
    public static class Node{
        private Node left;
        private Node right;
        private Integer start;
        private  Integer end;
        //start最小值下标
        private Integer index;
        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}