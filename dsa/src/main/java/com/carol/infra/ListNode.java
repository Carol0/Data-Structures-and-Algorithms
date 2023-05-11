package com.carol.infra;

/**
 * @author Carol
 * @date 2022/7/11
 * @since 1.0.0
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int length() {
        ListNode head = this;
        int length = 0;
        while (null != head) {
            ++ length;
            head = head.next;
        }
        return length;
    }

}