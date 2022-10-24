package com.carol.leetcode.day;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/10/20
 * @since 1.0.0
 */
public class LC799 {
    /**
     * 我们构建了一个包含 n 行(索引从 1 开始)的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
     *
     * 例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
     * 给定行数n和序数 k，返回第 n 行中第 k个字符。（k从索引 1 开始）
     * 分析：最先想到的应该是暴力解法，直接递归模拟到n行，然后得到n行的第k个字符
     * 一个思路：当前行的数值与前一行有关，并且只会出现0，1，可能与位运算有关系，可以朝这个方向去考虑
     */
    /**
     * 暴力解法，直接模拟
     * @param n
     * @param k
     * @return
     */
    public int kthGrammar(int n, int k) {
        List<Integer> last = new ArrayList<>();
        last.add(0);
        int level = 1;
        while (level != n) {
            List<Integer> temp = new ArrayList<>();
            for (Integer l : last) {
                if (l == 0) {
                    temp.add(0);
                    temp.add(1);
                    continue;
                }
                if (l == 1) {
                    temp.add(1);
                    temp.add(0);
                }
            }
            ++ level;
            last = temp;
        }
        return last.get(k - 1);
    }

}