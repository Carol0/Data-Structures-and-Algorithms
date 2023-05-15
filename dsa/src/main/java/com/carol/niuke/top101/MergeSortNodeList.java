package com.carol.niuke.top101;


import com.carol.infra.ListNode;
import com.carol.interview.unkonw.Lis;

import java.util.ArrayList;

/**
 * @author Carol
 * @date 2023/5/15
 * @since 1.0.0
 */
public class MergeSortNodeList {
    public static void main(String[] args) {
        ArrayList<ListNode> result = new ArrayList<>();
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        ListNode list2  = new ListNode(3);
        list2.next = new ListNode(5);
        ListNode list3 = new ListNode(10);

        result.add(list1);
        result.add(list2);
        result.add(list3);

        MergeSortNodeList mergeSortNodeList = new MergeSortNodeList();
        ListNode xx = mergeSortNodeList.mergeKLists(result);
        System.out.println("");
    }

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        return mergeKLists(lists, 0, lists.size() - 1);
    }

    public ListNode mergeKLists(ArrayList<ListNode> lists, int m, int n) {
        if (m == n) {
            return lists.get(m);
        }
        if (m + 1 == n) {
            return Merge(lists.get(m), lists.get(n));
        }
        int mid = m + ((n - m) >> 1);
        return Merge(mergeKLists(lists, m, mid), mergeKLists(lists, mid + 1, n));
    }



    public ListNode Merge(ListNode list1, ListNode list2) {
        //1.判断边界条件，如果某一个为空，直接不需要进行任何合并，直接返回
        if (null == list1) {
            return list2;
        }
        if (null == list2) {
            return list1;
        }

        ListNode head = new ListNode(-1);
        ListNode temp = head;

        //开始进行合并
        while (null != list1 && null != list2) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        //判断合并完之后是否有链表未遍历完成，直接追加到后面
        if (null != list1) {
            temp.next = list1;
        }
        if (null != list2) {
            temp.next = list2;
        }
        return head.next;
    }

}