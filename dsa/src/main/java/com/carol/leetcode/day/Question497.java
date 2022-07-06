package com.carol.leetcode.day;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Carol
 * @date 2022/6/9
 * @since 1.0.0
 */
public class Question497 {
    /**
     * https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/
     * 给定一个由非重叠的轴对齐矩形的数组 rects ，
     * 其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi)
     * 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。
     * 设计一个算法来随机挑选一个被某一矩形覆盖的整数点。
     * 矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
     *
     * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
     *
     * 请注意，整数点是具有整数坐标的点。
     *
     * 实现Solution类:
     *
     * Solution(int[][] rects)用给定的矩形数组rects 初始化对象。
     * int[] pick()返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
     * 1 <= rects.length <= 100
     * rects[i].length == 4
     * -109<= ai< xi<= 109
     * -109<= bi< yi<= 109
     * xi- ai<= 2000
     * yi- bi<= 2000
     * 所有的矩形不重叠。
     * pick 最多被调用104次。
     */

    public static void main(String[] args) {
        Question497 question497 = new Question497(new int[][]{{-2, -2, 1, 1}, {2, 2, 4, 6}});
        int[] result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);
        result = question497.pick();
        System.out.println(result[0] + "," + result[1]);





    }
    int[][] rects;
    List<Integer> arr;
    public Question497(int[][] rects) {
       this.rects = rects;
        arr = new ArrayList<>();
        arr.add(0) ;
        for (int[] rect : rects) {
            //当前矩阵中的点数量
            int a = rect[0], b = rect[1], x = rect[2], y = rect[3];
            int nums = (x - a + 1) * (y - b + 1);
            //前缀和，矩阵中点的个数的前缀和 前面的矩阵点数 + 当前矩阵点数；
            arr.add(arr.get(arr.size() - 1) + nums);
        }
    }

    /**
     * 思路：因为每个矩形面积大小不一样，如果先随机选择矩阵，则导致概率不相等，不合题意
     * 因为选取的是整数点所以每个矩形里面的整数点个数是可以计算出来的，我们可以对整数点进行编号
     * 例如第一个矩形包含16个点（边上的也算），则编号1-16，第二个矩形依次类推，假设总共有n个点，
     * 则我们在1-n范围内随机选择编号k，然后计算编号k属于哪个矩形，再计算k是矩形中的位置
     * 当计算k属于哪个矩形时，我们可以提前准备好一个前缀和数组，记录之前矩形的累加和，因为前缀和
     * 数组是单调递增的，所以可以采用二分法进行查找，找到之后再计算位置
     * @return
     */
    public int[] pick() {
        //随机产生一点
        Random rand = new Random();
        int k = rand.nextInt(arr.get(arr.size() - 1));
        //找到矩形编号
        int reactIndex = (this.binarySearch(k + 1) - 1);
        //根据k和矩形编号确定x，y
        //index为随机点在当前矩阵的偏移量
        k -= arr.get(reactIndex);
        int[] currentReact = rects[reactIndex];
        int a = currentReact[0], b = currentReact[1], y = currentReact[3];
        int col = y - b + 1;
        int da = k / col;
        int db = k - col * da;
        return new int[]{a + da, b + db};
    }

    private int binarySearch(int target) {
        int low = 0, high = arr.size() - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = arr.get(mid);
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }



}