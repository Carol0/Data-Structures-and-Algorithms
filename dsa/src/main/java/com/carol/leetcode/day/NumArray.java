package com.carol.leetcode.day;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/6/14
 * @since 1.0.0
 */
public class NumArray {
    /**
     * https://leetcode.cn/problems/range-sum-query-mutable/
     * 307. 区域和检索 - 数组可修改
     * 给你一个数组 nums ，请你完成两类查询。
     * 其中一类查询要求 更新 数组nums下标对应的值
     * 另一类查询要求返回数组nums中索引left和索引right之间（包含）的nums元素的 和，其中left <= right
     * 实现 NumArray 类：
     * NumArray(int[] nums) 用整数数组 nums 初始化对象
     * void update(int index, int val) 将 nums[index] 的值 更新 为 val
     * int sumRange(int left, int right) 返回数组nums中索引left和索引right之间（包含）的nums元素的 和
     * （即，nums[left] + nums[left + 1], ..., nums[right]）
     */
    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1,3,5});
        System.out.println(numArray.sumRange(0,2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0,2));
    }
    //head 指向线段树root节点的指针，使得root节点与其余节点操作保持一致
    Node head;
    //前缀和数组，便于构建线段树时候计算区间值，用于初次构建辅助
    List<Integer> prefixSum ;
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    //初始化一个头节点，便于操作
        this.head = new Node(-1, -1, -1);
        prefixSum = new ArrayList<>(nums.length);
        prefixSum.add(0);
        for (int i = 0; i < nums.length ; i++) {
            prefixSum.add(prefixSum.get(prefixSum.size() - 1) + nums[i]);
        }
        this.build(1, nums.length);
    }
    //构建线段树
    public void build(int start, int end) {
        Node root = new Node(start, end, prefixSum.get(end) - prefixSum.get(start - 1));
        head.right = root;
        this.madeChild(root, start, end);
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

    public void update(int index, int val) {
        //自己的实现是从1开始的，不是从0
        ++ index;
        Node root = head.right;
        while (null != root) {
            if (index >= root.start && index <= root.end) {
                root.value += val - nums[index - 1];
            }
            int mid = root.start + ((root.end - root.start) >> 1);
            if (index <= mid) {
                root = root.left;
                continue;
            }
            root = root.right;
        }
        nums[index - 1] =  val;
    }

    public int sumRange(int left, int right) {
        //自己的实现是从1开始的，不是从0
        ++ left;
        ++ right;
        return dfsFindSectionSum(head.right, left, right);
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

    public static class Node {
        private int start;
        private int end;
        private int value;
        private Node left;
        private Node right;

        Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}