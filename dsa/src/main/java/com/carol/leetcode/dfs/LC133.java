package com.carol.leetcode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carol
 * @date 2022/9/14
 * @since 1.0.0
 */
public class LC133 {
    /**
     * 给你无向连通图中一个节点的引用，请你返回该图的深拷贝（克隆）。
     *
     * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
     * 思路：按照原来的无向连通图拷贝一个新的，新的节点需要新new
     * 对于每一个节点，采用map记录该节点是否已经新创建（key：oldNode，value：newNode）
     * 深度遍历每一个节点
     */
    public static void main(String[] args) {
        Node node = new Node(1, new ArrayList<>());
        Node node1 = new Node(2, new ArrayList<>());
        Node node2 = new Node(3, new ArrayList<>());
        Node node3 = new Node(4, new ArrayList<>());
        node.neighbors.add(node1);
        node.neighbors.add(node3);
        node1.neighbors.add(node1);
        node1.neighbors.add(node2);
        LC133 lc133 = new LC133();
        Node result = lc133.cloneGraph(node);
        System.out.println("test");
    }

    Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (null == node) {
            return node;
        }
        //如果之前已经创建新节点，直接返回之前节点
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        ArrayList<Node> neighbors = new ArrayList<>();
        Node newNode = new Node(node.val, neighbors);
        visited.put(node, newNode);
        //深度遍历每一个邻接点
        for (Node node1 : node.neighbors) {
            neighbors.add(cloneGraph(node1));
        }
        return newNode;
    }


    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}

