package com.carol.interview.bytedance;

import com.carol.infra.ListNode;

/**
 * @author Carol
 * @date 2022/11/29
 * @since 1.0.0
 */
public class MergeInBetween {
    /**
     * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
     * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
     * 请你返回结果链表的头指针。
     * 思路：
     * 直接遍历list1，找到list1下标为a-1的节点，标记为a1，再继续遍历找到下标为b+1的节点，标记为b1，
     * 然后将a1的next指向list2的第一个节点，将list2的最后一个节点指向b+1节点，
     * 3 <= list1.length <= 10^4
     * 1 <= a <= b < list1.length - 1  可以不用考虑，a-b删除完之后，list1为空
     * 1 <= list2.length <= 10^4
     * 示例：
     * 输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
     * 输出：[0,1,2,1000000,1000001,1000002,5]
     */
    public static void main(String[] args) {
        MergeInBetween mergeInBetween = new MergeInBetween();
        ListNode list1 = new ListNode(0);
        list1.next = new ListNode(1);
        list1.next.next = new ListNode(2);
        list1.next.next.next= new ListNode(3);
        list1.next.next.next.next= new ListNode(4);
        list1.next.next.next.next.next = new ListNode(5);
        ListNode list2 = new ListNode(100000);
        list2.next = new ListNode(2000);

        ListNode result = mergeInBetween.mergeInBetween(list1, 3, 4, list2);
        System.out.println("ddd");
    }
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode tempList1 = list1;
        ListNode lastA = null;
        ListNode nextB = null;
        int index = 0;
        ListNode last = null;
        while (null != tempList1) {
            if (index == a) {
                lastA = last;
            }
            if (index == b) {
                nextB = tempList1.next;
                break;
            }
            last = tempList1;
            tempList1 = tempList1.next;
            ++ index;
        }
        ListNode last2 = null;
        ListNode tempList2 = list2;
        while (null != tempList2) {
            last2 = tempList2;
            tempList2 = tempList2.next;
        }
        lastA.next = list2;
        last2.next = nextB;
        return list1;
    }
}