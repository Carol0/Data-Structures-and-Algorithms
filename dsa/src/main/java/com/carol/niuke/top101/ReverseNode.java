package com.carol.niuke.top101;

import com.carol.infra.ListNode;
/**
 * @author Carol
 * @date 2023/5/8
 * @since 1.0.0
 */
public class ReverseNode {
    /**
     * https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=295&tqId=23286&ru=/exam/company&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Fcompany
     * 牛客在线编程top101 第一题
     * 直接将后继节点指向前驱节点，最后需要注意将第一个节点的后继节点指向null
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ReverseNode reverseNode = new ReverseNode();
        head = reverseNode.ReverseList(head);
        System.out.println("");
    }

    public ListNode ReverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode first = head;
        ListNode last = head;
        ListNode current = head.next;
        while (null != current) {
            ListNode next = current.next;
            current.next = last;
            last = current;
            current = next;
        }
        first.next = null;
        return last;
    }
}