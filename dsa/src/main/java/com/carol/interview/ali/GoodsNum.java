package com.carol.interview.ali;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Carol
 * @date 2022/12/22
 * @since 1.0.0
 */
public class GoodsNum {
    /**
     *
     * 来源：牛客网
     * 【2021】阿里巴巴编程题（2星）
     * 2.选择物品
     * 有n个物品可供选择，必须选择
     * 其中的m个物品，请按字典顺序
     * 输出所有选取方案的物品编号123，
     * 321与312被认为是同一种方案，
     * 输出字典序最小的123即可
     * 数据范围：1<=m<=n<=10
     * 思路：枚举每一个符合要求的方案
     * 因为最终我返回是按字典顺序返回的，所以在进行答案组装时候，
     * 就直接按照字典顺序进行组装，避免不必要的回退环节
     * 1 2 3 4
     * dfs
     */
    public static void main(String[] args) {
        GoodsNum goodsNum = new GoodsNum();
        goodsNum.goodsNum(4,2);
        System.out.println("");
    }

    private void goodsNum(int n, int m) {
        this.dfs(new ArrayList<>(), m, 0, n);
    }

    List<List<Integer>> answer = new ArrayList<>();
    private void dfs(List<Integer> nums, int m, int index, int n) {
        if (nums.size() == m) {
            answer.add(new ArrayList<>(nums));
            System.out.println(nums.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            return;
        }
        for (int i = index; i < n ; i ++) {
            nums.add(i + 1);
            dfs(nums, m, i + 1, n);
            nums.remove(nums.size() - 1);
        }
    }
}