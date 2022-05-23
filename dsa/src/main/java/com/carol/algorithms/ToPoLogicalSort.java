package com.example.leetcode.prefixsum.algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/5/14
 * @since 1.0.0
 */
public class ToPoLogicalSort {
    /**
     * 拓扑排序是一个针对有向图的排序方式，表示了一个前驱后继关系
     *
     */

    public static void main(String[] args) {
        ToPoLogicalSort toPoLogicalSort = new ToPoLogicalSort();
        //初始化一个图
        Graph graph = new Graph(new char[]{'A', 'B', 'C', 'D','E','F','G','H'});
        graph.addEdge(0, 1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(1,4);
        graph.addEdge(2,4);
        graph.addEdge(3,4);
        graph.addEdge(4,7);
        graph.addEdge(4,6);
        graph.addEdge(7,5);
        graph.addEdge(6,7);
        List<Character> result = toPoLogicalSort.toPoLogicalSort(graph);
        System.out.println("dddd");

    }


    /**
     * @param graph 有向无环图
     * @return 拓扑排序结果
     */
    public List<Character> toPoLogicalSort(Graph graph) {
        //用一个数组标志所有节点入度
        int[] inDegree = new int[graph.nodeSize];
        for (LinkedList list : graph.adj) {
            for (Object index : list) {
                ++ inDegree[(int)index];
            }
        }
        //用一个数组标志所有节点是否已经被访问
        boolean[] visited = new boolean[graph.nodeSize];
        //开始进行遍历
        Deque<Integer> nodes = new LinkedList<>();
        //将入度为0节点入队
        for (int i = 0 ; i < graph.nodeSize; i++) {
            if (inDegree[i] == 0) {
                nodes.offer(i);
            }
        }
        List<Character> result = new ArrayList<>();
        //将入度为0节点一次出队处理
        while (!nodes.isEmpty()) {
            int node = nodes.poll();
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            result.add(graph.node[node]);
            //将当前node的邻接节点入度-1；
            for (Object list : graph.adj[node]) {
                -- inDegree[(int)list];
                if (inDegree[(int)list] == 0) {
                    //前驱节点全部访问完毕，入度为0
                    nodes.offer((int) list);
                }
            }
        }
        return result;
    }


    public static class Graph{
        /**
         * 节点个数
         */
        private Integer nodeSize;
        /**
         * 节点
         */
        private char[] node;
        /**
         * 邻接表
         */
        private LinkedList[] adj;

        public Graph(char[] node) {
            this.nodeSize = node.length;
            this.node = node;
            this.adj = new LinkedList[nodeSize];
            for (int i = 0 ; i < adj.length ; i++) {
                adj[i] = new LinkedList();
            }
        }
        /**
         * 在节点之间加边，前驱节点指向后继节点
         * @param front 前驱节点所在下标
         * @param end 后继节点所在下标
         */
        public void addEdge(int front, int end) {
            adj[front].add(end);
        }
    }
}