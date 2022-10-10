package com.carol.leetcode.dp;

/**
 * @author Carol
 * @date 2022/10/10
 * @since 1.0.0
 */
public class LC91 {
    /**
     * 一条包含字母A-Z 的消息通过以下映射进行了 编码 ：
     * 'A' -> "1"
     * 'B' -> "2"
     * ...
     * 'Z' -> "26"
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
     * "AAJF" ，将消息分组为 (1 1 10 6)
     * "KJF" ，将消息分组为 (11 10 6)
     * 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     * 题目数据保证答案肯定是一个 32 位 的整数。
     * 分析：从题目看，映射一个字母可能是一个数字，也可能是两个数字的组合，当前数字可以和它的前一个结合，也可以是后一个结合
     * 以当前数字与前一个结合为例：当前数字有两种情况 1。独立映射一个字母，2。与前一个字母共同组合映射一个字母
     * 因此，当前字母前面的选择可能影响当前字母的，所以考虑使用动态规划。
     * 思路：
     * 1。找转移方程
     *  假设当前位置i的可以映射的方法为f（i），当前位置独立映射一个字母：f（i）+= f（i - 1），当前字符与前一个能够合成一个映射一个字母 f（i） += f（i - 2）
     * 2。找初始值
     * 当前为空，只有一种走法
     */

    public static void main(String[] args) {
        LC91 lc91 = new LC91();
        System.out.println(lc91.numDecodings2("12"));
    }

    public int numDecodings(String s) {
        //每一个数字
        char[] chars = s.toCharArray();
        int[] f = new int[s.length() + 1];
        f[0] = 1;
        for (int i = 0; i < s.length() ; i ++) {
            if (chars[i] != '0') {
                f[i + 1] += f[i];
            }
            if (i >= 1 && chars[i - 1] != '0' && ((chars[i - 1] - '0') * 10 + (chars[i] - '0')) <= 26) {
                f[i + 1] += f[i - 1];
            }
        }
        return f[s.length()];
    }

    /**
     * 因为转移方程仅用到 f（i- 1），f（i - 2），所以只需要能够记录f（i-1），f（i-2）
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        int result = 0, last1 = 1,last2 = 0;
        for (int i = 0; i < s.length() ; i ++) {
            result = 0;
            if (chars[i] != '0') {
                result += last1;
            }
            if (i >= 1 && chars[i - 1] != '0' && ((chars[i - 1] - '0') * 10 + (chars[i] - '0')) <= 26) {
                result += last2;
            }
            last2 = last1;
            last1 = result;
        }
        return result;
    }
}