package com.carol.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carol
 * @date 2022/5/23
 * @since 1.0.0
 */
public class BPlusTree {


    /**
     * 每个节点有m/2～m-1个元素，指针个数为元素个数+1, 3叉树
     * 首先规定子节点指针指向节点较小值，第一个值
     */

    Node root;
    Map<Integer, User> data;


    public void init(int m) {
        root = new Node(m);
        data = new HashMap<>(10);
        for (int i = 0 ; i < 10 ; i++) {
            User user = new User(i, "小明" + i, 10 + i);
            data.put(i, user);
        }
    }


    public void insert(char value) {

    }

    public void delete(){

    }


    /**
     * 根据key查询记录
     * @param key == id
     * @return 查找到的user
     */
    public User find(int key) {
        //b+树索引节点不存储值，找到索引之后
        //查找b+树中是否存在value值，在节点内进行二分查找，
        Node head = root;
        while (null != head) {
            //在当前节点进行二分查找，找到对应节点
            //对于节点中的key，小于当前key的节点，由当前key的前一个指针指向，大于等于的由后一个指向
            if (head.isLeaf) {
                int index = binarySearchLeaf(key, head.keys, 0, head.size - 1);
                if (-1 == index) {
                    return null;
                }
                return data.get(index);
            }
            int index = binarySearch(key, head.keys, 0, head.size - 1);
            //继续往下找
            head = head.nodes.get(index);
        }
        return null;
    }


    /**
     * 查询key值出现的index
     * @param key
     * @param keys
     * @param start
     * @param end
     * @return
     */
    private int binarySearchLeaf(int key, List<Integer> keys, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (key == keys.get(mid)) {
            return mid;
        }
        if (key < keys.get(mid)) {
            return binarySearchLeaf(key, keys, start, mid - 1);
        }
        return binarySearchLeaf(key, keys, mid + 1, end);

    }

    /**
     * 查询子节点指针出现位置
     * @param key
     * @param keys
     * @param start
     * @param end
     * @return
     */
    private int binarySearch(int key, List<Integer> keys, int start, int end) {
        int mid = (start + end) / 2;
        if (key == keys.get(mid)) {
            return mid + 1;
        }
        if (key < keys.get(mid)) {
            if (mid - 1 >= 0 && key >= keys.get(mid - 1)) {
                //在这俩之间
                return mid;
            }
            return binarySearch(key, keys, start, mid - 1);
        }
        if (mid + 1 <= end && key <= keys.get(mid + 1)) {
            return mid + 1;
        }
        return binarySearch(key, keys, mid + 1, end);
    }

    public static class Node {
        //节点存的key
        private List<Integer> keys;
        //子指针
        private List<Node> nodes;

        //是否为叶子节点
        private boolean isLeaf;

        int size;

        Node(int m) {
            keys = new ArrayList<>(m);
            nodes = new ArrayList<>(m + 1);
            size = 0;
            isLeaf = true;
        }
    }


    public static class User{
        private int id;
        private String name;
        private int age;
        User(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }
}