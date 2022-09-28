package com.carol.leetcode.dfs;

import com.carol.leetcode.infra.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Carol
 * @date 2022/9/24
 * @since 1.0.0
 */
public class LC297 {
    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
     * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     *
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
     * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     * 树中结点数在范围 [0, 104] 内
     * -1000 <= Node.val <= 1000
     * 思路：dfs先序遍历二叉树，如果有值存储值，如果为空直接存储null
     * 输入：root = [1,2,3,null,null,4,5]
     * 输出：[1,2,3,null,null,4,5]
     */
    public static void main(String[] args) {
        LC297 lc297 = new LC297();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String data = lc297.serialize(root);
        System.out.println(data);
        TreeNode node = lc297.deserialize(data);
        System.out.println("ddd");
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) {
            return  "";
        }
       //层序遍历
        Deque<TreeNode> treeNodes = new LinkedList<>();
        StringBuilder str = new StringBuilder();
        treeNodes.addLast(root);
        //需要处理最后一层的叶子节点，不再需要null值标志左右子树为空
        while (!treeNodes.isEmpty()) {
            TreeNode node = treeNodes.pollFirst();
            if (null == node) {
                str.append("null").append(",");
            } else {
                str.append(node.val).append(",");
                treeNodes.addLast(node.left);
                treeNodes.addLast(node.right);
            }
        }
        str.setLength(str.length() - 1);
        return str.toString();

    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        //采用层序遍历方式记录每一层
        String[] strs = data.split(",");
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        deque.add(root);
        int index = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            //取出每一层元素，对左右子树进行赋值
            while(size > 0) {
                TreeNode node = deque.pollFirst();
                if (!"null".equals(strs[index])) {
                    node.left = new TreeNode(Integer.parseInt(strs[index]));
                    deque.addLast(node.left);
                }
                ++ index;
                if (!"null".equals(strs[index])) {
                    node.right = new TreeNode(Integer.parseInt(strs[index]));
                    deque.addLast(node.right);
                }
                ++ index;
                --size;
            }
        }
        return root;
    }

    /**
     * 利用（）标记左右子树，null特殊标记为X
     * decode时，递归创建左右子树
     *
     */
}
