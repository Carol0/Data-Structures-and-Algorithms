package com.carol.interview.ali;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Carol
 * @date 2023/1/9
 * @since 1.0.0
 */
public class EatCandy {
    /**
     * 有n个牛牛一起去朋友家吃糖果，
     * 第i个牛牛一定要吃ai块糖果.而朋友家一共只有m块糖果，
     * 可能不会满足所有的牛牛都吃上糖果。
     * 同时牛牛们有k个约定，每一个约定为一个牛牛的编号对(i,j)，
     * 表示第i个和第j个牛牛是好朋友，他俩要么一起都吃到糖果，
     * 要么一起都不吃。保证每个牛牛最多只出现在一个编号对中。
     * 您可以安排让一些牛牛吃糖果，一些牛牛不吃。
     * 要求使能吃上糖果的牛牛数量最多（吃掉的糖果总量要小于等于m），
     * 并要满足不违反牛牛们的k个约定。
     * 第一行2个正整数n,m,1<=m,n<=10^3 ，
     * 第二行n个正整数ai ,1<=a1<=10^6
     * 第三行1个整数k,0<=k<=n/2
     * 接下来k行，每行两个正整数 ，表示第i个牛牛与第j个牛牛有约定。
     * 输入例子：
     * 3 10
     * 5 1 5
     * 1
     * 1 3
     * 输出例子：
     * 2
     * 思路：如果没有约定的条件限制，可以直接采用贪心算法，优先选取吃的少的牛牛，那么最终就能保证更多的牛牛吃到糖果
     * 当有限制条件之后，我们每一次的选择，都有可能对后面造成影响。完全可以对应0-1背包
     * 知道了使用动态规划
     * 转移方程：dp[i] = 当我有i块糖果的时候，最多可以满足多少个牛牛
     * dp[i] = Math.max(dp[i], dp[i - need[j]] + value[j])
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] numStr = reader.readLine().split(" ");
        int n = Integer.parseInt(numStr[0]);
        int m = Integer.parseInt(numStr[1]);
        int[] need = new int[n + 1];
        String[] candy = reader.readLine().split(" ");
        for (int i = 0 ; i < candy.length ; i ++) {
            need[i + 1] = Integer.parseInt(candy[i]);
        }
        int k = Integer.parseInt(reader.readLine());
        int[] value = new int[n + 1];
        Arrays.fill(value, 1);
        for (int i = 0 ; i < k ; i++) {
            String[] str = reader.readLine().split(" ");
            int p = Integer.parseInt(str[0]);
            int q = Integer.parseInt(str[1]);
            need[p] += need[q];
            value[p] += 1;
            value[q] = 0;
        }
        int[] dp = new int[m + 1];
        for (int i = 1 ; i <= n ; i ++) {
            if (value[i] == 0) {
                continue;
            }
            for (int j = m ; j >= need[i] ; -- j) {
                dp[j] = Math.max(dp[j], dp[j - need[i]] + value[i]);
            }
        }
        System.out.println(dp[m]);
    }
}