package com.carol.interview.ali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Carol
 * @date 2023/1/11
 * @since 1.0.0
 */
public class PlanNum {
    /**
     * https://www.nowcoder.com/exam/test/64934603/detail?pid=30440638&examPageSource=Company
     * 第8题
     * 思路：其实之前做过很多相似的题目，例：从左上角走到右下角，之间存在障碍物。。。
     * 采用动态规划
     * 转移方程：dp[i][j]:表示从左上角(0,0)位置到当前位置(i,j)的路径条数
     * 假设当前求从(p,q)出发到(i,j)位置的路径
     * dp[i][j] = dp[p][q] + dp[i][j]
     */
    public static void main(String[] args) throws IOException {
        PlanNum planNum = new PlanNum();
//        int[][] matrix = new int[][]{{2,1,1},{1,1,1},{1,1,1}};
//        System.out.println(planNum.planNum(matrix));
        //效率比Scanner高
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        while (t > 0) {
            String[] nmStr = reader.readLine().split(" ");
            int n = Integer.parseInt(nmStr[0]);
            int m = Integer.parseInt(nmStr[1]);
            int[][] matrix = new int[n][m];
            for (int i = 0 ; i < n ; i ++) {
                String[] numStr = reader.readLine().split(" ");
                for (int j = 0 ; j < m ; j ++) {
                    matrix[i][j] = Integer.parseInt(numStr[j]);
                }
            }
            System.out.println(planNum.planNum(matrix));
            -- t;
        }

    }

    public int planNum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = 1;
        //起点(i,j)到终点(p,q)
        for (int i = 0 ; i < matrix.length ; i ++) {
            for (int j = 0 ; j < matrix[0].length ; j ++) {
                for (int p = i ; p - i <= matrix[i][j] && p < matrix.length ; p ++ ) {
                    for (int q = j ; q - j <= matrix[i][j] && q < matrix[0].length ; q++) {
                        if (p == i && q == j) {
                            continue;
                        }
                        if (p + q - i - j <= matrix[i][j]) {
                            dp[p][q] += dp[i][j];
                        }
                    }
                }
            }
        }
        return dp[matrix.length - 1][matrix[0].length -1];
    }
}