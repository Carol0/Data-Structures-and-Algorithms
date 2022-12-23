package com.carol.interview.ali;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Carol
 * @date 2022/12/23
 * @since 1.0.0
 */
public class MinTime {
    /**
     * 小强作为强班的班长.决定带着包含他在内的n个同学去春游.
     * 路程走到一半,发现前面有一条河流.且只有一条小船.经过实验后发现,
     * 这个小船一次最多只能运送两个人.而且过河的时间是等于两个人中体重较大的那个人的体重.
     * 如果只有一个人,那么过河时间就是这个人的体重.
     * 现在小强想请你帮他分析如何安排才能在最短时间内使所有人都通过这条河流.
     * 小强很懒,他并不想知道具体怎么过河,只要你告诉他最短的时间.
     * 输入描述：
     * 第一行输入一个整数T.表示有T组测试数据.
     * 每组数据,第一行输入一个整数N.表示人数.
     * 接下来一行输入N个整数,a[i]表示第i个人的体重是a[i]
     * 1<=T<=10,1<=N<=10^5,1<=a[i]<=10^4
     * 测试数据：
     * 输入例子：
     * 2 --有两个测试用例
     * 4 --有四个人
     * 2 10 12 11 --每个人的体重
     * 4
     * 2 3 7 8
     * 输出例子：
     * 37
     * 19
     * 思路：
     * 1.只有一条小船-》小船到河对面之后，如果还有人没有过河，那么需要有人把小船开回来
     * 2.每次的时间为小船上最重人的重量
     * 考虑方案的时候需要综合两个，如何去节省时间以及让谁把船开回来节省时间
     * 当人数小于4的时候：
     * 只有一个人：当前需要花费的时间就是当前这个人的重量
     * 两个人：两个人中体重最大的那个人
     * 三个人：最小值是三个人的重量相加
     * 当人数大于等于4的时候：考虑的是将两个最重的人送过去需要花费时间的方案
     * 1.先把最重的两个人送过去，如果让次重的开回来，花费的时间会很大，考虑先将一个较小（次轻）的送过去，让他做这个开回来的任务
     * a[0] + 2*a[1] +  a[n]
     * 2.每次让最轻的人去送当前两个最重的人
     * 2*a[0] + a[n] + a[n - 1]
     */
    public static void main(String[] args) {
        MinTime minTime = new MinTime();
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0 ; i < t ; i ++) {
            int n = scanner.nextInt();
            int[] weights = new int[n];
            for (int j = 0 ; j < n ; j ++) {
                weights[j] = scanner.nextInt();
            }
            System.out.println( minTime.minTime(weights));;
        }

        int[] weights1 = new int[]{2 ,10, 12, 11};
        System.out.println( minTime.minTime(weights1));;
        int[] weights2 = new int[]{2 ,3 ,7, 8};
        System.out.println(minTime.minTime(weights2));
    }
    public int minTime(int[] weights) {
        Arrays.sort(weights);
        int n = weights.length;
        int answer = 0;
        while (n >= 4) {
            answer += Math.min(weights[0] + 2*weights[1] + weights[n - 1], 2 * weights[0] + weights[n - 1] + weights[n -2]);
            n -= 2;
        }
        if (n == 3) {
            answer += weights[0] + weights[1] + weights[2];
        }
        if (n == 2) {
            answer += weights[1];
        }
        if(n == 1) {
            answer += weights[0];
        }
        return answer;
    }
}