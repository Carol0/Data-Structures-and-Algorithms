package com.carol.interview.tencent;

/**
 * @author Carol
 * @date 2022/12/16
 * @since 1.0.0
 */
public class MakeSame {
    /**
     * 4.01串修改
     * 给定一个只包含'0'和'1'两种字符的字符串，每次操作可以选择相邻的两个字符，将它们同时变成'0'或者同时变成'1'。
     * 请问最少多少次操作后，所有的字符都相同？
     * 字符串长度不超过1000。
     * 例子：
     * "1001101"
     * 输出：
     * 2
     * @param args
     * 首先分析题意：每一次的操作（变为0或者1），这个操作会影响我们后面操作，这个称为后效性，一般这种考虑使用dp
     * dp核心：转移方程
     * dp函数表示到当前字符，需要进行操作的次数
     * 但是因为不知道需要将当前操作为0还是1，所以我们设置两个dp函数，一个表示最终变为0一个表示最终变为1
     * dp[i] = Math.min(dp[i - 2], dp[i - 1]) + 1
     * 因为当前我们并不知道最终变为0还是1，所以需要两个
     * dp0,dp1用来分别记录到当前位置将所有字符变为0/1的最小操作次数
     *
     */
    public static void main(String[] args) {
        MakeSame makeSame = new MakeSame();
        System.out.println(makeSame.minOperations2("1001101"));
    }

    /** 动态规划
     * @param str
     * @return
     */
    public int minOperations (String str) {
        if (str.length() < 2) {
            return 0;
        }
        if (str.length() == 2) {
            if (str.charAt(0) == str.charAt(1)) {
                return 0;
            }
            return 1;
        }
        int[] dp0 = new int[str.length()];
        int[] dp1 = new int[str.length()];
        this.initDp(dp0, dp1, str);
        for (int i = 2 ; i < str.length() ; i++) {
            if (str.charAt(i) == '0') {
                dp0[i] = dp0[i - 1];
                dp1[i] = 1 + Math.min(dp1[i - 2], dp1[i - 1]);
            } else {
                dp0[i] = 1 + Math.min(dp0[i - 2], dp0[i - 1]);
                dp1[i] = dp1[i - 1];
            }
        }
        return Math.min(dp0[str.length() - 1], dp1[str.length() - 1]);
    }

    private void initDp(int[] dp0, int[] dp1, String str) {
        if (str.charAt(0) == str.charAt(1)) {
            if (str.charAt(0) == '0') {
                dp1[0] = 1;
                dp1[1] = 1;
            } else {
                dp0[0] = 1;
                dp0[1] = 1;
            }
        } else {
            if (str.charAt(0) == '0') {
                dp0[1] = 1;
                dp1[0] = 1;
                dp1[1] = 1;
            } else {
                dp0[0] = 1;
                dp0[1] = 1;
                dp1[1] = 1;
            }
        }
    }

    //贪心
    public int minOperations2 (String str) {
        int zeroOperation = 0;
        int oneOperation = 0;
        for (int i = 0 ; i < str.length() ; i ++) {
            if (str.charAt(i) == '0') {
                if (i < str.length() - 1 && str.charAt(i + 1) == '0') {
                    ++ i;
                }
                ++ oneOperation;
                continue;
            }
            if (i < str.length() - 1 && str.charAt(i + 1) == '1') {
                ++ i;
            }
            ++ zeroOperation;
        }
        return Math.min(oneOperation, zeroOperation);
    }
}