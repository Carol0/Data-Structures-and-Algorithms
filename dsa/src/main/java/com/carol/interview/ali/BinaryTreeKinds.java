package com.carol.interview.ali;

/**
 * @author Carol
 * @date 2022/12/29
 * @since 1.0.0
 */
public class BinaryTreeKinds {
    /**
     * 小强现在有n个节点,他想请你
     * 帮他计算出有多少种不同的二叉树满足
     * 节点个数为n且树的高度不超过m的方案.
     * 因为答案很大,所以答案需要模上1e9+7后输出.
     * 树的高度: 定义为所有叶子到根路径上节点个数的最大值.
     * 示例1
     * 输入例子：
     * 3 3
     * 输出例子：
     * 5
     * 示例2
     * 输入例子：
     * 3 2
     * 输出例子：
     * 1
     * 示例3
     * 输入例子：
     * 4 3
     * 输出例子：
     * 6
     * 思路就是：树的高度=1+Math.max(左子树的高度，右子树的高度)
     * 假设当前用n个结点生成一颗高度不大于m的树的种树dp[n][m] = dp[k][m-1]*dp[n - k - 1][m-1] (0<=k<=n-1)
     * 卡特兰数：出栈顺序以及括号 123 123 213  +1-1+1-1+1-1  +1-1-1+1-1+1 -1+1+1+1-1+1 n+1
     * 2n种找n个位置放1-2n中找n+1个位置放1即为正确序列的个数 = f（n） = f(0)*f(n-1) + f(1)*f(n-2).....
     */
    public static void main(String[] args) {
        BinaryTreeKinds binaryTreeKinds = new BinaryTreeKinds();
        System.out.println(binaryTreeKinds.binaryTreeKinds(4,3));;
    }
    long mod = (long) (Math.pow(10, 9) + 7);
    public long binaryTreeKinds(int n, int m) {
        //dp[i][j]表示由i个结点构成高度不大于j的树的种类
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0 ; i <= m ; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1 ; i <= n ; i ++) {
            for (int j = 1 ; j <= m ; j ++) {
                for (int k = 0 ; k < i ; k ++) {
                    dp[i][j] = (dp[i][j]) + (dp[k][j - 1] * dp[i - k - 1][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}