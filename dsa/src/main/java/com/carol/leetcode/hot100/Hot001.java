package com.carol.leetcode.hot100;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 题目地址：https://leetcode.cn/problems/two-sum/
 * 题目描述：给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 视频讲解：
 * @author Carol
 * @date 2022/7/6
 * @since 1.0.0
 */
public class Hot001 {
    /**
     * 思路：
     * 1.暴力解法：直接两次遍历数组，找到符合的两个值，返回，时间复杂度O(n)，空间复杂度O(1)
     * 2.二分查找：先将数组进行排序，然后遍历数组，二分查找值，时间复杂度O(nlogn),空间复杂度O(n)
     * 3.hash表，利用一个hash表直接存储元素，一次遍历数组，时间复杂度O(n)，空间复杂度O(n)
     */


    public static void main(String[] args) {
        Hot001 hto001 = new Hot001();
        int[] nums = new int[]{0,4,3,0};
        int[] ans1 = hto001.violence(nums, 0);
        System.out.println("index1 = " + ans1[0] + ",index2 = " + ans1[1]);
        ans1 = hto001.binarySearch(nums, 0);
        System.out.println("index1 = " + ans1[0] + ",index2 = " + ans1[1]);
        ans1 = hto001.hash(nums, 0);
        System.out.println("index1 = " + ans1[0] + ",index2 = " + ans1[1]);
        ans1 = hto001.violence(nums, 7);
        System.out.println("index1 = " + ans1[0] + ",index2 = " + ans1[1]);
        ans1 = hto001.binarySearch(nums, 7);
        System.out.println("index1 = " + ans1[0] + ",index2 = " + ans1[1]);
        ans1 = hto001.hash(nums, 7);
        System.out.println("index1 = " + ans1[0] + ",index2 = " + ans1[1]);
    }

    /**
     * 执行用时：54 ms,在所有 Java 提交中击败了26.02%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了28.34%的用户
     * @param nums
     * @param target
     * @return
     */
    private int[] violence(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0 ; i < nums.length ; i ++) {
            for (int j = i + 1 ; j < nums.length ; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了56.55%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了26.49%的用户
     * @param nums
     * @param target
     * @return
     */
    private int[] binarySearch(int[] nums, int target) {
        int[] tempNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tempNums);
        for (int i = 0 ; i < tempNums.length ; i++) {
            int index = binarySearch(tempNums, i + 1, tempNums.length - 1, target -tempNums[i]);
            if ( index != -1) {
                //找到目标值
                return findIndex(nums, tempNums[i], tempNums[index]);
            }
        }
        return null;
    }

    private int[] findIndex(int[] nums, int firstNumber, int secondNumber) {
        int[] ans = new int[]{-1, -1};
        int flag = 0;
        for (int i = 0 ; i < nums.length ; i++) {
            if (nums[i] == firstNumber && ans[0] == -1) {
                ans[0] = i;
                ++ flag;
            }else if (nums[i] == secondNumber && ans[1] == -1) {
                ans[1] = i;
                ++ flag;
            }
            if (flag == 2) {
                return ans;
            }
        }
        return ans;
    }
    private int binarySearch(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = start + ((end - start) >> 1);
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, end, target);
        }
        return binarySearch(nums, start, mid - 1,  target);
    }


    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了69.95%的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了38.30%的用户
     * @param nums
     * @param target
     * @return
     */
    private int[] hash(int[] nums, int target) {
        HashMap<Integer, Integer> numIndexMap = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0 ; i < nums.length ; i++) {
            if (numIndexMap.containsKey(nums[i])) {
                if (nums[i] * 2 == target) {
                    ans[0] = numIndexMap.get(nums[i]);
                    ans[1] = i;
                    return ans;
                }
            }
            if (numIndexMap.containsKey(target - nums[i])) {
                ans[0] = numIndexMap.get(target - nums[i]);
                ans[1] = i;
                return ans;
            }
            numIndexMap.put(nums[i], i);
        }
        return ans;
    }

}