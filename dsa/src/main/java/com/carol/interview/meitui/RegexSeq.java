package com.carol.interview.meitui;

import com.carol.interview.unkonw.Lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Carol
 * @date 2023/2/10
 * @since 1.0.0
 */
public class RegexSeq {
    /**
     * https://www.nowcoder.com/exam/test/65471527/detail?pid=28665343&examPageSource=Company&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26selectStatus%3D0%26tagIds%3D179&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
     * 第二题
     * 我们称一个长度为n的序列为正则序列，当且仅当该序列是一个由1~n组成的排列，
     * 即该序列由n个正整数组成，取值在[1,n]范围，且不存在重复的数，同时正则序列不要求排序
     * 有一天小团得到了一个长度为n的任意序列s，他需要在有限次操作内，
     * 将这个序列变成一个正则序列，每次操作他可以任选序列中的一个数字，并将该数字加一或者减一。
     * 请问他最少用多少次操作可以把这个序列变成正则序列？
     * 思路：要将一个任意序列变为正则序列，则需要将不属于正则序列的数字变为离它最近的缺少的正则数字
     * 如果n为5
     * -1 2 3 10 100 则将-1变为1 10变为4 100 变为5
     * 1.将原始序列进行排序
     * 2.初始化一个临时数组，下标==正则序列中的数字，并且只记录第一次出现
     * 3.记录所有多次出现的元素或者超过正则序列范围内的元素
     * 4.将所有多次出现的元素向正则序列中填充
     */

    public static void main(String[] args) {
        RegexSeq regexSeq = new RegexSeq();
        regexSeq.regexReq2();

    }

    /**
     * 直接进行排序，时间复杂度O(nlogn)
     */
    private void regexReq1() {
        //数据输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        Arrays.sort(nums);

        //记录等于当前位置的正则序列的数值是否出现
        boolean[] right = new boolean[n + 1];
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 1 && nums[i] <= n && !right[nums[i]]) {
                right[nums[i]] = true;
                continue;
            }
            temp.add(nums[i]);
        }
        if (temp.size() == 0) {
            System.out.println(0);
            return;
        }
        int ans = 0;
        int index = 0;
        for (int i = 1; i <= n ; i++) {
            if (!right[i]) {
                ans += Math.abs(i - temp.get(index));
                ++ index;
            }
        }
        System.out.println(ans);
    }


    /**
     * 直接遍历数组，将小于1的元素直接变为1，累加操作步数
     * 最后形成的数组，全部元素相加的值与1～n相加的差值即为还需要进行的步数
     */
    private void regexReq2() {
        //数据输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        //需要进行的操作步数
        int ans = 0;
        //变换之后的所有值
        int total = 0;
        //1-n相加
        int nTotal = 0;
        for (int i = 0; i < n; i++) {
            nTotal += (i + 1);
            if (nums[i] < 1) {
                //把小于1的数字直接变为1
                ans += (1 - nums[i]) ;
                total += 1;
                continue;
            }
            total += nums[i];
        }
        ans += Math.abs(total - nTotal);
        System.out.println(ans);
    }


}