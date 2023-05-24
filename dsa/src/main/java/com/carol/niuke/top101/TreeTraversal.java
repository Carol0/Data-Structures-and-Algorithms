package com.carol.niuke.top101;

import com.carol.infra.TreeNode;
import com.carol.interview.unkonw.Lis;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 树遍历：前序，中序，后序,层序
 * 前序，中序，后序遍历根据的是当前子树根结点的遍历的顺序
 * @author Carol
 * @date 2023/5/23
 * @since 1.0.0
 */
public class TreeTraversal {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(5);
        TreeTraversal treeTraversal = new TreeTraversal();
        List<List<Integer>> result = treeTraversal.levelOrder(treeNode);
        System.out.println("");
    }

    private List<Integer> preOrder(TreeNode tree) {
        List<Integer> result = new ArrayList<>();
        preOrder(tree, result);
        return  result;
    }


    private void preOrder(TreeNode tree, List<Integer> result) {
        if (null == tree) {
            return;
        }
        //前序遍历，取下根结点

        preOrder(tree.left, result);
        preOrder(tree.right, result);
        result.add(tree.val);


    }

    private List<Integer> middleOrder(TreeNode tree) {
        return null;
    }

    private List<Integer> afterOrder(TreeNode tree) {
        return null;
    }

    private List<List<Integer>> levelOrder(TreeNode tree){
        if (null == tree) {
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(tree);
        int levelSize = 1;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp ;
        while (!deque.isEmpty()) {
            int currentLevelSize = 0;
            temp = new ArrayList<>();
            for (int i = 0 ; i < levelSize ; i++) {
                TreeNode currentNode = deque.poll();
                temp.add(currentNode.val);
                if (null != currentNode.left) {
                    deque.addLast(currentNode.left);
                    ++ currentLevelSize;
                }
                if (null != currentNode.right) {
                    deque.addLast(currentNode.right);
                    ++ currentLevelSize;
                }

            }
            levelSize = currentLevelSize;
            result.add(temp);
        }
        return result;
    }

}