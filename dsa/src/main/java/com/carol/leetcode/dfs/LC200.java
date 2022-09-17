package com.carol.leetcode.dfs;

/**
 * @author Carol
 * @date 2022/9/17
 * @since 1.0.0
 */
public class LC200 {
    /**
     * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     * 思路：以1表示能走通，0表示不能走通，相当于求有多少连通分量
     * 深度遍历，遇到1可以走深度递归，0则返回，遍历过的设置为0
     */

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','0','1','1'},
                {'1','0','0','1','0'},{'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        LC200 lc200 = new LC200();
        System.out.println(lc200.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        //遍历grid，找到1出现的位置
        int result = 0;
        while (true) {
            int[] index = this.find(grid);
            if (null == index) {
                break;
            }
            this.dfs(grid, index[0], index[1]);
            ++ result;
        }
        return result;
    }

    private int[] find(char[][] grid) {
        for (int i = 0 ; i < grid.length ; i++) {
            for (int j = 0 ; j < grid[0].length ; j++) {
                if (grid[i][j] == '1') {
                    return new int[]{i , j};
                }
            }
        }
        return null;
    }
    /**
     *
     * @param grid 图
     * @param i 当前行
     * @param j 当前列
     */
    private void dfs(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length
                || i < 0 || j < 0
                ||grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        //四个方向
        dfs(grid, i + 1, j);
        dfs(grid, i , j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i ,j - 1);
    }
}