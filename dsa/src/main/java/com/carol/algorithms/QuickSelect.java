package com.carol.algorithms;

/**
 * @author gaoxiaolin
 * @date 2022/4/19
 * @since 1.0.0
 */
public class QuickSelect {

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,4,3,6};
        QuickSelect quickSelect = new QuickSelect();
        System.out.println(quickSelect.quickSelect(nums, 4));
    }

    /**
     * 快速选择排序求解topK问题
     * 1，2，3，4，5
     * 求解倒数第二小的数字
     * 求解的位置应该位于i= 1处，此时位于它左侧的都小于它，位于它右侧的都大于它
     * 1.选择一个数字作为基准数，将小于它的放置在左边，大于的放置在右边，一次排序完成之后
     * 得到当前数值的排序后的位置，如果当前数值位置位于k-1，则为目标值，如果不是则可以根据当前
     * 位置与k的大小关系进行新一轮的快速选择排序
     * @param nums
     * @param topK 从0开始
     */
    private int quickSelect(int[] nums, int topK) {
        if (topK >= nums.length) {
            return -1;
        }
        int index = selectSort(nums, 0, nums.length - 1);
        while (index != topK) {
            if (index < topK) {
                //所求在右边
                index = selectSort(nums, index + 1, nums.length - 1);
                continue;
            }
            index = selectSort(nums, 0, index - 1);
        }
        return nums[index];
    }

    /**
     * 一趟快速选择排序，返回此次排序后key的位置
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int selectSort(int[] nums, int start, int end) {
        int key = nums[end];
        //小于key的放置在左边，大于key的放置在右边
        int left = start;
        //首先找第一个大于key的位置
        while (left <= end && nums[left] <= key) {
            ++ left;
        }
        int right = left + 1;
        while (right <= end) {
            //从左到右开始遍历，left指向当前第一个大于key的位置
            //right指向当前遍历的位置，当前位置若小于key，则与left所在位置进行交换
            //left + 1， right + 1
            if (nums[right] < key) {
                //当前位置的数值<key
                swap(nums, left, right);
                ++ left;
            }
            ++ right;
        }
        //遍历完成
        if (left < end) {
            nums[end] = nums[left];
            nums[left] = key;
        }
        return left > end ? end : left;
    }

    private void swap(int[] nums, int i , int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }











}