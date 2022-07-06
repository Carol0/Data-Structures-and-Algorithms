package com.carol.leetcode.day;

import java.util.Random;

/**
 * @author Carol
 * @date 2022/6/5
 * @since 1.0.0
 */
public class RandPoint {
    /**
     * https://leetcode.cn/problems/generate-random-point-in-a-circle/
     * 478. 在圆内随机生成点
     * 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
     *
     * 实现Solution类:
     *
     * Solution(double radius, double x_center, double y_center)用圆的半径radius和圆心的位置 (x_center, y_center) 初始化对象
     * randPoint()返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
     * 输入:
     * ["Solution","randPoint","randPoint","randPoint"]
     * [[1.0, 0.0, 0.0], [], [], []]
     * 输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
     * 解释:
     * Solution solution = new Solution(1.0, 0.0, 0.0);
     * solution.randPoint ();//返回[-0.02493，-0.38077]
     * solution.randPoint ();//返回[0.82314,0.38945]
     * solution.randPoint ();//返回[0.36572,0.17248]
     */

    public static void main(String[] args) {
        RandPoint randPoint = new RandPoint(1.0, 0.0, 0.0);
        double[] x1 = randPoint.randPoint();
        System.out.println(x1[0] + "," + x1[1]);
        x1 = randPoint.randPoint();
        System.out.println(x1[0] + "," + x1[1]);
        x1 = randPoint.randPoint();
        System.out.println(x1[0] + "," + x1[1]);
    }

    private double radius;
    private double x_center;
    private double y_center;
    public RandPoint(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }

    public double[] randPoint() {
        //将圆作为正方形内切圆，在正方形上随机产生两个点，点落在每一处的概率相等
        //判断当前点是都落在内切圆上，落在则直接返回，否则直接生成
        Random rand = new Random();
        while (true) {
            //生成两个在正方形的随机点
            double x = rand.nextDouble() * 2 * radius + x_center - radius;
            double y = rand.nextDouble() * 2 * radius + y_center - radius;
            //判断这个点是否在圆内，距离圆心的距离<=半径
            if (Math.pow((y - y_center), 2) * Math.pow(x - x_center, 2) <= Math.pow(radius, 2)) {
                return new double[]{x, y};
            }
        }
    }
}