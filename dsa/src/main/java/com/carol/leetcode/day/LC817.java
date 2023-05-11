package com.carol.leetcode.day;

import com.carol.infra.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Carol
 * @date 2022/10/12
 * @since 1.0.0
 */
public class LC817 {
    /**
     * 给定链表头结点head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表nums，该列表是上述链表中整型值的一个子集。
     * 返回列表nums中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表nums中）构成的集合。
     * 分析：题目的意思其实是根据链表中数值的顺序，查看nums中有多少个连续的子序列。
     * 思路：可以使用一个set记录nums中的值，然后遍历链表，查看当前遍历的值是否存在nums中。
     * 输入: head = [0,1,2,3], nums = [0,1,3]
     * 输出: 2
     */

    public static void main(String[] args) {
        LC817 lc817 = new LC817();
        ListNode node = new ListNode(0);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        System.out.println(lc817.numComponents(node, new int[]{0,3,1,4}));;
    }

    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }
        int count = 0;
       //可能出现连续几个都不存在于nums，可以一个flag，标志上一个值是否存在于nums中，如果存在，但是当前值不存在，则是一个子序列
        boolean flag = false;
        //找到第一个存在于nums中的节点
        while (null != head) {
            if (numsSet.contains(head.val)) {
                flag = true;
                break;
            }
            head = head.next;
        }
        while (null != head.next) {
            head = head.next;
            if (!numsSet.contains(head.val)) {
                if (flag) {
                    ++ count;
                }
                flag = false;
                continue;
            }
            flag = true;
        }
        return flag ? ++ count : count;
    }
}