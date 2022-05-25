package com.carol.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoxiaolin
 * @date 2022/4/18
 * @since 1.0.0
 */
public class MaxHeap {
    //大顶堆：根节点数值最大，每个双亲节点大于等于子节点，可用于解决topN问题


    public static void main(String[] args) {
        int[] nums = new int[]{50,10,90,30,70,40,80,60,20};
        MaxHeap maxHeap = new MaxHeap();
        //构建大顶堆
        maxHeap.build(nums);
        //堆排序，从大到小
        //输出堆顶元素，将堆顶元素放到最后一个叶子节点，从根节点开始调整大顶堆使成为新大顶堆
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0 ; i < nums.length ; i++) {
            //拿出堆顶元素
            result.add(nums[0]);
            int temp = nums[0];
            nums[0] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = temp;
            //调整大顶堆
            maxHeap.adjustHeap(nums, 1, nums.length - i - 1);
        }
        System.out.println("");
    }
    public void build(int[] nums) {
        //构建一个大顶堆
        //满足大顶堆条件，即每个双亲节点大于等于子节点
        //找到最后一个双亲节点（n/2 - 1)，从下往上开始调整
        for (int i = nums.length / 2 ; i > 0 ; i --) {
            adjustHeap(nums, i, nums.length);
        }
    }

    /**
     *
     * @param nums 需要调整的树结构
     * @param i 调整位置
     * @param end 树最后一个节点
     */
    public void adjustHeap(int[] nums, int i, int end) {
        //调整当前节点到指定位置，从当前节点出发，往下遍历,将这条链路的全部调整称大顶堆
        //当前节点的孩子节点下表 2i，2i+1
        //最开始从第一个双亲节点开始调整，所以后面的都应该是一个大顶堆
        int j;
        for (j = i << 1 ; j <= end ; j = j << 1) {
            if (j < end && nums[j - 1] < nums[j]) {
                j = j + 1;
            }
            if (nums[i - 1] > nums[j - 1]) {
                break;
            }
            //当前数值大于需要调整数值，
            int temp = nums[i - 1];
            nums[i - 1] = nums[j - 1];
            nums[j - 1] = temp;
            i = j;
        }
    }

}