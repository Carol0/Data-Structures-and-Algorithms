package com.carol.niuke.top101;

import com.carol.infra.ListNode;

/**
 * @author Carol
 * @date 2023/5/16
 * @since 1.0.0
 */
public class HasCycle {
    public static void main(String[] args) {
        HasCycle h = new HasCycle();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);

       // System.out.println(h.hasCycle(node));

       // node.next.next.next = node.next;
        //System.out.println(h.hasCycle(node));
       // ListNode meetNode = h.EntryNodeOfLoop(node);
        ListNode kNode = h.FindKthToTail(node, 2);
        System.out.println("");
    }
    /**
     * 判断链表是否有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (null != fast && null != slow) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            if (null == fast) {
                break;
            }
            fast = fast.next;
        }
        return false;
    }


    /**
     * 找到有环链表快慢指针相遇点
     * @param head
     * @return
     */
    public ListNode meetNode(ListNode head) {
       ListNode fast = head.next.next;
       ListNode slow = head.next;
       while (null != fast && null != slow) {
           if (fast == slow) {
               return fast;
           }
           slow = slow.next;
           fast = fast.next.next;
       }
       return null;
    }

    /**
     * 查找有环链表的入口节点
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode temp = pHead;
        ListNode meetNode = meetNode(temp);
        while (null != pHead && null != meetNode) {
            if (pHead == meetNode) {
                return pHead;
            }
            pHead = pHead.next;
            meetNode = meetNode.next;
        }
        return null;
    }
    public ListNode FindKthToTail (ListNode pHead, int k) {
        ListNode fast = pHead;
        -- k;
        while (k > 0) {
            fast = fast.next;
            -- k;
        }
        ListNode slow = pHead;
        fast = fast.next;
        while (null != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode removeNthFromEnd (ListNode head, int n) {
        // write code here
        return null;
    }
}