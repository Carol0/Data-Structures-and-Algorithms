package com.carol.leetcode.dfs;

import com.carol.leetcode.infra.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/9/22
 * @since 1.0.0
 */
public class LC236 {
    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 思路：最近公共祖先，即从根节点往下遍历，查找p和q，最后一个重叠点即为最近公共节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (null == left) {
            return right;
        }
        if (null == right) {
            return left;
        }
        return root;
    }

}