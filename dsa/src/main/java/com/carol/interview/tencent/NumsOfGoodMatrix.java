package com.carol.interview.tencent;

/**
 * @author Carol
 * @date 2022/12/20
 * @since 1.0.0
 */
public class NumsOfGoodMatrix {
    /**
     * 6.好矩阵
     * 我们定义一个矩阵为好矩阵，当且仅当该矩阵所有的
     * 2*2的子矩阵数字和为偶数。
     * 请问n行m列的矩阵中每个数均在[1,x]范围内的好矩阵有多少种
     * 由于答案过大，请对10^9+7取模。
     * 数据范围：2<=n,m,x<=10^9
     * 保证x为偶数
     * 测试数据：
     * 输入例子：
     * 2,2,2
     * 输出例子：
     * 8
     * 分析：
     * 1。先对第一列，第一列的每一个数[1,x]都可以取，第一列的组合方式有：x^n
     * 2。对于后面的每一列，如果能保证当前列与前一列奇偶性完全相同，那么任意2*2相加一定是偶数
     *     (x/2)^n
     *    对于后面的每一列，如果保证当前列与前一列奇偶行完全相反，那么任意2*2相加一定是偶数
     *     (x/2)^n
     * 3。因为一共有m-1列，((x/2)^n*2)^(m-1)
     *
     *
     *  最终：x^n *((x/2)^n*2)^(m-1)
     *  x^n * (x/2)^(m*n) * 2^(m - 1) / (x/2)^n
     *  =x^n * (2/x)^n * (x/2)^(m*n) * 2^(m - 1)
     *  =2^(m + n - 1) * (x/2)^(m*n)
     *
     *  快速幂：
     *  a^n = (a^(n/2))^2  = .....
     */
    public static void main(String[] args) {
        NumsOfGoodMatrix nums = new NumsOfGoodMatrix();
        //System.out.println(nums.quickPow(2L, 3L));
        System.out.println(nums.numsOfGoodMatrix(2,2,2));
    }
    long mod = (long) (Math.pow(10, 9) + 7);
    public int numsOfGoodMatrix(int n, int m, int x) {
        long a = n;
        a *= m;
        long answer1 = this.quickPow(2, m + n - 1);
        long answer2 = this.quickPow(x/2, a);
        return (int) (answer1 * answer2 % mod);
    }

    private long quickPow(long x, long n) {
        long answer = 1;
        while (n > 0) {
            if ((n & 1) > 0) {
                answer = (answer * x) % mod;
            }
            x = x * x % mod;
            n = n >> 1;
        }
        return answer;
    }
}