package com.carol.interview.ali;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Carol
 * @date 2023/2/6
 * @since 1.0.0
 */
public class TwoStr {
    /**
     * 小强有两个序列 a和 b，这两个序列都是由相同的无重复数字集合组成的，现在小强想把a序列变成b序列，他只能进行以下的操作：
     * 从序列a中选择第一个或者最后一个数字并把它插入a中的任意位置。
     * 问小强至少需要几次操作可以将序列a变为序列b。
     * 两个信息：
     * 1.a，b中出现的数字一摸一样，只是顺序不一样
     * 2.在将a通过调整顺序变为b的过程中，只能操作第一个或者最后一个数字
     * 前提：假设b为1 2 3 4（考虑最简单的情况，递增）
     * 1 3 2 4 在不考虑第二个限制的情况下，我们只需找到最长递增子序列，这个子序列的相对位置不论怎么调整应该是不变的，
     * 只需要调整相对位置不一样的，每一次的调整，能够将它放到最终的相对位置，因此，需要的最少调整次数为：总数-最长递增子序列
     * 现在，再来考虑第二个限制，如果再使用最长递增子序列去做，在操作第一个或者最后一个的时候，都会破坏原始的最长递增子序列，
     * 甚至可能变得更短，所以我们需要找到一段最长连续递增子序列，使得操作不会影响原始递增子序列，并且每一次的操作都会将某个数字
     * 放在最终相对位置。
     * 对于题目，b的元素顺序不确定，但是a中的每一个元素都对应b中元素，有一个唯一位置，那么我们只需要将a中元素转为b中对应元素的位置
     * 并且找到最长连续递增子序列个数，最后需要操作的次数为：总数-最长连续递增子序列个数
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //数据输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0 ; i < n ; i ++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0 ; i < n ; i ++) {
            b[i] = scanner.nextInt();
        }
        //key:b的数值，value：位置
        //映射b中元素与位置的关系
        Map<Integer, Integer> bNumIndexMap = new HashMap<>(n);
        for (int i = 0 ; i < n ; i ++) {
            bNumIndexMap.put(b[i], i);
        }
        //将a中元素替换为当前元素在b中的位置
        for (int i = 0 ; i< n ; i++) {
            a[i] = a[bNumIndexMap.get(a[i])];
        }
        //求位置的最长连续递增子序列
        int ans = 1;
        int temp = 1;
        for (int i = 1 ; i < n ; i ++) {
            //如果位置大于前一个位置，证明当前元素可以与前一个元素组合成一个新的最长连续递增子序列
            if (a[i] > a[i - 1]) {
                ++ temp;
                ans = Math.max(ans, temp);
            } else {
                temp = 1;
            }
        }
        System.out.println(n - ans);

    }
}