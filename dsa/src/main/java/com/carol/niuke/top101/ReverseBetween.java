package com.carol.niuke.top101;

import com.carol.infra.ListNode;

/**
 * @author Carol
 * @date 2023/5/9
 * @since 1.0.0
 */
public class ReverseBetween {
    /**
     * 指定区间反转链表
     * https://www.nowcoder.com/practice/b58434e200a648c589ca2063f1faf58c?tpId=295&tqId=654&ru=/exam/company&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Fcompany
     * 牛客在线编程top101 第二题
     * 将链表分为三部分，A：反转区间前面一部分，B：反转区间 C：反转区间后面一部分，
     * 将B进行反转之后，再将A，B，C进行链接
     */
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        ReverseBetween reverseBetween = new ReverseBetween();
        node = reverseBetween.reverseBetween(node, 2,4);
        System.out.println("");
    }

    /**
     * 将链表的m到n节点进行反转
     * @param nodes
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode nodes, int m, int n) {
        ListNode head = new ListNode(-1);
        head.next = nodes;

        // 记录开始反转的第一个节点的前驱节点
        ListNode firstLast = head;
        int k = 1;
        //找到需要进行反转的第一个节点
        while (null != nodes && k < m) {
            firstLast = nodes;
            nodes = nodes.next;
            ++ k;
        }
        ListNode last = nodes;
        if (null == last || null == last.next) {
            return head.next;
        }

        //将需要反转的区间进行反转
        ListNode current = last.next;
        ListNode next = null;
        while (null != current && k < n) {
            next = current.next;
            current.next = last;
            last = current;
            current = next;
            ++ k;
        }

        //将反转前区间，反转区间，反转后区间进行链接
        firstLast.next.next = next;
        firstLast.next = last;
        return head.next;
    }
}