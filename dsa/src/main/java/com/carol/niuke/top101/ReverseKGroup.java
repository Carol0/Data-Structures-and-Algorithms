package com.carol.niuke.top101;

import com.carol.infra.ListNode;

/**
 * @author Carol
 * @date 2023/5/9
 * @since 1.0.0
 */
public class ReverseKGroup {
    /**
     * 分组反转链表
     * https://www.nowcoder.com/practice/b49c3dc907814e9bbfa8437c251b028e?tpId=295&tqId=722&ru=/exam/company&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Fcompany
     * 牛客在线编程top101 第三题
     */


    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        int length = node.length();
        int count = 1;
        int k = 2;
        ReverseBetween reverseBetween = new ReverseBetween();
        while (count < length) {
            node = reverseBetween.reverseBetween(node, count, count + k - 1);
            count += k;
        }
        System.out.println("");
    }
}