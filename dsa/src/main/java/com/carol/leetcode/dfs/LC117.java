package com.carol.leetcode.dfs;

import com.carol.leetcode.infra.Node;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Carol
 * @date 2022/9/11
 * @since 1.0.0
 */
public class LC117 {
    /**
     * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有next 指针都被设置为 NULL。
     * 思路：层序遍历，将每一层进行连接，
     * 输入：root = [1,2,3,4,5,null,7]
     * 输出：[1,#,2,3,#,4,5,7,#]
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，
     * 如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
     */

    public static void main(String[] args) {
        LC117 lc117 = new LC117();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        lc117.connect2(root);

    }

    public Node connect(Node root) {
        if (null == root) {
            return root;
        }
        Node result = root;
        //记录节点
        Deque<Node> deque = new LinkedList<>();
        //记录当前访问到的层的节点数
        int levelSize;
        deque.push(root);
        while (!deque.isEmpty()) {
            levelSize = deque.size();
            //一层一层出队
            Node last = deque.pollFirst();
            this.addDeque(last, deque);
            -- levelSize;
            while (levelSize > 0) {
                Node cur = deque.pollFirst();
                last.next = cur;
                last = cur;
                this.addDeque(last, deque);
                -- levelSize;
            }
        }
        return result;
    }

    private void addDeque(Node node, Deque<Node> deque) {
        if (null != node.left) {
            deque.addLast(node.left);
        }
        if (null != node.right) {
            deque.addLast(node.right);
        }
    }

    /**
     * 层序遍历树，在遍历当前层的时候先将下一层串起来，每一层相当于一个链表，为了方便操作以及能够找到下一层的节点
     * 给每一层链表设置一个头节点
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (null == root) {
            return root;
        }
        Node cur = root;
        while (null != cur) {
            //当前层的下一层串起来链表的头节点
            Node head = new Node(-1);
            //当前链表的上一个节点
            Node pre = head;
            while (null != cur) {
                //开始串当前节点下一层的左右子树
                if (null != cur.left) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (null != cur.right) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                //串完之后，访问当前层的下一个节点，如果有，继续串
                cur = cur.next;
            }
            //串完一层，将cur指向下一层第一个节点，继续串下下层
            cur = head.next;
        }
        return root;
    }
}