package com.carol.leetcode.test;

/**
 * @author Carol
 * @date 2022/11/7
 * @since 1.0.0
 */
public class L001 {
    /**
     * 除法计算，并且只取整数部分
     * 首先看除法计算其实就是被除数里面可以有多少个除数
     * 我们可以使用减法方式来计算
     * 并且有正负号区别，当两个符号相同的时候为正数，不相同时候为负数
     * 设置一个变量标志结果符号，然后将除数，被除数处理为正数，方便进行减法计算
     * @param a 被除数
     * @param b 除数
     * @return
     */
    public int divide(int a, int b) {
        if (a == 0) {
            return 0;
        }
        boolean symbol = ( a > 0 && b > 0 ) || (a < 0 && b < 0);
        a = Math.abs(a);
        b = Math.abs(b);
        int res = 0;
        int diff = a - b;
        while (diff >= 0) {
            ++ res;
            diff -= b;
        }
        return res;
    }
}