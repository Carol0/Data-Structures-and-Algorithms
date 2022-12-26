package com.carol.interview.ali;

import com.carol.interview.unkonw.Lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Carol
 * @date 2022/12/26
 * @since 1.0.0
 */
public class SubSet {
    /**
     * 小强现在有n个物品,每个物品有两种属性xi和yi，他想要从中挑出尽可能多的物品.
     * 满足以下条件:
     * 对于任意两个物品i和j,满足xi< xj且yi< yj或者xi> xj 且 yi > yj.问最多能挑出多少物品.
     *
     *  第一行输入一个正整数t.表示有t组数据.
     * 对于每组数据,第一行输入一个正整数n.表示物品个数.
     * 接下来两行,每行有个整数.
     * 第一行表示个n节点的x属性.
     * 第二行表示个n节点的y属性.
     * 1<=t<=10
     * 2<=n<=100000
     * 0<=x,y<=10^9
     * 输入例子：
     * 2
     * 3
     * 1 3 2
     * 0 2 3
     * 4
     * 1 5 4 2
     * 10 32 19 21
     * 输出例子：
     * 2
     * 3
     * 思路：题目需要我们找到一组物品，以单调递增为例，这组物品的x，y属性同时严格单调递增
     * 首先以物品的x属性作为作为标准进行由小到大排序，当x属性相同时，y属性按从大到小进行排序，
     * 现在需要找到y属性也从小到大，问题转化为
     * 求解最长上升子序列(dp,贪心+二分)
     */

    public static void main(String[] args) throws IOException {
        Goods[] goods = new Goods[3];
        goods[0] = new Goods(1,0);
        goods[1]=  new Goods(3,2);
        goods[2] = new Goods(2,3);
        SubSet subSet = new SubSet();
        System.out.println(subSet.subSet(goods));
        //内部正则匹配，相对较慢，会超时
        //Scanner scanner = new Scanner(System.in);

        //简单文本输入，会比较快
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String tStr = bufferedReader.readLine();
        int t = Integer.parseInt(tStr);
        while (t > 0) {
            String nStr = bufferedReader.readLine();
            int n = Integer.parseInt(nStr);
            String[] xStr = bufferedReader.readLine().split(" ");
            String[] yStr = bufferedReader.readLine().split(" ");
            Goods[] inputGoods = new Goods[n];
            for (int i = 0 ; i < n ; i++) {
                inputGoods[i] = new Goods(Integer.parseInt(xStr[i]), Integer.parseInt(yStr[i]));
            }
            System.out.println(subSet.subSet(inputGoods));
            -- t;
        }

    }

    public int subSet(Goods[] goods) {
        //排序
        Arrays.sort(goods, (o1, o2) -> {
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            }
            return o2.y - o1.y;
        });
        //开始求最长上升子序列
        return greedy(goods);
    }

    /**
     * minY[i] 长度为i + 1的最长子序列最小元素的值
     * 遍历元素
     * 1.当前元素如果大于minY中最后一个元素，直接将当前元素add到minY中
     * 2.如果当前元素小于minY中最后一个元素，我们查找整个minY，找到第一个大于等于
     * 当前元素的值，进行替换
     * @param goods
     * @return
     */
    private int greedy(Goods[] goods) {
        List<Integer> minY = new ArrayList<>();
        minY.add(goods[0].y);
        for (int i = 1 ; i < goods.length ; i ++) {
            if (goods[i].y > minY.get(minY.size() - 1)) {
                minY.add(goods[i].y);
                continue;
            }
            int index = binarySearch(minY, 0, minY.size() - 1, goods[i].y);
            minY.set(index, goods[i].y);
        }
        return minY.size();
    }

    private int binarySearch(List<Integer> minY, int start, int end, int num) {
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (minY.get(mid) == num) {
                return mid;
            }
            if (minY.get(mid) < num) {
                start = mid + 1;
                continue;
            }
            end = mid - 1;
        }
        return start;
    }


    public static class Goods{
        private int x;
        private int y;
        Goods(){};
        Goods(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}