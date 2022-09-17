package com.carol.leetcode.dfs;

/**
 * @author Carol
 * @date 2022/9/17
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
     *
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：[0,1]
     * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
     * 思路：一门课程可能在夺门课程之前，课程关系链接起来的应该是个图
     * 具有前驱后继关系的可以使用图的拓扑排序判断图是否存在环状
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return null;
    }

    public static class Node {
        private int val;
        private Node next;

    }
}