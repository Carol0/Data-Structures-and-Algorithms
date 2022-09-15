package com.carol.leetcode.dfs;

import java.util.Arrays;

/**
 * @author Carol
 * @date 2022/9/13
 * @since 1.0.0
 */
public class LC130 {
    /**
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的'O' 用 'X' 填充。
     * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
     * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
     * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。
     * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     * 思路：最终的结果需要将被X包围的O变为X，被X包围表示不是边界上的O或者不与边界直接相连
     * 如果直接找出需要被包围的O不太方便，可以从边界上的O出发，把所有边界上的O以及以及与边界上的O直接间接相连的O进行标记，
     * 这部分O不做处理，其余需要将O变为X
     *
     * [["O","O"],["O","O"]]
     * [["O","O","O"],["O","O","O"],["O","O","O"]]
     * [["X","O"],["O","X"]]
     */
    public static void main(String[] args) {
        LC130 lc130 = new LC130();
        //char[][] board = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        char[][] board = new char[][]{{'X','O'},{'O','X'}};
//        char[][] board = new char[][]{{'O','O','O','O','X','X'},{'O','O','O','O','O','O'}
//                ,{'O','X','O','X','O','O'},{'O','X','O','O','X','O'},
//                {'O','X','O','X','O','O'},{'O','X','O','O','O','O'}};
        lc130.solve(board);
        System.out.println(Arrays.deepToString(board));
    }
    public void solve(char[][] board) {
        for (int i = 0 ; i < board.length ; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }
        for (int j = 0 ; j < board[0].length ; j++) {
            dfs(board, 0, j);
            dfs(board, board.length - 1 , j);
        }
        for (int i = 0 ; i < board.length ; i ++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                    continue;
                }
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * 对每一个边上的点进行处理，采用一个特殊标记A，将边上或直接间接联系的O标记为A，以便后续直接处理
     * @param board
     * @param i
     * @param j
     */
    private void dfs(char[][] board, int i, int j) {
        if (i >= board.length || j >= board[0].length || i < 0 || j < 0) {
            return;
        }
        if (board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'A';
        dfs(board, i + 1, j);
        dfs(board, i, j + 1);
        dfs(board, i - 1, j);
        dfs(board, i, j - 1);
    }
}