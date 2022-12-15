package com.carol.interview.tencent;

/**
 * @author Carol
 * @date 2022/12/15
 * @since 1.0.0
 */
public class BalanceTree {
    /**
     * 3.嘤嘤的新平衡树
     * 给定一棵二叉树，二叉树的每个结点只有0或2个孩子。
     * 你需要对每个结点赋值一个正整数（>=1），使得每个结点的左右子树权值和相等。
     * 你需要返回所有结点的最小权值和对10^9+7取模的结果。
     * 二叉树结点个数不超过10^5
     * 思路：
     * 题目要求：按照要求给每个结点赋值，使得每一个结点的权值相加最小
     * 递归操作：递归至叶子结点，对于叶子结点肯定已经是平衡树，直接给叶子赋值为1，
     * 对于中间结点乃至根结点，我们需要判断当前结点的左右子树的权值是否相等
     * 如果相等：符合题意中的平衡树，直接给当前值赋值为1
     * 如果不相等：以为最开始我们从叶子结点开始进行赋值，每一步都是最小的，我们只能把之前的赋值放大，而不能缩小，所以当出现不相等的时候
     * 我们需要放大较小的值，让他们相等，然后再将当前直接赋值为1，直到根结点
     * 例子
     * {0,0,0}
     * 返回：3
     */
    public static void main(String[] args) {
        BalanceTree balanceTree = new BalanceTree();
        TreeNode treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(0);
        treeNode.right.left = new TreeNode(0);
        treeNode.right.right = new TreeNode(0);
        System.out.println(balanceTree.balance(treeNode));

    }
    public int balance(TreeNode node) {
        if (null == node.left && null == node.right) {
            node.value = 1;
            return 1;
        }
        int leftNodeValue = balance(node.left);
        int rightNodeValue = balance(node.right);
        node.value = 1;
        if (leftNodeValue == rightNodeValue) {
            return leftNodeValue + rightNodeValue + node.value;
        }
        if (leftNodeValue > rightNodeValue) {
            //放大右子树的值
            node.right.value += (leftNodeValue - rightNodeValue);
            return (leftNodeValue << 1) + 1;
        }
        //放大的是左子树的值
        node.left.value += (rightNodeValue - leftNodeValue);
        return (rightNodeValue << 1) + 1;
    }

    public static class TreeNode{
        private TreeNode left;
        private TreeNode right;
        private Integer value;
        TreeNode(Integer value) {
            this.value = value;
        }
    }
}