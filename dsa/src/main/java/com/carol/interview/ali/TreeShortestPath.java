package com.carol.interview.ali;

import com.carol.interview.unkonw.Lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Carol
 * @date 2023/1/5
 * @since 1.0.0
 */
public class TreeShortestPath {
    /**
     * 在一个地区有n 个城市以及 n−1 条无向边，
     * 每条边的时间边权都是 1，并且这些城市是联通的，
     * 即这个地区形成了一个树状结构。每个城市有一个等级。
     * 现在小强想从一个城市走到另一个不同的城市，并且每条边经过至多一次，
     * 同时他还有一个要求，起点和终点城市可以任意选择，但是等级必须是相同的。
     * 但是小强不喜欢走特别远的道路，
     * 所以他想知道时间花费最小是多少。
     *
     * 进阶：时间复杂度O(n^2logn)空间复杂度O(n)
     * 第一行一个正整数n，含义如题面所述。
     * 第二行 n个正整数A，代表每个城市的等级。
     * 接下来n-1行每行两个正整数u，v，代表一条无向边。
     * 保证给出的图是一棵树。
     * 1<=n<=5000
     * 1<=u,v<=n
     * 1<=Ai<=10^9
     * 思路：需要求一条路径，这条路径的两个城市等级一样，并且相聚最近
     * 遍历每一个结点，将每一个结点作为起点，广度优先搜索，往外一层一层扩展
     * 遇到的第一个与当前结点相同等级的即为当前结点符合题目要求的最短路径，进行一个记录更新
     * 最终遍历完成之后，所有找到中的最短即为最终答案
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] levelStr = reader.readLine().split(" ");
        List<Node> nodeArrayList = new ArrayList<>();
        for (String level : levelStr) {
            nodeArrayList.add(new Node(Integer.parseInt(level)));
        }
        for (int i = 1 ; i < n ; i ++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            nodeArrayList.get(u - 1).nodes.add(nodeArrayList.get(v - 1));
            nodeArrayList.get(v - 1).nodes.add(nodeArrayList.get(u - 1));
        }
        TreeShortestPath path = new TreeShortestPath();
        System.out.println(path.treeShortestPath(nodeArrayList));

    }
    public int treeShortestPath(List<Node> nodes) {
        int min = Integer.MAX_VALUE;
        for (Node node : nodes) {
            min = Math.min(min, this.bfs(node));
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int bfs(Node root) {
        Deque<Node> deque = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        int pathLength = 0;
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0 ; i < size ; i ++) {
                Node current = deque.poll();
                visited.add(current);
                for (Node node : current.nodes) {
                    if (!visited.contains(node)) {
                        if (node.level == root.level) {
                            return pathLength + 1;
                        }
                        deque.add(node);
                    }
                }
                ++ pathLength;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static class Node{
        private int level;
        private List<Node> nodes;
        Node(int level) {
            this.level = level;
            nodes = new ArrayList<>();
        }
    }
}