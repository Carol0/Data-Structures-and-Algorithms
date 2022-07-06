package com.carol.leetcode.day;

import java.util.Arrays;

/**
 * @author Carol
 * @date 2022/6/1
 * @since 1.0.0
 */
public class Makesquare {
    /**
     * 473. 火柴拼正方形
     * https://leetcode.cn/problems/matchsticks-to-square/
     * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i个火柴棒的长度。
     * 你要用 所有的火柴棍拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
     * 如果你能使这个正方形，则返回 true ，否则返回 false 。
     * 1 <= matchsticks.length <= 15
     * 1 <= matchsticks[i] <= 108
     *
     * 思路：需要拼盛一个正方形，则火柴加起来的长度需要是4的倍数，每条边长度length/4，每条边由若干根独立的火柴组成
     * 用四个桶存储组成四条边的火柴棍，首先需要明确，如果能符合题意组成拼成一个正方形，那么每根火柴必然落在其中一个桶
     * 里面，如果发发现有一根火柴放不进，则表示拼不成，直接false，如果所有火柴均放入，则拼接成功，返回true
     *
     *
     *
     */
    public static void main(String[] args) {
        Makesquare m = new Makesquare();
        System.out.println(m.makesquare2(new int[]{5,5,5,5,4,4,4,4,3,3,3,3}));
    }
    //四个桶标志当前桶里火柴长度，每一根火柴最终会落到一个桶里
    public boolean makesquare2(int[] matchsticks) {
        int totalLength = Arrays.stream(matchsticks).sum();
        if (totalLength % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        nums = matchsticks;
        perLength = totalLength / 4;
        return this.dfs2(nums.length - 1, new int[4]);
    }
    int[] nums;
    int perLength;
    private boolean dfs2(int index, int[] bucket) {
        if (-1 == index) {
            //所有火柴全部放完，拼接成功
            return true;
        }
        for (int i= 0 ; i < 4 ; i ++) {
            //如果当前桶里面的火柴长度+当前火柴长度大于每一边的长度，则换放下一个桶试试
            if (bucket[i] + nums[index] > perLength) {
                continue;
            }
            //这是一个试放过程，先将火柴放进去，因为这根火柴可能只是可以放进去，但是不是最终结果
            bucket[i] += nums[index];
            //继续放下一根火柴
            if (this.dfs2(index - 1, bucket)) {
                return true;
            }
            //如果之前火柴不符合条件，把放进去的火柴拿出来，放入下一个bucket
            bucket[i] -= nums[index];
        }
        //如果当前这根火柴放不进任何一个桶，这次放置是失败的
        return false;
    }
}