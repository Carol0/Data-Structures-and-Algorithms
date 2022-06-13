package com.carol.algorithms;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 这里我们实现节点为区间和，构建线段树，求解任意区间和，更新节点值
     */

    public static void main(String[] args) {
        SegmentTree tree = new SegmentTree();
        tree.init(Arrays.asList(1,4,6,3,9));
        System.out.println(tree.findSectionSum(1,5));
        System.out.println(tree.findSectionSum(1,1));
        System.out.println(tree.findSectionSum(2,4));
    }

    //head 指向线段树root节点的指针，使得root节点与其余节点操作保持一致
    Node head;
    int size;

    //用于初次构建
    List<Integer> nums;
    //前缀和数组，便于构建线段树时候计算区间值，用于初次构建
    List<Integer> prefixSum ;
    public void init(List<Integer> nums) {
        //初始化一个头节点，便于操作
        this.head = new Node(-1, -1, -1);
        this.nums = nums;
        prefixSum = new ArrayList<>(nums.size());
        prefixSum.add(0);
        for (int i = 0; i < nums.size() ; i++) {
            prefixSum.add(prefixSum.get(prefixSum.size() - 1) + nums.get(i));
        }
        this.build(1, nums.size());
        size = nums.size();
    }

    //构建线段树
    public void build(int start, int end) {
       Node root = new Node(start, end, prefixSum.get(end) - prefixSum.get(start - 1));
       head.right = root;
       this.madeChild(root, start, end);
    }

    //求解区间和
    public int findSectionSum(int start, int end) {
        //深度遍历线段树，找到对应区间
        if (start < 1 || end > size || start > end) {
            return -1;
        }
        return dfsFindSectionSum(head.right, start, end);
    }



    private void madeChild(Node node, int start, int end) {
        if (start >= end) {
            return;
        }
        //分个左右子树，左子树取start～mid，右子树取mid+1～end
        int mid = start + ((end - start) >> 1);
        if (start <= mid) {
            Node left = new Node(start, mid, prefixSum.get(mid) - prefixSum.get(start - 1));
            node.left = left;
            madeChild(left, start, mid);
        }
        if (mid + 1 <= end) {
            Node right = new Node(mid + 1, end, prefixSum.get(end) - prefixSum.get(mid));
            node.right = right;
            madeChild(right, mid + 1, end);
        }
    }


    /**
     * 深度遍历线段树结构，分为三种情况
     * 1.区间在当前节点的左子树中
     * 2.区间在当前节点的右子树中
     * 3.左子树中一部分，右子树中一部分
     * @param node
     * @param start
     * @param end
     * @return
     */
    private int dfsFindSectionSum(Node node, int start, int end) {
        if (node.start == start && node.end == end) {
            //找到区间
            return node.value;
        }
        if (this.isContain(node.left.start, node.left.end, start, end)) {
            //在左子树中
            return this.dfsFindSectionSum(node.left, start, end);
        }
        if (this.isContain(node.right.start, node.right.end, start, end)) {
            //包含在右子树中
            return this.dfsFindSectionSum(node.right, start, end);
        }
        //左边一部分，右边一部分
        return this.dfsFindSectionSum(node.left, start, node.left.end) + this.dfsFindSectionSum(node.right, node.right.start, end);
    }

    /**
     * 判断区间[start2, end2]是否包含在[start1, end1]中
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    private boolean isContain(int start1, int end1, int start2, int end2){
        return start2 >= start1 && end2 <= end1;
    }

    @Data
    public static class Node {
        private int start;
        private int end;
        private int value;
        private int max;
        private int min;
        private Node left;
        private Node right;

        Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}