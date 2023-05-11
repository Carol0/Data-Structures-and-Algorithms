package com.carol.leetcode.dfs;

import com.carol.infra.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Carol
 * @date 2022/9/20
 * @since 1.0.0
 */
public class LC222 {
    /**
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     *
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h个节点。
     * 思路：用bfs更简单，累加每一层节点即可,需要时间空间均为O（n）
     */
    public int countNodes(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        int result = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            result += size;
            while (size > 0) {
                TreeNode temp = deque.pollFirst();
                if (null != temp.left) {
                    deque.addLast(temp.left);
                }
                if (null != temp.right) {
                    deque.addLast(temp.right);
                }
                -- size;
            }
        }
        return result;

    }

    /**
     * 对于满二叉树，节点树=2^层高-1
     * 完全二叉树是最后一层没有满
     * 以根节点为起点，分别计算左右二叉树的层高，如果左子树层高=右子树层高，则可以推测出，左子树为满二叉树，右子树可能不是
     * 如果不等于，证明最后一层左子树没有满，但是右子树在上一层满了
     * 在这两种情况下，可以分别计算满二叉树的节点数
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftLevel = getLevel(root.left);
        int rightLevel = getLevel(root.right);
        if (leftLevel == rightLevel) {
            return countNodes2(root.right) + (1 << leftLevel);
        } else {
            return countNodes2(root.left) + (1 << rightLevel);
        }
    }

    /**
     * 计算层高
     * @param root
     * @return
     */
    private int getLevel(TreeNode root) {
        int level = 0;
        while (null != root) {
            ++ level;
            root = root.left;
        }
        return level;
    }
}