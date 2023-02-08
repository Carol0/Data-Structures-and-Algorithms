package com.carol.interview.meitui;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Carol
 * @date 2023/2/8
 * @since 1.0.0
 */
public class FailScore {
    /**
     * 某比赛已经进入了淘汰赛阶段,已知共有n名选手参与了此阶段比赛，
     * 他们的得分分别是a_1,a_2….a_n,小美作为比赛的裁判希望设定一个分数线m，使得所有分数大于m的选手晋级，其他人淘汰。
     * 但是为了保护粉丝脆弱的心脏，小美希望晋级和淘汰的人数均在[x,y]之间。
     * 显然这个m有可能是不存在的，也有可能存在多个m，如果不存在，请你输出-1，如果存在多个，请你输出符合条件的最低的分数线。
     * https://www.nowcoder.com/exam/test/65405099/detail?pid=28665343&examPageSource=Company&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26selectStatus%3D0%26tagIds%3D179&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
     * 思路：根据题意，我们需要通过一个分数m，将所有选手分成两个部分，左边部分为淘汰选手，右边部分为晋级选手
     * 并且，淘汰选手和晋级选手的人数都需要在[x,y]的范围内
     * 1.如果2*y 小于n 那么一定找不到符合符合条件的，直接返回-1
     * 2.将所有选手的得分进行一个排序，如果存在一个解，那么位于左边的x个选手一定为淘汰选手，继续往后遍历，一旦找到一个值大于x位置的选手
     * 那么一定为晋级选手，然后对晋级选手的个数进行判断
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        if ((y << 1) < n) {
            System.out.println(-1);
            return;
        }
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = scanner.nextInt();
        }
        Arrays.sort(score);
        int end = Math.min(x + y, n);
        for (int i = x; i < end; i++) {
            //淘汰人数超过范围，直接返回
            if (i > y) {
                System.out.println(-1);
                return;
            }
            //找到晋级选手
            if (score[i] != score[i - 1]) {
                int temp = n - i;
                if (temp >= x && temp <= y) {
                    System.out.println(score[i - 1]);
                    return;
                }
            }
        }
        System.out.println(-1);
    }
}