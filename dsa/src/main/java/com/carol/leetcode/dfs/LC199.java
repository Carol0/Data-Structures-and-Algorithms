package com.carol.leetcode.dfs;

import com.carol.infra.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/9/15
 * @since 1.0.0
 */
public class LC199 {
    /**
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * 思路：题目的意思翻译一下就是返回树的最右边节点，层序遍历，返回每一层最后一个节点即可
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left  = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right.right = new TreeNode(4);
        LC199 lc199 = new LC199();
        List<Integer> result = lc199.rightSideView(root);
        System.out.println("test");
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        List<Integer> result = new ArrayList<>();
        while (!deque.isEmpty()) {
            //直接取当前最后一个
            result.add(deque.peekLast().val);
            //遍历当前层，将下一层入队列
            int size = deque.size();
            while (size > 0) {
                TreeNode node = deque.pollFirst();
                if (null != node.left) {
                    deque.addLast(node.left);
                }
                if (null != node.right) {
                    deque.addLast(node.right);
                }
                -- size;
            }
        }
        return result;
    }
}