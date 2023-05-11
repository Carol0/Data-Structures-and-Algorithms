package com.carol.leetcode.hot100;

import com.carol.infra.ListNode;

/**
 * @author Carol
 * @date 2022/7/6
 * @since 1.0.0
 */
public class Hot002 {
    /**
     * 2. 两数相加
     * https://leetcode.cn/problems/add-two-numbers/
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     * 思路： 题目很容易理解，需要得到两个数字相加之和，每个数字是由链表存储的，并且以逆序即链表第一个为个位
     * 我们直接遍历链表相加即可，同时需要考虑进位问题
     */

    public static void main(String[] args) {
        Hot002 hot002 = new Hot002();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(5);
        ListNode ans = hot002.addTwoNumbers(l1, l2);
        System.out.println("");
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //步骤：1.任意选择一个链表作为结果链表，在上面进行操作
        ListNode head = l1;
        ListNode last = l1;
        while (null != l1 && null != l2) {
            int temp = l1.val + l2.val;
            l1.val = temp % 10;
            if (temp / 10 > 0) {
                if (null == l1.next) {
                    l1.next = new ListNode();
                }
                l1.next.val += temp / 10;
            }
            last = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        //l2不为空，l1已经为空
        if (null != l2) {
            last.next = l2;
        }
        while(null != l1 && l1.val > 9) {
            if (null == l1.next) {
                l1.next = new ListNode(0);
            }
            l1.next.val += (l1.val / 10);
            l1.val %= 10;
            l1 = l1.next;
        }
        return head;
    }
}