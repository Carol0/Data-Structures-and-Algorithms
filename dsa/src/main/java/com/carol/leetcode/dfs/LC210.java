package com.carol.leetcode.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Carol
 * @date 2022/9/18
 * @since 1.0.0
 */
public class LC210 {
    /**
     * 现在你总共有 numCourses 门课需要选，记为0到numCourses - 1。
     * 给你一个数组prerequisites ，其中 prerequisites[i] = [ai, bi] ，
     * 表示在选修课程 ai 前 必须 先选修bi 。
     *
     * 例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示：[0,1] 。
     * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，
     * 你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     * 思路：首先题目会给多个序列，表示课程的依赖关系，即要学一门课程，需要先学会他的前置课程
     * 并且一门课程的前置课程可能有多门，所以课程的关系组合起来应该是一个有向图，题目要求返回
     * 一个序列，可以学完所有课程，则表示有向图无环，使用拓扑排序。
     * 1。图包括两要素，顶点，边
     * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     * 输出：[0,2,1,3]
     *
     */
    public static void main(String[] args) {
        LC210 lc = new LC210();
        int[][] prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        //int[][] prerequisites = new int[][]{{0,1}};
        int[] xx = lc.findOrder(4, prerequisites);
        System.out.println("ddd");
    }
    //存储图的边
    List<Set<Integer>> edges = new ArrayList<>();
    //标志该顶点是否已经访问
    int[] visited ;
    int[] result ;
    int index = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //初始化边
        for (int i = 0 ; i < numCourses ; i ++) {
            edges.add(new HashSet<>());
        }
        //加入边
        for (int i = 0 ; i < prerequisites.length ; i ++) {
            //记录指向当前位置节点的节点
            edges.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        while (true) {
            boolean flag = true;
            for (int i = 0 ; i < numCourses ; i ++) {
                //找到当前未被访问，并且入度为0的顶点进行访问
                if (visited[i] == 0 && edges.get(i).isEmpty()) {
                    for (int j = 0 ; j < numCourses ; j ++) {
                        if (visited[j] == 0 && edges.get(j).contains(i)) {
                            edges.get(j).remove(i);
                        }
                    }
                    visited[i] = 1;
                    result[index ++] = i;
                    flag = false;
                }
            }
            if (this.allVisit()) {
                return result;
            }
            //如果这次遍历没有找到任何符合要求的，不符合
            if (flag) {
                return new int[0];
            }
        }
    }

    private boolean allVisit() {
        for (int j : visited) {
            if (j == 0) {
                return false;
            }
        }
        return true;
    }

}