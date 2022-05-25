package com.carol.algorithms;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Carol
 * @date 2022/5/2
 * @since 1.0.0
 */
public class SkipList {
    /**
     * 跳表结构：主要就是在链表上增加索引1，2，3，4，5，6，7，8，9
     * 第一层索引1，3，5，7 则需要找到8，首先遍历索引，找到7，然后遍历数据链表找到8
     * 第二层1，5
     * 从第二层中找到5，从第二层中找到7，从最后数据链表中找到8
     * 问题：
     * 索引需要几层?
     * 用什么来存索引？
     * 初始化为跳表，
     * 添加删除数据时候，如何调整索引？
     * 如果3为间隔，需要的层数n/3^k = 2;最顶层用2个节点  k = log3（n/2）
     * 可以用一个链表来存索引链表，索引送最顶层一只到最底层，需要顺序遍历，链表合适
     * 跳表的结构：
     * 索引及最终数据为链表
     * 用一个链表存储链表list<list>样式
     *
     */
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.init();
        skipList.init(2, new int[]{1,2,3,4,6,7,8,9});
        Node testNode = skipList.head;
        List<Node> testList = skipList.indexList;
        System.out.println(skipList.hasNum(5));
        skipList.remove(6);
        testList = skipList.indexList;
        System.out.println("test");
        skipList.add(6);
        testList = skipList.indexList;
        System.out.println("test2");
    }

       /**
         * 原始链表
         */
        private Node head ;
        /**
         * 保存索引链表及原始链表
         */
        private List<Node> indexList;
        /**
         * 跳表层数
         */
        private int level;

        public void init() {
            head = new Node(-1);
            head.next = head;
            indexList = new ArrayList<>();
            level = 0;
        }
        /**
         * 初始化跳表
         * @param k 间隔
         * @param nums 原始数据（已排序）
         */
        public void init(int k, int[] nums) {
            //初始化数据链表
            Node temp = head;
            for (int num : nums) {
                Node cur = new Node(num);
                cur.prev = temp;
                temp.next = cur;
                temp = temp.next;
            }
            //新节点保存
            indexList.add(head);

            //循环生成索引结构，结束条件，当层仅一个元素
            temp = head.next;
            while (true) {
                //当前链表第几个元素
                int i = 0;
                //生成另一条链表长度
                int size = 0;
                Node indexNode = new Node(-1);
                indexNode.next = indexNode;
                Node indexNodeTemp = indexNode;
                while (null != temp) {
                    if (i % k == 0) {
                        Node curNode = new Node(temp.value);
                        curNode.nodeValue = temp;
                        curNode.prev = indexNodeTemp;
                        indexNodeTemp.next = curNode;
                        indexNodeTemp = indexNodeTemp.next;
                        ++ size;
                    }
                    ++ i;
                    temp = temp.next;
                }
                indexList.add(indexNode);
                temp = indexNode.next;
                //当生成的索引链表仅1时不需要再继续生成
                if (size == 1) {
                    break;
                }
            }
            level = indexList.size();
    }
    /**
     * 是否存在num
     * @param num
     * @return
     */
    public boolean hasNum(int num) {
        Node result = this.findNum(num);
        return null != result;
    }
    /**
     * 查找num（返回的可能是索引，也可能是原始数据，根据nodeValue可以判断，也可以找到原始数据）
     * @param num
     */
    public Node findNum(int num) {
        //跳表结构indexList是数据-》第一层索引-》第二层索引-》。。。。
        //需要从后往前
        //1.直接匹配到，直接返回true
        //2.找到第一个大于当前元素的数，找前一个
        Node node = indexList.get(indexList.size() - 1).next;
        Node last = null;
        while (null != node) {
            if (node.value == num) {
                //已经找到元素
                return node;
            }
            if (node.value > num) {
                if (null == last) {
                    //比最小值还小
                    return null;
                }
                //找到了第一个大于num的索引node
                //到下一层去继续找
                node = last.nodeValue;
                last = null;
                continue;
            }
            last = node;
            node = null != node.next ? node.next : node.nodeValue;
        }
        return null;
    }
    /**
     * 首先找到该元素，如果是索引值，删除后需要调整，不是暂时不需要调整，等删除到索引值，自然就删除了
     * 根据跳表结构特点，找到的node如果是索引，下面的每一层均为索引，都需要处理，
     * 构建索引时：自底向上逐层构建，如果索引需要删除（当两个索引之间没有任何数据时候，删除）
     * @param num
     * @return
     */
    public boolean remove(int num) {
        Node node = this.findNum(num);
        if (null == node) {
            //不需要移除
            return false;
        }
        if (null == node.nodeValue) {
            //数据链表，可以直接移除
            //是否最后一个节点
            if (null == node.next) {
                node.prev.next = null;
                return true;
            }
            node.next.prev = node.prev;
            node.prev.next = node.next;
            return true;
        }
        //当前在索引上，自上而下删除索引及数据
        while (null != node) {
            Node cur = node.nodeValue;
            if (null == node.next) {
                node.prev.next = null;
            } else {
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
            node = cur;
        }
        return true;
    }
    /**
     * 当新增节点时候，随机选择k，即第k级新增索引,从第1-k新增索引
     * 首先需要查找插入位置，如果比最小的还小，直接在前面插入
     * 否则需要从最顶级一直查找到数据链表，找到插入位置，插入，在查找的过程中，就可以开始插入索引节点，
     * 从上往下进行插入
     * @param num
     */
    public void add(int num) {
        int k = this.generatorLevelK();
        //寻找插入点的过程和查找过程基本一致
        //顶级索引链表
        Node node = indexList.get(indexList.size() - 1).next;
        int index = 1;
        while (null != node) {
            //找到第一个node.value >= num的元素，在前面插入
            if (node.value >= num) {
                //已经找到,前插
                if (index >= k) {
                    Node newNode = new Node(num);
                    Node temp = node.prev;
                    newNode.next = temp.next;
                    temp.next.prev = newNode;
                    newNode.prev = temp;
                    temp.next = newNode;
                }
                //找的时候往后面找的，但是当前已经先于num了，下一次再往后面找，就出现问题
                if (null == node.prev.prev) {
                    //第一个节点就符合条件
                    node = node.nodeValue;
                    continue;
                }
                node = node.prev.nodeValue;
                ++ index;
                continue;
            }

            //没有找到，但是当前已经是链表最后一个元素了
            if (null == node.next) {
                if (index >= k) {
                    Node newNode = new Node(num);
                    newNode.prev = node;
                    node.next = newNode;
                }
                if (null == node.prev.prev) {
                    //第一个节点就符合条件
                    node = node.nodeValue;
                    continue;
                }
                node = node.prev.nodeValue;
                ++ index;
                continue;
            }

            node = node.next;
        }

    }
    private int generatorLevelK() {
        Random random = new Random();
        return random.nextInt(level);
    }
    class Node {
        //当前节点值
        private Integer value;
        //当前节点所属链表下一个节点
        private Node next;
        //当前节点所属链表上一个节点
        private Node prev;
        //当前节点指向的另一个索引链表/值链表节点
        private Node nodeValue;
        Node(Integer value) {
            this.value = value;
        }
    }





}