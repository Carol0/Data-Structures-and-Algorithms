package com.carol.leetcode.day;

import com.carol.infra.TreeNode;


/**
 * @author Carol
 * @date 2022/6/2
 * @since 1.0.0
 */
public class DeleteNode {
    /**
     * 450. 删除二叉搜索树中的节点
     * https://leetcode.cn/problems/delete-node-in-a-bst/
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，
     * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * 输入：root = [5,3,6,2,4,null,7], key = 3
     * 输出：[5,4,6,2,null,null,7]
     * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     * 思路：删除二叉搜索树的指定节点，并且需要保证二叉搜索树的性质不变，即还
     * 符合左子树<=当前节点<=右子树
     * 首先需要查询节点：所以需要一个查询方法，并且删除节点时候，需要用其他节点放在当前
     * 节点位置，父节点指向，所以我们查询的时候还应该保存删除节点的父节点
     * 删除节点：删除节点分为三种情况
     * 1：删除的节点为叶子节点，则直接删除即可，不用做任何处理
     * 2：删除节点仅有左子树或者右子树，则删除当前节点，将当前节点左子树或者右子树直接
     * 放到当前节点位置
     * 3：当前节点同时具有左子树和右子树，这个时候我们可以将当前节点的左子树放在当前节点
     * 右子树的最左边节点的左子树上，然后将右子树放在当前节点位置（反过来也可以）
     *
     *
     */
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left= new TreeNode(3);
        treeNode.right = new TreeNode(6);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.right = new TreeNode(7);
        DeleteNode deleteNode = new DeleteNode();
        TreeNode node = deleteNode.deleteNode(treeNode, 3);
        System.out.println("");

    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if (null == root) {
            return root;
        }
        //为了方便操作，增加一个头节点，保证包括root节点在内的节点操作能够一致
        TreeNode head = new TreeNode(-100001);
        head.right = root;
        TreeNode[] curParent = this.find(key, head);
        if (null == curParent[1]){
            //没有找到目标值,不用进行删除
            return root;
        }
        TreeNode parent = curParent[0];
        TreeNode cur = curParent[1];
        //这个节点用来记录，父节点将要指向的节点，因为我们还需要判断当前节点是
        //父节点的左子树还是右子树，为了避免重复判断，我们先计算出要加上去的值
        //再一起去判断
        TreeNode changed ;
        if (null == cur.left && null == cur.right) {
            //当前节点为叶子节点，直接删除
            changed = null;
        } else if (null != cur.right && null != cur.left) {
            //左右子树均存在，先将左子树放到右子树最左边节点的左子树上
            TreeNode temp = cur.right;
            while (null != temp.left) {
                temp = temp.left;
            }
            //已经找到
            temp.left = cur.left;
            changed = cur.right;
        } else if (null != cur.left) {
            changed = cur.left;
        } else {
            changed = cur.right;
        }
        //所有情况已经处理完成
        if (parent.left == cur) {
            parent.left = changed;
            return root;
        }
        parent.right = changed;
        return head.right;
    }

    /**
     * 查找val值节点
     * @param val
     * @return 返回一个数组，0位置保存查找到的节点的父节点
     * 1位置保存找到到的节点
     */
    private TreeNode[] find(int val, TreeNode head) {
        TreeNode[] result = new TreeNode[2];
        result[0] = head;
        TreeNode tempNode = head.right;
        while (null != tempNode) {
            if (tempNode.val == val) {
                //已经找到节点，返回
                result[1] = tempNode;
                return  result;
            }
            result[0] = tempNode;
            if (tempNode.val < val) {
                //当前节点值小于目标值，目标值可能存在在右子树
                tempNode = tempNode.right;
                continue;
            }
            //可能存在在左子树
            tempNode = tempNode.left;
        }
        //如果走到这里，表示根本没有找到
        return result;
    }

}