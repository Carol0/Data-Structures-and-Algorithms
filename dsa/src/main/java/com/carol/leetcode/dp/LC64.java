package com.carol.leetcode.dp;

/**
 * @author Carol
 * @date 2022/9/30
 * @since 1.0.0
 */
public class LC64 {
    /**
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * 说明：每次只能向下或者向右移动一步。
     * 思路：定义到当前位置（i，j）的最短路径为f（i，j）
     * 第一步确定转移方程：可以定义转移方程f（i，j） = min（f（i- 1，j），f（i，j - 1）） + grid[i][j];
     * 第二步确定初始条件：只走最上边或者最左边时候能够唯一确定一条路径，即最小值；
     */
    public static void main(String[] args) {
        LC64 lc64 = new LC64();
        //输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
        int[][] grid = new int[][]{{1,2,3},{4,5,6}};
        System.out.println(lc64.minPathSu90m(grid));;
    }
    public int minPathSu90m(int[][] grid) {
        if (grid.length < 1) {
            return 0;
        }
        //f存储的是当前位置（i，j）的最短路径值
        int[][] f = new int[grid.length][grid[0].length];
        //初始化边界条件
        f[0][0] = grid[0][0];
        for (int i = 1 ; i < grid.length ; i++) {
            f[i][0] = f[i - 1][0] + grid[i][0];
        }
        for (int j = 1 ; j < grid[0].length ; j++) {
            f[0][j] = f[0][j - 1] + grid[0][j];
        }
        //迭代计算最小值，转移方程f（i，j）= min（f（i - 1， j）, f(i , j - 1)）+ grid[i][j];
        for (int i = 1 ; i < grid.length ; i ++) {
            for (int j = 1 ; j < grid[0].length ; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
        }
        //最右下角的最短路径值
        return f[grid.length - 1][grid[0].length - 1];
    }
}