package com.carol.interview.tencent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Carol
 * @date 2022/12/19
 * @since 1.0.0
 */
public class SubarrayNum {
    /**
     * 5.
     * 连续子数组数量
     * 给定一个数组，请你编写一个函数，返回元素乘积末尾零数量大于等于的连续子数组数量。
     * 答案可能太大，请将答案对10^9+7取模再返回。
     *
     * 数组长度不超过10^5。
     * 数组元素、x均为不超过10^9的正整数。（>0）
     * 输入例子：
     * [5,2,3,50,4],2
     * 输出例子：
     * 6
     * 思路：
     * 题目要求：求一个数量（子数组的数量），子数组满足乘积末尾的0>=x
     * 转为：如果已经知道了一个子数组，如何知道他末尾有多少个0
     * 规律：
     * 1。一个数末尾有多少个0，那么它与其他数的乘积的末尾至少也有这么多个0
     * 2.找2和5，一对2和5就有一个0
     * 双指针，每次判断双指针内部元素是否符合要求，符合要求即记为一个有效子数组
     * 1.当前范围内若不符合要求，移动第二个指针，直至符合要求，当符合要求的时候，后面所有的进来都应该是符合要求，应当直接计入，并且
     * 移动第一个指针。
     * 2.当第二个指针不能进行移动时需要移动第一个指针
     */
    public static void main(String[] args) {
        SubarrayNum subarrayNum = new SubarrayNum();
        System.out.println(subarrayNum.getSubarrayNum(new ArrayList<>(Arrays.asList(5,2,3,50,4)), 2));
    }
    public int getSubarrayNum(List<Integer> a, int x) {
        if (a.size() < 1) {
            return 0;
        }
        //0, 2, 5出现的次数
        int[] count = new int[3];
        int answer = 0 ;
        int i = 0, j =0;
        this.addCountByNum(count, a.get(0));
        while (i <= j && i < a.size()) {
            if (count[0] + Math.min(count[1], count[2]) >= x) {
                answer = (int) ((answer + (a.size() - j)) % (Math.pow(10, 9) + 7));
                this.removeCountByNum(count, a.get(i));
                ++ i;
                continue;
            }
            if (j == a.size() - 1) {
                this.removeCountByNum(count, a.get(i));
                ++ i;
                continue;
            }
            ++ j;
            this.addCountByNum(count, a.get(j));
        }
        return answer;
    }

    /**
     * 更新当前num移入范围内是0，2，5的情况
     * @param num
     */
    private void addCountByNum(int[] count, int num) {
        while (num % 10 == 0) {
            ++  count[0];
            num /= 10;
        }
        while (num % 2 == 0) {
            ++ count[1];
            num /= 2;
        }
        while (num % 5 == 0) {
            ++ count[2];
            num /= 5;
        }
    }

    private void removeCountByNum(int[] count, int num) {
        while (num % 10 == 0) {
            --  count[0];
            num /= 10;
        }
        while (num % 2 == 0) {
            -- count[1];
            num /= 2;
        }
        while (num % 5 == 0) {
            -- count[2];
            num /= 5;
        }
    }

}