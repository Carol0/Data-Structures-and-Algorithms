package com.carol.leetcode.dfs;

import com.carol.infra.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/8/27
 * @since 1.0.0
 */
public class LC113 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        LC113 l = new LC113();
        l.pathSum(root, 1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return result;
        }
        List<Integer> tempPath = new ArrayList<>();
        tempPath.add(root.val);
        this.dfs(root, targetSum, root.val, tempPath);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    private void dfs(TreeNode root, int targetSum, int tempSum, List<Integer> tempPath) {
        if (null == root) {
            return;
        }
        if (tempSum == targetSum && null == root.left && null == root.right) {
            result.add(new ArrayList<>(tempPath));
        }
        if (null != root.left) {
            tempPath.add(root.left.val);
            tempSum += root.left.val;
            dfs(root.left, targetSum, tempSum, tempPath);
            tempPath.remove(tempPath.size() - 1);
            tempSum -= root.left.val;
        }
        if (null != root.right) {
            tempPath.add(root.right.val);
            tempSum += root.right.val;
            dfs(root.right, targetSum, tempSum, tempPath);
            tempPath.remove(tempPath.size() - 1);
            tempSum -= root.right.val;
        }
    }
}