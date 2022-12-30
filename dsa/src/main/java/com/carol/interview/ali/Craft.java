package com.carol.interview.ali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Carol
 * @date 2022/12/30
 * @since 1.0.0
 */
public class Craft {
    /**
     * 小强在玩一个走迷宫的游戏，
     * 他操控的人物现在位于迷宫的起点，
     * 他的目标是尽快的到达终点。
     * 每一次他可以选择花费一个时间单位
     * 向上或向下或向左或向右走一格，
     * 或是使用自己的对称飞行器花费一个时间单位
     * 瞬移到关于当前自己点中心对称的格子，
     * 且每一次移动的目的地不能存在障碍物。
     * 具体来说，设当前迷宫有n 行 m 列，
     * 如果当前小强操控的人物位于点A(x,y)，
     * 那么关于点A 中心对称的格子B(x ′ ,y ′)
     *  满足 x+x'=n+1且 y+y'=m+1
     * 需要注意的是，对称飞行器最多使用5次。
     * 第一行两个空格分隔的正整数n,m  ，分别代表迷宫的行数和列数。
     * 接下来  n行 每行一个长度为m的字符串来描述这个迷宫。
     * 其中
     * .代表通路。
     *  #代表障碍。
     *  S代表起点。
     *  E代表终点。
     * 保证只有一个 S和 一个E  。
     * 思路：在当前位置到达中点，每一次的选择可能有5中，上下左右以及中心位置输入例子：
     * 4 4
     * #S..
     * E#..
     * #...
     * ....
     * 输出例子：
     * 4
     * 以S为中心，采用广度搜索，第一次到达E点的路径一定是最短路径
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        char[][] matrix = new char[n][m];
        for (int i = 0 ; i < n ; i ++) {
            matrix[i] = reader.readLine().toCharArray();
        }
        Craft craft = new Craft();
        System.out.println(craft.craft(matrix));;

    }
    public int craft(char[][] matrix) {
        int x = 0,y = 0;
        //找到起点
        boolean find = false;
        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
               if (matrix[i][j] == 'S') {
                   x = i ;
                   y = j;
                   find = true;
                   break;
               }
            }
            if (find) {
                break;
            }
        }
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[]{x, y, 5, 0});
        while (!deque.isEmpty()) {
            int[] temp = deque.poll();
            int row = temp[0], col = temp[1], cnt = temp[2], step = temp[3];
            if (matrix[row][col] == 'E') {
                return step;
            }
            //上下左右已经中心位置吧
            //向上
            if (row - 1 >= 0 && (matrix[row - 1][col] == '.' || matrix[row - 1][col] == 'E')) {
                deque.add(new int[]{row - 1, col, cnt, step + 1});
                if (matrix[row - 1][col] == '.') {
                    matrix[row - 1][col] = '#';
                }
            }
            //向下
            if (row + 1 < matrix.length && (matrix[row + 1][col] == '.' || matrix[row + 1][col] == 'E')) {
                deque.add(new int[]{row + 1, col, cnt, step + 1});
                if (matrix[row + 1][col] == '.') {
                    matrix[row + 1][col] = '#';
                }
            }
            //向左
            if (col - 1 >= 0 && (matrix[row][col - 1] == '.' || matrix[row][col - 1] == 'E')) {
                deque.add(new int[]{row, col - 1, cnt, step + 1});
                if (matrix[row][col-1] == '.') {
                    matrix[row][col-1] = '#';
                }
            }
            //向右
            if (col + 1 < matrix[0].length && (matrix[row][col + 1] == '.' || matrix[row][col + 1] == 'E')) {
                deque.add(new int[]{row, col + 1, cnt, step + 1});
                if (matrix[row][col + 1] == '.') {
                    matrix[row][col + 1] = '#';
                }
            }
            //飞行器 x+x'=n+1且 y+y'=m+1
            if (matrix.length - row - 1 >= 0
                    && matrix[0].length - col - 1 >= 0
                    && (matrix[matrix.length - row - 1][matrix[0].length - col - 1] == '.' || matrix[matrix.length - row - 1][matrix[0].length - col - 1] == 'E')
                    && cnt > 0) {
                deque.add(new int[]{matrix.length - row - 1, matrix[0].length - col - 1, cnt - 1, step + 1});
                if (matrix[matrix.length - row - 1][matrix[0].length - col - 1] == '.') {
                    matrix[matrix.length - row - 1][matrix[0].length - col - 1] = '#';
                }
            }
        }
        return -1;
    }
}