package com.carol.interview.tencent;

/**
 * @author Carol
 * @date 2022/12/12
 * @since 1.0.0
 */
public class MinOperation {
    /**
     * 给定一个只包含小写字母字符串，每次可以选择两个相同的字符删除，并在字符串结尾新增任意一个小写字母。
     * 请问最少多少次操作后，所有的字母都不相同？
     * 输入例子：
     * "abab"
     * 输出例子：
     * 2
     * 例子说明：
     * 第一次操作将两个'a'变成一个'f'，字符串变成"bbf"。
     * 第二次操作将两个'b'变成一个'b'，字符串变成"fb"。
     * 操作方式不是唯一的，但可以证明，最少操作次数为2。
     * 思路：
     * 1。只需要查看有多少是相同的字符，对于相同的字符需要去判断，需要多少个字符去替换他，但是不需要考虑替换他的字符是什么
     * eg，当前两个a，任意取一个字符进行替换，当前使用到了26个字母中的一个
     * 当前如果是三个a，为了保证最少的替换次数，这里需要使用26个字母中的两个
     * 2。我们按照1的逻辑，将字符串中的所有字符进行一次替换，并且记录我们使用了多少个26个字母，如果少于直接返回
     * 如果大于，使用的里面还有重复的字符，还要继续进行替换，需要进行替换的次数为超过的个数
     */
    public static void main(String[] args) {
        MinOperation minOperation = new MinOperation();
        System.out.println(minOperation.minOperation("abcdefghijklmnopqrstuvwxyz"));
    }
    public int minOperation(String str) {
        //用下标代替字母，value值记录字母出现的次数
        int[] chars = new int[26];
        //初始化字母出现的次数
        for (char ch : str.toCharArray()) {
            ++ chars[ch - 'a'];
        }
        //记录使用到的26个字母的个数
        int ans = 0;
        //记录操作次数
        int operate = 0;
        for (int i = 0 ; i < 26 ; i ++) {
            if (chars[i] == 0) {
                continue;
            }
            if (chars[i] == 1) {
                ans += 1;
                continue;
            }
            ans += (chars[i] % 2 == 0 ? chars[i] / 2 : chars[i] / 2 + 1);
            operate += chars[i] / 2;
        }
        if (ans > 26) {
            operate += (ans - 26);
        }
        return operate;
    }
}