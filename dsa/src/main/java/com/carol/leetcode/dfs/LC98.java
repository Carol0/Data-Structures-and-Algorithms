package com.carol.leetcode.dfs;

import com.carol.infra.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/8/27
 * @since 1.0.0
 */
public class LC98 {

    public static void main(String[] args) {
        //[5,4,6,null,null,3,7]
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(1);
        LC98 lc98 = new LC98();
        System.out.println(lc98.isValidBST(treeNode));
    }

    /**
     * 方法一
     * 思路：中序遍历结果应该是由小变大的
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        this.dfs(root);
        for (int i = 1 ; i < result.size() ; i++) {
            if (result.get(i) <= result.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    List<Integer> result = new ArrayList<>();
    private void dfs(TreeNode root) {
        if (null != root.left) {
            dfs(root.left);
        }
        result.add(root.val);
        if (null != root.right) {
            dfs(root.right);
        }
    }

    /**
     * 方法二
     * 思路：因为二叉搜索树左子树的所有值必小于父节点，右子树的所有值必大于父节点
     * 所以这些值一定在一个区间内，我们深度遍历二叉树，不断缩小范围进行比较即可
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return this.dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long min, long max) {
        if (null == root) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

}