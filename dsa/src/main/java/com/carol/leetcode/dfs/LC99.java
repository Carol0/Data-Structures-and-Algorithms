package com.carol.leetcode.dfs;

import com.carol.leetcode.infra.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/9/10
 * @since 1.0.0
 */
public class LC99 {
    /**
     * https://leetcode.cn/problems/recover-binary-search-tree/
     * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树。
     * 思路：深度遍历树结构，找到不符合的两个节点值，将它们进行交换
     */
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.right = new TreeNode(4);
        node.right.left = new TreeNode(2);
        LC99 lc99 = new LC99();
        lc99.recoverTree(node);
    }
    public void recoverTree(TreeNode root) {
        if (null == root) {
            return;
        }
        this.dfs(root);
        int firstIndex = -1;
        int secondIndex = -1;
        //循环遍历找到顺序错乱的地方
        for (int i = 0 ; i < nodes.size() - 1 ; i++) {
            if (nodes.get(i + 1).val < nodes.get(i).val) {
                //当前位置的值应该放在后面
                if (-1 == firstIndex) {
                    firstIndex = i;
                } else {
                    secondIndex = i;
                    break;
                }
            }
        }
        if (secondIndex == -1) {
            this.swap(firstIndex, firstIndex + 1);
            return;
        }
        this.swap(firstIndex, secondIndex + 1);
    }

    private void swap(int i, int j) {
        int temp = nodes.get(i).val;
        nodes.get(i).val = nodes.get(j).val;
        nodes.get(j).val = temp;
    }

    List<TreeNode> nodes = new ArrayList<>();
    public void dfs(TreeNode root) {
        if (null == root) {
            return;
        }
        if (null != root.left) {
            dfs(root.left);
        }
        nodes.add(root);
        if (null != root.right) {
            dfs(root.right);
        }
    }
}