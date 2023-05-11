package com.carol.leetcode.dfs;

import com.carol.infra.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Carol
 * @date 2022/9/24
 * @since 1.0.0
 */
public class LC257 {
    /**
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        LC257 lc257 = new LC257();
        List<String> re = lc257.binaryTreePaths(root);
        System.out.println("");
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (null == root) {
            return result;
        }
        List<String> paths = new ArrayList<>();
        paths.add(String.valueOf(root.val));
        this.dfs(root, paths);
        return result;
    }

    List<String> result  = new ArrayList<>();

    private void dfs(TreeNode root, List<String> paths) {
        //到达叶子节点
        if (null == root.left && null == root.right) {
            String path = paths.stream().collect(Collectors.joining("->"));
            result.add(path);
            return;
        }
        if (null != root.left) {
            paths.add(String.valueOf(root.left.val));
            dfs(root.left, paths);
            paths.remove(paths.size() - 1);
        }
        if (null != root.right) {
            paths.add(String.valueOf(root.right.val));
            dfs(root.right, paths);
            paths.remove(paths.size() - 1);
        }

    }
}