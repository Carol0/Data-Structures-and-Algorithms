package com.carol.leetcode;

/**
 * @author Carol
 * @date 2022/6/11
 * @since 1.0.0
 */
public class MinFlipsMonoIncr {
    /**
     * 如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）
     * 的形式组成的，那么该字符串是 单调递增 的。
     *
     * 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
     *
     * 返回使 s 单调递增的最小翻转次数。
     *：s = "010110"
     * 输出：2
     * 解释：翻转得到 011111，或者是 000111。
     *
     * 一个位置可能有两种情况0，1， 当前位置的取值会受到上一个位置取值的影响
     * ，上一个位置为0，则当前位置可以为0，也可为1，上一个位置为1，当前位置只能为1
     * 才可以单调递增
     */

    public static void main(String[] args) {
        MinFlipsMonoIncr minFlipsMonoIncr = new MinFlipsMonoIncr();
        System.out.println(minFlipsMonoIncr.minFlipsMonoIncr2("00110"));
    }

    int min ;
    public int minFlipsMonoIncr(String s) {
        min = s.length();
        this.dfs(0, s, 0);
        System.out.println(min);
        return min;
    }

    /**
     *
     * @param index 当前字符位置
     * @param s 字符春
     */
    private void dfs(int index, String s, int changeTimes) {
        if (index == s.length()) {
            min = Math.min(min, changeTimes);
            return;
        }
        //当前位置两种选择0，1
        //将当前位置设置为0，且当前位置为1，需要进行翻转
        if (s.charAt(index) == '1') {
            ++ changeTimes;
        }
        this.dfs(index + 1, s, changeTimes);
        if (s.charAt(index) == '1') {
            -- changeTimes;
        }
        //将当前位置设置为1，后面的应该全部为1,但是有些已经为1了，不用修改
        int times = changeTimes;
        for (int i = index ; i < s.length() ; i ++) {
            if (s.charAt(i) == '0') {
                ++ times;
            }
        }
        min = Math.min(times, min);
    }

    public int minFlipsMonoIncr2(String s) {
       //当前位置的取值只与上一个位置有关，当前位置取0，上一个位置需要为0，当前位置取1，上一个位置可为0可为1
        //保存上一次将当前位置设为0，1的次数
        int lastDp0 = 0, lastDp1 = 0;
        for (int i = 0 ; i < s.length() ; i++) {
            //当前位置设置为0，上一次需要设置为0
            int currentDp0 = lastDp0;
            //当前位置设置为1，上一次可以为0，可以为1，题意需要最小翻转次数，则取较小值
            int currentDp1 = Math.min(lastDp0, lastDp1);
            //当前为0的话，那么更改为1的操作次数需要增加，否则更改为0的次数需要增加
            if (s.charAt(i) == '0') {
                ++ currentDp1;
            } else {
                ++ currentDp0;
            }
            lastDp0 = currentDp0;
            lastDp1 = currentDp1;
        }
        //取二者较小值
        return Math.min(lastDp0, lastDp1);
    }
}