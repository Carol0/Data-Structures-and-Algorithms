package com.carol.interview.ali;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Carol
 * @date 2023/1/12
 * @since 1.0.0
 */
public class ContinuitySegment {
    /**
     * 思路：需要找到区间内出现最多的次数>=m
     * 1。找到区间：采用双指针（i，j），首先判断双指针指向区间内是否满足，如果刚好满足，
     * 则已经找以左指针为起点的所有子序列count = nums.length - j，计算完成
     * 之后，开始移动左指针，不断缩小我们的区间，在缩小过程中，仍然进行上述判断
     * 如果不满足，需要不断移动右指针，去扩大区间
     * 2。判断区间是否符合，如果是移动右指针，那么这个过程是一个不断扩大区间的过程
     * 因此我们只需要判断当前加入的数字出现的次数是是否满足条件
     * 如果移动左指针，当前元素如果等于右指针指向的元素，那么一定会影响这个元素出现的次数，缩小
     * 区间之后，将会不满足条件，这个时候继续下一轮移动右指针
     * 如果当前元素不等于右指针指向的元素，则不会影响出现次数，直接继续移动左指针，并且得到新的以左指针为
     * 起点的元素 nums.length - 1;
     */

    public static void main(String[] args) {
        ContinuitySegment segment = new ContinuitySegment();
        long ans = segment.continuitySegment(new int[]{1,2,1,2,2}, 3);
        System.out.println(ans);
    }

    public long continuitySegment(int[] nums, int m) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        int i = 0 , j = 0;
        long answer = 0;
        while (i <= j && i < nums.length && j < nums.length){
            int times = numCountMap.getOrDefault(nums[j], 0) + 1;
            numCountMap.put(nums[j], times);
            if (times == m) {
                answer += nums.length - j;
                while (i <= j) {
                    if (nums[i] != nums[j]) {
                        answer += nums.length - j;
                        numCountMap.put(nums[i], numCountMap.get(nums[i]) - 1);
                        ++ i;
                        continue;
                    }
                    numCountMap.put(nums[i], numCountMap.get(nums[i]) - 1);
                    ++ i;
                    break;
                }
            }
            if (j == nums.length - 1) {
                break;
            }
            ++ j;
        }
        return answer;
    }
}