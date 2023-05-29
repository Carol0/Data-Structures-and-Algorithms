package com.carol.niuke.top101;

import java.util.PriorityQueue;

/**
 * 堆应用：topk
 * @author Carol
 * @date 2023/5/29
 * @since 1.0.0
 */
public class Heap {

    private int[] heap = new int[]{-1, 50,10,90,30,70,40,80,60,20};

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.heapSort();
        System.out.println("");
    }

    /**
     * 大顶堆
     * 将start到end范围内调整为堆
     * 以start为堆顶元素，找到它的左右子树，偏大的一个
     * 与堆顶元素比较，如果堆顶元素更大，不需要进行任何调整，已经符合堆的定义
     * 如果更小，将堆顶元素与更大值交换，继续调整堆
     * @param start
     * @param end
     */
    private void heapAdjust(int start, int end) {
        for(int i = 2 * start ; i <= end ;i = i * 2) {
            if (i < end && heap[i] < heap[i + 1]) {
                ++ i;
            }
            if (heap[start] > heap[i]) {
                break;
            }
            swap(start, i);
            start = i;
        }
    }

    /**
     * 堆排序
     * 1.将数组进行堆化
     * 2.不断进行取出堆顶元素，重新堆的过程，直至完成，排序完成
     */
    private void heapSort(){
        //将二叉树从下网上进行堆化
        for (int i = (heap.length - 1) / 2 ; i > 0 ; i --) {
            heapAdjust(i, heap.length - 1);
        }
        //开始进行堆排序，不断取下堆顶元素
        for (int i = heap.length - 1; i > 0 ; i --) {
            swap(1 , i);
            heapAdjust(1, i - 1);
        }
    }

    void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}