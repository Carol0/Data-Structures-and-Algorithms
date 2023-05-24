package com.carol.niuke.top101;

import com.carol.infra.ListNode;
import com.carol.interview.unkonw.Lis;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Carol
 * @date 2023/5/18
 * @since 1.0.0
 */
public class SortInList {

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(7);
        head.next.next = new ListNode(9);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(2);
        SortInList sortInList = new SortInList();
        ListNode result = sortInList.sortInList(head);
        System.out.println("");
    }

    /**  5 7 9 0 2
     *
     *
     * 2  0   5   9 7
     * 对单链表进行排序
     * 先将链表的节点取下来，对节点进行排序之后，再重新进行链接
     * @param head
     * @return
     */
    public ListNode sortInList (ListNode head) {

        // write code here
        //取下节点
        List<ListNode> nodes = new ArrayList<>();
        while (null != head) {
            nodes.add(head);
            head = head.next;
        }
        quickSort(nodes, 0, nodes.size() - 1);
        ListNode p = new ListNode(-1);
        ListNode temp = p;
        for (ListNode node : nodes) {
            temp.next = node;
            temp = temp.next;
        }
        temp.next = null;
        return p.next;
    }


    private void quickSort(List<ListNode> nodes, int l, int r) {
        if (l < r) {
           int index = sort(nodes, l, r);
           quickSort(nodes, l, index - 1);
           quickSort(nodes, index + 1, r);
        }
    }


    /**
     *
     * @param nodes
     * @param l
     * @param r
     * @return
     */
    private int sort(List<ListNode> nodes, int l, int r) {
        ListNode key = nodes.get(l);
        while (l < r) {
            while (nodes.get(r).val > key.val && l < r) {
                -- r;
            }
            if (l < r) {
                //将从右往左开始找到的第一个小于key的值的node，放到左边
                nodes.set(l, nodes.get(r));
            }
            while (nodes.get(l).val < key.val && l < r) {
                ++ l;
            }
            if (l < r) {
                //将从左边开始找到的第一个大于key的值的node，放到右边
                nodes.set(r, nodes.get(l));
            }
        }
        nodes.set(l, key);
        return l;
    }


}