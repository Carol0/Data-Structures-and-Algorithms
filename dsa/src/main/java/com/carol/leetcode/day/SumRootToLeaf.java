package com.carol.leetcode.day;

import com.carol.infra.TreeNode;

/**
 * @author Carol
 * @date 2022/5/30
 * @since 1.0.0
 */
public class SumRootToLeaf {
    /**
     * https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers/
     * 2022-05-31 每日一题：1022. 从根到叶的二进制数之和
     * 给出一棵二叉树，其上每个结点的值都是0或1。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
     *
     * 例如，如果路径为0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数01101，也就是13。
     * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
     *
     * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
     * 输入：root = [1,0,1,0,1,0,1]
     * 输出：22
     * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     *
     * 思路：遍历二叉树，到叶子节点计算二进制值
     *
     */
    public static void main(String[] args) {
        SumRootToLeaf leaf = new SumRootToLeaf();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        System.out.println(leaf.sumRootToLeaf(root));
    }
    int total = 0;
    public int sumRootToLeaf(TreeNode root) {
        if (null == root) {
            return 0;
        }
        this.dfs(root, new StringBuilder());
        return total;
    }

    private void dfs(TreeNode root, StringBuilder numStr) {
        numStr.append(root.val);
        if (null == root.left && null == root.right) {
            //叶子节点
            total += this.calculate(numStr.toString());
            return;
        }
        if (null != root.left) {
            //存在左子树
            this.dfs(root.left, numStr);
            numStr.setLength(numStr.length() - 1);
        }
        if (null != root.right) {
            //存在右子树
            this.dfs(root.right, numStr);
            numStr.setLength(numStr.length() - 1);
        }
    }

    private int calculate(String num) {
        int index = num.length() - 1;
        int result = 0;
        int cal = 1;
        while (index >= 0) {
            result += (num.charAt(index) - '0') * cal;
            cal <<= 1;
            -- index;
        }
        return result;
    }


}