package com.carol.interview.bytedance;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Carol
 * @date 2022/11/30
 * @since 1.0.0
 */
public class SmallestK {
    public static void main(String[] args) {
        SmallestK smallestK = new SmallestK();
        int[] result = smallestK.smallestK(new int[]{1,4,6,1,2,4}, 4);
        System.out.println("kkk");
    }
    /**
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     * 思路：
     * 1。先排序，返回前k个数
     * 2。利用大顶堆，堆中保存k个数，遍历arr，将当前元素与堆顶元素进行对比，如果当前元素小于堆顶元素，则堆顶元素出来，当前元素进去
     */
    public int[] smallestK(int[] arr, int k) {
        //1。初始化一个大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //2。将arr中的前k个元素入堆
        for (int i = 0 ; i < k ; i ++) {
            queue.add(arr[i]);
        }
        //3.开始数组后续元素的比较，如果小于堆顶元素，堆定元素出来，当前元素进去
        for (int i = k ; i < arr.length ; i++) {
            if (arr[i] < queue.peek()) {
                queue.poll();
                queue.add(arr[i]);
            }
        }
        //4.堆中存在的元素应该就是arr的前k小的数值
        int[] result = new int[k];
        int index = 0;
        while (!queue.isEmpty()) {
            result[index] = queue.poll();
            ++ index;
        }
        return result;
    }
}