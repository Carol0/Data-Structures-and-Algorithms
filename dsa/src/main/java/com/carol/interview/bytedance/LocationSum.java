package com.carol.interview.bytedance;

/**
 * @author Carol
 * @date 2022/12/6
 * @since 1.0.0
 */
public class LocationSum {
    /**
     * 2.万万没想到之抓捕孔连顺
     * 我叫王大锤，是一名特工。我刚刚接到任务：在字节跳动大街进行埋伏，抓捕恐怖分子孔连顺。和我一起行动的还有另外两名特工，我提议
     * 1. 我们在字节跳动大街的 N 个建筑中选定 3 个埋伏地点。
     * 2. 为了相互照应，我们决定相距最远的两名特工间的距离不超过 D 。
     * 我特喵是个天才! 经过精密的计算，我们从X种可行的埋伏方案中选择了一种。这个方案万无一失，颤抖吧，孔连顺！
     * ……
     * 万万没想到，计划还是失败了，孔连顺化妆成小龙女，混在cosplay的队伍中逃出了字节跳动大街。只怪他的伪装太成功了，就是杨过本人来了也发现不了的！
     * 请听题：给定 N（可选作为埋伏点的建筑物数）、 D（相距最远的两名特工间的距离的最大值）以及可选建筑的坐标，计算在这次行动中，大锤的小队有多少种埋伏选择。
     * 注意：
     * 1. 两个特工不能埋伏在同一地点
     * 2. 三个特工是等价的：即同样的位置组合( A , B , C ) 只算一种埋伏方法，不能因“特工之间互换位置”而重复使用
     * 数据范围： 
     * 时间限制：C/C++ 1秒，其他语言2秒
     * 空间限制：C/C++ 128M，其他语言256M
     * 输入描述：
     * 第一行包含空格分隔的两个数字 N和D(1≤N≤1000000; 1≤D≤1000000)
     * 第二行包含N个建筑物的的位置，每个位置用一个整数（取值区间为[0, 1000000]）表示，从小到大排列（将字节跳动大街看做一条数轴）
     * 输出描述：
     * 一个数字，表示不同埋伏方案的数量。结果可能溢出，请对 99997867 取模
     * 示例1
     * 输入例子：
     * 4 3
     * 1 2 3 4
     * 输出例子：
     * 4
     * 例子说明：
     * 可选方案 (1, 2, 3), (1, 2, 4), (1, 3, 4), (2, 3, 4)   
     * 示例2
     * 输入例子：
     * 5 19
     * 1 10 20 30 50
     * 输出例子：
     * 1
     * 例子说明：
     * 可选方案 (1, 10, 20)
     * 思路：
     * 1。有三名特工，任意两名之间的距离是有限制的
     * 2。建筑物位置的排序是从小到大的
     * 1 ，2， 3，4,5
     * p       q（p，q表示最远的特工的位置）
     * 如果q到p的距离满足限制，那么第三名特工只需要放置在p，q之间肯定满足要求， 在这一轮的answer = q - p + 1 - 2 = q - p - 1
     * 最后使用双指针，p，q确定最远两名特工的位置，第三名特工放置在pq之间，通过不断移动pq进行计算。
     */

    public static void main(String[] args) {
        LocationSum locationSum = new LocationSum();
        System.out.println(locationSum.locationSum(new int[]{1,10,20,30,50}, 19));
    }
    /**
     *
     * @param nums 建筑物位置
     * @param len 两名特工最远的距离
     * @return 三名特工放在不同位置上的解法
     */

    private int locationSum(int[] nums, int len) {
        if (nums.length < 3) {
            return 0;
        }
        int p = 0, q = 2;
        int answer = 0;
        while (p < nums.length) {
            /**
             * 1.如果pq之间的位置<3，那么需要往后移动q，确保能够放下第三名特工，如果当前q已经在最后一个位置，不需要再进行判断，直接break
             * 2.查看当前q位置-p位置的距离是否<=len,如果小于，向后移动q（如果q已经在最后一个位置时，不能再移动，直接固定）
             * 如果大于，需要移动往后移动p位置
             */
            if (q - p + 1 < 3) {
                if (q >= nums.length - 1) {
                    break;
                }
                ++ q;
                continue;
            }
            if (nums[q] - nums[p] <= len) {
                answer += (q - p - 1);
                if (q >= nums.length - 1) {
                    ++ p;
                } else {
                    ++ q;
                }
                continue;
            }
            ++ p;
        }
        return answer;
    }
}