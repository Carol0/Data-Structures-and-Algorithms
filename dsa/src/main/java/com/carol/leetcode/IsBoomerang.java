package com.carol.leetcode;

/**
 * @author Carol
 * @date 2022/6/8
 * @since 1.0.0
 */
public class IsBoomerang {
    /**
     * https://leetcode.cn/problems/valid-boomerang/
     * 1037. 有效的回旋镖
     * 给定一个数组points，其中points[i] = [xi, yi]表示 X-Y 平面上的一个点，
     * 如果这些点构成一个回旋镖则返回true。
     * 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
     * 提示：
     * points.length == 3
     * points[i].length == 2
     * 0 <= xi, yi<= 100
     * 思路：三个点不在一条直线上且不重合，三点可以成为一个三角形
     */
    public static void main(String[] args) {
        IsBoomerang boomerang = new IsBoomerang();
        System.out.println(boomerang.isBoomerang(new int[][]{{1,1},{2,2},{7,7}}));
    }
    public boolean isBoomerang(int[][] points) {
        //如果有两个点重合，返回f
        double a = this.edgeLength(points[0], points[1]);
        double b = this.edgeLength(points[0], points[2]);
        double c = this.edgeLength(points[1], points[2]);
        //判断是否能成为一个三角形
        //浮点数存在精度损失，一般判断大小与0.000001比较即可
        return a + b - c > 0.000001 && a + c - b > 0.000001 && b + c - a > 0.00001;
    }

    private double edgeLength(int[] point1, int[] point2) {
        return Math.sqrt(Math.pow(point2[1] - point1[1], 2) + Math.pow(point2[0] - point1[0], 2));
    }

}