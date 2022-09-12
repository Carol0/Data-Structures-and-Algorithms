package com.carol.leetcode.dfs;

import com.carol.leetcode.infra.TreeNode;

/**
 * @author Carol
 * @date 2022/9/12
 * @since 1.0.0
 */
public class LC124 {

    /**
     * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
     * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
     * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     *
     * 路径和 是路径中各节点值的总和。
     *
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     * 思路：1。所要查找的路径可以从任意节点出发
     * 如果从叶子节点出发，仅包含当前叶子节点的路径长度=叶子节点值，往上走，叶子节点的父节点的路径长度
     * 应该与左右子节点有关系，如果左右子节点值均大于0，三个相加，如果有小于0的，加上路径变短，应该舍弃
     * [5,4,8,11,null,13,4,7,2,null,null,null,1]
     *
     * 以当前节点为基准的时候，包含这个节点的路径最大长度=当前节点值+左路径最大长度+右路径最大长度
     * 但是在递归求解路径的时候需要注意，对于当前节点的父节点，是不能同时选择当前节点的左右子树的（分叉），需要选择路径最长的子树
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        LC124 lc124 = new LC124();
        lc124.maxPathSum(root);
    }
    public int maxPathSum(TreeNode root) {
        this.pathSum(root);
        return result;
    }

    int result = Integer.MIN_VALUE;
    private int pathSum(TreeNode root) {
        if (null == root) {
            return 0;
        }
        //从叶子节点往上迭代，对每一个节点取通过这个节点往下能够得到的最大路径值
        if (null == root.left && null == root.right) {
            result = Math.max(result, root.val);
            return root.val;
        }
        //对于当前节点 当前节点值+左子树最大值+右子树最大值
        int leftMaxPath = Math.max(pathSum(root.left), 0);
        int rightMaxPath = Math.max(pathSum(root.right), 0);
        int curMaxPath = root.val + leftMaxPath + rightMaxPath;
        result = Math.max(result, curMaxPath);
        //对于上上个节点来说，不能同时选择左右路径
        return root.val + Math.max(leftMaxPath, rightMaxPath);
    }
}