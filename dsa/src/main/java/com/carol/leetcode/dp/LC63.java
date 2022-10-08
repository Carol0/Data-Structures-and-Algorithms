package com.carol.leetcode.dp;

/**
 * @author Carol
 * @date 2022/10/8
 * @since 1.0.0
 */
public class LC63 {
    /**
     * 一个机器人位于一个m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 思路：63为62题的进阶，唯一区别为在网格中可能出现障碍物，同样采用动态规划
     * 1。找转移方程，假设f（i，j）表示到达位置（i，j）的路径条数，并且机器人只能从上面或者左边走到这个位置。
     *  分为两种情况，如果当前位置为障碍物，则无论如何都不能走到这里 f（i，j） = 0；
     *  如果当前不是障碍物，则f（i，j）= f（i - 1，j） + f（i， j - 1）；
     * 2。初始条件
     * 对于最左边一列，和最上面一列，只可能为0（当为障碍物时），或者1。
     * 测试样例：
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 输入：obstacleGrid = [[0,1],[0,0]]
     * 输出：1
     *
     * [[0,0],[1,1],[0,0]]
     */

    public static void main(String[] args) {
        LC63 lc63 = new LC63();
        System.out.println(lc63.uniquePathsWithObstacles2(new int[][]{{0,1},{0,0}}));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        //记录当前位置的路径条数
        int[][] f = new int[obstacleGrid.length][obstacleGrid[0].length];
        //初始化
        f[0][0] = 1;
        for (int i = 1 ; i < obstacleGrid.length ; i++) {
            f[i][0] =obstacleGrid[i][0] == 0 ? f[i - 1][0] : 0;
        }
        for (int j = 1 ; j < obstacleGrid[0].length ; j++) {
            f[0][j] = obstacleGrid[0][j] == 0 ? f[0][j - 1] : 0;
        }
        //开始根据转移方程去进行转移
        for (int i = 1 ; i < obstacleGrid.length ; i++) {
            for (int j = 1 ; j < obstacleGrid[0].length ; j ++) {
                f[i][j] = obstacleGrid[i][j] == 0 ? f[i - 1][j] + f[i][j - 1] : 0;
            }
        }
        return f[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }


    /**
     * 滚动数组优化dp，当前位置f（i，j）仅与当前位置及相邻俩位置有关系，所以可以简化空间占用
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        //一行一行的计算,f仅记录当前行路径树
        int[] f = new int[obstacleGrid[0].length];
        //初始化
        f[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i= 0 ; i < obstacleGrid.length ; i ++) {
            for (int j = 0 ; j < obstacleGrid[0].length ; j ++) {
                //当前位置是障碍物，直接不能到这里
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                //j - 1 >= 0 ： 如果是最左边那个，只有一条路径可以走到，所以不用计算
                //obstacleGrid[i][j - 1] == 0: 如果之前是障碍物，上面走不下来，也不需要更新
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    //此时的f[j]相当于记录的是到达左边格子的路径数， f[j - 1]相当于是记录上边格子的路径数
                    f[j] += f[j - 1];
                }
            }
        }
        return f[obstacleGrid[0].length - 1];
    }


}