package com.carol.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carol
 * @date 2022/6/3
 * @since 1.0.0
 */
public class ConsecutiveNumbersSum {
    /**
     * https://leetcode.cn/problems/consecutive-numbers-sum/
     * 829. 连续整数求和
     * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n的组数 。
     * 输入: n = 5
     * 输出: 2
     * 解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5
     * 1 <= n <= 10^9'
     * sn = (an-a1+1)(an+a1)/2
     * 思路：一组连续的数相加==n，则除了m这组以外，其余数不能超过n/2 + 1；
     * 枚举a1到an
     */
    public static void main(String[] args) {
        ConsecutiveNumbersSum consecutiveNumbers = new ConsecutiveNumbersSum();
        System.out.println(consecutiveNumbers.consecutiveNumbersSum(9));
    }
    public int consecutiveNumbersSum(int n) {
        //sn = k(a1+an)/2 = k(2a1+k+1)/2 => k(k+1)<=2sn
        int result = 0;
        int max = 2 * n;
        for (int k = 1 ; k * (k + 1) <= max  ; k ++ ) {
            if (isConsecutiveNumber(k, n)) {
                ++ result;
            }
        }
        return result;
    }

    private boolean isConsecutiveNumber(int k, int n) {
        if (k % 2 == 1) {
            //k为奇数 n / k = a1 + (k - 1)/ 2
            return n % k == 0;
        }
        //2n/k = 2*a1 + k -1
        return n % k != 0 && (2 * n) % k == 0;
    }
}