package com.carol.leetcode.dp;

/**
 * @author Carol
 * @date 2022/9/29
 * @since 1.0.0
 */
public class LC62 {
    /**
     * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * 思路：对于坐标为（i，j）的位置，机器人到达该位置的方式是从该位置上方（i-1，j），或者该位置左边（i， j-1）
     * 设f（i，j）表示到达（i，j）位置的路径条数，则可以定义转移方程f（i，j）= f（i - 1，j） + f（i， j - 1）
     * 一般还需要一个出口，即找到某个位置是确定的值，方便迭代时候确定值
     * 对于边界上的位置，对于最上面一条边，只可以从左往右走，所以路径只有一条
     * 对于最左边的一条边，只可以往下走，同样路径只有一条，所以目前初始条件就找到了，
     * 接下来需要利用初始条件得到的确定值迭代出指定位置的值
     */
    public static void main(String[] args) {
        LC62 lc62 = new LC62();
        System.out.println(lc62.uniquePaths(3,3));
    }
    public int uniquePaths(int m, int n) {
        //f(i,j)到当前位置的路径条数
        int[][] f = new int[m][n];
        //初始条件
        for (int i = 0 ; i < m ; i++) {
            f[i][0] = 1;
        }
        for (int j = 0 ; j < n ; j++) {
            f[0][j] = 1;
        }
        //转移方程 f[i][j] = f[i - 1][j] + f[i][j - 1];
        for (int i = 1 ; i < m ; i++) {
            for (int j = 1; j < n ; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        //最终答案，到达最右下方
        return f[m - 1][n - 1];
    }
}