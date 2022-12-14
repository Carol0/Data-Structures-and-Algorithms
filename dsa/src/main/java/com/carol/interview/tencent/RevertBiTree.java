package com.carol.interview.tencent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/12/14
 * @since 1.0.0
 */
public class RevertBiTree {
    /**
     * 2.
     * 带重复节点的前序中序二叉树
     * 已知一个二叉树的先序遍历序列和中序遍历序列，但其中一些节点的值可能相同。请你返回所有满足条件的二叉树。二叉树在数组中的顺序是任意的。
     * 输入例子：
     * [1,1,2],[1,2,1]
     * 输出例子：
     * [{1,1,#,#,2},{1,#,1,2}]
     * 思路：遍历先序序列，对当前遍历的结点，找到其在中序序列中的位置，对每一个位置一一进行试算
     */
    public static void main(String[] args) {
        RevertBiTree revertBiTree = new RevertBiTree();
        revertBiTree.build(new int[]{1,1,2}, 0, new int[]{1,2,1},0, 2 );
        System.out.println("");
    }


    List<Node> answer = new ArrayList<>();
    private Node build(int[] front, int frontIndex, int[] middle, int start, int end) {
        if (start > end) {
            return null;
        }
        Node root = new Node(front[frontIndex]);
        for (int i = start ; i <= end ; i ++) {
            if (middle[i] == front[frontIndex]) {
                root.left = build(front, frontIndex + 1, middle, start, i - 1);
                root.right = build(front, frontIndex + 1, middle, i + 1, end);
                if (0 == frontIndex) {
                    //不能直接取add root，需要进行一个copy再进行保存
                    answer.add(root.copy());
                }
            }
        }
        return root;
    }

    public static class Node{
        Node left;
        Node right;
        Integer value;
        Node(int value) {
            this.value = value;
        }

        public Node copy () {
            Node root = new Node(-1);
            this.copy(root, this);
            return root;
        }

        private void copy(Node newNode, Node oldNode) {
            newNode.value = oldNode.value;
            if (null != oldNode.left) {
                newNode.left = new Node(-1);
                copy(newNode.left, oldNode.left);
            }
            if (null != oldNode.right) {
                newNode.right = new Node(-1);
                copy(newNode.right, oldNode.right);
            }
        }
    }

}