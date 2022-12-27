package com.carol.interview.ali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Carol
 * @date 2022/12/27
 * @since 1.0.0
 */
public class Calculate {
    /**
     * 2.小强爱数学
     * 小强发现当已知xy = B以及x+y = A时,能很轻易的算出x^2+y^2
     *  的值.但小强想请你在已知A和B的情况下,计算出x^n+y^n
     *  的值.因为这个结果可能很大,所以所有的运算都在模1e9+7下进行.
     * 输入描述：
     * 第一行输入一个正整数t.表示有t组数据
     * 接下来行,每行输入三个整数A,B和n.
     * 1<=t<=10
     * 0<=A,B<=1e9+7
     * 1<=n<=1e5
     * 输出描述：
     * 输出t行,每一行表示每组数据的结果.
     * 输入例子：
     * 3
     * 4 4 3
     * 2 3 4
     * 5 2 6
     * 输出例子：
     * 16
     * 999999993
     * 9009
     * (x+y)^n
     * f(1) = x+y = x+y
     * f(2) = x^2+y^2 -> (x+y)^2 = x^2+2xy+y^2 -> x^2+y^2 = (x+y)^2 - 2xy
     * f(3) = x^3+y^3 -> (x+y)^3 = x^3 + 3x^2y + 3xy^2 + y^3 -> x^3+y^3 = (x+y)^3 - 3(xy^2+x^2y)
     * =(x+y)^3 - 3xy(x+y) = (x+y)((x+y)^2 - 3xy) = (x+y)((x+y)^2 - 2xy - xy) = (x+y)((x+y)^2-2xy)-xy(x+y)
     * f(3) = (x+y)f(2) - xyf(1)
     * f(n) = Af(n - 1) - Bf(n -2)
     * (x+y)^n =
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String tStr = reader.readLine();
        int t = Integer.parseInt(tStr);
        Calculate calculate = new Calculate();
        while (t > 0) {
            String[] nums = reader.readLine().split(" ");
            System.out.println(calculate.calculate(Long.parseLong(nums[0]), Long.parseLong(nums[1]), Long.parseLong(nums[2])));
            -- t;
        }
    }

    long mod = (long) (Math.pow(10, 9) + 7);
    public long calculate(long A, long B, long n) {
        //p,q分别表示当前位置的前两项
        long p = A, q = A * A - 2*B;
        if (n == 1) {
            return p;
        }
        if (n == 2) {
            return q;
        }
        n -= 2;
        while (n > 0) {
            long temp = (((A * q) % mod) - ((B * p) % mod) + mod) % mod;
            p = q;
            q = temp;
            -- n;
        }
        return q;
    }
}