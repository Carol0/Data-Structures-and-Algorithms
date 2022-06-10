package com.carol.algorithms;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/6/9
 * @since 1.0.0
 */
public class SegmentTree {
    /**
     * 线段树每个节点表示一个区间，至于节点值存什么，常见的有区间和，区间最大值，区间最小值，对应都有不同的作用
     * 比如当我们需要多次计算区间和的时候节点值可以为区间和，如果我们需要求一个区间内的最大值或者最小值，那么我们的
     * 节点值就可以为当前区间的最大值或者最小值。
     * 这里我们实现节点为区间和，构建线段树，求解任意区间和，插入节点，删除节点
     */
    Node head;
    List<Integer> nums = new ArrayList<>();
    public void init(List<Integer> nums) {
        //初始化一个头节点，便于操作
        this.head = new Node(-1, -1, -1);
        this.nums = nums;
    }

    //构建线段树
    public void build() {

    }

    @Data
    public static class Node {
        private int start;
        private int end;
        private int value;

        Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}