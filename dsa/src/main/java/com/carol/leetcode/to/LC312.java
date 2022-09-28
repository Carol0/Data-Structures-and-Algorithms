package com.carol.leetcode.to;

import java.util.*;

/**
 * @author Carol
 * @date 2022/9/28
 * @since 1.0.0
 */
public class LC312 {
    /**
     * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。（多叉树）
     *
     * 给你一棵包含n个节点的树，标记为0到n - 1 。给定数字n和一个有 n - 1 条无向边的 edges列表（每一个边都是一对标签），
     * 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
     *
     * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
     *
     * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
     *
     * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
     *
     * 思路：要找到高度最矮的树，如果只有一条边，可以作为叶子节点，也可以作为根节点，但是作为根节点，有可能高1层
     * 利用拓扑排序，每次将叶子节点剥离出去，同时减少叶子节点相邻节点的入度，最后剩下的1个或者两个即为最矮树
     *
     *
     *6
     * [[0,1],[0,2],[0,3],[3,4],[4,5]]
     */
    public static void main(String[] args) {
        LC312 lc312 = new LC312();
        int[][] edges = new int[][]{{0,1},{0,2},{0,3},{3,4},{4,5}};
        lc312.findMinHeightTrees(6, edges);
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //记录每个节点的度
        int[] degree = new int[n];
        //每个节点相邻节点的map
        Map<Integer, Set<Integer>> nodeMap = new HashMap<>();
        List<Integer> nodes = new ArrayList<>();
        for (int i = 0 ; i < n ; i ++) {
            nodes.add(i);
        }
        for (int i = 0 ; i < edges.length ; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            ++ degree[start];
            ++ degree[end];
            if (!nodeMap.containsKey(start)) {
                nodeMap.put(start, new HashSet<>());
            }
            if (!nodeMap.containsKey(end)) {
                nodeMap.put(end, new HashSet<>());
            }
            nodeMap.get(start).add(end);
            nodeMap.get(end).add(start);
        }
        //每次剥离出去度为1的节点
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0 ; i < degree.length ; i++) {
            if (degree[i] == 1) {
                deque.add(i);
                nodes.remove(Integer.valueOf(i));
            }
        }
        while (!deque.isEmpty()) {
            if (nodes.size() <= 2) {
                return nodes;
            }

            int size = deque.size();
            while (size > 0) {
                int node = deque.pollFirst();
                Set<Integer> nearNodes = nodeMap.get(node);
                if (!nearNodes.isEmpty()) {
                    for (Integer near : nearNodes) {
                        -- degree[near];
                        //度减1之后成为叶子节点
                        if (degree[near] == 1) {
                            deque.add(near);
                            nodes.remove(Integer.valueOf(near));
                        }
                    }
                }
                -- size;
            }
        }
        return nodes;
    }
}